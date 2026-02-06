package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.custom.PioneerPouch;
import net.louis.overhaulmod.item.custom.PotionPouch;
import net.louis.overhaulmod.utils.BundleContext;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BundleContentsComponent.Builder.class)
public abstract class BundleContentsComponentBuilderMixin {

    @Shadow private List<ItemStack> stacks;

    @Shadow
    protected abstract int getInsertionIndex(ItemStack stack);

    @Inject(method = "getMaxAllowed", at = @At("RETURN"), cancellable = true)
    private void LOM$considerStackLimit(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        int maxByCapacity = cir.getReturnValue();
        int insertionIndex = this.getInsertionIndex(stack);

        if (insertionIndex != -1) {
            ItemStack existingStack = this.stacks.get(insertionIndex);
            int spaceLeft = existingStack.getMaxCount() - existingStack.getCount();
            cir.setReturnValue(Math.min(maxByCapacity, spaceLeft));
        }
    }

    @Inject(method = "add(Lnet/minecraft/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private void LOM$filterByBundleType(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        ItemStack bundleStack = BundleContext.get();

        if (bundleStack != null && bundleStack.getItem() instanceof PotionPouch) {
            if (!(stack.getItem() instanceof PotionItem)) {
                cir.setReturnValue(0);
            }
        }
        else if (bundleStack != null && bundleStack.getItem() instanceof PioneerPouch) {
            if (!(stack.getItem() instanceof BlockItem)) {
                cir.setReturnValue(0);
            }
        }
    }
}
