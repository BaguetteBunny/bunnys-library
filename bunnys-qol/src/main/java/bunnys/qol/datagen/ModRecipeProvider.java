package bunnys.qol.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture = null;

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        ModRecipeProvider.registriesFuture = registriesFuture;
    }


    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.TOOLS, Items.RECOVERY_COMPASS, 1)
                        .pattern("AAA")
                        .pattern("ACA")
                        .pattern("AAA")
                        .input('C', Items.COMPASS)
                        .input('A', Items.AMETHYST_SHARD)
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .offerTo(exporter);

                for (Item disc : MUSIC_DISC) {
                    createShaped(RecipeCategory.MISC, disc)
                            .pattern("AAA")
                            .pattern("ACA")
                            .pattern("AAA")
                            .input('C', disc)
                            .input('A', Items.ECHO_SHARD)
                            .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                            .offerTo(exporter);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "BunnyLib Recipes";
    }

    public static final Set<Item> MUSIC_DISC = Set.of(
            Items.MUSIC_DISC_13,
            Items.MUSIC_DISC_CAT,
            Items.MUSIC_DISC_BLOCKS,
            Items.MUSIC_DISC_CHIRP,
            Items.MUSIC_DISC_CREATOR,
            Items.MUSIC_DISC_CREATOR_MUSIC_BOX,
            Items.MUSIC_DISC_FAR,
            Items.MUSIC_DISC_MALL,
            Items.MUSIC_DISC_MELLOHI,
            Items.MUSIC_DISC_OTHERSIDE,
            Items.MUSIC_DISC_PIGSTEP,
            Items.MUSIC_DISC_PRECIPICE,
            Items.MUSIC_DISC_RELIC,
            Items.MUSIC_DISC_STAL,
            Items.MUSIC_DISC_STRAD,
            Items.MUSIC_DISC_WAIT,
            Items.MUSIC_DISC_WARD,
            Items.MUSIC_DISC_11,
            Items.MUSIC_DISC_5
    );
}