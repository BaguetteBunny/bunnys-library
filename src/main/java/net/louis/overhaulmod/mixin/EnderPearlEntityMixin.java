package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ProjectileEntity {

    public EnderPearlEntityMixin(EntityType<? extends ProjectileEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void LOM$teleportHorseOnPearlLand(HitResult hitResult, CallbackInfo ci) {
        Entity owner = this.getOwner();

        if (!(owner instanceof ServerPlayerEntity player) || !ModConfig.INSTANCE.enderpearlTeleportsHorses) return;

        if (player.hasVehicle() && player.getVehicle() instanceof HorseEntity horse) {
            player.stopRiding();
            ServerWorld serverWorld = (ServerWorld) player.getWorld();
            Vec3d targetPos = this.getPos();
            horse.teleport(serverWorld, targetPos.x, targetPos.y, targetPos.z, Set.of(), player.getYaw(), player.getPitch());
        }
    }
}

