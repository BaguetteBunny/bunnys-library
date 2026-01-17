package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.recipe.SawmillRecipe;
import net.louis.overhaulmod.recipe.SawmillRecipeJsonBuilder;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ModRecipeProvider extends FabricRecipeProvider {
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture;

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        this.registriesFuture = registriesFuture;
    }

    public static void offerSawmillingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input, int count) {
        SawmillRecipeJsonBuilder.createSawmilling(Ingredient.ofItems(input), category, output, count)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, convertBetween(output, input) + "_sawmilling");
    }

    public static void offerAllWoodSawmillingRecipes(RecipeExporter exporter) {
        WoodTypeData[] woods = new WoodTypeData[] {
                new WoodTypeData("oak", Blocks.OAK_PLANKS, Blocks.OAK_SLAB, Blocks.OAK_STAIRS, Blocks.OAK_TRAPDOOR,
                        Blocks.OAK_DOOR, Blocks.OAK_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_SIGN,
                        Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Items.STICK),
                new WoodTypeData("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_TRAPDOOR,
                        Blocks.SPRUCE_DOOR, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_SIGN,
                        Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Items.STICK),
                new WoodTypeData("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, Blocks.BIRCH_STAIRS, Blocks.BIRCH_TRAPDOOR,
                        Blocks.BIRCH_DOOR, Blocks.BIRCH_BUTTON, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_SIGN,
                        Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Items.STICK),
                new WoodTypeData("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_TRAPDOOR,
                        Blocks.JUNGLE_DOOR, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_SIGN,
                        Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Items.STICK),
                new WoodTypeData("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, Blocks.ACACIA_STAIRS, Blocks.ACACIA_TRAPDOOR,
                        Blocks.ACACIA_DOOR, Blocks.ACACIA_BUTTON, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_SIGN,
                        Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Items.STICK),
                new WoodTypeData("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_TRAPDOOR,
                        Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_SIGN,
                        Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Items.STICK),
                new WoodTypeData("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_TRAPDOOR,
                        Blocks.MANGROVE_DOOR, Blocks.MANGROVE_BUTTON, Blocks.MANGROVE_PRESSURE_PLATE, Blocks.MANGROVE_SIGN,
                        Blocks.MANGROVE_FENCE, Blocks.MANGROVE_FENCE_GATE, Items.STICK),
                new WoodTypeData("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, Blocks.CHERRY_STAIRS, Blocks.CHERRY_TRAPDOOR,
                        Blocks.CHERRY_DOOR, Blocks.CHERRY_BUTTON, Blocks.CHERRY_PRESSURE_PLATE, Blocks.CHERRY_SIGN,
                        Blocks.CHERRY_FENCE, Blocks.CHERRY_FENCE_GATE, Items.STICK),
                new WoodTypeData("bamboo", Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_TRAPDOOR,
                        Blocks.BAMBOO_DOOR, Blocks.BAMBOO_BUTTON, Blocks.BAMBOO_PRESSURE_PLATE, Blocks.BAMBOO_SIGN,
                        Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, Items.STICK),
                new WoodTypeData("bamboo_mosaic", Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_TRAPDOOR,
                        Blocks.BAMBOO_DOOR, Blocks.BAMBOO_BUTTON, Blocks.BAMBOO_PRESSURE_PLATE, Blocks.BAMBOO_SIGN,
                        Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, Items.STICK),
                new WoodTypeData("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_TRAPDOOR,
                        Blocks.CRIMSON_DOOR, Blocks.CRIMSON_BUTTON, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_SIGN,
                        Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE, Items.STICK),
                new WoodTypeData("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, Blocks.WARPED_STAIRS, Blocks.WARPED_TRAPDOOR,
                        Blocks.WARPED_DOOR, Blocks.WARPED_BUTTON, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_SIGN,
                        Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE, Items.STICK)
        };

        for (WoodTypeData wood : woods) {
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.stickOutput, wood.planks, 2);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.slab, wood.planks, 2);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.stairs, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.trapdoor, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.pressurePlate, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.door, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.button, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.sign, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.fence, wood.planks, 1);
            offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, wood.fenceGate, wood.planks, 1);
        }

        // Bamboo
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_PLANKS, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_MOSAIC_STAIRS, Blocks.BAMBOO_PLANKS, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_MOSAIC_SLAB, Blocks.BAMBOO_PLANKS, 2);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_PLANKS, 1);

        // Stripped Logs & Wood
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_LOG, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM, 1);

        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CHERRY_WOOD, Blocks.CHERRY_WOOD, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE, 1);
        offerSawmillingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE, 1);
    }

    private static class WoodTypeData {
        final String name;
        final ItemConvertible planks, slab, stairs, trapdoor, door, button, pressurePlate, sign, fence, fenceGate, stickOutput;

        public WoodTypeData(String name, ItemConvertible planks, ItemConvertible slab, ItemConvertible stairs,
                            ItemConvertible trapdoor, ItemConvertible door, ItemConvertible button,
                            ItemConvertible pressurePlate, ItemConvertible sign, ItemConvertible fence,
                            ItemConvertible fenceGate, ItemConvertible stickOutput) {
            this.name = name;
            this.planks = planks;
            this.slab = slab;
            this.stairs = stairs;
            this.trapdoor = trapdoor;
            this.door = door;
            this.button = button;
            this.pressurePlate = pressurePlate;
            this.sign = sign;
            this.fence = fence;
            this.fenceGate = fenceGate;
            this.stickOutput = stickOutput;
        }
    }

    private static ItemStack createPaintingStack(RegistryEntry<PaintingVariant> variantEntry, RegistryWrapper.WrapperLookup registryLookup) {
        RegistryOps<NbtElement> ops = registryLookup.getOps(NbtOps.INSTANCE);

        NbtComponent nbtComponent = NbtComponent.DEFAULT
                .with(ops, PaintingEntity.VARIANT_MAP_CODEC, variantEntry)
                .getOrThrow() // DataResult -> either codec result or throw
                .apply(nbt -> nbt.putString("id", "minecraft:painting")); // vanilla adds id = minecraft:painting

        ItemStack stack = new ItemStack(Items.PAINTING);
        stack.set(DataComponentTypes.ENTITY_DATA, nbtComponent);
        return stack;
    }

    private static Identifier idOfPaintingEntry(RegistryEntry<PaintingVariant> entry) {
        Optional<RegistryKey<PaintingVariant>> key = entry.getKey();
        Identifier variantId = key.map(k -> k.getValue()).orElseGet(() -> Identifier.of("minecraft", "unknown_painting"));
        return Identifier.of(LouisOverhaulMod.MOD_ID, "painting_to_" + variantId.getPath() + "_sawmilling");
    }

    private static void offerAllPaintingVariantSawmillRecipes(RecipeExporter exporter, RegistryWrapper.WrapperLookup registryLookup) {
        RegistryWrapper.Impl<PaintingVariant> paintingRegistry = registryLookup.getWrapperOrThrow(RegistryKeys.PAINTING_VARIANT);
        paintingRegistry.streamEntries()
                .forEach(variantEntry -> {
                    ItemStack outStack = createPaintingStack(variantEntry, registryLookup);

                    SawmillRecipe recipe = new SawmillRecipe(
                            "",
                            Ingredient.ofItems(Items.PAINTING),
                            outStack
                    );

                    Advancement.Builder adv = Advancement.Builder.create()
                            .criterion("has_painting", RecipeUnlockedCriterion.create(idOfPaintingEntry(variantEntry)));
                    exporter.accept(
                            idOfPaintingEntry(variantEntry),
                            recipe,
                            adv.build(idOfPaintingEntry(variantEntry).withPrefixedPath("recipes/" + RecipeCategory.DECORATIONS.getName()))
                    );
                });
    }

    private static void offerStairRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private static void offerStairSlabWallRecipe(RecipeExporter exporter, ItemConvertible[] output, ItemConvertible input) {
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output[2], input);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output[1], input);
        offerStairRecipe(exporter, output[0], input);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // SAW MILLING
        offerAllWoodSawmillingRecipes(exporter);
        try {
            offerAllPaintingVariantSawmillRecipes(exporter, this.registriesFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // STONE CUTTING
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_DEEPSLATE, Blocks.DEEPSLATE_TILES, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_BRICKS, Blocks.COBBLED_DEEPSLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_SLAB, Blocks.END_STONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_STAIRS, Blocks.END_STONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_WALL, Blocks.END_STONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS, Blocks.END_STONE, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ROSE_QUARTZ, ModBlocks.ROSE_QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_STAIRS, ModBlocks.ROSE_QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_SLAB, ModBlocks.ROSE_QUARTZ_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_WALL, ModBlocks.ROSE_QUARTZ_BRICKS, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LAVENDER_QUARTZ, ModBlocks.LAVENDER_QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_STAIRS, ModBlocks.LAVENDER_QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_SLAB, ModBlocks.LAVENDER_QUARTZ_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_WALL, ModBlocks.LAVENDER_QUARTZ_BRICKS, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_SLAB, Blocks.QUARTZ_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_STAIRS, Blocks.QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_WALL, Blocks.QUARTZ_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_SLAB, Blocks.QUARTZ_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_STAIRS, Blocks.QUARTZ_BLOCK, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_WALL, Blocks.QUARTZ_BLOCK, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_STAIRS, Blocks.CALCITE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_WALL, Blocks.CALCITE, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_WALL, Blocks.DRIPSTONE_BLOCK, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_WALL, Blocks.QUARTZ_BLOCK, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_QUARTZ_WALL, Blocks.SMOOTH_QUARTZ, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL, Blocks.SMOOTH_RED_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL, Blocks.SMOOTH_SANDSTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICKS_WALL, Blocks.PRISMARINE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_WALL, Blocks.PRISMARINE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL, Blocks.STONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DARK_PRISMARINE, Blocks.DARK_PRISMARINE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS, Blocks.END_STONE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, Blocks.MUD_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE, Blocks.PRISMARINE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PURPUR, Blocks.PURPUR_BLOCK, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BRICKS, Blocks.BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_QUARTZ_BRICKS, Blocks.QUARTZ_BRICKS, 1);

        // SMELTING
        offerSmelting(exporter,List.of(ModItems.SANDY_FLESH), RecipeCategory.BUILDING_BLOCKS, Items.SAND, 0.1f, 300, "flesh_to_sand");
        offerSmelting(exporter, List.of(ModItems.DECAYING_FLESH), RecipeCategory.BUILDING_BLOCKS, Items.SEAGRASS, 0.1f, 300, "flesh_to_seagrass");

        // FOOD & DRINKS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FISH_STEW, 1)
                .input(Items.BOWL)
                .input(Items.DRIED_KELP)
                .input(Items.COOKED_SALMON)
                .input(Items.COOKED_COD)
                .input(Items.BAKED_POTATO)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.VEGETABLE_STEW, 1)
                .input(Items.BOWL)
                .input(Items.POTATO)
                .input(Items.CARROT)
                .input(Items.BEETROOT)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ROTTEN_STEW, 1)
                .input(Items.BOWL)
                .input(Items.ROTTEN_FLESH)
                .input(Items.ROTTEN_FLESH)
                .input(ModItems.DECAYING_FLESH)
                .input(ModItems.DECAYING_FLESH)
                .input(ModItems.SANDY_FLESH)
                .input(ModItems.SANDY_FLESH)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        // REDSTONE
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.COPPER_RAIL, 18)
                .pattern("C C")
                .pattern("CSC")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        offerPressurePlateRecipe(exporter, Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE, Blocks.BLACKSTONE);
        offerPressurePlateRecipe(exporter, ModBlocks.END_STONE_PRESSURE_PLATE, Blocks.END_STONE);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.END_STONE_BUTTON, 1)
                .input(Blocks.END_STONE)
                .criterion(hasItem(Blocks.END_STONE), conditionsFromItem(Blocks.END_STONE))
                .offerTo(exporter);

        // COMBAT
        offerNetheriteUpgradeRecipe(exporter, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR);

        // TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.RECOVERY_COMPASS, 1)
                .pattern("AAA")
                .pattern("ACA")
                .pattern("AAA")
                .input('C', Items.COMPASS)
                .input('A', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AMETHYST_DAGGER, 1)
                .pattern(" A")
                .pattern("S ")
                .input('S', Items.STICK)
                .input('A', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        // INGREDIENT
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AZURITE, 2)
                .input(Items.LAPIS_LAZULI)
                .input(Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHILLED_BONE_MEAL, 3)
                .input(ModItems.CHILLED_BONE)
                .criterion(hasItem(ModItems.CHILLED_BONE), conditionsFromItem(ModItems.CHILLED_BONE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOXIC_BONE, 3)
                .input(ModItems.TOXIC_BONE)
                .criterion(hasItem(ModItems.TOXIC_BONE), conditionsFromItem(ModItems.TOXIC_BONE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DECREPIT_BONE_MEAL, 3)
                .input(ModItems.DECREPIT_BONE)
                .criterion(hasItem(ModItems.DECREPIT_BONE), conditionsFromItem(ModItems.DECREPIT_BONE))
                .offerTo(exporter);

        // MISC
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE, 1)
                .input(ModBlocks.MYSTIC_ROSE)
                .criterion(hasItem(ModBlocks.MYSTIC_ROSE), conditionsFromItem(ModBlocks.MYSTIC_ROSE))
                .offerTo(exporter, Identifier.of(LouisOverhaulMod.MOD_ID, "light_blue_dye_from_mystic_rose"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLUE_DYE, 1)
                .input(ModBlocks.COBALT_FLOWER)
                .criterion(hasItem(ModBlocks.COBALT_FLOWER), conditionsFromItem(ModBlocks.COBALT_FLOWER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MAGENTA_DYE, 1)
                .input(ModBlocks.HEART_FLOWER)
                .criterion(hasItem(ModBlocks.HEART_FLOWER), conditionsFromItem(ModBlocks.HEART_FLOWER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLACK_DYE, 1)
                .input(ModBlocks.WILTED_POPPY)
                .criterion(hasItem(ModBlocks.WILTED_POPPY), conditionsFromItem(ModBlocks.WILTED_POPPY))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PURPLE_DYE, 1)
                .input(ModBlocks.LAVENDER_DANDELION)
                .criterion(hasItem(ModBlocks.LAVENDER_DANDELION), conditionsFromItem(ModBlocks.LAVENDER_DANDELION))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE, 1)
                .input(ModBlocks.SHINY_CORNFLOWER)
                .criterion(hasItem(ModBlocks.SHINY_CORNFLOWER), conditionsFromItem(ModBlocks.SHINY_CORNFLOWER))
                .offerTo(exporter, Identifier.of(LouisOverhaulMod.MOD_ID, "light_blue_dye_from_shiny_cornflower"));


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ADVANCED_FLETCHING_TABLE, 1)
                .pattern("FF ")
                .pattern("PP ")
                .pattern("PP ")
                .input('F', Items.FLINT)
                .input('P', ItemTags.PLANKS)
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SAWMILL, 1)
                .pattern(" I ")
                .pattern("WWW")
                .pattern("   ")
                .input('I', Items.FLINT)
                .input('W', ItemTags.LOGS)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RECALL_CLOCK, 1)
                .pattern("PEP")
                .pattern("PCP")
                .pattern("PEP")
                .input('C', Items.CLOCK)
                .input('P', Items.POPPED_CHORUS_FRUIT)
                .input('E', ModItems.ENDERMITE_HEART)
                .criterion(hasItem(Items.CLOCK), conditionsFromItem(Items.CLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE, 1)
                .pattern("DND")
                .pattern("DGD")
                .pattern("DDD")
                .input('N', Items.ENDER_EYE)
                .input('G', Items.GLOW_INK_SAC)
                .input('D', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE, 1)
                .pattern("DND")
                .pattern("DSD")
                .pattern("DDD")
                .input('N', Items.SCULK_SHRIEKER)
                .input('S', Items.NETHER_STAR)
                .input('D', Items.PRISMARINE_SHARD)
                .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIONEER_POUCH, 1)
                .pattern("RBR")
                .pattern("AKA")
                .pattern("RBR")
                .input('R', Items.REDSTONE_BLOCK)
                .input('B', ModItems.BAT_FANG)
                .input('A', Items.RABBIT_HIDE)
                .input('K', Items.LEATHER)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LARGE_BUNDLE, 1)
                .pattern("LBL")
                .pattern("AKA")
                .pattern("LBL")
                .input('L', Items.LEATHER)
                .input('B', ModItems.BAT_FANG)
                .input('A', Items.IRON_INGOT)
                .input('K', Items.BUNDLE)
                .criterion(hasItem(Items.BUNDLE), conditionsFromItem(Items.BUNDLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MASSIVE_BUNDLE, 1)
                .pattern("LBL")
                .pattern("AKA")
                .pattern("LBL")
                .input('L', Items.RABBIT_HIDE)
                .input('B', ModItems.BAT_FANG)
                .input('A', Items.GOLD_INGOT)
                .input('K', Items.BUNDLE)
                .criterion(hasItem(Items.BUNDLE), conditionsFromItem(Items.BUNDLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUNDLE, 1)
                .input(Items.LEATHER)
                .input(Items.STRING)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PET_RECOVERY_COMPASS, 1)
                .input(Items.TOTEM_OF_UNDYING)
                .input(Items.RECOVERY_COMPASS)
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SADDLED_GOAT_HORN, 1)
                .input(Items.GOAT_HORN)
                .input(Items.SADDLE)
                .criterion(hasItem(Items.SADDLE), conditionsFromItem(Items.SADDLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POTION_POUCH, 1)
                .pattern("SBS")
                .pattern("H H")
                .pattern("   ")
                .input('H', Items.RABBIT_HIDE)
                .input('S', Items.STRING)
                .input('B', ModItems.BAT_FANG)
                .criterion(hasItem(Items.RABBIT_HIDE), conditionsFromItem(Items.RABBIT_HIDE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHILLED_BONE_BLOCK, 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.CHILLED_BONE_MEAL)
                .criterion(hasItem(ModItems.CHILLED_BONE_MEAL), conditionsFromItem(ModItems.CHILLED_BONE_MEAL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TOXIC_BONE_BLOCK, 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.TOXIC_BONE_MEAL)
                .criterion(hasItem(ModItems.TOXIC_BONE_MEAL), conditionsFromItem(ModItems.TOXIC_BONE_MEAL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DECREPIT_BONE_BLOCK, 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.DECREPIT_BONE_MEAL)
                .criterion(hasItem(ModItems.DECREPIT_BONE_MEAL), conditionsFromItem(ModItems.DECREPIT_BONE_MEAL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CRYING_OBSIDIAN, 8)
                .pattern("OOO")
                .pattern("OGO")
                .pattern("OOO")
                .input('O', Items.OBSIDIAN)
                .input('G', Items.GHAST_TEAR)
                .criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CALCITE, 4)
                .pattern("AB")
                .pattern("BA")
                .input('A', Items.BONE_MEAL)
                .input('B', Items.CHARCOAL)
                .criterion(hasItem(Items.BONE_MEAL), conditionsFromItem(Items.BONE_MEAL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.DEEPSLATE, 4)
                .pattern("AB")
                .pattern("BA")
                .input('A', Blocks.STONE)
                .input('B', Blocks.CLAY)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SUSPICIOUS_SAND, 1)
                .pattern("FS")
                .pattern("SF")
                .input('S', Items.SAND)
                .input('F', ModItems.SANDY_FLESH)
                .criterion(hasItem(ModItems.SANDY_FLESH), conditionsFromItem(ModItems.SANDY_FLESH))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SUSPICIOUS_GRAVEL, 1)
                .pattern("DG")
                .pattern("GD")
                .input('G', Items.GRAVEL)
                .input('D', ModItems.DECAYING_FLESH)
                .criterion(hasItem(ModItems.DECAYING_FLESH), conditionsFromItem(ModItems.DECAYING_FLESH))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.PLAYER_HEAD, 1)
                .pattern("DDD")
                .pattern("DHD")
                .pattern("DDD")
                .input('D', Items.DRIED_KELP)
                .input('H', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_BRICKS, 4)
                .pattern("QA")
                .pattern("AQ")
                .input('A', Items.REDSTONE)
                .input('Q', Items.QUARTZ)
                .criterion(hasItem(Items.QUARTZ), conditionsFromItem(Items.QUARTZ))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_PILLAR, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.ROSE_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_COLUMN, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.ROSE_QUARTZ_PILLAR)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_BRICKS, 4)
                .pattern("QA")
                .pattern("AQ")
                .input('A', Items.AMETHYST_SHARD)
                .input('Q', Items.QUARTZ)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_PILLAR, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_COLUMN, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.LAVENDER_QUARTZ_PILLAR)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);

        // TRIPLE
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.ROSE_QUARTZ_STAIRS, ModBlocks.ROSE_QUARTZ_SLAB, ModBlocks.ROSE_QUARTZ_WALL}, ModBlocks.ROSE_QUARTZ_BRICKS);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.LAVENDER_QUARTZ_STAIRS, ModBlocks.LAVENDER_QUARTZ_SLAB, ModBlocks.LAVENDER_QUARTZ_WALL}, ModBlocks.LAVENDER_QUARTZ_BRICKS);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.QUARTZ_BRICKS_SLAB, ModBlocks.QUARTZ_BRICKS_STAIRS, ModBlocks.QUARTZ_BRICKS_WALL}, Blocks.QUARTZ_BRICKS);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.CALCITE_SLAB, ModBlocks.CALCITE_STAIRS, ModBlocks.CALCITE_WALL}, Blocks.CALCITE);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.DRIPSTONE_SLAB, ModBlocks.DRIPSTONE_STAIRS, ModBlocks.DRIPSTONE_WALL}, Blocks.DRIPSTONE_BLOCK);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.SMOOTH_BASALT_SLAB, ModBlocks.SMOOTH_BASALT_STAIRS, ModBlocks.SMOOTH_BASALT_WALL}, Blocks.SMOOTH_BASALT);
        offerStairSlabWallRecipe(exporter, new Block[]{ModBlocks.END_STONE_SLAB, ModBlocks.END_STONE_STAIRS, ModBlocks.END_STONE_WALL}, Blocks.END_STONE);

        // WALLS
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_MOSAIC);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_WALL, Blocks.QUARTZ_BLOCK);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_QUARTZ_WALL, Blocks.SMOOTH_QUARTZ);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL, Blocks.BAMBOO_MOSAIC);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL, Blocks.BAMBOO_MOSAIC);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL, Blocks.BAMBOO_MOSAIC);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL, Blocks.BAMBOO_MOSAIC);

        // STAIRS
        offerStairRecipe(exporter, ModBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
        offerStairRecipe(exporter, ModBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
        offerStairRecipe(exporter, ModBlocks.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);

        // WALLS
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICKS_WALL, Blocks.PRISMARINE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_WALL, Blocks.PRISMARINE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL, Blocks.STONE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE);

        // CHISELED
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DARK_PRISMARINE, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ROSE_QUARTZ, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PURPUR, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LAVENDER_QUARTZ, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_QUARTZ_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MOSSY_STONE_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, Blocks.DARK_PRISMARINE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_DEEPSLATE, Blocks.DEEPSLATE_TILE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS);

        // CRACKED
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_BRICKS, Blocks.BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_END_STONE_BRICKS, Blocks.END_STONE_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_TUFF_BRICKS, Blocks.TUFF_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_MUD_BRICKS, Blocks.MUD_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        offerCrackingRecipe(exporter, ModBlocks.CRACKED_QUARTZ_BRICKS, Blocks.QUARTZ_BRICKS);

        // Red Nether Brick Fence
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_NETHER_BRICK_FENCE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('A', Blocks.RED_NETHER_BRICKS)
                .input('B', Items.NETHER_BRICKS)
                .criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS))
                .offerTo(exporter);

        // CONCRETE
        for (DyeColor color : DyeColor.values()) {
            Block base = Registries.BLOCK.get(Identifier.ofVanilla(color.getName() + "_concrete"));

            offerStairRecipe(exporter, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_stairs")), base);
            offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_slab")), base);
            offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_wall")), base);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_stairs")), base, 1);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_slab")), base, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Registries.BLOCK.get(Identifier.of("louis-overhaul-mod", color.getName() + "_concrete_wall")), base, 1);
        }
    }
}