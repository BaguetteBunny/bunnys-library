package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.utils.ParticleShapeUtil;
import net.louis.overhaulmod.utils.accessors.WitherHealthAccessor;
import net.minecraft.block.WitherSkullBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector4d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.louis.overhaulmod.utils.RadiusGetterUtil.*;

@Mixin(WitherEntity.class)
public class WitherEntityMixin implements WitherHealthAccessor {
    WitherEntity self = (WitherEntity) (Object) this;

    @Unique private float threeQuarterHealthValue = self.getMaxHealth() * 0.75f;
    @Unique private float halfHealthValue = self.getMaxHealth() * 0.5f;
    @Unique private float quarterHealthValue = self.getMaxHealth() * 0.25f;
    @Unique private boolean threeQuarterHealthFlag = false;
    @Unique private boolean halfHealthFlag = false;
    @Unique private boolean quarterHealthFlag = false;

    @Shadow @Final private ServerBossBar bossBar;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyBossBar(EntityType<? extends WitherEntity> type, World world, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.enableWitherBossPhases) return;
        bossBar.setStyle(ServerBossBar.Style.NOTCHED_12);
        bossBar.setThickenFog(true);
    }

    @ModifyConstant(method = "mobTick", constant = @Constant(floatValue = 7.0F))
    private float modifyExplosionRadius(float original) {
        if (!ModConfig.INSTANCE.strongerWither) return original;
        return 12.0F;
    }

    @Inject(method = "onSummoned", at = @At("TAIL"))
    private void onSummoned(CallbackInfo ci) {
        self.setHealth(self.getMaxHealth());
        ParticleShapeUtil.drawCircle(ParticleTypes.ASH, self.getPos(), 5, self.getServer().getWorld(self.getWorld().getRegistryKey()), 25, new Vector4d(0, 0, 0, 0));
    }

    @Inject(method = "createWitherAttributes", at = @At("RETURN"), cancellable = true)
    private static void modifyWitherAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        if (!ModConfig.INSTANCE.strongerWither) return;

        DefaultAttributeContainer.Builder builder = cir.getReturnValue();

        builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 750.0);
        builder.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.8);
        builder.add(EntityAttributes.GENERIC_ARMOR, 8.0);
        cir.setReturnValue(builder);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void preventSuffocationAndExplosionDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isOf(DamageTypes.IN_WALL) || source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mobTick", at = @At("HEAD"))
    private void onMobTick(CallbackInfo ci) {
        if (!ModConfig.INSTANCE.enableWitherBossPhases) return;

        if (!threeQuarterHealthFlag && self.getHealth() <= threeQuarterHealthValue) {
            threeQuarterHealthFlag = true;
            ItemStack helmet = new ItemStack(Items.IRON_HELMET, 1);
            ItemStack weapon = new ItemStack(Items.IRON_SWORD, 1);

            self.getWorld().playSound(self, self.getBlockPos(), SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.HOSTILE, 3.f, 0.5f);

            List<BlockPos> locations = getRandomSpawnableBlockPosInRadius(self.getWorld(), self.getBlockPos(), 10, 5);

            ServerWorld serverWorld = self.getServer().getWorld(self.getWorld().getRegistryKey());
            for (BlockPos loc : locations) {
                WitherSkeletonEntity wse = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, self.getWorld());

                wse.setPos(loc.getX(), loc.getY() + 1.5f, loc.getZ());
                wse.equipStack(EquipmentSlot.HEAD, helmet);
                wse.equipStack(EquipmentSlot.MAINHAND, weapon);
                wse.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, StatusEffectInstance.INFINITE, 0, false, false));

                double mag = Math.sqrt(loc.getX()*loc.getX() + loc.getY()*loc.getY() + loc.getZ()*loc.getZ());
                double newX = loc.getX()/mag;
                double newY = loc.getY()/mag;
                double newZ = loc.getZ()/mag;

                serverWorld.spawnParticles(ParticleTypes.CRIT, loc.getX(), loc.getY() + 2f, loc.getZ(), 10, 0, 2, 0, 1);
                serverWorld.spawnParticles(ParticleTypes.ASH, self.getX(), self.getY() + 0.5f, self.getZ(), 10, newX, newY, newZ, 2);

                self.getWorld().spawnEntity(wse);
            }
        }

        if (!halfHealthFlag && self.getHealth() <= halfHealthValue) {
            halfHealthFlag = true;
            self.getWorld().playSound(self, self.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.HOSTILE, 3.f, 0.5f);
            threeQuarterHealthFlag = false;
        }

        if (!quarterHealthFlag && self.getHealth() <= quarterHealthValue) {
            quarterHealthFlag = true;
            self.getWorld().playSound(self, self.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.HOSTILE, 3.f, 0.5f);
            self.getWorld().createExplosion(
                    null,
                    self.getX(),
                    self.getY(),
                    self.getZ(),
                    7.0F,
                    false,
                    World.ExplosionSourceType.MOB
            );
            List<LivingEntity> entities = getEntitiesInRadius(self.getWorld(), self.getPos(), 10);
            for (LivingEntity le : entities) {
                if (!(le instanceof PlayerEntity)) continue;
                le.setInvulnerable(false);
                le.takeKnockback(30, -self.getX(), -self.getZ());
                le.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 320));
                le.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 160));
            }
            threeQuarterHealthFlag = false;
        }
    }

    @Override
    public boolean isHalfHp() {
        return halfHealthFlag;
    }
}

