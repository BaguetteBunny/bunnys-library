package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.recipe.ModRecipes;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.RecipeEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
    private static void fixSawmillRecipeCategory(RecipeEntry<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        if (recipe.value().getType() == ModRecipes.SAWMILL_TYPE) {
            cir.setReturnValue(RecipeBookGroup.CRAFTING_BUILDING_BLOCKS);
        }
    }
}