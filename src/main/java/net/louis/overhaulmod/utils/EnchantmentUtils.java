package net.louis.overhaulmod.utils;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentUtils {
    public static boolean hasEnchant(ItemStack stack, String path) {
        ItemEnchantmentsComponent enchantments = stack.get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && !enchantments.isEmpty()) {
            for (var entry : enchantments.getEnchantmentEntries()) {
                RegistryEntry<Enchantment> enchantment = entry.getKey();
                if (enchantment.matchesId(Identifier.of(LouisOverhaulMod.MOD_ID, path))) return true;
                if (enchantment.matchesId(Identifier.ofVanilla(path))) return true;
            }
        }
        return false;
    }

    public static final Map<Block, Item> SMELTING_TRANSFORM_MAP = new HashMap<>();
    static {
        SMELTING_TRANSFORM_MAP.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.COPPER_ORE, Items.COPPER_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT);
        SMELTING_TRANSFORM_MAP.put(Blocks.RAW_IRON_BLOCK, Items.IRON_BLOCK);
        SMELTING_TRANSFORM_MAP.put(Blocks.RAW_GOLD_BLOCK, Items.GOLD_BLOCK);
        SMELTING_TRANSFORM_MAP.put(Blocks.RAW_COPPER_BLOCK, Items.COPPER_BLOCK);
        SMELTING_TRANSFORM_MAP.put(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP);
        SMELTING_TRANSFORM_MAP.put(Blocks.SEA_PICKLE, Items.LIME_DYE);
        SMELTING_TRANSFORM_MAP.put(Blocks.CACTUS, Items.GREEN_DYE);

        SMELTING_TRANSFORM_MAP.put(Blocks.CLAY, Items.TERRACOTTA);
        SMELTING_TRANSFORM_MAP.put(Blocks.WET_SPONGE, Items.SPONGE);
        SMELTING_TRANSFORM_MAP.put(Blocks.NETHERRACK, Items.NETHER_BRICKS);

        SMELTING_TRANSFORM_MAP.put(Blocks.POTATOES, Items.BAKED_POTATO);
        SMELTING_TRANSFORM_MAP.put(Blocks.KELP, Items.DRIED_KELP);
        SMELTING_TRANSFORM_MAP.put(Blocks.KELP_PLANT, Items.DRIED_KELP);

        SMELTING_TRANSFORM_MAP.put(Blocks.OAK_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.SPRUCE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.BIRCH_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.JUNGLE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.ACACIA_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.DARK_OAK_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.MANGROVE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.CHERRY_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.BAMBOO_BLOCK, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_OAK_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_SPRUCE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_BIRCH_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_JUNGLE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_ACACIA_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_DARK_OAK_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_MANGROVE_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_CHERRY_LOG, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_BAMBOO_BLOCK, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.OAK_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.SPRUCE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.BIRCH_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.JUNGLE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.ACACIA_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.DARK_OAK_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.MANGROVE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.CHERRY_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_OAK_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_SPRUCE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_BIRCH_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_JUNGLE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_ACACIA_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_DARK_OAK_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_MANGROVE_WOOD, Items.CHARCOAL);
        SMELTING_TRANSFORM_MAP.put(Blocks.STRIPPED_CHERRY_WOOD, Items.CHARCOAL);
    }

    public static final Map<Block, BlockState> SHOVEL_TILLABLE_MAP = new HashMap<>();
    static {
        SHOVEL_TILLABLE_MAP.put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState());
        SHOVEL_TILLABLE_MAP.put(Blocks.DIRT, Blocks.DIRT_PATH.getDefaultState());
        SHOVEL_TILLABLE_MAP.put(Blocks.PODZOL, Blocks.DIRT_PATH.getDefaultState());
        SHOVEL_TILLABLE_MAP.put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.getDefaultState());
        SHOVEL_TILLABLE_MAP.put(Blocks.MYCELIUM, Blocks.DIRT_PATH.getDefaultState());
        SHOVEL_TILLABLE_MAP.put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.getDefaultState());
    }

    public static final Map<Block, BlockState> HOE_TILLABLE_MAP = new HashMap<>();
    static {
        HOE_TILLABLE_MAP.put(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState());
        HOE_TILLABLE_MAP.put(Blocks.DIRT, Blocks.FARMLAND.getDefaultState());
        HOE_TILLABLE_MAP.put(Blocks.DIRT_PATH, Blocks.FARMLAND.getDefaultState());
        HOE_TILLABLE_MAP.put(Blocks.MYCELIUM, Blocks.FARMLAND.getDefaultState());

        HOE_TILLABLE_MAP.put(Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState());
        HOE_TILLABLE_MAP.put(Blocks.ROOTED_DIRT, Blocks.DIRT.getDefaultState());
    }
}
