package bunny.lib.utils;

import bunny.lib.utils.enums.FluidType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.EnumMap;
import java.util.Map;

public class FluidColors {
    public static final Map<FluidType, Integer> COLORS = new EnumMap<>(FluidType.class);
    static {
        COLORS.put(FluidType.WHITE,      0xF9FFFE);
        COLORS.put(FluidType.ORANGE,     0xF9801D);
        COLORS.put(FluidType.MAGENTA,    0xC74EBD);
        COLORS.put(FluidType.LIGHT_BLUE, 0x3AB3DA);
        COLORS.put(FluidType.YELLOW,     0xFED83D);
        COLORS.put(FluidType.LIME,       0x80C71F);
        COLORS.put(FluidType.PINK,       0xF38BAA);
        COLORS.put(FluidType.GRAY,       0x474F52);
        COLORS.put(FluidType.LIGHT_GRAY, 0x9D9D97);
        COLORS.put(FluidType.CYAN,       0x169C9C);
        COLORS.put(FluidType.PURPLE,     0x8932B8);
        COLORS.put(FluidType.BLUE,       0x3C44AA);
        COLORS.put(FluidType.BROWN,      0x835432);
        COLORS.put(FluidType.GREEN,      0x5E7C16);
        COLORS.put(FluidType.RED,        0xB02E26);
        COLORS.put(FluidType.BLACK,      0x1D1D21);
    }

    public static final Map<FluidType, DyeColor> DYE_ITEM = new EnumMap<>(FluidType.class);
    static {
        DYE_ITEM.put(FluidType.WHITE,      DyeColor.WHITE);
        DYE_ITEM.put(FluidType.ORANGE,     DyeColor.ORANGE);
        DYE_ITEM.put(FluidType.MAGENTA,    DyeColor.MAGENTA);
        DYE_ITEM.put(FluidType.LIGHT_BLUE, DyeColor.LIGHT_BLUE);
        DYE_ITEM.put(FluidType.YELLOW,     DyeColor.YELLOW);
        DYE_ITEM.put(FluidType.LIME,       DyeColor.LIME);
        DYE_ITEM.put(FluidType.PINK,       DyeColor.PINK);
        DYE_ITEM.put(FluidType.GRAY,       DyeColor.GRAY);
        DYE_ITEM.put(FluidType.LIGHT_GRAY, DyeColor.LIGHT_GRAY);
        DYE_ITEM.put(FluidType.CYAN,       DyeColor.CYAN);
        DYE_ITEM.put(FluidType.PURPLE,     DyeColor.PURPLE);
        DYE_ITEM.put(FluidType.BLUE,       DyeColor.BLUE);
        DYE_ITEM.put(FluidType.BROWN,      DyeColor.BROWN);
        DYE_ITEM.put(FluidType.GREEN,      DyeColor.GREEN);
        DYE_ITEM.put(FluidType.RED,        DyeColor.RED);
        DYE_ITEM.put(FluidType.BLACK,      DyeColor.BLACK);
    }

