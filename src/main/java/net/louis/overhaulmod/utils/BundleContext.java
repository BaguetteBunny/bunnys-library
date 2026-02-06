package net.louis.overhaulmod.utils;

import net.minecraft.item.ItemStack;

public class BundleContext {
    private static final ThreadLocal<ItemStack> CURRENT_BUNDLE = new ThreadLocal<>();

    public static void set(ItemStack stack) {
        CURRENT_BUNDLE.set(stack);
    }

    public static ItemStack get() {
        return CURRENT_BUNDLE.get();
    }

    public static void clear() {
        CURRENT_BUNDLE.remove();
    }
}
