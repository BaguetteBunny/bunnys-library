package bunny.lib.utils;

import net.minecraft.item.*;

public class ToolArmorUtil {

    public static String getToolMaterialString(ItemStack stack) {
        if (stack.isOf(Items.IRON_INGOT)) {
            return "iron";
        } else if (stack.isOf(Items.GOLD_INGOT)) {
            return "gold";
        } else if (stack.isOf(Items.DIAMOND)) {
            return "diamond";
        } else if (stack.isOf(Items.NETHERITE_SCRAP)) {
            return "netherite";
        }
        return null;
    }

    public static Item getToolMaterialItem(String s) {
        return switch (s) {
            case "" -> null;
            case "iron" -> Items.IRON_INGOT;
            case "gold" -> Items.GOLD_INGOT;
            case "diamond" -> Items.DIAMOND;
            case "netherite" -> Items.NETHERITE_SCRAP;
            default -> null;
        };
    }

    public static boolean isNetheriteRepairable(ItemStack left, Item repair) {
        if (repair != Items.NETHERITE_SCRAP) return false;
        if (left.getItem() instanceof MiningToolItem toolItem && isNetheriteTool(toolItem)) return true;
        return left.getItem() instanceof ArmorItem armorItem && isNetheriteArmor(armorItem);
    }

    public static boolean isNetheriteArmor(ArmorItem armorItem) {
        return (armorItem == Items.NETHERITE_BOOTS ||
                armorItem == Items.NETHERITE_LEGGINGS ||
                armorItem == Items.NETHERITE_CHESTPLATE ||
                armorItem == Items.NETHERITE_HELMET);
    }

    public static boolean isToolOrWeapon(Item item) {
        return item instanceof MiningToolItem ||
                item instanceof SwordItem ||
                item instanceof TridentItem ||
                item instanceof MaceItem ||
                item instanceof BowItem ||
                item instanceof CrossbowItem ||
                item instanceof FishingRodItem ||
                item instanceof OnAStickItem ||
                item instanceof BrushItem ||
                item instanceof ShieldItem ||
                item instanceof ShearsItem;
    }

    private static boolean isNetheriteTool(MiningToolItem item) {
        return (item == Items.NETHERITE_PICKAXE ||
                item == Items.NETHERITE_AXE ||
                item == Items.NETHERITE_SHOVEL ||
                item == Items.NETHERITE_HOE ||
                item == Items.NETHERITE_SWORD);
    }
}
