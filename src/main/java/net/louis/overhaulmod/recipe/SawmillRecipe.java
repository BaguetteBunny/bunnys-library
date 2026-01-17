package net.louis.overhaulmod.recipe;

import net.louis.overhaulmod.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.world.World;

public class SawmillRecipe extends CuttingRecipe {
    private final RecipeCategory category;

    public SawmillRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.SAWMILL_TYPE, ModRecipes.SAWMILL_SERIALIZER, group, ingredient, result);
        this.category = RecipeCategory.MISC;
    }

    public SawmillRecipe(RecipeCategory category, String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.SAWMILL_TYPE, ModRecipes.SAWMILL_SERIALIZER, group, ingredient, result);
        this.category = category;
    }

    public RecipeCategory getCategory() {
        return this.category;
    }

    public boolean matches(SingleStackRecipeInput singleStackRecipeInput, World world) {
        return this.ingredient.test(singleStackRecipeInput.item());
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.SAWMILL);
    }
}