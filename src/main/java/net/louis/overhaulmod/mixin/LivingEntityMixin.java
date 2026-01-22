package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.TeleportUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    LivingEntity self = (LivingEntity) (Object) this;

    @Inject(method = "damage", at = @At("HEAD"))
    private void LOM$teleportOnEndermiteHit(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();

        if (attacker instanceof EndermiteEntity && self instanceof ServerPlayerEntity player && ModConfig.INSTANCE.endermiteTeleportPlayerOnHit) {
            Random random = new Random();
            if (random.nextBoolean()) {TeleportUtils.chorusTeleport(player);}
        }
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void LOM$cancelJumpIfGrounded(CallbackInfo ci) {
        if (self.hasStatusEffect(ModEffects.GROUNDED)) {
            ci.cancel();
        }
    }

    @Inject(method = "getAttackDistanceScalingFactor", at = @At("RETURN"), cancellable = true)
    private void LOM$injectedHeadDetectionReduction(Entity entity, CallbackInfoReturnable<Double> cir) {
        double original = cir.getReturnValue();
        ItemStack head = self.getEquippedStack(EquipmentSlot.HEAD);

        if (entity != null) {
            if (entity.getType().equals(EntityType.SKELETON) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.STRAY) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.BOGGED) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.ZOMBIE) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.HUSK) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.DROWNED) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIE_VILLAGER) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN_BRUTE) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIFIED_PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.CREEPER) && head.isOf(Items.CREEPER_HEAD)) {
                cir.setReturnValue((double) ModConfig.INSTANCE.decreaseMobHeadDetectionRange);
            } else {
                cir.setReturnValue(original);
            }
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
    private void LOM$removeNegEffects(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getComponents().get(ModComponents.SEASONING) == ModItems.EMPYREAN_POWDER && self instanceof ServerPlayerEntity player && !world.isClient()) {
            Collection<StatusEffectInstance> effects = List.copyOf(player.getStatusEffects());
            for (StatusEffectInstance effectInstance : effects) {
                StatusEffect se = effectInstance.getEffectType().value();
                if (se.getCategory() == StatusEffectCategory.HARMFUL) {
                    player.removeStatusEffect(effectInstance.getEffectType());
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$applyFrostWalkerWhileMounted(CallbackInfo ci) {

        if (self.getWorld().isClient() || !self.hasVehicle()) return;
        if (!(self.getWorld() instanceof ServerWorld world)) return;

        // Check if player has Frost Walker on boots
        ItemEnchantmentsComponent enchantments = self.getEquippedStack(
                net.minecraft.entity.EquipmentSlot.FEET
        ).get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments == null) return;

        int frostWalkerLevel = 0;
        for (var entry : enchantments.getEnchantmentEntries()) {
            String enchantId = world.getRegistryManager()
                    .get(RegistryKeys.ENCHANTMENT)
                    .getId(entry.getKey().value())
                    .toString();

            if (enchantId.contains("frost_walker")) {
                frostWalkerLevel = entry.getIntValue();
                break;
            }
        }

        if (frostWalkerLevel == 0) return;

        // Apply Frost Walker effect
        BlockPos blockPos = self.getBlockPos().down();
        BlockState frostedIce = Blocks.FROSTED_ICE.getDefaultState();
        int radius = 2*frostWalkerLevel;

        for (BlockPos targetPos : BlockPos.iterate(
                blockPos.add(-radius, -radius, -radius),
                blockPos.add(radius, radius, radius)
        )) {
            if (targetPos.getSquaredDistance(self.getBlockPos()) < MathHelper.square(radius)) {
                BlockState stateAtPos = world.getBlockState(targetPos);
                BlockState aboveState = world.getBlockState(targetPos.up());

                if (stateAtPos.getFluidState().isOf(net.minecraft.fluid.Fluids.WATER) &&
                        stateAtPos.getFluidState().isStill() &&
                        aboveState.isAir() &&
                        world.canSetBlock(targetPos) &&
                        frostedIce.canPlaceAt(world, targetPos)) {

                    world.setBlockState(targetPos, frostedIce);
                    world.scheduleBlockTick(targetPos, Blocks.FROSTED_ICE, MathHelper.nextInt(world.random, 60, 120));
                }
            }
        }
    }
}

