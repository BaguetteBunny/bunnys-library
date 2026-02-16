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
                .add(Items.DIAMOND_HORSE_ARMOR)
                .add(Items.IRON_HORSE_ARMOR)
                .add(Items.GOLDEN_HORSE_ARMOR)
                .add(Items.LEATHER_HORSE_ARMOR);

        getOrCreateTagBuilder(ItemTags.BUNDLES)
                .add(ModItems.PIONEER_POUCH)
                .add(ModItems.POTION_POUCH);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.AMETHYST_DAGGER);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.BEAR_CLAW);

        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItems.ADVANCED_ARROW);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItems.BIG_BUNDLE_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItems.TITANIC_BUNDLE_UPGRADE_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.AZURITE);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.POTION_POUCH)
                .add(ModItems.PIONEER_POUCH)

                .add(Items.BUNDLE)
                .add(Items.BUNDLE)
                .add(Items.WHITE_BUNDLE)
                .add(Items.ORANGE_BUNDLE)
                .add(Items.MAGENTA_BUNDLE)
                .add(Items.LIGHT_BLUE_BUNDLE)
                .add(Items.YELLOW_BUNDLE)
                .add(Items.LIME_BUNDLE)
                .add(Items.PINK_BUNDLE)
                .add(Items.GRAY_BUNDLE)
                .add(Items.LIGHT_GRAY_BUNDLE)
                .add(Items.CYAN_BUNDLE)
                .add(Items.BLACK_BUNDLE)
                .add(Items.BROWN_BUNDLE)
                .add(Items.GREEN_BUNDLE)
                .add(Items.RED_BUNDLE)
                .add(Items.BLUE_BUNDLE)
                .add(Items.PURPLE_BUNDLE);

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .add(Items.END_STONE)
                .add(Items.STONE)
                .add(Items.DRIPSTONE_BLOCK)
                .add(Items.DIORITE)
                .add(Items.ANDESITE)
                .add(Items.GRANITE);

    }
}
