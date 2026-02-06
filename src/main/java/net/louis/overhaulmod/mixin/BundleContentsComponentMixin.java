package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.fabricmc.fabric.mixin.transfer.BundleContentsComponentAccessor.getOccupancy;
import static net.minecraft.component.type.BundleContentsComponent.canBeBundled;

@Mixin(BundleContentsComponent.class)
public class BundleContentsComponentMixin {
    @Inject(method = "getOccupancy", at = @At("RETURN"), cancellable = true)
    private static void LOM$chageBundleCapacity(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {
        int factor = 1;

        if (stack.getItem() == ModItems.PIONEER_POUCH) {
            factor *= 4;
        }

        Fraction original = cir.getReturnValue();
        Fraction doubledCapacity = original.divideBy(Fraction.getFraction(factor, 1));
        cir.setReturnValue(doubledCapacity);

        if (stack.get(DataComponentTypes.BUNDLE_CONTENTS) != null ||
                !stack.getOrDefault(DataComponentTypes.BEES, List.of()).isEmpty()) {
            return;
        }

        if (stack.getMaxCount() == 1) {
            cir.setReturnValue(Fraction.getFraction(1, factor*8));
        }
    }
}

