package bunny.lib.mixin;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static net.minecraft.component.type.PotionContentsComponent.buildTooltip;

@Mixin(PotionContentsComponent.class)
public abstract class PotionContentsComponentMixin {

    @Inject(method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V", at = @At("HEAD"), cancellable = true)
    private static void LOM$uniformPotionDuration(
            Iterable<StatusEffectInstance> effects,
            Consumer<Text> textConsumer,
            float durationMultiplier,
            float tickRate,
            CallbackInfo ci
    ) {
        if (durationMultiplier != 1.0F) {
            buildTooltip(effects, textConsumer, 1.0F, tickRate);
            ci.cancel();
        }
    }
}

