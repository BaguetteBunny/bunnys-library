package bunnys.qol.mixin;

import bunny.lib.config.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void bunny$dropJungleSapplingMoreOften(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (ModConfig.INSTANCE.increaseJungleSaplingDropRate && !(Boolean)state.get(LeavesBlock.PERSISTENT) && (Integer)state.get(LeavesBlock.DISTANCE) == 7) {
            LeavesBlock.dropStacks(state, world, pos);
            if (state.isOf(Blocks.JUNGLE_LEAVES) && random.nextInt(35) == 0) {
                LeavesBlock.dropStack(world, pos, new ItemStack(Blocks.JUNGLE_SAPLING));
            }
            world.removeBlock(pos, false);
        }
    }
}
