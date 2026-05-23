package bunny.lib.mixin;

import bunny.lib.config.ModConfig;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void LOM$ignoreHorseLeafCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getBlock() instanceof LeavesBlock && context instanceof EntityShapeContext esc && ModConfig.INSTANCE.disableHorseLeafCollision) {
            Entity entity = esc.getEntity();
            if (entity instanceof HorseEntity && ((HorseEntity) entity).isTame()) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void LOM$ignoreWitherCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getBlock() == Blocks.BEDROCK && context instanceof EntityShapeContext esc && ModConfig.INSTANCE.disableWitherBedrockCollision) {
            Entity entity = esc.getEntity();
            if (entity instanceof WitherEntity) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }
}



