package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static final List<Block> COLORED_WATER_BLOCKS = List.of(
            ModFluids.WHITE_WATER_BLOCK,
            ModFluids.ORANGE_WATER_BLOCK,
            ModFluids.MAGENTA_WATER_BLOCK,
            ModFluids.LIGHT_BLUE_WATER_BLOCK,
            ModFluids.YELLOW_WATER_BLOCK,
            ModFluids.LIME_WATER_BLOCK,
            ModFluids.PINK_WATER_BLOCK,
            ModFluids.GRAY_WATER_BLOCK,
            ModFluids.LIGHT_GRAY_WATER_BLOCK,
            ModFluids.CYAN_WATER_BLOCK,
            ModFluids.PURPLE_WATER_BLOCK,
            ModFluids.BROWN_WATER_BLOCK,
            ModFluids.GREEN_WATER_BLOCK,
            ModFluids.RED_WATER_BLOCK,
            ModFluids.BLACK_WATER_BLOCK
    );

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //  blockStateModelGenerator.registerSimpleCubeAll(ModBlocks);
        blockStateModelGenerator.registerStraightRail(ModBlocks.COPPER_RAIL);

        blockStateModelGenerator.registerLantern(ModBlocks.GLOW_LANTERN);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.MYSTIC_ROSE, ModBlocks.POTTED_MYSTIC_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.COBALT_FLOWER, ModBlocks.POTTED_COBALT_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WILTED_POPPY, ModBlocks.POTTED_WILTED_POPPY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SHINY_CORNFLOWER, ModBlocks.POTTED_SHINY_CORNFLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.LAVENDER_DANDELION, ModBlocks.POTTED_LAVENDER_DANDELION, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HEART_FLOWER, ModBlocks.POTTED_HEART_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerLog(ModBlocks.CHILLED_BONE_BLOCK);
        blockStateModelGenerator.registerLog(ModBlocks.TOXIC_BONE_BLOCK);
        blockStateModelGenerator.registerLog(ModBlocks.DECREPIT_BONE_BLOCK);

        BlockStateModelGenerator.BlockTexturePool endstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.END_STONE);
        endstonePool.stairs(ModBlocks.END_STONE_STAIRS);
        endstonePool.slab(ModBlocks.END_STONE_SLAB);
        endstonePool.wall(ModBlocks.END_STONE_WALL);
        endstonePool.pressurePlate(ModBlocks.END_STONE_PRESSURE_PLATE);
        endstonePool.button(ModBlocks.END_STONE_BUTTON);

        BlockStateModelGenerator.BlockTexturePool roseQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ROSE_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ROSE_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.ROSE_QUARTZ_PILLAR).log(ModBlocks.ROSE_QUARTZ_PILLAR).wood(ModBlocks.ROSE_QUARTZ_COLUMN);
        roseQuartzPool.stairs(ModBlocks.ROSE_QUARTZ_STAIRS);
        roseQuartzPool.slab(ModBlocks.ROSE_QUARTZ_SLAB);
        roseQuartzPool.wall(ModBlocks.ROSE_QUARTZ_WALL);

        BlockStateModelGenerator.BlockTexturePool lavenderQuartz = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAVENDER_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_LAVENDER_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.LAVENDER_QUARTZ_PILLAR).log(ModBlocks.LAVENDER_QUARTZ_PILLAR).wood(ModBlocks.LAVENDER_QUARTZ_COLUMN);
        lavenderQuartz.stairs(ModBlocks.LAVENDER_QUARTZ_STAIRS);
        lavenderQuartz.slab(ModBlocks.LAVENDER_QUARTZ_SLAB);
        lavenderQuartz.wall(ModBlocks.LAVENDER_QUARTZ_WALL);

        BlockStateModelGenerator.BlockTexturePool quartzBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.QUARTZ_BRICKS);
        quartzBricksPool.stairs(ModBlocks.QUARTZ_BRICKS_STAIRS);
        quartzBricksPool.slab(ModBlocks.QUARTZ_BRICKS_SLAB);
        quartzBricksPool.wall(ModBlocks.QUARTZ_BRICKS_WALL);

        BlockStateModelGenerator.BlockTexturePool calcitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CALCITE);
        calcitePool.stairs(ModBlocks.CALCITE_STAIRS);
        calcitePool.slab(ModBlocks.CALCITE_SLAB);
        calcitePool.wall(ModBlocks.CALCITE_WALL);

        BlockStateModelGenerator.BlockTexturePool sbasaltPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_BASALT);
        sbasaltPool.stairs(ModBlocks.SMOOTH_BASALT_STAIRS);
        sbasaltPool.slab(ModBlocks.SMOOTH_BASALT_SLAB);
        sbasaltPool.wall(ModBlocks.SMOOTH_BASALT_WALL);

        BlockStateModelGenerator.BlockTexturePool dripstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DRIPSTONE_BLOCK);
        dripstonePool.stairs(ModBlocks.DRIPSTONE_STAIRS);
        dripstonePool.slab(ModBlocks.DRIPSTONE_SLAB);
        dripstonePool.wall(ModBlocks.DRIPSTONE_WALL);

        BlockStateModelGenerator.BlockTexturePool bmosaicPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BAMBOO_MOSAIC);
        bmosaicPool.wall(ModBlocks.BAMBOO_MOSAIC_WALL);
        BlockStateModelGenerator.BlockTexturePool purpurPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PURPUR_BLOCK);
        purpurPool.wall(ModBlocks.PURPUR_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_QUARTZ);
        smoothQuartzPool.wall(ModBlocks.SMOOTH_QUARTZ_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedAndesitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE);
        polishedAndesitePool.wall(ModBlocks.POLISHED_ANDESITE_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedGranitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE);
        polishedGranitePool.wall(ModBlocks.POLISHED_GRANITE_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedDioritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE);
        polishedDioritePool.wall(ModBlocks.POLISHED_DIORITE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothRedSandstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_RED_SANDSTONE);
        smoothRedSandstonePool.wall(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothSandstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_SANDSTONE);
        smoothSandstonePool.wall(ModBlocks.SMOOTH_SANDSTONE_WALL);
        BlockStateModelGenerator.BlockTexturePool prismarineBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PRISMARINE_BRICKS);
        prismarineBricksPool.wall(ModBlocks.PRISMARINE_BRICKS_WALL);
        BlockStateModelGenerator.BlockTexturePool prismarinePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PRISMARINE);
        prismarinePool.wall(ModBlocks.PRISMARINE_WALL);
        BlockStateModelGenerator.BlockTexturePool darkPrismarinePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DARK_PRISMARINE);
        darkPrismarinePool.wall(ModBlocks.DARK_PRISMARINE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_STONE);
        smoothStonePool.wall(ModBlocks.SMOOTH_STONE_WALL);
        smoothStonePool.stairs(ModBlocks.SMOOTH_STONE_STAIRS);
        BlockStateModelGenerator.BlockTexturePool stonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.STONE);
        stonePool.wall(ModBlocks.STONE_WALL);
        BlockStateModelGenerator.BlockTexturePool cutRedSandstone = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_RED_SANDSTONE);
        cutRedSandstone.stairs(ModBlocks.CUT_RED_SANDSTONE_STAIRS);
        BlockStateModelGenerator.BlockTexturePool cutSandstone = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_SANDSTONE);
        cutSandstone.stairs(ModBlocks.CUT_SANDSTONE_STAIRS);

        BlockStateModelGenerator.BlockTexturePool redNetherBricks = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        redNetherBricks.fence(ModBlocks.RED_NETHER_BRICK_FENCE);

        // Manually load Wall models for Quartz, Cut Sandstone & Cut Red Sandstone

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_END_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_MUD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_MOSSY_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_TUFF_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_PRISMARINE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_QUARTZ_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DARK_PRISMARINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PURPUR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_END_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_MUD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PRISMARINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PRISMARINE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_MOSSY_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_QUARTZ_BRICKS);

        for (DyeColor color : DyeColor.values()) {
            BlockStateModelGenerator.BlockTexturePool pool = blockStateModelGenerator.registerCubeAllModelTexturePool(Registries.BLOCK.get(Identifier.ofVanilla(color + "_concrete")));
            pool.stairs(Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color + "_concrete_stairs")));
            pool.slab(Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color + "_concrete_slab")));
            pool.wall(Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color + "_concrete_wall")));
        }

        for (var block : COLORED_WATER_BLOCKS) blockStateModelGenerator.registerStateWithModelReference(block, Blocks.WATER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PURIFIED_WATER_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILLED_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOXIC_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DECREPIT_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANDY_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAT_FANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERMITE_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.DECAYING_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTION_POUCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SADDLED_GOAT_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RECALL_CLOCK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LLAMAS_SPIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.FISH_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILLED_BONE_MEAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOXIC_BONE_MEAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.DECREPIT_BONE_MEAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPYREAN_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BEAR_CLAW, Models.HANDHELD_ROD);

        itemModelGenerator.register(ModItems.WHITE_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGENTA_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHT_BLUE_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIME_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAY_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHT_GRAY_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CYAN_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURPLE_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROWN_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_WATER_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ModItems.LIGHT_GRAY_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAY_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROWN_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIME_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.CYAN_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHT_BLUE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURPLE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGENTA_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_NAME_TAG, Models.GENERATED);

        itemModelGenerator.register(ModItems.WISP_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIOACTIVE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRAGRANT_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.AQUAMARINE_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASHEN_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLOSSOM_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATACLYSM_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.IMMOLATION_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRIDESCENT_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRIMORDIAL_NAME_TAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOLCANIC_NAME_TAG, Models.GENERATED);

        itemModelGenerator.register(ModItems.BROWN_BEAR_SPAWN_EGG, new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        // Pet Recovery Compass Generated Manually
        // Azurite Generated Manually
        // Advanced Arrow Generated Manually
    }
}