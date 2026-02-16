package net.louis.overhaulmod.interfaces;

import net.minecraft.item.ItemStack;

public interface BundleBuilderAccessor {
    void LOM$setBundleStack(ItemStack stack);
    ItemStack LOM$getBundleStack();
}
