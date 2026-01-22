package net.louis.overhaulmod.mixin;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(StatusEffectInstance.class)
public abstract class StatusEffectInstanceMixin {
    StatusEffectInstance self = (StatusEffectInstance) (Object) this;

    @Inject(method = "mapDuration", at = @At("HEAD"), cancellable = true)
    private void LOM$fixMapDuration(Int2IntFunction mapper, CallbackInfoReturnable<Integer> cir) {
        int dur = self.getDuration();
        if (dur == 1) return;
        boolean isDivideBy4 = (dur != 0 && mapper.applyAsInt(dur) == dur / 4);
        cir.setReturnValue(!self.isInfinite() && !isDivideBy4 ? mapper.applyAsInt(dur) : dur);
    }
}

