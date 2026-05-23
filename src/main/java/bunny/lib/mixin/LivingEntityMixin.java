package bunny.lib.mixin;

import bunny.lib.config.ModConfig;
import bunny.lib.effect.ModEffects;
import bunny.lib.utils.TeleportUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    LivingEntity self = (LivingEntity) (Object) this;

    @Inject(method = "damage", at = @At("HEAD"))
    private void LOM$teleportOnEndermiteHit(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
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

    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$applyFrostWalkerWhileMounted(CallbackInfo ci) {

        if (self.getEntityWorld().isClient() || !self.hasVehicle()) return;
        if (!(self.getEntityWorld() instanceof ServerWorld world)) return;

        // Check if player has Frost Walker on boots
        ItemEnchantmentsComponent enchantments = self.getEquippedStack(
                net.minecraft.entity.EquipmentSlot.FEET
        ).get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments == null) return;

        int frostWalkerLevel = 0;
        for (var entry : enchantments.getEnchantmentEntries()) {
            String enchantId = world.getRegistryManager()
                    .getOrThrow(RegistryKeys.ENCHANTMENT)
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
                        world.canPlace(stateAtPos, targetPos, ShapeContext.absent()) &&
                        frostedIce.canPlaceAt(world, targetPos)) {

                    world.setBlockState(targetPos, frostedIce);
                    world.scheduleBlockTick(targetPos, Blocks.FROSTED_ICE, MathHelper.nextInt(world.random, 60, 120));
                }
            }
        }
    }
}

