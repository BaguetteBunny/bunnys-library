package net.louis.overhaulmod.utils;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentCapRegistry {
    private static final Map<Item, Integer> ENCHANTMENT_CAPS = new HashMap<>();

    static int baseSword = 5;
    static int basePickaxe = 3;
    static int baseAxe = 5;
    static int baseShovel = 3;
    static int baseHoe = 3;

    static int baseHelmet = 6;
    static int baseChestplate = 4;
    static int basePants = 5;
    static int baseBoots = 7;

    public static void register() {
        // Armor caps
        ENCHANTMENT_CAPS.put(Items.LEATHER_HELMET, baseHelmet + 1);
        ENCHANTMENT_CAPS.put(Items.LEATHER_CHESTPLATE, baseChestplate + 1);
        ENCHANTMENT_CAPS.put(Items.LEATHER_LEGGINGS, basePants + 1);
        ENCHANTMENT_CAPS.put(Items.LEATHER_BOOTS, baseBoots + 1);

        ENCHANTMENT_CAPS.put(Items.IRON_HELMET, baseHelmet + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_CHESTPLATE, baseChestplate + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_LEGGINGS, basePants + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_BOOTS, baseBoots + 1);

        ENCHANTMENT_CAPS.put(Items.CHAINMAIL_HELMET, baseHelmet + 2);
        ENCHANTMENT_CAPS.put(Items.CHAINMAIL_CHESTPLATE, baseChestplate + 1);
        ENCHANTMENT_CAPS.put(Items.CHAINMAIL_LEGGINGS, basePants + 1);
        ENCHANTMENT_CAPS.put(Items.CHAINMAIL_BOOTS, baseBoots + 2);

        ENCHANTMENT_CAPS.put(Items.GOLDEN_HELMET, baseHelmet + 2);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_CHESTPLATE, baseChestplate + 2);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_LEGGINGS, basePants + 2);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_BOOTS, baseBoots + 2);

        ENCHANTMENT_CAPS.put(Items.DIAMOND_HELMET, baseHelmet);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_CHESTPLATE, baseChestplate);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_LEGGINGS, basePants);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_BOOTS, baseBoots);

        ENCHANTMENT_CAPS.put(Items.NETHERITE_HELMET, baseHelmet);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_CHESTPLATE, baseChestplate);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_LEGGINGS, basePants);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_BOOTS, baseBoots);

        // Weapons
        ENCHANTMENT_CAPS.put(Items.WOODEN_SWORD, baseSword + 1);
        ENCHANTMENT_CAPS.put(Items.STONE_SWORD, baseSword + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_SWORD, baseSword + 1);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_SWORD, baseSword + 2);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_SWORD, baseSword);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_SWORD, baseSword);

        // Tools
        ENCHANTMENT_CAPS.put(Items.WOODEN_PICKAXE, basePickaxe + 1);
        ENCHANTMENT_CAPS.put(Items.STONE_PICKAXE, basePickaxe + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_PICKAXE, basePickaxe + 1);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_PICKAXE, basePickaxe + 2);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_PICKAXE, basePickaxe);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_PICKAXE, basePickaxe);

        ENCHANTMENT_CAPS.put(Items.WOODEN_AXE, baseAxe + 1);
        ENCHANTMENT_CAPS.put(Items.STONE_AXE, baseAxe + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_AXE, baseAxe + 1);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_AXE, baseAxe + 2);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_AXE, baseAxe);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_AXE, baseAxe);

        ENCHANTMENT_CAPS.put(Items.WOODEN_SHOVEL, baseShovel + 1);
        ENCHANTMENT_CAPS.put(Items.STONE_SHOVEL, baseShovel + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_SHOVEL, baseShovel + 1);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_SHOVEL, baseShovel + 2);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_SHOVEL, baseShovel);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_SHOVEL, baseShovel);

        ENCHANTMENT_CAPS.put(Items.WOODEN_HOE, baseHoe + 1);
        ENCHANTMENT_CAPS.put(Items.STONE_HOE, baseHoe + 1);
        ENCHANTMENT_CAPS.put(Items.IRON_HOE, baseHoe + 1);
        ENCHANTMENT_CAPS.put(Items.GOLDEN_HOE, baseHoe + 2);
        ENCHANTMENT_CAPS.put(Items.DIAMOND_HOE, baseHoe);
        ENCHANTMENT_CAPS.put(Items.NETHERITE_HOE, baseHoe);

        // Other
        ENCHANTMENT_CAPS.put(Items.TURTLE_HELMET, baseHelmet + 3);
        ENCHANTMENT_CAPS.put(Items.ELYTRA, baseChestplate - 1);
        ENCHANTMENT_CAPS.put(Items.SHIELD, 3);
        ENCHANTMENT_CAPS.put(Items.BOW, 4);
        ENCHANTMENT_CAPS.put(Items.CROSSBOW, 3);
        ENCHANTMENT_CAPS.put(Items.TRIDENT, 5);
        ENCHANTMENT_CAPS.put(Items.FISHING_ROD, 3);
        ENCHANTMENT_CAPS.put(Items.SHEARS, 2);
        ENCHANTMENT_CAPS.put(Items.FLINT_AND_STEEL, 2);
        ENCHANTMENT_CAPS.put(Items.CARROT_ON_A_STICK, 3);
        ENCHANTMENT_CAPS.put(Items.WARPED_FUNGUS_ON_A_STICK, 3);
        ENCHANTMENT_CAPS.put(Items.MACE, 4);
        ENCHANTMENT_CAPS.put(ModItems.BEAR_CLAW, 3);
    }

    public static int getCap(Item item) {
        return ENCHANTMENT_CAPS.getOrDefault(item, Integer.MAX_VALUE);
    }

    public static boolean hasCap(Item item) {
        return ENCHANTMENT_CAPS.containsKey(item);
    }
}
