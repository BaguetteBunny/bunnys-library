package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
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
        Fraction nouveau = calculateProperOccupancy(stack);
        cir.setReturnValue(nouveau == null ? Fraction.ONE : nouveau);
    }

    private static Fraction calculateProperOccupancy(ItemStack stack) {
        if (stack.get(DataComponentTypes.BUNDLE_CONTENTS) != null ||
                !stack.getOrDefault(DataComponentTypes.BEES, List.of()).isEmpty()) {
            return null;
        }

        Integer ctx = stack.get(ModComponents.BUNDLE_CONTEXT);
        int factor = ctx != null ? ctx : 1;

        if (stack.getMaxCount() == 1) return Fraction.getFraction(1, 8*factor);
        else if (stack.getMaxCount() == 16) return Fraction.getFraction(1, 32*factor);
        else return Fraction.getFraction(1, 64*factor);
    }
}

