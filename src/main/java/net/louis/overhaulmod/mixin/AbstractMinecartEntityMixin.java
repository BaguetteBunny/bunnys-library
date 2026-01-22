package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    AbstractMinecartEntity self = (AbstractMinecartEntity) (Object) this;
    private double speedBoost = 1.0;

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    private void LOM$overrideMaxSpeed(CallbackInfoReturnable<Double> cir) {
        BlockPos pos = self.getBlockPos();
        BlockState state = self.getWorld().getBlockState(pos);
        boolean inWater = self.isTouchingWater();
        double base = (inWater ? 4.0 : 8.0) / 20.0;

        if (state.isOf(Blocks.POWERED_RAIL) && state.get(PoweredRailBlock.POWERED)) {
            cir.setReturnValue(base * 2.0);
            return;
        }
        cir.setReturnValue(base);
    }

    @ModifyVariable(method = "moveOnRail", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private boolean LOM$overrideBl(boolean bl, BlockPos pos, BlockState state) {
        if (state.isOf(ModBlocks.COPPER_RAIL)) {
            boolean powered = state.get(PoweredRailBlock.POWERED);
            if (powered) return true;
        }
        return bl;
    }

    @Inject(method = "moveOnRail", at = @At("HEAD"))
    private void LOM$calculateBoost(BlockPos pos, BlockState state, CallbackInfo ci) {
        speedBoost = 1.0;

        if (state.isOf(Blocks.POWERED_RAIL) && state.get(PoweredRailBlock.POWERED)) {
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
