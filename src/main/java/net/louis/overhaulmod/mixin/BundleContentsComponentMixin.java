package net.louis.overhaulmod.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BundleContentsComponent.class)
public class BundleContentsComponentMixin {
    @Inject(method = "getOccupancy", at = @At("HEAD"), cancellable = true)
    private static void LOM$makeUnstackablesInBundle(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {
        if (stack.get(DataComponentTypes.BUNDLE_CONTENTS) != null ||
                !stack.getOrDefault(DataComponentTypes.BEES, List.of()).isEmpty()) {
            return;
        }

        if (stack.getMaxCount() == 1) {
            cir.setReturnValue(Fraction.getFraction(1, 8));
        }
    }
}

