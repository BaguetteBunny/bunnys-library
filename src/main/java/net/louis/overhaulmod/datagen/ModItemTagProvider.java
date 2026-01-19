package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.NETHERITE_HORSE_ARMOR)
                .add(Items.DIAMOND_HORSE_ARMOR)
                .add(Items.IRON_HORSE_ARMOR)
                .add(Items.GOLDEN_HORSE_ARMOR)
                .add(Items.LEATHER_HORSE_ARMOR);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.AMETHYST_DAGGER);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.BEAR_CLAW);

        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItems.ADVANCED_ARROW);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .add(Items.END_STONE)
                .add(Items.STONE)
                .add(Items.DRIPSTONE_BLOCK)
                .add(Items.DIORITE)
                .add(Items.ANDESITE)
                .add(Items.GRANITE);

    }
}
