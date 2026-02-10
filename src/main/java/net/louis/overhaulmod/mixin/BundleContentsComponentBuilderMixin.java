package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.*;
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

    @Inject(method = "add(Lnet/minecraft/world/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private void LOM$wrapAddWithContext(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (!BundleContentsComponent.canBeBundled(stack)) return;
        stack.set(ModComponents.BUNDLE_CONTEXT, 4);
    }

    @Inject(method = "removeSelected", at = @At("RETURN"))
    private void LOM$cleanupRemovedItem(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack removed = cir.getReturnValue();
        if (removed != null) removed.remove(ModComponents.BUNDLE_CONTEXT);
    }
}
