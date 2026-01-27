package net.louis.overhaulmod.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.RareItemUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.louis.overhaulmod.events.ModServerEvents.playerInNether;
import static net.louis.overhaulmod.utils.RNGUtil.oneIn;

public class ModBreakEvents {
    public static void registerBreakEvents() {
        PlayerBlockBreakEvents.AFTER.register(ModBreakEvents::dropVolcanicNameTag);
    }

    public static void dropVolcanicNameTag(World world, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        if (playerInNether(player) && !blockState.isAir() && oneIn(player.getWorld(), 1_000_000)) {
            RareItemUtil.spawnRareNametag(ModItems.VOLCANIC_NAME_TAG, player, player.getBlockPos());
        }
    }
}
