package bunny.lib.utils;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

public record WoodTypeData(String name, ItemConvertible planks, ItemConvertible slab, ItemConvertible stairs,
                           ItemConvertible trapdoor, ItemConvertible door, ItemConvertible button,
                           ItemConvertible pressurePlate, ItemConvertible sign, ItemConvertible fence,
                           ItemConvertible fenceGate, ItemConvertible stickOutput) {

    public final static WoodTypeData[] WOODS = new WoodTypeData[]{
            new WoodTypeData("oak", Blocks.OAK_PLANKS, Blocks.OAK_SLAB, Blocks.OAK_STAIRS, Blocks.OAK_TRAPDOOR, Blocks.OAK_DOOR, Blocks.OAK_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_SIGN, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Items.STICK),
            new WoodTypeData("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_SIGN, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Items.STICK),
            new WoodTypeData("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, Blocks.BIRCH_STAIRS, Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_DOOR, Blocks.BIRCH_BUTTON, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_SIGN, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Items.STICK),
            new WoodTypeData("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_SIGN, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Items.STICK),
            new WoodTypeData("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, Blocks.ACACIA_STAIRS, Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_DOOR, Blocks.ACACIA_BUTTON, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_SIGN, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Items.STICK),
            new WoodTypeData("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Items.STICK),
            new WoodTypeData("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_TRAPDOOR, Blocks.MANGROVE_DOOR, Blocks.MANGROVE_BUTTON, Blocks.MANGROVE_PRESSURE_PLATE, Blocks.MANGROVE_SIGN, Blocks.MANGROVE_FENCE, Blocks.MANGROVE_FENCE_GATE, Items.STICK),
            new WoodTypeData("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, Blocks.CHERRY_STAIRS, Blocks.CHERRY_TRAPDOOR, Blocks.CHERRY_DOOR, Blocks.CHERRY_BUTTON, Blocks.CHERRY_PRESSURE_PLATE, Blocks.CHERRY_SIGN, Blocks.CHERRY_FENCE, Blocks.CHERRY_FENCE_GATE, Items.STICK),
            new WoodTypeData("bamboo", Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_TRAPDOOR, Blocks.BAMBOO_DOOR, Blocks.BAMBOO_BUTTON, Blocks.BAMBOO_PRESSURE_PLATE, Blocks.BAMBOO_SIGN, Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, Items.STICK),
            new WoodTypeData("bamboo_mosaic", Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_TRAPDOOR, Blocks.BAMBOO_DOOR, Blocks.BAMBOO_BUTTON, Blocks.BAMBOO_PRESSURE_PLATE, Blocks.BAMBOO_SIGN, Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, Items.STICK),
            new WoodTypeData("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_DOOR, Blocks.CRIMSON_BUTTON, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_SIGN, Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE, Items.STICK),
            new WoodTypeData("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, Blocks.WARPED_STAIRS, Blocks.WARPED_TRAPDOOR, Blocks.WARPED_DOOR, Blocks.WARPED_BUTTON, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_SIGN, Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE, Items.STICK)
    };
}

