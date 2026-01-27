package net.louis.overhaulmod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.RareItemUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.louis.overhaulmod.utils.RNGUtil.oneIn;

public class ModServerEvents {
    public static void registerServerEvents() {
        ServerLivingEntityEvents.AFTER_DEATH.register(ModServerEvents::dropWispNameTag);
        ServerLivingEntityEvents.AFTER_DEATH.register(ModServerEvents::dropPeachNameTag);
        ServerLivingEntityEvents.AFTER_DEATH.register(ModServerEvents::dropImmolationNameTag);
        ServerLivingEntityEvents.AFTER_DAMAGE.register(ModServerEvents::dropCataclysmNameTag);
    }

    private static void dropCataclysmNameTag(LivingEntity living, DamageSource damageSource, float v, float v1, boolean b) {
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof PlayerEntity player && !b && v1 > 1 && oneIn(living.getWorld(), 1_000_000)) {
            RareItemUtil.spawnRareNametag(ModItems.CATACLYSM_NAME_TAG, player, living.getBlockPos());
        }
    }

    private static void dropWispNameTag(LivingEntity living, DamageSource damageSource) {
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof PlayerEntity player && living instanceof BreezeEntity && oneIn(living.getWorld(), 10_000)) {
            RareItemUtil.spawnRareNametag(ModItems.WISP_NAME_TAG, player, living.getBlockPos());
        }
    }

    private static void dropPeachNameTag(LivingEntity living, DamageSource damageSource) {
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof PlayerEntity player && living instanceof EnderDragonEntity && oneIn(living.getWorld(), 50)) {
            RareItemUtil.spawnRareNametag(ModItems.PEACH_NAME_TAG, player, living.getBlockPos());
        }
    }

    private static void dropImmolationNameTag(LivingEntity living, DamageSource damageSource) {
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof PlayerEntity player && playerAndOtherInNether(player, living) && isNetherMob(living) && oneIn(living.getWorld(), 150_000)) {
            RareItemUtil.spawnRareNametag(ModItems.IMMOLATION_NAME_TAG, player, living.getBlockPos());
        }
    }


    private static boolean isNetherMob(LivingEntity entity) {
        return (entity instanceof BlazeEntity
                || entity instanceof WitherSkeletonEntity
                || entity instanceof MagmaCubeEntity
                || entity instanceof GhastEntity
                || entity instanceof SkeletonEntity
                || entity instanceof AbstractPiglinEntity
                || entity instanceof ZombifiedPiglinEntity
                || entity instanceof HoglinEntity
                || entity instanceof ZoglinEntity
                || entity instanceof StriderEntity
                || entity instanceof EndermanEntity);
    }

    private static boolean playerAndOtherInNether(PlayerEntity player, LivingEntity entity) {
        return (player.getWorld().getDimension().ultrawarm() && entity.getWorld().getDimension().ultrawarm());
    }

    public static boolean playerInNether(PlayerEntity player) {
        return (player.getWorld().getDimension().ultrawarm());
    }
}
