package bunny.lib.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    AbstractMinecartEntity self = (AbstractMinecartEntity) (Object) this;

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    private void LOM$overrideMaxSpeed(ServerWorld world, CallbackInfoReturnable<Double> cir) {
        BlockPos pos = self.getBlockPos();
        BlockState state = self.getEntityWorld().getBlockState(pos);
        boolean inWater = self.isTouchingWater();
        double base = (inWater ? 4.0 : 8.0) / 20.0;

        if (state.isOf(Blocks.POWERED_RAIL) && state.get(PoweredRailBlock.POWERED)) {
            cir.setReturnValue(base * 2.0);
            return;
        }
        cir.setReturnValue(base);
    }
}
