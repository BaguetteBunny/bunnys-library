package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.interfaces.BundleBuilderAccessor;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BundleContentsComponent.Builder.class)
public abstract class BundleContentsComponentBuilderMixin implements BundleBuilderAccessor {

    @Shadow private List<ItemStack> stacks;

    @Unique
    private ItemStack LOM$bundleStack;

    @Shadow
    protected abstract int getInsertionIndex(ItemStack stack);

    @Override
    public void LOM$setBundleStack(ItemStack stack) {
        this.LOM$bundleStack = stack;
    }

    @Override
    public ItemStack LOM$getBundleStack() {
        return this.LOM$bundleStack;
    }

    @Inject(method = "add(Lnet/minecraft/item/ItemStack;)I", at = @At("HEAD"))
    private void LOM$wrapAddWithContext(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (!BundleContentsComponent.canBeBundled(stack)) return;

        int context = getBundleContextFactor(this.LOM$bundleStack);
        stack.set(ModComponents.BUNDLE_CONTEXT, context);
    }

    @Unique
    private int getBundleContextFactor(ItemStack bundleStack) {
        if (bundleStack == null) return 1;

        Item bundleItem = bundleStack.getItem();

        int bundleComponentFactor = bundleStack.getComponents().getOrDefault(ModComponents.BUNDLE_MAX_FACTOR, 0);

        int total;

        if (bundleItem == ModItems.POTION_POUCH) total = 2 + bundleComponentFactor;
        else if (bundleItem == ModItems.PIONEER_POUCH) total = 4 + bundleComponentFactor;
        else total = (bundleComponentFactor == 0) ? 1 : bundleComponentFactor;

        return total;
    }

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

    @Inject(method = "removeSelected", at = @At("RETURN"))
    private void LOM$cleanupRemovedItem(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack removed = cir.getReturnValue();
        if (removed != null) removed.remove(ModComponents.BUNDLE_CONTEXT);
    }
}