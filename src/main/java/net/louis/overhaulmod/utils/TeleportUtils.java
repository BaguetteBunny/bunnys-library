package net.louis.overhaulmod.utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class TeleportUtils {
    public static void chorusTeleport(ServerPlayerEntity player) {
        ServerWorld world = player.getServerWorld();
        Random random = world.random;

        double originalX = player.getX();
        double originalY = player.getY();
        double originalZ = player.getZ();

        for (int i = 0; i < 16; i++) {
            double offsetX = originalX + (random.nextDouble() - 0.5) * 16.0;
            double offsetY = MathHelper.clamp(originalY + (random.nextInt(8) - 4), world.getBottomY(), world.getTopYInclusive() - 1);
            double offsetZ = originalZ + (random.nextDouble() - 0.5) * 16.0;

            if (player.hasVehicle()) {
                player.stopRiding();
            }

            // Try teleport
            if (player.teleport(offsetX, offsetY, offsetZ, true)) {
                world.playSound(null, originalX, originalY, originalZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, player.getSoundCategory(), 1.0F, 1.0F);
                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                break;
            }
        }
    }
}
