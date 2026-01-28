package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.louis.overhaulmod.utils.RareItemUtil.*;

@Mixin(StatHandler.class)
public abstract class StatHandlerMixin {

    @Inject(method = "increaseStat", at = @At("HEAD"))
    private void LOM$rareNameTagSpawn(PlayerEntity player, Stat<?> stat, int amount, CallbackInfo ci) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

        if (Stats.CUSTOM.getOrCreateStat(Stats.SWIM_ONE_CM)  == stat && oneIn(serverPlayer.getWorld(), 500_000)) {
            spawnRareNametag(ModItems.AQUAMARINE_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
        if (Stats.CUSTOM.getOrCreateStat(Stats.EAT_CAKE_SLICE)  == stat && oneIn(serverPlayer.getWorld(), 20_000)) {
            spawnRareNametag(ModItems.FRAGRANT_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
        if (Stats.CUSTOM.getOrCreateStat(Stats.FISH_CAUGHT)  == stat && oneIn(serverPlayer.getWorld(), 10_000)) {
            spawnRareNametag(ModItems.IRIDESCENT_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
        if (Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)  == stat && oneIn(serverPlayer.getWorld(), 18_000_000)) {
            spawnRareNametag(ModItems.PRIMORDIAL_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
        if (Stats.CUSTOM.getOrCreateStat(Stats.ANIMALS_BRED) == stat && player.getWorld().getBiome(serverPlayer.getBlockPos()) == BiomeKeys.CHERRY_GROVE && oneIn(serverPlayer.getWorld(), 5_000)) {
            spawnRareNametag(ModItems.BLOSSOM_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
        if (Stats.USED.getOrCreateStat(Items.POTION) == stat && oneIn(serverPlayer.getWorld(), 10_000)) {
            spawnRareNametag(ModItems.RADIOACTIVE_NAME_TAG, serverPlayer, serverPlayer.getBlockPos());
        }
    }
}

