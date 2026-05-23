package bunny.lib.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import bunny.lib.item.ModItems;
import bunny.lib.utils.RareItemUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static bunny.lib.events.ModServerLivingEntityEvents.playerInNether;
import static bunny.lib.utils.RareItemUtil.oneIn;

public class ModBreakEvents {
    public static void register() {
        PlayerBlockBreakEvents.AFTER.register(ModBreakEvents::dropVolcanicNameTag);
    }

    public static void dropVolcanicNameTag(World world, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        if (playerInNether(player) && !blockState.isAir() && oneIn(player.getEntityWorld(), 1_000_000)) {
            RareItemUtil.spawnRareNametag(ModItems.VOLCANIC_NAME_TAG, player, player.getBlockPos());
        }
    }
}
