package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.mixin.accessor.MinecartControllerAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.DefaultMinecartController;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultMinecartController.class)
public class DefaultMinecartControllerMixin {
    private double speedBoost = 1.0;

    @ModifyVariable(method = "moveOnRail", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private boolean LOM$overrideBl(boolean bl, ServerWorld world) {
        // Access the minecart to get position and state
        DefaultMinecartController controller = (DefaultMinecartController) (Object) this;
        AbstractMinecartEntity minecart = ((MinecartControllerAccessor) controller).getMinecart();

        BlockPos blockPos = minecart.getRailOrMinecartPos();
        BlockState blockState = world.getBlockState(blockPos);

        if (blockState.isOf(ModBlocks.COPPER_RAIL)) {
            boolean powered = blockState.get(PoweredRailBlock.POWERED);
            if (powered) return true;
        }
        return bl;
    }

    @Inject(method = "moveOnRail", at = @At("HEAD"))
    private void LOM$calculateBoost(ServerWorld world, CallbackInfo ci) {
        DefaultMinecartController controller = (DefaultMinecartController) (Object) this;
        AbstractMinecartEntity minecart = ((MinecartControllerAccessor) controller).getMinecart();

        BlockPos blockPos = minecart.getRailOrMinecartPos();
        BlockState blockState = world.getBlockState(blockPos);

        speedBoost = 1.0;

        if (blockState.isOf(Blocks.POWERED_RAIL) && blockState.get(PoweredRailBlock.POWERED)) {
            speedBoost = 2.0;
        }
    }

    @ModifyConstant(method = "moveOnRail", constant = @Constant(doubleValue = 0.06))
    private double LOM$modifySpeedBoost(double original) {
        return original * speedBoost;
    }

    @ModifyConstant(method = "moveOnRail", constant = @Constant(doubleValue = 0.02))
    private double LOM$modifySmallPush(double original) {
        return original * speedBoost;
    }
}
