package bunny.lib.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import bunny.lib.block.ModBlocks;
import bunny.lib.tags.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // .add(ModBlocks.)
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_AMETHYST_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL);

        getOrCreateTagBuilder(BlockTags.CAULDRONS)
                .add(ModBlocks.DRAGON_BREATH_CAULDRON)
                .add(ModBlocks.HONEY_CAULDRON)
                .add(ModBlocks.COLORED_WATER_CAULDRON);

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.MYSTIC_ROSE)
                .add(ModBlocks.LAVENDER_DANDELION)
                .add(ModBlocks.WILTED_POPPY)
                .add(ModBlocks.HEART_FLOWER)
                .add(ModBlocks.SHINY_CORNFLOWER)
                .add(ModBlocks.COBALT_FLOWER);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.MYSTIC_ROSE)
                .add(ModBlocks.LAVENDER_DANDELION)
                .add(ModBlocks.WILTED_POPPY)
                .add(ModBlocks.HEART_FLOWER)
                .add(ModBlocks.SHINY_CORNFLOWER)
                .add(ModBlocks.COBALT_FLOWER);
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_MYSTIC_ROSE)
                .add(ModBlocks.POTTED_LAVENDER_DANDELION)
                .add(ModBlocks.POTTED_WILTED_POPPY)
                .add(ModBlocks.POTTED_HEART_FLOWER)
                .add(ModBlocks.POTTED_SHINY_CORNFLOWER)
                .add(ModBlocks.POTTED_COBALT_FLOWER);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.BAMBOO_MOSAIC_WALL);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(ModBlocks.END_STONE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.STONE_BUTTONS)
                .add(ModBlocks.END_STONE_BUTTON);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.DRAGON_BREATH_CAULDRON)
                .add(ModBlocks.COLORED_WATER_CAULDRON)
                .add(ModBlocks.HONEY_CAULDRON)

                .add(ModBlocks.COPPER_RAIL)

                .add(ModBlocks.INFUSED_BONE_BLOCK)

                .add(ModBlocks.END_STONE_STAIRS)
                .add(ModBlocks.END_STONE_WALL)
                .add(ModBlocks.END_STONE_BUTTON)
                .add(ModBlocks.END_STONE_PRESSURE_PLATE)
                .add(ModBlocks.END_STONE_SLAB)

                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_ROSE_QUARTZ)
                .add(ModBlocks.ROSE_QUARTZ_PILLAR)
                .add(ModBlocks.ROSE_QUARTZ_COLUMN)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)

                .add(ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_LAVENDER_QUARTZ)
                .add(ModBlocks.LAVENDER_QUARTZ_PILLAR)
                .add(ModBlocks.LAVENDER_QUARTZ_COLUMN)
                .add(ModBlocks.LAVENDER_QUARTZ_SLAB)
                .add(ModBlocks.LAVENDER_QUARTZ_STAIRS)
                .add(ModBlocks.LAVENDER_QUARTZ_WALL)

                .add(ModBlocks.CRACKED_QUARTZ_BRICKS)
                .add(ModBlocks.CRACKED_PRISMARINE_BRICKS)
                .add(ModBlocks.CRACKED_TUFF_BRICKS)
                .add(ModBlocks.CRACKED_RED_NETHER_BRICKS)
                .add(ModBlocks.CRACKED_BRICKS)
                .add(ModBlocks.CRACKED_MUD_BRICKS)
                .add(ModBlocks.CRACKED_END_STONE_BRICKS)
                .add(ModBlocks.CRACKED_MOSSY_STONE_BRICKS)

                .add(ModBlocks.QUARTZ_BRICKS_SLAB)
                .add(ModBlocks.QUARTZ_BRICKS_STAIRS)
                .add(ModBlocks.QUARTZ_BRICKS_WALL)

                .add(ModBlocks.CALCITE_SLAB)
                .add(ModBlocks.CALCITE_STAIRS)
                .add(ModBlocks.CALCITE_WALL)

                .add(ModBlocks.SMOOTH_BASALT_SLAB)
                .add(ModBlocks.SMOOTH_BASALT_STAIRS)
                .add(ModBlocks.SMOOTH_BASALT_WALL)

                .add(ModBlocks.DRIPSTONE_SLAB)
                .add(ModBlocks.DRIPSTONE_STAIRS)
                .add(ModBlocks.DRIPSTONE_WALL)

                .add(ModBlocks.SMOOTH_STONE_STAIRS)
                .add(ModBlocks.CUT_RED_SANDSTONE_STAIRS)
                .add(ModBlocks.CUT_SANDSTONE_STAIRS)

                .add(ModBlocks.PURPUR_WALL)
                .add(ModBlocks.QUARTZ_WALL)
                .add(ModBlocks.SMOOTH_QUARTZ_WALL)
                .add(ModBlocks.POLISHED_ANDESITE_WALL)
                .add(ModBlocks.POLISHED_GRANITE_WALL)
                .add(ModBlocks.POLISHED_DIORITE_WALL)
                .add(ModBlocks.CUT_RED_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL)
                .add(ModBlocks.CUT_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_SANDSTONE_WALL)
                .add(ModBlocks.PRISMARINE_BRICKS_WALL)
                .add(ModBlocks.PRISMARINE_WALL)
                .add(ModBlocks.DARK_PRISMARINE_WALL)
                .add(ModBlocks.STONE_WALL)
                .add(ModBlocks.SMOOTH_STONE_WALL)

                .add(ModBlocks.CHISELED_DARK_PRISMARINE)
                .add(ModBlocks.CHISELED_PURPUR)
                .add(ModBlocks.CHISELED_DEEPSLATE_BRICKS)
                .add(ModBlocks.CHISELED_END_STONE_BRICKS)
                .add(ModBlocks.CHISELED_MUD_BRICKS)
                .add(ModBlocks.CHISELED_RED_NETHER_BRICKS)
                .add(ModBlocks.CHISELED_MOSSY_STONE_BRICKS)
                .add(ModBlocks.CHISELED_PRISMARINE)
                .add(ModBlocks.CHISELED_PRISMARINE_BRICKS)
                .add(ModBlocks.CHISELED_BRICKS)
                .add(ModBlocks.CHISELED_QUARTZ_BRICKS)

                .add(ModBlocks.RED_NETHER_BRICK_FENCE)


                .add(ModBlocks.WHITE_CONCRETE_STAIRS)
                .add(ModBlocks.ORANGE_CONCRETE_STAIRS)
                .add(ModBlocks.MAGENTA_CONCRETE_STAIRS)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_STAIRS)
                .add(ModBlocks.YELLOW_CONCRETE_STAIRS)
                .add(ModBlocks.LIME_CONCRETE_STAIRS)
                .add(ModBlocks.PINK_CONCRETE_STAIRS)
                .add(ModBlocks.GRAY_CONCRETE_STAIRS)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_STAIRS)
                .add(ModBlocks.CYAN_CONCRETE_STAIRS)
                .add(ModBlocks.PURPLE_CONCRETE_STAIRS)
                .add(ModBlocks.BLUE_CONCRETE_STAIRS)
                .add(ModBlocks.BROWN_CONCRETE_STAIRS)
                .add(ModBlocks.GREEN_CONCRETE_STAIRS)
                .add(ModBlocks.RED_CONCRETE_STAIRS)
                .add(ModBlocks.BLACK_CONCRETE_STAIRS)

                .add(ModBlocks.WHITE_CONCRETE_SLAB)
                .add(ModBlocks.ORANGE_CONCRETE_SLAB)
                .add(ModBlocks.MAGENTA_CONCRETE_SLAB)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_SLAB)
                .add(ModBlocks.YELLOW_CONCRETE_SLAB)
                .add(ModBlocks.LIME_CONCRETE_SLAB)
                .add(ModBlocks.PINK_CONCRETE_SLAB)
                .add(ModBlocks.GRAY_CONCRETE_SLAB)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_SLAB)
                .add(ModBlocks.CYAN_CONCRETE_SLAB)
                .add(ModBlocks.PURPLE_CONCRETE_SLAB)
                .add(ModBlocks.BLUE_CONCRETE_SLAB)
                .add(ModBlocks.BROWN_CONCRETE_SLAB)
                .add(ModBlocks.GREEN_CONCRETE_SLAB)
                .add(ModBlocks.RED_CONCRETE_SLAB)
                .add(ModBlocks.BLACK_CONCRETE_SLAB)

                .add(ModBlocks.WHITE_CONCRETE_WALL)
                .add(ModBlocks.ORANGE_CONCRETE_WALL)
                .add(ModBlocks.MAGENTA_CONCRETE_WALL)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_WALL)
                .add(ModBlocks.YELLOW_CONCRETE_WALL)
                .add(ModBlocks.LIME_CONCRETE_WALL)
                .add(ModBlocks.PINK_CONCRETE_WALL)
                .add(ModBlocks.GRAY_CONCRETE_WALL)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_WALL)
                .add(ModBlocks.CYAN_CONCRETE_WALL)
                .add(ModBlocks.PURPLE_CONCRETE_WALL)
                .add(ModBlocks.BLUE_CONCRETE_WALL)
                .add(ModBlocks.BROWN_CONCRETE_WALL)
                .add(ModBlocks.GREEN_CONCRETE_WALL)
                .add(ModBlocks.RED_CONCRETE_WALL)
                .add(ModBlocks.BLACK_CONCRETE_WALL);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);

        getOrCreateTagBuilder(BlockTags.RAILS)
                .add(ModBlocks.COPPER_RAIL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.WHITE_CONCRETE_STAIRS)
                .add(ModBlocks.ORANGE_CONCRETE_STAIRS)
                .add(ModBlocks.MAGENTA_CONCRETE_STAIRS)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_STAIRS)
                .add(ModBlocks.YELLOW_CONCRETE_STAIRS)
                .add(ModBlocks.LIME_CONCRETE_STAIRS)
                .add(ModBlocks.PINK_CONCRETE_STAIRS)
                .add(ModBlocks.GRAY_CONCRETE_STAIRS)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_STAIRS)
                .add(ModBlocks.CYAN_CONCRETE_STAIRS)
                .add(ModBlocks.PURPLE_CONCRETE_STAIRS)
                .add(ModBlocks.BLUE_CONCRETE_STAIRS)
                .add(ModBlocks.BROWN_CONCRETE_STAIRS)
                .add(ModBlocks.GREEN_CONCRETE_STAIRS)
                .add(ModBlocks.RED_CONCRETE_STAIRS)
                .add(ModBlocks.BLACK_CONCRETE_STAIRS)

                .add(ModBlocks.CUT_RED_SANDSTONE_STAIRS)
                .add(ModBlocks.CUT_SANDSTONE_STAIRS)
                .add(ModBlocks.DRIPSTONE_STAIRS)
                .add(ModBlocks.END_STONE_STAIRS)
                .add(ModBlocks.SMOOTH_STONE_STAIRS)
                .add(ModBlocks.SMOOTH_BASALT_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.LAVENDER_QUARTZ_STAIRS)
                .add(ModBlocks.QUARTZ_BRICKS_STAIRS)
                .add(ModBlocks.CALCITE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.WHITE_CONCRETE_SLAB)
                .add(ModBlocks.ORANGE_CONCRETE_SLAB)
                .add(ModBlocks.MAGENTA_CONCRETE_SLAB)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_SLAB)
                .add(ModBlocks.YELLOW_CONCRETE_SLAB)
                .add(ModBlocks.LIME_CONCRETE_SLAB)
                .add(ModBlocks.PINK_CONCRETE_SLAB)
                .add(ModBlocks.GRAY_CONCRETE_SLAB)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_SLAB)
                .add(ModBlocks.CYAN_CONCRETE_SLAB)
                .add(ModBlocks.PURPLE_CONCRETE_SLAB)
                .add(ModBlocks.BLUE_CONCRETE_SLAB)
                .add(ModBlocks.BROWN_CONCRETE_SLAB)
                .add(ModBlocks.GREEN_CONCRETE_SLAB)
                .add(ModBlocks.RED_CONCRETE_SLAB)
                .add(ModBlocks.BLACK_CONCRETE_SLAB)

                .add(ModBlocks.DRIPSTONE_SLAB)
                .add(ModBlocks.END_STONE_SLAB)
                .add(ModBlocks.SMOOTH_BASALT_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.LAVENDER_QUARTZ_SLAB)
                .add(ModBlocks.QUARTZ_BRICKS_SLAB)
                .add(ModBlocks.CALCITE_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)
                .add(ModBlocks.LAVENDER_QUARTZ_WALL)

                .add(ModBlocks.QUARTZ_BRICKS_WALL)
                .add(ModBlocks.CALCITE_WALL)
                .add(ModBlocks.DRIPSTONE_WALL)
                .add(ModBlocks.SMOOTH_BASALT_WALL)
                .add(ModBlocks.END_STONE_WALL)

                .add(ModBlocks.PURPUR_WALL)
                .add(ModBlocks.QUARTZ_WALL)
                .add(ModBlocks.SMOOTH_QUARTZ_WALL)
                .add(ModBlocks.POLISHED_ANDESITE_WALL)
                .add(ModBlocks.POLISHED_GRANITE_WALL)
                .add(ModBlocks.POLISHED_DIORITE_WALL)
                .add(ModBlocks.CUT_RED_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL)
                .add(ModBlocks.CUT_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_SANDSTONE_WALL)
                .add(ModBlocks.PRISMARINE_BRICKS_WALL)
                .add(ModBlocks.PRISMARINE_WALL)
                .add(ModBlocks.DARK_PRISMARINE_WALL)
                .add(ModBlocks.STONE_WALL)
                .add(ModBlocks.SMOOTH_STONE_WALL)
                .add(ModBlocks.BAMBOO_MOSAIC_WALL)

                .add(ModBlocks.WHITE_CONCRETE_WALL)
                .add(ModBlocks.ORANGE_CONCRETE_WALL)
                .add(ModBlocks.MAGENTA_CONCRETE_WALL)
                .add(ModBlocks.LIGHT_BLUE_CONCRETE_WALL)
                .add(ModBlocks.YELLOW_CONCRETE_WALL)
                .add(ModBlocks.LIME_CONCRETE_WALL)
                .add(ModBlocks.PINK_CONCRETE_WALL)
                .add(ModBlocks.GRAY_CONCRETE_WALL)
                .add(ModBlocks.LIGHT_GRAY_CONCRETE_WALL)
                .add(ModBlocks.CYAN_CONCRETE_WALL)
                .add(ModBlocks.PURPLE_CONCRETE_WALL)
                .add(ModBlocks.BLUE_CONCRETE_WALL)
                .add(ModBlocks.BROWN_CONCRETE_WALL)
                .add(ModBlocks.GREEN_CONCRETE_WALL)
                .add(ModBlocks.RED_CONCRETE_WALL)
                .add(ModBlocks.BLACK_CONCRETE_WALL);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE);

    }
}
