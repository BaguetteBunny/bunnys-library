package net.louis.overhaulmod.enchantments;

import com.mojang.serialization.MapCodec;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.enchantments.custom.*;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> VAMPIRISM =
            registerEntityEffect("vampirism", LifestealEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> SMELTING =
            registerEntityEffect("smelting", SmeltingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> TILLING =
            registerEntityEffect("tilling", TillingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FIRE_BLAST =
            registerEntityEffect("fire_blast", FireBlastEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> GIANT_KILLER =
            registerEntityEffect("giant_killer", GiantKillerEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> ILLAGERS_BANE =
            registerEntityEffect("illagers_bane", IllagersBaneEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> MAGIC_TOUCH =
            registerEntityEffect("magic_touch", MagicTouchEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> REELING =
            registerEntityEffect("reeling", ReelingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> DOUBLE_HOOK =
            registerEntityEffect("double_hook", DoubleHookEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Enchantment Effects for " + LouisOverhaulMod.MOD_ID);
    }
}
