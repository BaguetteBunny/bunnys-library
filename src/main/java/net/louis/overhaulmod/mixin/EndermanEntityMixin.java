package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
    EndermanEntity self = (EndermanEntity) (Object) this;

    @Inject(method = "hurtByWater", at = @At("HEAD"), cancellable = true)
    private void LOM$disableIfWaterBreathing(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.INSTANCE.endermanImmuneToWaterWithEffect
                && self.hasStatusEffect(StatusEffects.WATER_BREATHING))
            cir.setReturnValue(false);
    }
}
