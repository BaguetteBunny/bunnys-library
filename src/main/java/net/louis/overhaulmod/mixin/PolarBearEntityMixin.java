package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PolarBearEntity.class)
public abstract class PolarBearEntityMixin {
    PolarBearEntity self = (PolarBearEntity) (Object) this;

    private int aggressionTimer = 450;

    @Inject(method = "tick", at = @At("HEAD"))
    private void LOM$nearbyAggressionLogic(CallbackInfo ci) {
        if (self.getEntityWorld().isClient || !ModConfig.INSTANCE.moreAggressivePolarBears) return;

        ServerWorld world = (ServerWorld) self.getEntityWorld();
        PlayerEntity nearest = world.getClosestPlayer(self, 16.0);

        if (nearest != null) {
            double dist = self.squaredDistanceTo(nearest);

            if (dist <= (10 * 10)) aggressionTimer -= 1;
            if (dist <= (5 * 5)) aggressionTimer -= 2;
            if (dist <= (2.5 * 2.5)) aggressionTimer -= 5;
            if (aggressionTimer <= 0) self.setAngryAt(nearest.getUuid());

        } else if (aggressionTimer < 450) aggressionTimer = 400;
    }
}
