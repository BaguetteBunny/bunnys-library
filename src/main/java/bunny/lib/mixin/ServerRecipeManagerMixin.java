package bunny.lib.mixin;

import net.minecraft.recipe.*;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

@Mixin(ServerRecipeManager.class)
public class ServerRecipeManagerMixin {
    private static final Set<Identifier> RECIPES_TO_REMOVE = Set.of(
            Identifier.ofVanilla("polished_blackstone_button"),
            Identifier.ofVanilla("polished_blackstone_pressure_plate"),
            Identifier.ofVanilla("chiseled_polished_blackstone"),
            Identifier.ofVanilla("chiseled_deepslate"),
            Identifier.ofVanilla("fletching_table"),
            Identifier.ofVanilla("recovery_compass")
    );

    @Inject(method = "apply", at = @At("RETURN"))
    private void LOM$removeRecipeAfterApply(PreparedRecipes preparedRecipes,
                                            ResourceManager resourceManager,
                                            Profiler profiler,
                                            CallbackInfo ci) {
        try {
            Field preparedRecipesField = ServerRecipeManager.class.getDeclaredField("preparedRecipes");
            preparedRecipesField.setAccessible(true);

            PreparedRecipes currentPrepared = (PreparedRecipes) preparedRecipesField.get(this);

            // Filter out unwanted recipes
            List<RecipeEntry<?>> filteredRecipes = currentPrepared.recipes().stream()
                    .filter(entry -> !RECIPES_TO_REMOVE.contains(entry.id().getValue()))
                    .toList();

            // Create new PreparedRecipes with filtered list
            PreparedRecipes newPrepared = PreparedRecipes.of(filteredRecipes);

            // Set the filtered PreparedRecipes back
            preparedRecipesField.set(this, newPrepared);

            System.out.println("[RecipeManagerMixin] Removed " +
                    (currentPrepared.recipes().size() - filteredRecipes.size()) +
                    " unwanted recipes: " + RECIPES_TO_REMOVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
