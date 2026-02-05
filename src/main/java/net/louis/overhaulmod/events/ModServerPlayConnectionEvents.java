package net.louis.overhaulmod.events;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.louis.overhaulmod.utils.accessors.LastTickHolder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModServerPlayConnectionEvents {
    public static void register() {
        ServerPlayConnectionEvents.DISCONNECT.register(ModServerPlayConnectionEvents::deleteLeftoverLights);
    }

    private static void deleteLeftoverLights(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
        ServerPlayerEntity player = serverPlayNetworkHandler.player;
        ((LastTickHolder) player).setLastTick(true);
    }
}
