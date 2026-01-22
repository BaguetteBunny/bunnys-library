package net.louis.overhaulmod.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmallFireballEntity.class)
public abstract class SmallFireballEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    private void LOM$explodeCreeperWhenHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        SmallFireballEntity fireball = (SmallFireballEntity)(Object) this;

        if (entityHitResult.getEntity() instanceof CreeperEntity creeper
                && fireball.getOwner() instanceof PlayerEntity) {
            World world = fireball.getWorld();
            if (!world.isClient()) creeper.ignite();
            ci.cancel();
        }
    }
}
