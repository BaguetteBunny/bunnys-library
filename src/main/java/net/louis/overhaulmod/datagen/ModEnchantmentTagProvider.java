package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.louis.overhaulmod.enchantments.ModEnchantments;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.SMELTING)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.REELING)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.REELING)
                .add(ModEnchantments.DOUBLE_HOOK)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.ON_RANDOM_LOOT)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.SMELTING)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.REELING)
                .add(ModEnchantments.DOUBLE_HOOK)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.ON_TRADED_EQUIPMENT)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.REELING)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.DOUBLE_TRADE_PRICE)
                .add(ModEnchantments.DOUBLE_HOOK)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                .add(ModEnchantments.VAMPIRISM)
                .add(ModEnchantments.TILLING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.REELING)
                .add(ModEnchantments.SHIELD_BASH)
                .add(ModEnchantments.GIANT_KILLER);

        getOrCreateTagBuilder(EnchantmentTags.TREASURE)
                .add(ModEnchantments.SMELTING)
                .add(ModEnchantments.ILLAGERS_BANE)
                .add(ModEnchantments.DOUBLE_HOOK)
                .add(ModEnchantments.GIANT_KILLER);

    }
}
