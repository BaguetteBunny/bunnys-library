package net.louis.overhaulmod.tags;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_AMETHYST_TOOL = createTag("needs_amethyst_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(LouisOverhaulMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SHOVEL_HOE_ENCHANTABLE  = createTag("shovel_hoe_enchantable");
        public static final TagKey<Item> FLINT_AND_STEEL_ENCHANTABLE  = createTag("flint_and_steel_enchantable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name));
        }
    }
}
