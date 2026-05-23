package bunny.lib.mixin;

import bunny.lib.item.ModItems;
import bunny.lib.potion.ModPotions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {
    @Inject(method = "registerDefaults", at = @At("HEAD"), cancellable = true)
    private static void LOM$overrideDefaults(BrewingRecipeRegistry.Builder builder, CallbackInfo ci) {

        // Potion Types
        builder.registerPotionType(Items.POTION);
        builder.registerPotionType(Items.SPLASH_POTION);
        builder.registerPotionType(Items.LINGERING_POTION);

        // Potion Type Recipes
        builder.registerItemRecipe(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        builder.registerItemRecipe(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);

        // Placeholder Potions
        builder.registerPotionRecipe(Potions.WATER, Items.GLOWSTONE_DUST, Potions.THICK);
        builder.registerPotionRecipe(Potions.WATER, Items.REDSTONE, Potions.MUNDANE);
        builder.registerPotionRecipe(Potions.WATER, Items.NETHER_WART, Potions.AWKWARD);

        // Negative Potion Effects
        builder.registerPotionRecipe(Potions.MUNDANE, Items.TURTLE_SCUTE, Potions.SLOWNESS);
        builder.registerPotionRecipe(Potions.SLOWNESS, Items.REDSTONE, Potions.LONG_SLOWNESS);
        builder.registerPotionRecipe(Potions.SLOWNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SLOWNESS);
        builder.registerPotionRecipe(Potions.LONG_SLOWNESS, Items.REDSTONE_BLOCK, ModPotions.LONGEST_SLOWNESS);
        builder.registerPotionRecipe(Potions.STRONG_SLOWNESS, Items.GLOWSTONE, ModPotions.STRONGEST_SLOWNESS);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
        builder.registerPotionRecipe(Potions.HARMING, Items.GLOWSTONE_DUST, Potions.STRONG_HARMING);
        builder.registerPotionRecipe(Potions.STRONG_HARMING, Items.GLOWSTONE, ModPotions.STRONGEST_HARMING);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.SPIDER_EYE, Potions.POISON);
        builder.registerPotionRecipe(Potions.POISON, Items.REDSTONE, Potions.LONG_POISON);
        builder.registerPotionRecipe(Potions.POISON, Items.GLOWSTONE_DUST, Potions.STRONG_POISON);
        builder.registerPotionRecipe(Potions.LONG_POISON, Items.REDSTONE_BLOCK, ModPotions.LONGEST_POISON);
        builder.registerPotionRecipe(Potions.STRONG_POISON, Items.GLOWSTONE, ModPotions.STRONGEST_POISON);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.WARPED_FUNGUS, Potions.WEAKNESS);
        builder.registerPotionRecipe(Potions.WEAKNESS, Items.REDSTONE, Potions.LONG_WEAKNESS);
        builder.registerPotionRecipe(Potions.WEAKNESS, Items.GLOWSTONE_DUST, ModPotions.STRONG_WEAKNESS);
        builder.registerPotionRecipe(Potions.LONG_WEAKNESS, Items.REDSTONE_BLOCK, ModPotions.LONGEST_WEAKNESS);
        builder.registerPotionRecipe(ModPotions.STRONG_WEAKNESS, Items.GLOWSTONE, ModPotions.STRONGEST_WEAKNESS);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.DEAD_BUSH, ModPotions.UNLUCK);
        builder.registerPotionRecipe(ModPotions.UNLUCK, Items.REDSTONE, ModPotions.LONG_UNLUCK);
        builder.registerPotionRecipe(ModPotions.UNLUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_UNLUCK);
        builder.registerPotionRecipe(ModPotions.LONG_UNLUCK, Items.REDSTONE_BLOCK, ModPotions.LONGEST_UNLUCK);
        builder.registerPotionRecipe(ModPotions.STRONG_UNLUCK, Items.GLOWSTONE, ModPotions.STRONGEST_UNLUCK);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.DISC_FRAGMENT_5, ModPotions.DARKNESS);
        builder.registerPotionRecipe(ModPotions.DARKNESS, Items.REDSTONE, ModPotions.LONG_DARKNESS);
        builder.registerPotionRecipe(ModPotions.LONG_DARKNESS, Items.REDSTONE_BLOCK, ModPotions.LONGEST_DARKNESS);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.INK_SAC, ModPotions.BLINDNESS);
        builder.registerPotionRecipe(ModPotions.BLINDNESS, Items.REDSTONE, ModPotions.LONG_BLINDNESS);
        builder.registerPotionRecipe(ModPotions.LONG_BLINDNESS, Items.REDSTONE_BLOCK, ModPotions.LONGEST_BLINDNESS);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.PALE_MOSS_BLOCK, ModPotions.HUNGER);
        builder.registerPotionRecipe(ModPotions.HUNGER, Items.REDSTONE, ModPotions.LONG_HUNGER);
        builder.registerPotionRecipe(ModPotions.HUNGER, Items.GLOWSTONE_DUST, ModPotions.STRONG_HUNGER);
        builder.registerPotionRecipe(ModPotions.LONG_HUNGER, Items.REDSTONE_BLOCK, ModPotions.LONGEST_HUNGER);
        builder.registerPotionRecipe(ModPotions.STRONG_HUNGER, Items.GLOWSTONE, ModPotions.STRONGEST_HUNGER);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.WITHER_ROSE, ModPotions.WITHER);
        builder.registerPotionRecipe(ModPotions.WITHER, Items.REDSTONE, ModPotions.LONG_WITHER);
        builder.registerPotionRecipe(ModPotions.WITHER, Items.GLOWSTONE_DUST, ModPotions.STRONG_WITHER);
        builder.registerPotionRecipe(ModPotions.LONG_WITHER, Items.REDSTONE_BLOCK, ModPotions.LONGEST_WITHER);
        builder.registerPotionRecipe(ModPotions.STRONG_WITHER, Items.GLOWSTONE, ModPotions.STRONGEST_WITHER);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.SHULKER_SHELL, ModPotions.LEVITATION);
        builder.registerPotionRecipe(ModPotions.LEVITATION, Items.REDSTONE, ModPotions.LONG_LEVITATION);
        builder.registerPotionRecipe(ModPotions.LEVITATION, Items.GLOWSTONE_DUST, ModPotions.STRONG_LEVITATION);
        builder.registerPotionRecipe(ModPotions.LONG_LEVITATION, Items.REDSTONE_BLOCK, ModPotions.LONGEST_LEVITATION);
        builder.registerPotionRecipe(ModPotions.STRONG_LEVITATION, Items.GLOWSTONE, ModPotions.STRONGEST_LEVITATION);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.OXIDIZED_COPPER, ModPotions.NAUSEA);
        builder.registerPotionRecipe(ModPotions.NAUSEA, Items.REDSTONE, ModPotions.LONG_NAUSEA);
        builder.registerPotionRecipe(ModPotions.LONG_NAUSEA, Items.REDSTONE_BLOCK, ModPotions.LONGEST_NAUSEA);

        // Mid Potion Effects
        builder.registerPotionRecipe(Potions.THICK, Items.BREEZE_ROD, Potions.WIND_CHARGED);
        builder.registerPotionRecipe(Potions.WIND_CHARGED, Items.REDSTONE, ModPotions.LONG_WIND_CHARGED);
        builder.registerPotionRecipe(Potions.WIND_CHARGED, Items.GLOWSTONE_DUST, ModPotions.STRONG_WIND_CHARGED);
        builder.registerPotionRecipe(ModPotions.LONG_WIND_CHARGED, Items.REDSTONE_BLOCK, ModPotions.LONGEST_WIND_CHARGED);
        builder.registerPotionRecipe(ModPotions.STRONG_WIND_CHARGED, Items.GLOWSTONE, ModPotions.STRONGEST_WIND_CHARGED);

        builder.registerPotionRecipe(Potions.THICK, Items.SLIME_BLOCK, Potions.OOZING);
        builder.registerPotionRecipe(Potions.OOZING, Items.REDSTONE, ModPotions.LONG_OOZING);
        builder.registerPotionRecipe(Potions.OOZING, Items.GLOWSTONE_DUST, ModPotions.STRONG_OOZING);
        builder.registerPotionRecipe(ModPotions.LONG_OOZING, Items.REDSTONE_BLOCK, ModPotions.LONGEST_OOZING);
        builder.registerPotionRecipe(ModPotions.STRONG_OOZING, Items.GLOWSTONE, ModPotions.STRONGEST_OOZING);

        builder.registerPotionRecipe(Potions.THICK, Items.STONE, Potions.INFESTED);
        builder.registerPotionRecipe(Potions.INFESTED, Items.REDSTONE, ModPotions.LONG_INFESTED);
        builder.registerPotionRecipe(Potions.INFESTED, Items.GLOWSTONE_DUST, ModPotions.STRONG_INFESTED);
        builder.registerPotionRecipe(ModPotions.LONG_INFESTED, Items.REDSTONE_BLOCK, ModPotions.LONGEST_INFESTED);
        builder.registerPotionRecipe(ModPotions.STRONG_INFESTED, Items.GLOWSTONE, ModPotions.STRONGEST_INFESTED);

        builder.registerPotionRecipe(Potions.THICK, Items.COBWEB, Potions.WEAVING);
        builder.registerPotionRecipe(Potions.WEAVING, Items.REDSTONE, ModPotions.LONG_WEAVING);
        builder.registerPotionRecipe(Potions.WEAVING, Items.GLOWSTONE_DUST, ModPotions.STRONG_WEAVING);
        builder.registerPotionRecipe(ModPotions.LONG_WEAVING, Items.REDSTONE_BLOCK, ModPotions.LONGEST_WEAVING);
        builder.registerPotionRecipe(ModPotions.STRONG_WEAVING, Items.GLOWSTONE, ModPotions.STRONGEST_WEAVING);

        // Positive Potion Effects
        builder.registerPotionRecipe(Potions.AWKWARD, Items.TURTLE_HELMET, ModPotions.TRUE_TURTLE_MASTER);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.GLOW_LICHEN, Potions.NIGHT_VISION);
        builder.registerPotionRecipe(Potions.NIGHT_VISION, Items.REDSTONE, Potions.LONG_NIGHT_VISION);
        builder.registerPotionRecipe(Potions.LONG_NIGHT_VISION, Items.REDSTONE_BLOCK, ModPotions.LONGEST_NIGHT_VISION);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.PHANTOM_MEMBRANE, Potions.INVISIBILITY);
        builder.registerPotionRecipe(Potions.INVISIBILITY, Items.REDSTONE, Potions.LONG_INVISIBILITY);
        builder.registerPotionRecipe(Potions.LONG_INVISIBILITY, Items.REDSTONE_BLOCK, ModPotions.LONGEST_INVISIBILITY);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.MAGMA_CREAM, Potions.FIRE_RESISTANCE);
        builder.registerPotionRecipe(Potions.FIRE_RESISTANCE, Items.REDSTONE, Potions.LONG_FIRE_RESISTANCE);
        builder.registerPotionRecipe(Potions.LONG_FIRE_RESISTANCE, Items.REDSTONE_BLOCK, ModPotions.LONGEST_FIRE_RESISTANCE);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.RABBIT, Potions.LEAPING);
        builder.registerPotionRecipe(Potions.LEAPING, Items.REDSTONE, Potions.LONG_LEAPING);
        builder.registerPotionRecipe(Potions.LEAPING, Items.GLOWSTONE_DUST, Potions.STRONG_LEAPING);
        builder.registerPotionRecipe(Potions.LONG_LEAPING, Items.REDSTONE_BLOCK, ModPotions.LONGEST_LEAPING);
        builder.registerPotionRecipe(Potions.STRONG_LEAPING, Items.GLOWSTONE, ModPotions.STRONGEST_LEAPING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.SUGAR, Potions.SWIFTNESS);
        builder.registerPotionRecipe(Potions.SWIFTNESS, Items.REDSTONE, Potions.LONG_SWIFTNESS);
        builder.registerPotionRecipe(Potions.SWIFTNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SWIFTNESS);
        builder.registerPotionRecipe(Potions.LONG_SWIFTNESS, Items.REDSTONE_BLOCK, ModPotions.LONGEST_SWIFTNESS);
        builder.registerPotionRecipe(Potions.STRONG_SWIFTNESS, Items.GLOWSTONE, ModPotions.STRONGEST_SWIFTNESS);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.PUFFERFISH, Potions.WATER_BREATHING);
        builder.registerPotionRecipe(Potions.WATER_BREATHING, Items.REDSTONE, Potions.LONG_WATER_BREATHING);
        builder.registerPotionRecipe(Potions.LONG_WATER_BREATHING, Items.REDSTONE_BLOCK, ModPotions.LONGEST_WATER_BREATHING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.GLISTERING_MELON_SLICE, Potions.HEALING);
        builder.registerPotionRecipe(Potions.HEALING, Items.GLOWSTONE_DUST, Potions.STRONG_HEALING);
        builder.registerPotionRecipe(Potions.STRONG_HEALING, Items.GLOWSTONE, ModPotions.STRONGEST_HEALING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.GHAST_TEAR, Potions.REGENERATION);
        builder.registerPotionRecipe(Potions.REGENERATION, Items.REDSTONE, Potions.LONG_REGENERATION);
        builder.registerPotionRecipe(Potions.REGENERATION, Items.GLOWSTONE_DUST, Potions.STRONG_REGENERATION);
        builder.registerPotionRecipe(Potions.LONG_REGENERATION, Items.REDSTONE_BLOCK, ModPotions.LONGEST_REGENERATION);
        builder.registerPotionRecipe(Potions.STRONG_REGENERATION, Items.GLOWSTONE, ModPotions.STRONGEST_REGENERATION);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.BLAZE_POWDER, Potions.STRENGTH);
        builder.registerPotionRecipe(Potions.STRENGTH, Items.REDSTONE, Potions.LONG_STRENGTH);
        builder.registerPotionRecipe(Potions.STRENGTH, Items.GLOWSTONE_DUST, Potions.STRONG_STRENGTH);
        builder.registerPotionRecipe(Potions.LONG_STRENGTH, Items.REDSTONE_BLOCK, ModPotions.LONGEST_STRENGTH);
        builder.registerPotionRecipe(Potions.STRONG_STRENGTH, Items.GLOWSTONE, ModPotions.STRONGEST_STRENGTH);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.RABBIT_FOOT, Potions.LUCK);
        builder.registerPotionRecipe(Potions.LUCK, Items.REDSTONE, ModPotions.LONG_LUCK);
        builder.registerPotionRecipe(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);
        builder.registerPotionRecipe(ModPotions.LONG_LUCK, Items.REDSTONE_BLOCK, ModPotions.LONGEST_LUCK);
        builder.registerPotionRecipe(ModPotions.STRONG_LUCK, Items.GLOWSTONE, ModPotions.STRONGEST_LUCK);

        builder.registerPotionRecipe(Potions.AWKWARD, ModItems.BAT_FANG, Potions.SLOW_FALLING);
        builder.registerPotionRecipe(Potions.SLOW_FALLING, Items.REDSTONE, Potions.LONG_SLOW_FALLING);
        builder.registerPotionRecipe(Potions.LONG_SLOW_FALLING, Items.REDSTONE_BLOCK, ModPotions.LONGEST_SLOW_FALLING);

        // NEW POTIONS
        builder.registerPotionRecipe(Potions.AWKWARD, Items.HANGING_ROOTS, ModPotions.GROUNDED);
        builder.registerPotionRecipe(ModPotions.GROUNDED, Items.REDSTONE, ModPotions.LONG_GROUNDED);
        builder.registerPotionRecipe(ModPotions.LONG_GROUNDED, Items.REDSTONE_BLOCK, ModPotions.LONGEST_GROUNDED);

        builder.registerPotionRecipe(Potions.AWKWARD, ModItems.LLAMAS_SPIT, ModPotions.DWARFISM);
        builder.registerPotionRecipe(ModPotions.DWARFISM, Items.REDSTONE, ModPotions.LONG_DWARFISM);
        builder.registerPotionRecipe(ModPotions.DWARFISM, Items.GLOWSTONE_DUST, ModPotions.STRONG_DWARFISM);
        builder.registerPotionRecipe(ModPotions.LONG_DWARFISM, Items.REDSTONE_BLOCK, ModPotions.LONGEST_DWARFISM);
        builder.registerPotionRecipe(ModPotions.STRONG_DWARFISM, Items.GLOWSTONE, ModPotions.STRONGEST_DWARFISM);

        builder.registerPotionRecipe(Potions.MUNDANE, ModItems.LLAMAS_SPIT, ModPotions.GIGANTISM);
        builder.registerPotionRecipe(ModPotions.GIGANTISM, Items.REDSTONE, ModPotions.LONG_GIGANTISM);
        builder.registerPotionRecipe(ModPotions.GIGANTISM, Items.GLOWSTONE_DUST, ModPotions.STRONG_GIGANTISM);
        builder.registerPotionRecipe(ModPotions.LONG_GIGANTISM, Items.REDSTONE_BLOCK, ModPotions.LONGEST_GIGANTISM);
        builder.registerPotionRecipe(ModPotions.STRONG_GIGANTISM, Items.GLOWSTONE, ModPotions.STRONGEST_GIGANTISM);

        ci.cancel();

    }
}