    public static final Map<FluidType, Item> WOOL_ITEM = new EnumMap<>(FluidType.class);
    static {
        WOOL_ITEM.put(FluidType.WHITE, Items.WHITE_WOOL);
        WOOL_ITEM.put(FluidType.ORANGE, Items.ORANGE_WOOL);
        WOOL_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_WOOL);
        WOOL_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_WOOL);
        WOOL_ITEM.put(FluidType.YELLOW, Items.YELLOW_WOOL);
        WOOL_ITEM.put(FluidType.LIME, Items.LIME_WOOL);
        WOOL_ITEM.put(FluidType.PINK, Items.PINK_WOOL);
        WOOL_ITEM.put(FluidType.GRAY, Items.GRAY_WOOL);
        WOOL_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_WOOL);
        WOOL_ITEM.put(FluidType.CYAN, Items.CYAN_WOOL);
        WOOL_ITEM.put(FluidType.PURPLE, Items.PURPLE_WOOL);
        WOOL_ITEM.put(FluidType.BLUE, Items.BLUE_WOOL);
        WOOL_ITEM.put(FluidType.BROWN, Items.BROWN_WOOL);
        WOOL_ITEM.put(FluidType.GREEN, Items.GREEN_WOOL);
        WOOL_ITEM.put(FluidType.RED, Items.RED_WOOL);
        WOOL_ITEM.put(FluidType.BLACK, Items.BLACK_WOOL);
    }

    public static final Map<FluidType, Item> CANDLE_ITEM = new EnumMap<>(FluidType.class);
    static {
        CANDLE_ITEM.put(FluidType.WHITE, Items.WHITE_CANDLE);
        CANDLE_ITEM.put(FluidType.ORANGE, Items.ORANGE_CANDLE);
        CANDLE_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_CANDLE);
        CANDLE_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_CANDLE);
        CANDLE_ITEM.put(FluidType.YELLOW, Items.YELLOW_CANDLE);
        CANDLE_ITEM.put(FluidType.LIME, Items.LIME_CANDLE);
        CANDLE_ITEM.put(FluidType.PINK, Items.PINK_CANDLE);
        CANDLE_ITEM.put(FluidType.GRAY, Items.GRAY_CANDLE);
        CANDLE_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_CANDLE);
        CANDLE_ITEM.put(FluidType.CYAN, Items.CYAN_CANDLE);
        CANDLE_ITEM.put(FluidType.PURPLE, Items.PURPLE_CANDLE);
        CANDLE_ITEM.put(FluidType.BLUE, Items.BLUE_CANDLE);
        CANDLE_ITEM.put(FluidType.BROWN, Items.BROWN_CANDLE);
        CANDLE_ITEM.put(FluidType.GREEN, Items.GREEN_CANDLE);
        CANDLE_ITEM.put(FluidType.RED, Items.RED_CANDLE);
        CANDLE_ITEM.put(FluidType.BLACK, Items.BLACK_CANDLE);
    }

    public static final Map<FluidType, Item> CONCRETE_POWDER_ITEM = new EnumMap<>(FluidType.class);
    static {
        CONCRETE_POWDER_ITEM.put(FluidType.WHITE, Items.WHITE_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.ORANGE, Items.ORANGE_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.YELLOW, Items.YELLOW_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.LIME, Items.LIME_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.PINK, Items.PINK_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.GRAY, Items.GRAY_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.CYAN, Items.CYAN_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.PURPLE, Items.PURPLE_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.BLUE, Items.BLUE_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.BROWN, Items.BROWN_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.GREEN, Items.GREEN_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.RED, Items.RED_CONCRETE_POWDER);
        CONCRETE_POWDER_ITEM.put(FluidType.BLACK, Items.BLACK_CONCRETE_POWDER);
    }

    public static final Map<FluidType, Item> CONCRETE_ITEM = new EnumMap<>(FluidType.class);
    static {
        CONCRETE_ITEM.put(FluidType.WHITE, Items.WHITE_CONCRETE);
        CONCRETE_ITEM.put(FluidType.ORANGE, Items.ORANGE_CONCRETE);
        CONCRETE_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_CONCRETE);
        CONCRETE_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_CONCRETE);
        CONCRETE_ITEM.put(FluidType.YELLOW, Items.YELLOW_CONCRETE);
        CONCRETE_ITEM.put(FluidType.LIME, Items.LIME_CONCRETE);
        CONCRETE_ITEM.put(FluidType.PINK, Items.PINK_CONCRETE);
        CONCRETE_ITEM.put(FluidType.GRAY, Items.GRAY_CONCRETE);
        CONCRETE_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_CONCRETE);
        CONCRETE_ITEM.put(FluidType.CYAN, Items.CYAN_CONCRETE);
        CONCRETE_ITEM.put(FluidType.PURPLE, Items.PURPLE_CONCRETE);
        CONCRETE_ITEM.put(FluidType.BLUE, Items.BLUE_CONCRETE);
        CONCRETE_ITEM.put(FluidType.BROWN, Items.BROWN_CONCRETE);
        CONCRETE_ITEM.put(FluidType.GREEN, Items.GREEN_CONCRETE);
        CONCRETE_ITEM.put(FluidType.RED, Items.RED_CONCRETE);
        CONCRETE_ITEM.put(FluidType.BLACK, Items.BLACK_CONCRETE);
    }

    public static final Map<FluidType, Item> GLAZED_TERRACOTTA_ITEM = new EnumMap<>(FluidType.class);
    static {
        GLAZED_TERRACOTTA_ITEM.put(FluidType.WHITE, Items.WHITE_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.ORANGE, Items.ORANGE_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.YELLOW, Items.YELLOW_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.LIME, Items.LIME_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.PINK, Items.PINK_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.GRAY, Items.GRAY_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.CYAN, Items.CYAN_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.PURPLE, Items.PURPLE_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.BLUE, Items.BLUE_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.BROWN, Items.BROWN_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.GREEN, Items.GREEN_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.RED, Items.RED_GLAZED_TERRACOTTA);
        GLAZED_TERRACOTTA_ITEM.put(FluidType.BLACK, Items.BLACK_GLAZED_TERRACOTTA);
    }

    public static final Map<FluidType, Item> TERRACOTTA_ITEM = new EnumMap<>(FluidType.class);
    static {
        TERRACOTTA_ITEM.put(FluidType.WHITE, Items.WHITE_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.ORANGE, Items.ORANGE_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.YELLOW, Items.YELLOW_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.LIME, Items.LIME_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.PINK, Items.PINK_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.GRAY, Items.GRAY_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.CYAN, Items.CYAN_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.PURPLE, Items.PURPLE_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.BLUE, Items.BLUE_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.BROWN, Items.BROWN_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.GREEN, Items.GREEN_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.RED, Items.RED_TERRACOTTA);
        TERRACOTTA_ITEM.put(FluidType.BLACK, Items.BLACK_TERRACOTTA);
    }

    public static final Map<FluidType, Item> BED_ITEM = new EnumMap<>(FluidType.class);
    static {
        BED_ITEM.put(FluidType.WHITE, Items.WHITE_BED);
        BED_ITEM.put(FluidType.ORANGE, Items.ORANGE_BED);
        BED_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_BED);
        BED_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_BED);
        BED_ITEM.put(FluidType.YELLOW, Items.YELLOW_BED);
        BED_ITEM.put(FluidType.LIME, Items.LIME_BED);
        BED_ITEM.put(FluidType.PINK, Items.PINK_BED);
        BED_ITEM.put(FluidType.GRAY, Items.GRAY_BED);
        BED_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_BED);
        BED_ITEM.put(FluidType.CYAN, Items.CYAN_BED);
        BED_ITEM.put(FluidType.PURPLE, Items.PURPLE_BED);
        BED_ITEM.put(FluidType.BLUE, Items.BLUE_BED);
        BED_ITEM.put(FluidType.BROWN, Items.BROWN_BED);
        BED_ITEM.put(FluidType.GREEN, Items.GREEN_BED);
        BED_ITEM.put(FluidType.RED, Items.RED_BED);
        BED_ITEM.put(FluidType.BLACK, Items.BLACK_BED);
    }

    public static final Map<FluidType, Item> CARPET_ITEM = new EnumMap<>(FluidType.class);
    static {
        CARPET_ITEM.put(FluidType.WHITE, Items.WHITE_CARPET);
        CARPET_ITEM.put(FluidType.ORANGE, Items.ORANGE_CARPET);
        CARPET_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_CARPET);
        CARPET_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_CARPET);
        CARPET_ITEM.put(FluidType.YELLOW, Items.YELLOW_CARPET);
        CARPET_ITEM.put(FluidType.LIME, Items.LIME_CARPET);
        CARPET_ITEM.put(FluidType.PINK, Items.PINK_CARPET);
        CARPET_ITEM.put(FluidType.GRAY, Items.GRAY_CARPET);
        CARPET_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_CARPET);
        CARPET_ITEM.put(FluidType.CYAN, Items.CYAN_CARPET);
        CARPET_ITEM.put(FluidType.PURPLE, Items.PURPLE_CARPET);
        CARPET_ITEM.put(FluidType.BLUE, Items.BLUE_CARPET);
        CARPET_ITEM.put(FluidType.BROWN, Items.BROWN_CARPET);
        CARPET_ITEM.put(FluidType.GREEN, Items.GREEN_CARPET);
        CARPET_ITEM.put(FluidType.RED, Items.RED_CARPET);
        CARPET_ITEM.put(FluidType.BLACK, Items.BLACK_CARPET);
    }

    public static final Map<FluidType, Item> STAINED_GLASS_ITEM = new EnumMap<>(FluidType.class);
    static {
        STAINED_GLASS_ITEM.put(FluidType.WHITE, Items.WHITE_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.ORANGE, Items.ORANGE_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.YELLOW, Items.YELLOW_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.LIME, Items.LIME_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.PINK, Items.PINK_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.GRAY, Items.GRAY_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.CYAN, Items.CYAN_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.PURPLE, Items.PURPLE_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.BLUE, Items.BLUE_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.BROWN, Items.BROWN_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.GREEN, Items.GREEN_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.RED, Items.RED_STAINED_GLASS);
        STAINED_GLASS_ITEM.put(FluidType.BLACK, Items.BLACK_STAINED_GLASS);
    }

    public static final Map<FluidType, Item> STAINED_GLASS_PANE_ITEM = new EnumMap<>(FluidType.class);
    static {
        STAINED_GLASS_PANE_ITEM.put(FluidType.WHITE, Items.WHITE_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.ORANGE, Items.ORANGE_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.YELLOW, Items.YELLOW_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.LIME, Items.LIME_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.PINK, Items.PINK_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.GRAY, Items.GRAY_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.CYAN, Items.CYAN_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.PURPLE, Items.PURPLE_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.BLUE, Items.BLUE_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.BROWN, Items.BROWN_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.GREEN, Items.GREEN_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.RED, Items.RED_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE_ITEM.put(FluidType.BLACK, Items.BLACK_STAINED_GLASS_PANE);
    }

    public static final Map<FluidType, Item> SHULKER_BOX_ITEM = new EnumMap<>(FluidType.class);
    static {
        SHULKER_BOX_ITEM.put(FluidType.WHITE, Items.WHITE_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.ORANGE, Items.ORANGE_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.MAGENTA, Items.MAGENTA_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.LIGHT_BLUE, Items.LIGHT_BLUE_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.YELLOW, Items.YELLOW_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.LIME, Items.LIME_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.PINK, Items.PINK_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.GRAY, Items.GRAY_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.LIGHT_GRAY, Items.LIGHT_GRAY_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.CYAN, Items.CYAN_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.PURPLE, Items.PURPLE_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.BLUE, Items.BLUE_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.BROWN, Items.BROWN_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.GREEN, Items.GREEN_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.RED, Items.RED_SHULKER_BOX);
        SHULKER_BOX_ITEM.put(FluidType.BLACK, Items.BLACK_SHULKER_BOX);
    }

    public static int getColor(FluidType type) {return COLORS.getOrDefault(type, 0xFFFFFF);}

    public static DyeColor getDyeItem(FluidType type) {return DYE_ITEM.getOrDefault(type, DyeColor.WHITE);}

    public static Item getWoolItem(FluidType type) {return WOOL_ITEM.getOrDefault(type, Items.WHITE_WOOL);}

    public static Item getCandleItem(FluidType type) {return CANDLE_ITEM.getOrDefault(type, Items.WHITE_CANDLE);}

    public static Item getConcretePowderItem(FluidType type) {return CONCRETE_POWDER_ITEM.getOrDefault(type, Items.WHITE_CONCRETE_POWDER);}

    public static Item getConcreteItem(FluidType type) {return CONCRETE_ITEM.getOrDefault(type, Items.WHITE_CONCRETE);}

    public static Item getGlazedTerracottaItem(FluidType type) {return GLAZED_TERRACOTTA_ITEM.getOrDefault(type, Items.WHITE_GLAZED_TERRACOTTA);}

    public static Item getTerracottaItem(FluidType type) {return TERRACOTTA_ITEM.getOrDefault(type, Items.WHITE_TERRACOTTA);}

    public static Item getBedItem(FluidType type) {return BED_ITEM.getOrDefault(type, Items.WHITE_BED);}

    public static Item getCarpetItem(FluidType type) {return CARPET_ITEM.getOrDefault(type, Items.WHITE_CARPET);}

    public static Item getStainedGlassItem(FluidType type) {return STAINED_GLASS_ITEM.getOrDefault(type, Items.WHITE_STAINED_GLASS);}

    public static Item getStainedGlassPaneItem(FluidType type) {return STAINED_GLASS_PANE_ITEM.getOrDefault(type, Items.WHITE_STAINED_GLASS_PANE);}

    public static Item getShulkerBoxItem(FluidType type) {return SHULKER_BOX_ITEM.getOrDefault(type, Items.WHITE_SHULKER_BOX);}

}



