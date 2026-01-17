package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.GrindstoneScreenHandler;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin {

    @Inject(method = "grind", at = @At("HEAD"), cancellable = true)
    private void removeOnlyFirstEnchantment(ItemStack item, CallbackInfoReturnable<ItemStack> cir) {
        ItemEnchantmentsComponent enchantments = item.get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && !enchantments.isEmpty() && ModConfig.INSTANCE.grindstoneRemoveEnchantsOneByOne) {
            RegistryEntry<Enchantment> firstEnchant = null;

            for (var entry : enchantments.getEnchantmentEntries()) {
                if (!entry.getKey().isIn(net.minecraft.registry.tag.EnchantmentTags.CURSE)) {
                    firstEnchant = entry.getKey();
                    break;
                }
            }

            if (firstEnchant != null) {
                ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(enchantments);
                RegistryEntry<Enchantment> finalFirstEnchant = firstEnchant;
                builder.remove(entry -> entry.equals(finalFirstEnchant));
                ItemEnchantmentsComponent newEnchantments = builder.build();

                item.set(DataComponentTypes.ENCHANTMENTS, newEnchantments);

                if (item.isOf(Items.ENCHANTED_BOOK) && newEnchantments.isEmpty()) item = item.withItem(Items.BOOK);

                int repairCost = 0;
                for (int j = 0; j < newEnchantments.getSize(); j++) repairCost = AnvilScreenHandler.getNextCost(repairCost);

                item.set(DataComponentTypes.REPAIR_COST, repairCost);

                cir.setReturnValue(item);
            }
        }
    }
}
