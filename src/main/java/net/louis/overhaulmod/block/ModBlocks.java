package net.louis.overhaulmod.block;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.block.custom.AdvancedFletchingTable;
import net.louis.overhaulmod.cauldron.custom.ColoredWaterCauldronBlock;
import net.louis.overhaulmod.cauldron.custom.DragonBreathCauldronBlock;
import net.louis.overhaulmod.cauldron.custom.HoneyCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block ADVANCED_FLETCHING_TABLE = registerBlock("advanced_fletching_table",
            properties -> new AdvancedFletchingTable(properties.mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block COPPER_RAIL = registerBlock("copper_rail",
            properties -> new PoweredRailBlock(properties.strength(0.7f).sounds(BlockSoundGroup.METAL).noCollision()));

    public static final Block MYSTIC_ROSE = registerBlock("mystic_rose",
            properties -> new FlowerBlock(StatusEffects.REGENERATION, 8, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_MYSTIC_ROSE = registerBlock("potted_mystic_rose",
            properties -> new FlowerPotBlock(ModBlocks.MYSTIC_ROSE, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block COBALT_FLOWER = registerBlock("cobalt_flower",
            properties -> new FlowerBlock(StatusEffects.POISON, 12, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_COBALT_FLOWER = registerBlock("potted_cobalt_flower",
            properties -> new FlowerPotBlock(ModBlocks.COBALT_FLOWER, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block WILTED_POPPY = registerBlock("wilted_poppy",
            properties -> new FlowerBlock(StatusEffects.WITHER, 8, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_WILTED_POPPY = registerBlock("potted_wilted_poppy",
            properties -> new FlowerPotBlock(ModBlocks.WILTED_POPPY, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block LAVENDER_DANDELION = registerBlock("lavender_dandelion",
            properties -> new FlowerBlock(StatusEffects.SATURATION, 0.35f, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_LAVENDER_DANDELION = registerBlock("potted_lavender_dandelion",
            properties -> new FlowerPotBlock(ModBlocks.LAVENDER_DANDELION, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block HEART_FLOWER = registerBlock("heart_flower",
            properties -> new FlowerBlock(StatusEffects.REGENERATION, 8, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_HEART_FLOWER = registerBlock("potted_heart_flower",
            properties -> new FlowerPotBlock(ModBlocks.HEART_FLOWER, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SHINY_CORNFLOWER = registerBlock("shiny_cornflower",
            properties -> new FlowerBlock(StatusEffects.JUMP_BOOST, 6, properties.mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_SHINY_CORNFLOWER = registerBlock("potted_shiny_cornflower",
            properties -> new FlowerPotBlock(ModBlocks.SHINY_CORNFLOWER, properties.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block GLOW_LANTERN = registerBlock("glow_lantern",
            properties -> new LanternBlock(properties.mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance((state) -> 10).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    // BONE BLOCKS
    public static final Block CHILLED_BONE_BLOCK = registerBlock("chilled_bone_block",
            properties -> new PillarBlock(properties.mapColor(DyeColor.LIGHT_BLUE).slipperiness(0.95f).instrument(NoteBlockInstrument.XYLOPHONE).requiresTool().strength(2.0F).sounds(BlockSoundGroup.BONE)));
    public static final Block TOXIC_BONE_BLOCK = registerBlock("toxic_bone_block",
            properties -> new PillarBlock(properties.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.XYLOPHONE).requiresTool().strength(2.0F).sounds(BlockSoundGroup.BONE)));
    public static final Block DECREPIT_BONE_BLOCK = registerBlock("decrepit_bone_block",
            properties -> new PillarBlock(properties.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.XYLOPHONE).requiresTool().strength(2.0F).sounds(BlockSoundGroup.BONE)));

    // ENDSTONE FAMILY
    public static final Block END_STONE_SLAB = registerBlock("end_stone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block END_STONE_STAIRS = registerBlock("end_stone_stairs",
            properties -> new StairsBlock(Blocks.END_STONE.getDefaultState(), properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block END_STONE_WALL = registerBlock("end_stone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block END_STONE_PRESSURE_PLATE = registerBlock("end_stone_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block END_STONE_BUTTON = registerBlock("end_stone_button",
            properties -> new ButtonBlock(BlockSetType.STONE, 30, properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));

    // ROSE QUARTZ FAMILY
    public static final Block ROSE_QUARTZ_BRICKS = registerBlock("rose_quartz_bricks",
            properties -> new Block(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block ROSE_QUARTZ_PILLAR = registerBlock("rose_quartz_pillar",
            properties -> new PillarBlock(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block ROSE_QUARTZ_COLUMN = registerBlock("rose_quartz_column",
            properties -> new PillarBlock(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block CHISELED_ROSE_QUARTZ = registerBlock("chiseled_rose_quartz",
            properties -> new Block(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block ROSE_QUARTZ_SLAB = registerBlock("rose_quartz_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block ROSE_QUARTZ_STAIRS = registerBlock("rose_quartz_stairs",
            properties -> new StairsBlock(ModBlocks.ROSE_QUARTZ_BRICKS.getDefaultState(), properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block ROSE_QUARTZ_WALL = registerBlock("rose_quartz_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));

    // QUARTZ BRICK FAMILY
    public static final Block QUARTZ_BRICKS_SLAB = registerBlock("quartz_bricks_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block QUARTZ_BRICKS_STAIRS = registerBlock("quartz_bricks_stairs",
            properties -> new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), properties.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block QUARTZ_BRICKS_WALL = registerBlock("quartz_bricks_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));

    // CALCITE FAMILY
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.CALCITE).requiresTool().strength(0.75F)));
    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            properties -> new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.CALCITE).requiresTool().strength(0.75F)));
    public static final Block CALCITE_WALL = registerBlock("calcite_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.CALCITE).requiresTool().strength(0.75F)));

    // DRIPSTONE FAMILY
    public static final Block DRIPSTONE_SLAB = registerBlock("dripstone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.DRIPSTONE_BLOCK).requiresTool().strength(1.5F, 1.0F)));
    public static final Block DRIPSTONE_STAIRS = registerBlock("dripstone_stairs",
            properties -> new StairsBlock(Blocks.DRIPSTONE_BLOCK.getDefaultState(), properties.mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.DRIPSTONE_BLOCK).requiresTool().strength(1.5F, 1.0F)));
    public static final Block DRIPSTONE_WALL = registerBlock("dripstone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.DRIPSTONE_BLOCK).requiresTool().strength(1.5F, 1.0F)));

    // SMOOTH BASALT FAMILY
    public static final Block SMOOTH_BASALT_SLAB = registerBlock("smooth_basalt_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT)));
    public static final Block SMOOTH_BASALT_STAIRS = registerBlock("smooth_basalt_stairs",
            properties -> new StairsBlock(Blocks.BASALT.getDefaultState(), properties.mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT)));
    public static final Block SMOOTH_BASALT_WALL = registerBlock("smooth_basalt_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT)));

    // MISSING WALL FAMILY
    public static final Block BAMBOO_MOSAIC_WALL = registerBlock("bamboo_mosaic_wall", p -> new WallBlock(p.mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.BAMBOO_WOOD).burnable()));
    public static final Block PURPUR_WALL = registerBlock("purpur_wall", p -> new WallBlock(p.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block QUARTZ_WALL = registerBlock("quartz_wall", p -> new WallBlock(p.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block SMOOTH_QUARTZ_WALL = registerBlock("smooth_quartz_wall", p -> new WallBlock(p.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall", p -> new WallBlock(p.mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall", p -> new WallBlock(p.mapColor(MapColor.RAW_IRON_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall", p -> new WallBlock(p.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block CUT_RED_SANDSTONE_WALL = registerBlock("cut_red_sandstone_wall", p -> new WallBlock(p.mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block SMOOTH_RED_SANDSTONE_WALL = registerBlock("smooth_red_sandstone_wall", p -> new WallBlock(p.mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block CUT_SANDSTONE_WALL = registerBlock("cut_sandstone_wall", p -> new WallBlock(p.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block SMOOTH_SANDSTONE_WALL = registerBlock("smooth_sandstone_wall", p -> new WallBlock(p.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block PRISMARINE_BRICKS_WALL = registerBlock("prismarine_bricks_wall", p -> new WallBlock(p.mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block PRISMARINE_WALL = registerBlock("prismarine_wall", p -> new WallBlock(p.mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall", p -> new WallBlock(p.mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block SMOOTH_STONE_WALL = registerBlock("smooth_stone_wall", p -> new WallBlock(p.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block STONE_WALL = registerBlock("stone_wall", p -> new WallBlock(p.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));


    // MISSING STAIRS FAMILY
    public static final Block CUT_SANDSTONE_STAIRS = registerBlock("cut_sandstone_stairs",
            properties -> new StairsBlock(Blocks.CUT_SANDSTONE.getDefaultState(), properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block CUT_RED_SANDSTONE_STAIRS = registerBlock("cut_red_sandstone_stairs",
            properties -> new StairsBlock(Blocks.CUT_RED_SANDSTONE.getDefaultState(), properties.mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            properties -> new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));

    // LAVENDER QUARTZ FAMILY
    public static final Block LAVENDER_QUARTZ_BRICKS = registerBlock("lavender_quartz_bricks",
            properties -> new Block(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block LAVENDER_QUARTZ_PILLAR = registerBlock("lavender_quartz_pillar",
            properties -> new PillarBlock(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block LAVENDER_QUARTZ_COLUMN = registerBlock("lavender_quartz_column",
            properties -> new PillarBlock(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block CHISELED_LAVENDER_QUARTZ = registerBlock("chiseled_lavender_quartz",
            properties -> new Block(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block LAVENDER_QUARTZ_SLAB = registerBlock("lavender_quartz_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block LAVENDER_QUARTZ_STAIRS = registerBlock("lavender_quartz_stairs",
            properties -> new StairsBlock(ModBlocks.LAVENDER_QUARTZ_BRICKS.getDefaultState(), properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block LAVENDER_QUARTZ_WALL = registerBlock("lavender_quartz_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.PALE_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));

    // CRACKED BRICKS FAMILY
    public static final Block CRACKED_BRICKS = registerBlock("cracked_bricks",
            properties -> new Block(properties.mapColor(MapColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            properties -> new Block(properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block CRACKED_MOSSY_STONE_BRICKS = registerBlock("cracked_mossy_stone_bricks",
            properties -> new Block(properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CRACKED_MUD_BRICKS = registerBlock("cracked_mud_bricks",
            properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 3.0F).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_PRISMARINE_BRICKS = registerBlock("cracked_prismarine_bricks",
            properties -> new Block(properties.mapColor(MapColor.DIAMOND_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CRACKED_QUARTZ_BRICKS = registerBlock("cracked_quartz_bricks",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));
    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            properties -> new Block(properties.mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS = registerBlock("cracked_tuff_bricks",
            properties -> new Block(properties.mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));

    // CHISELED BRICKS FAMILY
    public static final Block CHISELED_DARK_PRISMARINE = registerBlock("chiseled_dark_prismarine",
            properties -> new Block(properties.mapColor(MapColor.DIAMOND_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_DEEPSLATE_BRICKS = registerBlock("chiseled_deepslate_bricks",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).strength(3.5F, 6.0F)));
    public static final Block CHISELED_END_STONE_BRICKS = registerBlock("chiseled_end_stone_bricks",
            properties -> new Block(properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)));
    public static final Block CHISELED_MOSSY_STONE_BRICKS = registerBlock("chiseled_mossy_stone_bricks",
            properties -> new Block(properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 3.0F).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CHISELED_PRISMARINE = registerBlock("chiseled_prismarine",
            properties -> new Block(properties.mapColor(MapColor.DIAMOND_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_PRISMARINE_BRICKS = registerBlock("chiseled_prismarine_bricks",
            properties -> new Block(properties.mapColor(MapColor.DIAMOND_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_PURPUR = registerBlock("chiseled_purpur",
            properties -> new Block(properties.mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks",
            properties -> new Block(properties.mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS)));
    public static final Block CHISELED_BRICKS = registerBlock("chiseled_bricks",
            properties -> new Block(properties.mapColor(MapColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)));
    public static final Block CHISELED_QUARTZ_BRICKS = registerBlock("chiseled_quartz_bricks",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)));

    public static final Block RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            properties -> new FenceBlock(properties.mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS)));


    // Concrete Family
    public static final Block WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs", p -> new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(), p.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs", p -> new StairsBlock(Blocks.ORANGE_CONCRETE.getDefaultState(), p.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs", p -> new StairsBlock(Blocks.MAGENTA_CONCRETE.getDefaultState(), p.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs", p -> new StairsBlock(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), p.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs", p -> new StairsBlock(Blocks.YELLOW_CONCRETE.getDefaultState(), p.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs", p -> new StairsBlock(Blocks.LIME_CONCRETE.getDefaultState(), p.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs", p -> new StairsBlock(Blocks.PINK_CONCRETE.getDefaultState(), p.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs", p -> new StairsBlock(Blocks.GRAY_CONCRETE.getDefaultState(), p.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs", p -> new StairsBlock(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), p.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs", p -> new StairsBlock(Blocks.CYAN_CONCRETE.getDefaultState(), p.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs", p -> new StairsBlock(Blocks.PURPLE_CONCRETE.getDefaultState(), p.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs", p -> new StairsBlock(Blocks.BLUE_CONCRETE.getDefaultState(), p.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs", p -> new StairsBlock(Blocks.BROWN_CONCRETE.getDefaultState(), p.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs", p -> new StairsBlock(Blocks.GREEN_CONCRETE.getDefaultState(), p.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs", p -> new StairsBlock(Blocks.RED_CONCRETE.getDefaultState(), p.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs", p -> new StairsBlock(Blocks.BLACK_CONCRETE.getDefaultState(), p.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));

    public static final Block WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", p -> new SlabBlock(p.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));

    public static final Block WHITE_CONCRETE_WALL = registerBlock("white_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block ORANGE_CONCRETE_WALL = registerBlock("orange_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block MAGENTA_CONCRETE_WALL = registerBlock("magenta_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_BLUE_CONCRETE_WALL = registerBlock("light_blue_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block YELLOW_CONCRETE_WALL = registerBlock("yellow_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIME_CONCRETE_WALL = registerBlock("lime_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PINK_CONCRETE_WALL = registerBlock("pink_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GRAY_CONCRETE_WALL = registerBlock("gray_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_GRAY_CONCRETE_WALL = registerBlock("light_gray_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block CYAN_CONCRETE_WALL = registerBlock("cyan_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PURPLE_CONCRETE_WALL = registerBlock("purple_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLUE_CONCRETE_WALL = registerBlock("blue_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BROWN_CONCRETE_WALL = registerBlock("brown_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GREEN_CONCRETE_WALL = registerBlock("green_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block RED_CONCRETE_WALL = registerBlock("red_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLACK_CONCRETE_WALL = registerBlock("black_concrete_wall", p -> new WallBlock(p.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));

    public static final Block HONEY_CAULDRON = registerBlock("honey_cauldron",
            properties -> new HoneyCauldronBlock(properties.mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F).nonOpaque()));
    public static final Block COLORED_WATER_CAULDRON = registerBlock("colored_water_cauldron",
            properties -> new ColoredWaterCauldronBlock(properties.mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F).nonOpaque()));
    public static final Block DRAGON_BREATH_CAULDRON = registerBlock("dragon_breath_cauldron",
            properties -> new DragonBreathCauldronBlock(properties.mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F).nonOpaque()));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LouisOverhaulMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(LouisOverhaulMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Blocks for " + LouisOverhaulMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.ADVANCED_FLETCHING_TABLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.MYSTIC_ROSE);
            entries.add(ModBlocks.COBALT_FLOWER);
            entries.add(ModBlocks.WILTED_POPPY);
            entries.add(ModBlocks.SHINY_CORNFLOWER);
            entries.add(ModBlocks.LAVENDER_DANDELION);
            entries.add(ModBlocks.HEART_FLOWER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(ModBlocks.COPPER_RAIL);
            entries.add(ModBlocks.END_STONE_PRESSURE_PLATE);
            entries.add(ModBlocks.END_STONE_BUTTON);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.GLOW_LANTERN);

            entries.add(ModBlocks.CHILLED_BONE_BLOCK);
            entries.add(ModBlocks.TOXIC_BONE_BLOCK);
            entries.add(ModBlocks.DECREPIT_BONE_BLOCK);

            entries.add(ModBlocks.ROSE_QUARTZ_BRICKS);
            entries.add(ModBlocks.ROSE_QUARTZ_PILLAR);
            entries.add(ModBlocks.ROSE_QUARTZ_COLUMN);
            entries.add(ModBlocks.CHISELED_ROSE_QUARTZ);
            entries.add(ModBlocks.ROSE_QUARTZ_SLAB);
            entries.add(ModBlocks.ROSE_QUARTZ_STAIRS);
            entries.add(ModBlocks.ROSE_QUARTZ_WALL);

            entries.add(ModBlocks.LAVENDER_QUARTZ_BRICKS);
            entries.add(ModBlocks.LAVENDER_QUARTZ_PILLAR);
            entries.add(ModBlocks.LAVENDER_QUARTZ_COLUMN);
            entries.add(ModBlocks.CHISELED_LAVENDER_QUARTZ);
            entries.add(ModBlocks.LAVENDER_QUARTZ_SLAB);
            entries.add(ModBlocks.LAVENDER_QUARTZ_STAIRS);
            entries.add(ModBlocks.LAVENDER_QUARTZ_WALL);

            entries.add(ModBlocks.CRACKED_BRICKS);
            entries.add(ModBlocks.CRACKED_QUARTZ_BRICKS);
            entries.add(ModBlocks.CRACKED_RED_NETHER_BRICKS);
            entries.add(ModBlocks.CRACKED_TUFF_BRICKS);
            entries.add(ModBlocks.CRACKED_MUD_BRICKS);
            entries.add(ModBlocks.CRACKED_END_STONE_BRICKS);
            entries.add(ModBlocks.CRACKED_PRISMARINE_BRICKS);
            entries.add(ModBlocks.CRACKED_MOSSY_STONE_BRICKS);

            entries.add(ModBlocks.QUARTZ_BRICKS_SLAB);
            entries.add(ModBlocks.QUARTZ_BRICKS_STAIRS);
            entries.add(ModBlocks.QUARTZ_BRICKS_WALL);

            entries.add(ModBlocks.CALCITE_SLAB);
            entries.add(ModBlocks.CALCITE_STAIRS);
            entries.add(ModBlocks.CALCITE_WALL);

            entries.add(ModBlocks.SMOOTH_STONE_STAIRS);
            entries.add(ModBlocks.CUT_RED_SANDSTONE_STAIRS);
            entries.add(ModBlocks.CUT_SANDSTONE_STAIRS);

            entries.add(ModBlocks.PURPUR_WALL);
            entries.add(ModBlocks.QUARTZ_WALL);
            entries.add(ModBlocks.SMOOTH_QUARTZ_WALL);
            entries.add(ModBlocks.POLISHED_ANDESITE_WALL);
            entries.add(ModBlocks.POLISHED_GRANITE_WALL);
            entries.add(ModBlocks.POLISHED_DIORITE_WALL);
            entries.add(ModBlocks.CUT_RED_SANDSTONE_WALL);
            entries.add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);
            entries.add(ModBlocks.CUT_SANDSTONE_WALL);
            entries.add(ModBlocks.SMOOTH_SANDSTONE_WALL);
            entries.add(ModBlocks.PRISMARINE_BRICKS_WALL);
            entries.add(ModBlocks.PRISMARINE_WALL);
            entries.add(ModBlocks.DARK_PRISMARINE_WALL);
            entries.add(ModBlocks.SMOOTH_STONE_WALL);
            entries.add(ModBlocks.STONE_WALL);
            entries.add(ModBlocks.BAMBOO_MOSAIC_WALL);

            entries.add(ModBlocks.CHISELED_DARK_PRISMARINE);
            entries.add(ModBlocks.CHISELED_PURPUR);
            entries.add(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
            entries.add(ModBlocks.CHISELED_END_STONE_BRICKS);
            entries.add(ModBlocks.CHISELED_MUD_BRICKS);
            entries.add(ModBlocks.CHISELED_RED_NETHER_BRICKS);
            entries.add(ModBlocks.CHISELED_MOSSY_STONE_BRICKS);
            entries.add(ModBlocks.CHISELED_PRISMARINE);
            entries.add(ModBlocks.CHISELED_PRISMARINE_BRICKS);
            entries.add(ModBlocks.CHISELED_BRICKS);
            entries.add(ModBlocks.CHISELED_QUARTZ_BRICKS);

            entries.add(ModBlocks.RED_NETHER_BRICK_FENCE);


            entries.add(ModBlocks.WHITE_CONCRETE_STAIRS);
            entries.add(ModBlocks.ORANGE_CONCRETE_STAIRS);
            entries.add(ModBlocks.MAGENTA_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
            entries.add(ModBlocks.YELLOW_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIME_CONCRETE_STAIRS);
            entries.add(ModBlocks.PINK_CONCRETE_STAIRS);
            entries.add(ModBlocks.GRAY_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
            entries.add(ModBlocks.CYAN_CONCRETE_STAIRS);
            entries.add(ModBlocks.PURPLE_CONCRETE_STAIRS);
            entries.add(ModBlocks.BLUE_CONCRETE_STAIRS);
            entries.add(ModBlocks.BROWN_CONCRETE_STAIRS);
            entries.add(ModBlocks.GREEN_CONCRETE_STAIRS);
            entries.add(ModBlocks.RED_CONCRETE_STAIRS);
            entries.add(ModBlocks.BLACK_CONCRETE_STAIRS);

            entries.add(ModBlocks.WHITE_CONCRETE_SLAB);
            entries.add(ModBlocks.ORANGE_CONCRETE_SLAB);
            entries.add(ModBlocks.MAGENTA_CONCRETE_SLAB);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_SLAB);
            entries.add(ModBlocks.YELLOW_CONCRETE_SLAB);
            entries.add(ModBlocks.LIME_CONCRETE_SLAB);
            entries.add(ModBlocks.PINK_CONCRETE_SLAB);
            entries.add(ModBlocks.GRAY_CONCRETE_SLAB);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_SLAB);
            entries.add(ModBlocks.CYAN_CONCRETE_SLAB);
            entries.add(ModBlocks.PURPLE_CONCRETE_SLAB);
            entries.add(ModBlocks.BLUE_CONCRETE_SLAB);
            entries.add(ModBlocks.BROWN_CONCRETE_SLAB);
            entries.add(ModBlocks.GREEN_CONCRETE_SLAB);
            entries.add(ModBlocks.RED_CONCRETE_SLAB);
            entries.add(ModBlocks.BLACK_CONCRETE_SLAB);

            entries.add(ModBlocks.WHITE_CONCRETE_WALL);
            entries.add(ModBlocks.ORANGE_CONCRETE_WALL);
            entries.add(ModBlocks.MAGENTA_CONCRETE_WALL);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_WALL);
            entries.add(ModBlocks.YELLOW_CONCRETE_WALL);
            entries.add(ModBlocks.LIME_CONCRETE_WALL);
            entries.add(ModBlocks.PINK_CONCRETE_WALL);
            entries.add(ModBlocks.GRAY_CONCRETE_WALL);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_WALL);
            entries.add(ModBlocks.CYAN_CONCRETE_WALL);
            entries.add(ModBlocks.PURPLE_CONCRETE_WALL);
            entries.add(ModBlocks.BLUE_CONCRETE_WALL);
            entries.add(ModBlocks.BROWN_CONCRETE_WALL);
            entries.add(ModBlocks.GREEN_CONCRETE_WALL);
            entries.add(ModBlocks.RED_CONCRETE_WALL);
            entries.add(ModBlocks.BLACK_CONCRETE_WALL);

            entries.add(ModBlocks.DRIPSTONE_SLAB);
            entries.add(ModBlocks.DRIPSTONE_STAIRS);
            entries.add(ModBlocks.DRIPSTONE_WALL);

            entries.add(ModBlocks.SMOOTH_BASALT_SLAB);
            entries.add(ModBlocks.SMOOTH_BASALT_STAIRS);
            entries.add(ModBlocks.SMOOTH_BASALT_WALL);

            entries.add(ModBlocks.END_STONE_SLAB);
            entries.add(ModBlocks.END_STONE_STAIRS);
            entries.add(ModBlocks.END_STONE_WALL);
        });

    }
}
