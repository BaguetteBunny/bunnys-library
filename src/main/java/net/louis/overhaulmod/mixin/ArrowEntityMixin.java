package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.mixin.accessor.ArrowEntityAccessor;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static net.louis.overhaulmod.utils.ApplyArrowComponents.applyArrowComponentAbilities;
import static net.louis.overhaulmod.utils.RadiusGetterUtil.getNearestEntity;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin {

    @Inject(
            method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V",
            at = @At("RETURN")
    )    private void LOM$onPlayerArrowSpawn(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom, CallbackInfo ci) {
        applyArrowComponentAbilities((ArrowEntity) (Object) this, stack);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V",
            at = @At("RETURN")
    )
    private void LOM$onDispenserArrowSpawn(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom, CallbackInfo ci) {
        applyArrowComponentAbilities((ArrowEntity) (Object) this, stack);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V",
            at = @At("RETURN")
    )
    private void LOM$onArrowSpawn(World world, @Nullable LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom, CallbackInfo ci) {
        applyArrowComponentAbilities((ArrowEntity) (Object) this, stack);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$injectHomingLogic(CallbackInfo ci) {
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        ComponentMap components = arrow.getItemStack().getComponents();
        if (arrow.getEntityWorld().isClient() || arrow.isOnGround()) return;

        ServerWorld serverWorld = (ServerWorld) arrow.getEntityWorld();

        if (components.contains(ModComponents.ARROW_HEAD)) {
            if (Items.ECHO_SHARD.equals(components.get(ModComponents.ARROW_HEAD))) {
                Optional<LivingEntity> target = getNearestEntity(serverWorld, arrow.getBlockPos(), 5);

                if (target.isPresent() && target.get() != arrow.getOwner()) {
                    Vec3d toTarget = target.get().getPos().add(0, target.get().getHeight() * 0.5, 0).subtract(arrow.getPos()).normalize();
                    Vec3d newVel = arrow.getVelocity().normalize().lerp(toTarget, 0.3).normalize().multiply(arrow.getVelocity().length());
                    arrow.setVelocity(newVel);
                }
            }
        }
    }

    /**
     * @author BaguetteBunny @ LouisOverhaulMod
     * @reason Tipped arrows drop AoE Cloud
     */
    @Overwrite
    public void onHit(LivingEntity target) {
        ArrowEntity self = (ArrowEntity)(Object)this;
        World world = self.getEntityWorld();

        Entity entity = self.getEffectCause();
        PotionContentsComponent potionContentsComponent = ((ArrowEntityAccessor) self).callgetPotionContents();
        if (potionContentsComponent.potion().isPresent() && ModConfig.INSTANCE.doLingeringDropOnHit) {
            for (StatusEffectInstance statusEffectInstance : ((Potion)((RegistryEntry)potionContentsComponent.potion().get()).value()).getEffects()) {
                target.addStatusEffect(
                        new StatusEffectInstance(
                                statusEffectInstance.getEffectType(),
                                Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1),
                                statusEffectInstance.getAmplifier(),
                                statusEffectInstance.isAmbient(),
                                statusEffectInstance.shouldShowParticles()
                        ),
                        entity
                );

                AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, target.getX(), target.getY(), target.getZ());
                cloud.setOwner(self.getOwner() instanceof LivingEntity living ? living : null);
                cloud.setRadius(3.0F);
                cloud.setRadiusOnUse(-0.5F);
                cloud.setWaitTime(0);
                cloud.setDuration(20+Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1)/2);
                cloud.setRadiusGrowth(-cloud.getRadius() / (float)cloud.getDuration());
                cloud.setPotionContents(potionContentsComponent);
                world.spawnEntity(cloud);
            }
        }

        for (StatusEffectInstance statusEffectInstance : potionContentsComponent.customEffects()) {
            target.addStatusEffect(statusEffectInstance, entity);
        }
    }
}
