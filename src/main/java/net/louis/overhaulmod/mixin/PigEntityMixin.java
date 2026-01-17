package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.utils.enums.PigPersonality;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin {
    PigEntity self = (PigEntity) (Object) this;

    private final PigPersonality personality = PigPersonality.random(self.getWorld());

    private final float volatility = personality.getVolatility();
    private final float minRange = personality.getMinRange();
    private final float maxRange = personality.getMaxRange();
    private final int volatilityConstant = personality.getVolatilityCst();

    private float speedMultiplier = 1.5f;

    @Inject(method = "getSaddledSpeed", at = @At("RETURN"), cancellable = true)
    private void modifySaddleSpeed(PlayerEntity controllingPlayer, CallbackInfoReturnable<Float> cir) {
        if (!ModConfig.INSTANCE.randomPigSpeed) return;

        if (speedMultiplier > maxRange) speedMultiplier -= volatility;
        else if (speedMultiplier < minRange) speedMultiplier += volatility;
        else speedMultiplier = controllingPlayer.getWorld().getRandom().nextBetween(0, volatilityConstant) < 50 ? speedMultiplier+volatility : speedMultiplier-volatility;

        cir.setReturnValue(cir.getReturnValueF() * speedMultiplier);
    }

}
