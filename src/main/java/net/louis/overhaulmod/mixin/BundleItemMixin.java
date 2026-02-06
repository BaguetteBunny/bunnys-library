package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.custom.PioneerPouch;
import net.louis.overhaulmod.item.custom.PotionPouch;
import net.louis.overhaulmod.utils.BundleContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleItem.class)
public class BundleItemMixin {

    @Inject(method = "onStackClicked", at = @At("HEAD"), cancellable = true)
    private void LOM$filterBeforeAdd(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof PotionPouch && !slot.getStack().isEmpty() && !(slot.getStack().getItem() instanceof PotionItem)) {
            cir.setReturnValue(false);
            return;
        }
        else if (stack.getItem() instanceof PioneerPouch && !slot.getStack().isEmpty() && !(slot.getStack().getItem() instanceof BlockItem)) {
            cir.setReturnValue(false);
            return;
        }
        BundleContext.set(stack);
    }

    @Inject(method = "onStackClicked", at = @At("RETURN"))
    private void LOM$clearBundleContext(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        BundleContext.clear();
    }

    @Inject(method = "onClicked", at = @At("HEAD"), cancellable = true)
    private void LOM$filterBeforeAdd2(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof PotionPouch && !otherStack.isEmpty() && !(otherStack.getItem() instanceof PotionItem)) {
            cir.setReturnValue(false);
            return;
        }
        else if (stack.getItem() instanceof PioneerPouch && !otherStack.isEmpty() && !(otherStack.getItem() instanceof BlockItem)) {
            cir.setReturnValue(false);
            return;
        }

        BundleContext.set(stack);
    }

    @Inject(method = "onClicked", at = @At("RETURN"))
    private void LOM$clearBundleContext2(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
        BundleContext.clear();
    }
}