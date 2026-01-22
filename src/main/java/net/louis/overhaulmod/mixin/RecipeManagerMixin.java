package net.louis.overhaulmod.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    private static final Set<Identifier> RECIPES_TO_REMOVE = Set.of(
            Identifier.ofVanilla("polished_blackstone_button"),
            Identifier.ofVanilla("polished_blackstone_pressure_plate"),
            Identifier.ofVanilla("chiseled_polished_blackstone"),
            Identifier.ofVanilla("chiseled_deepslate"),

            Identifier.ofVanilla("fletching_table"),
            Identifier.ofVanilla("recovery_compass")
    );

    @Inject(method = "apply", at = @At("RETURN"))
    private void LOM$removeRecipeAfterApply(Map<Identifier, JsonElement> map,
                                        ResourceManager resourceManager,
                                        Profiler profiler,
                                        CallbackInfo ci) {
        RecipeManager self = (RecipeManager) (Object) this;
        try {
            Field recipesByIdField = RecipeManager.class.getDeclaredField("recipesById");
            Field recipesByTypeField = RecipeManager.class.getDeclaredField("recipesByType");
            recipesByIdField.setAccessible(true);
            recipesByTypeField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<Identifier, RecipeEntry<?>> recipesById =
                    (Map<Identifier, RecipeEntry<?>>) recipesByIdField.get(self);

            Map<Identifier, RecipeEntry<?>> filteredById = recipesById.entrySet().stream()
                    .filter(entry -> !RECIPES_TO_REMOVE.contains(entry.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<?>> byTypeBuilder = ImmutableMultimap.builder();
            for (RecipeEntry<?> entry : filteredById.values()) {
                RecipeType<?> type = entry.value().getType();
                byTypeBuilder.put(type, entry);
            }

            recipesByIdField.set(self, ImmutableMap.copyOf(filteredById));
            recipesByTypeField.set(self, byTypeBuilder.build());

            System.out.println("[RecipeManagerMixin] Removed " + (recipesById.size() - filteredById.size()) + " unwanted recipes: " + RECIPES_TO_REMOVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
