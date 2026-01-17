package net.louis.overhaulmod.recipe;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;


public class ModRecipes {
    public static final RecipeSerializer<SawmillRecipe> SAWMILL_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LouisOverhaulMod.MOD_ID, "sawmill"),
            new SawmillRecipeSerializer());
    public static final RecipeType<SawmillRecipe> SAWMILL_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, "sawmill"), new RecipeType<SawmillRecipe>() {
                @Override
                public String toString() {
                    return "sawmill";
                }
            });

    public static void registerRecipes() {
        // ... existing code

        // Add a callback to log when recipes are loaded
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return Identifier.of(LouisOverhaulMod.MOD_ID, "recipe_debug");
            }

            @Override
            public void reload(ResourceManager manager) {
                System.out.println("=== RECIPES BEING LOADED ===");
                // This will show us what's happening
            }
        });
    }
}