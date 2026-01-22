package net.louis.overhaulmod.mixin;

import net.minecraft.item.ArmorMaterial;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterial.class)
public class ArmorMaterialMixin {

    @Inject(method = "knockbackResistance", at = @At("RETURN"), cancellable = true)
    private void LOM$addChainmailKnockbackResistance(CallbackInfoReturnable<Float> cir) {
        if ((Object) this == ArmorMaterials.CHAIN.value()) {
            float currentKB = cir.getReturnValue();
            cir.setReturnValue(currentKB + 0.25f);
        }
    }
}
