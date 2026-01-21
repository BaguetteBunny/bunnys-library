package net.louis.overhaulmod.fluid;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.fluid.colors.*;
import net.minecraft.block.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static FlowableFluid STILL_WHITE_WATER;
    public static FlowableFluid FLOWING_WHITE_WATER;
    public static Block WHITE_WATER_BLOCK;

    public static FlowableFluid STILL_ORANGE_WATER;
    public static FlowableFluid FLOWING_ORANGE_WATER;
    public static Block ORANGE_WATER_BLOCK;

    public static FlowableFluid STILL_MAGENTA_WATER;
    public static FlowableFluid FLOWING_MAGENTA_WATER;
    public static Block MAGENTA_WATER_BLOCK;

    public static FlowableFluid STILL_LIGHT_BLUE_WATER;
    public static FlowableFluid FLOWING_LIGHT_BLUE_WATER;
    public static Block LIGHT_BLUE_WATER_BLOCK;

    public static FlowableFluid STILL_YELLOW_WATER;
    public static FlowableFluid FLOWING_YELLOW_WATER;
    public static Block YELLOW_WATER_BLOCK;

    public static FlowableFluid STILL_LIME_WATER;
    public static FlowableFluid FLOWING_LIME_WATER;
    public static Block LIME_WATER_BLOCK;

    public static FlowableFluid STILL_PINK_WATER;
    public static FlowableFluid FLOWING_PINK_WATER;
    public static Block PINK_WATER_BLOCK;

    public static FlowableFluid STILL_GRAY_WATER;
    public static FlowableFluid FLOWING_GRAY_WATER;
    public static Block GRAY_WATER_BLOCK;

    public static FlowableFluid STILL_LIGHT_GRAY_WATER;
    public static FlowableFluid FLOWING_LIGHT_GRAY_WATER;
    public static Block LIGHT_GRAY_WATER_BLOCK;

    public static FlowableFluid STILL_CYAN_WATER;
    public static FlowableFluid FLOWING_CYAN_WATER;
    public static Block CYAN_WATER_BLOCK;

    public static FlowableFluid STILL_PURPLE_WATER;
    public static FlowableFluid FLOWING_PURPLE_WATER;
    public static Block PURPLE_WATER_BLOCK;

    public static FlowableFluid STILL_BROWN_WATER;
    public static FlowableFluid FLOWING_BROWN_WATER;
    public static Block BROWN_WATER_BLOCK;

    public static FlowableFluid STILL_GREEN_WATER;
    public static FlowableFluid FLOWING_GREEN_WATER;
    public static Block GREEN_WATER_BLOCK;

    public static FlowableFluid STILL_RED_WATER;
    public static FlowableFluid FLOWING_RED_WATER;
    public static Block RED_WATER_BLOCK;

    public static FlowableFluid STILL_BLACK_WATER;
    public static FlowableFluid FLOWING_BLACK_WATER;
    public static Block BLACK_WATER_BLOCK;

    public static void register() {
        STILL_WHITE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "white_water"), new WhiteWaterFluid.Still());
        FLOWING_WHITE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_white_water"), new WhiteWaterFluid.Flowing());
        WHITE_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "white_water_block"),
                new FluidBlock(ModFluids.STILL_WHITE_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.WHITE)));

        STILL_ORANGE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "orange_water"), new OrangeWaterFluid.Still());
        FLOWING_ORANGE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_orange_water"), new OrangeWaterFluid.Flowing());
        ORANGE_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "orange_water_block"),
                new FluidBlock(ModFluids.STILL_ORANGE_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.ORANGE)));

        STILL_MAGENTA_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "magenta_water"), new MagentaWaterFluid.Still());
        FLOWING_MAGENTA_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_magenta_water"), new MagentaWaterFluid.Flowing());
        MAGENTA_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "magenta_water_block"),
                new FluidBlock(ModFluids.STILL_MAGENTA_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.MAGENTA)));

        STILL_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "light_blue_water"), new LightBlueWaterFluid.Still());
        FLOWING_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_light_blue_water"), new LightBlueWaterFluid.Flowing());
        LIGHT_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "light_blue_water_block"),
                new FluidBlock(ModFluids.STILL_LIGHT_BLUE_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.LIGHT_BLUE)));

        STILL_YELLOW_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "yellow_water"), new YellowWaterFluid.Still());
        FLOWING_YELLOW_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_yellow_water"), new YellowWaterFluid.Flowing());
        YELLOW_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "yellow_water_block"),
                new FluidBlock(ModFluids.STILL_YELLOW_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.YELLOW)));

        STILL_LIME_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "lime_water"), new LimeWaterFluid.Still());
        FLOWING_LIME_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_lime_water"), new LimeWaterFluid.Flowing());
        LIME_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "lime_water_block"),
                new FluidBlock(ModFluids.STILL_LIME_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.LIME)));

        STILL_PINK_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "pink_water"), new PinkWaterFluid.Still());
        FLOWING_PINK_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_pink_water"), new PinkWaterFluid.Flowing());
        PINK_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "pink_water_block"),
                new FluidBlock(ModFluids.STILL_PINK_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.PINK)));

        STILL_GRAY_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "gray_water"), new GrayWaterFluid.Still());
        FLOWING_GRAY_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_gray_water"), new GrayWaterFluid.Flowing());
        GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "gray_water_block"),
                new FluidBlock(ModFluids.STILL_GRAY_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.GRAY)));

        STILL_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "light_gray_water"), new LightGrayWaterFluid.Still());
        FLOWING_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_light_gray_water"), new LightGrayWaterFluid.Flowing());
        LIGHT_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "light_gray_water_block"),
                new FluidBlock(ModFluids.STILL_LIGHT_GRAY_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.LIGHT_GRAY)));

        STILL_CYAN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "cyan_water"), new CyanWaterFluid.Still());
        FLOWING_CYAN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_cyan_water"), new CyanWaterFluid.Flowing());
        CYAN_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "cyan_water_block"),
                new FluidBlock(ModFluids.STILL_CYAN_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.CYAN)));

        STILL_PURPLE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "purple_water"), new PurpleWaterFluid.Still());
        FLOWING_PURPLE_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_purple_water"), new PurpleWaterFluid.Flowing());
        PURPLE_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "purple_water_block"),
                new FluidBlock(ModFluids.STILL_PURPLE_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.PURPLE)));

        STILL_BROWN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "brown_water"), new BrownWaterFluid.Still());
        FLOWING_BROWN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_brown_water"), new BrownWaterFluid.Flowing());
        BROWN_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "brown_water_block"),
                new FluidBlock(ModFluids.STILL_BROWN_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.BROWN)));

        STILL_GREEN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "green_water"), new GreenWaterFluid.Still());
        FLOWING_GREEN_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_green_water"), new GreenWaterFluid.Flowing());
        GREEN_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "green_water_block"),
                new FluidBlock(ModFluids.STILL_GREEN_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.GREEN)));

        STILL_RED_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "red_water"), new RedWaterFluid.Still());
        FLOWING_RED_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_red_water"), new RedWaterFluid.Flowing());
        RED_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "red_water_block"),
                new FluidBlock(ModFluids.STILL_RED_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.RED)));

        STILL_BLACK_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "black_water"), new BlackWaterFluid.Still());
        FLOWING_BLACK_WATER = Registry.register(Registries.FLUID,
                Identifier.of(LouisOverhaulMod.MOD_ID, "flowing_black_water"), new BlackWaterFluid.Flowing());
        BLACK_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(LouisOverhaulMod.MOD_ID, "black_water_block"),
                new FluidBlock(ModFluids.STILL_BLACK_WATER, AbstractBlock.Settings.copy(Blocks.WATER).mapColor(DyeColor.BLACK)));
    }
}
