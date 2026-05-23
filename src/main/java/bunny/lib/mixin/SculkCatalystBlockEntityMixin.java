package bunny.lib.mixin;

import bunny.lib.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SculkCatalystBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkCatalystBlockEntity.Listener.class)
public class SculkCatalystBlockEntityMixin {

    @Inject(method = "bloom", at = @At("TAIL"))
    private void LOM$echoShardOnBloom(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        if (random.nextInt(10) == 0 && ModConfig.INSTANCE.sculkCatalystBloomsEchoShards) {
            BlockPos above = pos.up();
            if (world.getBlockState(above).isAir()) {
                Block.dropStack(world, above, new ItemStack(Items.ECHO_SHARD));
            }
        }
    }
}
