package bunny.lib.fluid;

import bunny.lib.BunnyLib;
import bunny.lib.fluid.colors.*;
import bunny.lib.fluid.colors.*;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModFluids {
    public static final FlowableFluid STILL_WHITE_WATER =
            registerFluid("white_water", WhiteWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_WHITE_WATER =
            registerFluid("flowing_white_water", WhiteWaterFluid.Flowing::new);
    public static final Block WHITE_WATER_BLOCK =
            registerFluidBlock("white_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_WHITE_WATER,
                            properties.mapColor(DyeColor.WHITE).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_ORANGE_WATER =
            registerFluid("orange_water", OrangeWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_ORANGE_WATER =
            registerFluid("flowing_orange_water", OrangeWaterFluid.Flowing::new);
    public static final Block ORANGE_WATER_BLOCK =
            registerFluidBlock("orange_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_ORANGE_WATER,
                            properties.mapColor(DyeColor.ORANGE).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_MAGENTA_WATER =
            registerFluid("magenta_water", MagentaWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_MAGENTA_WATER =
            registerFluid("flowing_magenta_water", MagentaWaterFluid.Flowing::new);
    public static final Block MAGENTA_WATER_BLOCK =
            registerFluidBlock("magenta_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_MAGENTA_WATER,
                            properties.mapColor(DyeColor.MAGENTA).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_LIGHT_BLUE_WATER =
            registerFluid("light_blue_water", LightBlueWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_LIGHT_BLUE_WATER =
            registerFluid("flowing_light_blue_water", LightBlueWaterFluid.Flowing::new);
    public static final Block LIGHT_BLUE_WATER_BLOCK =
            registerFluidBlock("light_blue_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_LIGHT_BLUE_WATER,
                            properties.mapColor(DyeColor.LIGHT_BLUE).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_YELLOW_WATER =
            registerFluid("yellow_water", YellowWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_YELLOW_WATER =
            registerFluid("flowing_yellow_water", YellowWaterFluid.Flowing::new);
    public static final Block YELLOW_WATER_BLOCK =
            registerFluidBlock("yellow_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_YELLOW_WATER,
                            properties.mapColor(DyeColor.YELLOW).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_LIME_WATER =
            registerFluid("lime_water", LimeWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_LIME_WATER =
            registerFluid("flowing_lime_water", LimeWaterFluid.Flowing::new);
    public static final Block LIME_WATER_BLOCK =
            registerFluidBlock("lime_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_LIME_WATER,
                            properties.mapColor(DyeColor.LIME).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_PINK_WATER =
            registerFluid("pink_water", PinkWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_PINK_WATER =
            registerFluid("flowing_pink_water", PinkWaterFluid.Flowing::new);
    public static final Block PINK_WATER_BLOCK =
            registerFluidBlock("pink_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_PINK_WATER,
                            properties.mapColor(DyeColor.PINK).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_GRAY_WATER =
            registerFluid("gray_water", GrayWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_GRAY_WATER =
            registerFluid("flowing_gray_water", GrayWaterFluid.Flowing::new);
    public static final Block GRAY_WATER_BLOCK =
            registerFluidBlock("gray_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_GRAY_WATER,
                            properties.mapColor(DyeColor.GRAY).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_LIGHT_GRAY_WATER =
            registerFluid("light_gray_water", LightGrayWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_LIGHT_GRAY_WATER =
            registerFluid("flowing_light_gray_water", LightGrayWaterFluid.Flowing::new);
    public static final Block LIGHT_GRAY_WATER_BLOCK =
            registerFluidBlock("light_gray_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_LIGHT_GRAY_WATER,
                            properties.mapColor(DyeColor.LIGHT_GRAY).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_CYAN_WATER =
            registerFluid("cyan_water", CyanWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_CYAN_WATER =
            registerFluid("flowing_cyan_water", CyanWaterFluid.Flowing::new);
    public static final Block CYAN_WATER_BLOCK =
            registerFluidBlock("cyan_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_CYAN_WATER,
                            properties.mapColor(DyeColor.CYAN).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_PURPLE_WATER =
            registerFluid("purple_water", PurpleWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_PURPLE_WATER =
            registerFluid("flowing_purple_water", PurpleWaterFluid.Flowing::new);
    public static final Block PURPLE_WATER_BLOCK =
            registerFluidBlock("purple_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_PURPLE_WATER,
                            properties.mapColor(DyeColor.PURPLE).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_BROWN_WATER =
            registerFluid("brown_water", BrownWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_BROWN_WATER =
            registerFluid("flowing_brown_water", BrownWaterFluid.Flowing::new);
    public static final Block BROWN_WATER_BLOCK =
            registerFluidBlock("brown_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_BROWN_WATER,
                            properties.mapColor(DyeColor.BROWN).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_GREEN_WATER =
            registerFluid("green_water", GreenWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_GREEN_WATER =
            registerFluid("flowing_green_water", GreenWaterFluid.Flowing::new);
    public static final Block GREEN_WATER_BLOCK =
            registerFluidBlock("green_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_GREEN_WATER,
                            properties.mapColor(DyeColor.GREEN).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_RED_WATER =
            registerFluid("red_water", RedWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_RED_WATER =
            registerFluid("flowing_red_water", RedWaterFluid.Flowing::new);
    public static final Block RED_WATER_BLOCK =
            registerFluidBlock("red_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_RED_WATER,
                            properties.mapColor(DyeColor.RED).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

    public static final FlowableFluid STILL_BLACK_WATER =
            registerFluid("black_water", BlackWaterFluid.Still::new);
    public static final FlowableFluid FLOWING_BLACK_WATER =
            registerFluid("flowing_black_water", BlackWaterFluid.Flowing::new);
    public static final Block BLACK_WATER_BLOCK =
            registerFluidBlock("black_water_block",
                    properties -> new FluidBlock(ModFluids.STILL_BLACK_WATER,
                            properties.mapColor(DyeColor.BLACK).replaceable().noCollision()
                                    .strength(100.0F).pistonBehavior(PistonBehavior.DESTROY)
                                    .dropsNothing().liquid()
                                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));


    public static void register() {

    }

    private static <T extends Fluid> T registerFluid(String name, Supplier<T> fluidSupplier) {
        return Registry.register(Registries.FLUID,
                Identifier.of(BunnyLib.MOD_ID, name),
                fluidSupplier.get());
    }

    private static Block registerFluidBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BunnyLib.MOD_ID, name))));
        return Registry.register(Registries.BLOCK,
                Identifier.of(BunnyLib.MOD_ID, name),
                toRegister);
    }
}
