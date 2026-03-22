package net.louis.overhaulmod.enchantments;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.enchantments.custom.*;
import net.louis.overhaulmod.tags.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> VAMPIRISM =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "vampirism"));
    public static final RegistryKey<Enchantment> SMELTING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "smelting"));
    public static final RegistryKey<Enchantment> TILLING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "tilling"));
    public static final RegistryKey<Enchantment> MAGIC_TOUCH =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "magic_touch"));
    public static final RegistryKey<Enchantment> FIRE_BLAST =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "fire_blast"));
    public static final RegistryKey<Enchantment> GIANT_KILLER =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "giant_killer"));
    public static final RegistryKey<Enchantment> ILLAGERS_BANE =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "illagers_bane"));
    public static final RegistryKey<Enchantment> REELING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "reeling"));
    public static final RegistryKey<Enchantment> DOUBLE_HOOK =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "double_hook"));
    public static final RegistryKey<Enchantment> SHIELD_BASH =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "shield_bash"));
    public static final RegistryKey<Enchantment> RECOVERY =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "recovery"));
    public static final RegistryKey<Enchantment> SHIELD_AURA =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "shield_aura"));
    public static final RegistryKey<Enchantment> TAILORING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "tailoring"));
    public static final RegistryKey<Enchantment> EXCAVATOR =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(LouisOverhaulMod.MOD_ID, "excavator"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, VAMPIRISM, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        2,
                        4,
                        Enchantment.leveledCost(5, 7),
                        Enchantment.leveledCost(15, 9),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new LifestealEnchantmentEffect()));

        register(registerable, SMELTING, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.leveledCost(23, 2),
                        Enchantment.leveledCost(35, 5),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET)));

        register(registerable, TILLING, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ModTags.Items.SHOVEL_HOE_ENCHANTABLE),
                        items.getOrThrow(ModTags.Items.SHOVEL_HOE_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.leveledCost(23, 2),
                        Enchantment.leveledCost(35, 5),
                        0,
                        AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET)));

        register(registerable, FIRE_BLAST, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ModTags.Items.FLINT_AND_STEEL_ENCHANTABLE),
                        items.getOrThrow(ModTags.Items.FLINT_AND_STEEL_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.leveledCost(23, 2),
                        Enchantment.leveledCost(35, 5),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET)));

        register(registerable, MAGIC_TOUCH, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.HOES),
                        items.getOrThrow(ItemTags.HOES),
                        1,
                        1,
                        Enchantment.leveledCost(23, 2),
                        Enchantment.leveledCost(35, 5),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET)));

        register(registerable, GIANT_KILLER, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.leveledCost(5, 8),
                        Enchantment.leveledCost(25, 8),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET)));

        register(registerable, ILLAGERS_BANE, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.leveledCost(5, 8),
                        Enchantment.leveledCost(25, 8),
                        0,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET)));

        register(registerable, REELING, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                        items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.leveledCost(5, 8),
                        Enchantment.leveledCost(25, 8),
                        0,
                        AttributeModifierSlot.MAINHAND)));

        register(registerable, DOUBLE_HOOK, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                1,
                1,
                Enchantment.leveledCost(30, 8),
                Enchantment.leveledCost(30, 8),
                0,
                AttributeModifierSlot.MAINHAND)));

        register(registerable, SHIELD_BASH, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                2,
                1,
                Enchantment.leveledCost(25, 3),
                Enchantment.leveledCost(35, 3),
                0,
                AttributeModifierSlot.OFFHAND)));

        register(registerable, RECOVERY, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                3,
                3,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(30, 5),
                0,
                AttributeModifierSlot.OFFHAND)));

        register(registerable, SHIELD_AURA, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                2,
                1,
                Enchantment.leveledCost(25, 3),
                Enchantment.leveledCost(35, 3),
                0,
                AttributeModifierSlot.HAND))
                .addEffect(EnchantmentEffectComponentTypes.TICK, new ShieldAuraEnchantmentEffect())
        );

        register(registerable, TAILORING, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.SHEARS_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.SHEARS_ENCHANTABLE),
                3,
                3,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(30, 5),
                0,
                AttributeModifierSlot.MAINHAND)));

        register(registerable, EXCAVATOR, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                2,
                3,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(30, 5),
                0,
                AttributeModifierSlot.MAINHAND))
                .addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES, new AttributeEnchantmentEffect(
                        Identifier.of(LouisOverhaulMod.MOD_ID, "excavator_range"),
                        EntityAttributes.BLOCK_INTERACTION_RANGE,
                        EnchantmentLevelBasedValue.linear(1.0f, 0.5f),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ))
        );
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
