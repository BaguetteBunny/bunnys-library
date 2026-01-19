package net.louis.overhaulmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.ModEntities;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.custom.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item WHITE_WATER_BUCKET = registerItem("white_water_bucket", new BucketItem(ModFluids.STILL_WHITE_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item ORANGE_WATER_BUCKET = registerItem("orange_water_bucket", new BucketItem(ModFluids.STILL_ORANGE_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item MAGENTA_WATER_BUCKET = registerItem("magenta_water_bucket", new BucketItem(ModFluids.STILL_MAGENTA_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIGHT_BLUE_WATER_BUCKET = registerItem("light_blue_water_bucket", new BucketItem(ModFluids.STILL_LIGHT_BLUE_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item YELLOW_WATER_BUCKET = registerItem("yellow_water_bucket", new BucketItem(ModFluids.STILL_YELLOW_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIME_WATER_BUCKET = registerItem("lime_water_bucket", new BucketItem(ModFluids.STILL_LIME_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item PINK_WATER_BUCKET = registerItem("pink_water_bucket", new BucketItem(ModFluids.STILL_PINK_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item GRAY_WATER_BUCKET = registerItem("gray_water_bucket", new BucketItem(ModFluids.STILL_GRAY_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIGHT_GRAY_WATER_BUCKET = registerItem("light_gray_water_bucket", new BucketItem(ModFluids.STILL_LIGHT_GRAY_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item CYAN_WATER_BUCKET = registerItem("cyan_water_bucket", new BucketItem(ModFluids.STILL_CYAN_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item PURPLE_WATER_BUCKET = registerItem("purple_water_bucket", new BucketItem(ModFluids.STILL_PURPLE_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BLUE_WATER_BUCKET = registerItem("blue_water_bucket", new BucketItem(ModFluids.STILL_BLUE_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BROWN_WATER_BUCKET = registerItem("brown_water_bucket", new BucketItem(ModFluids.STILL_BROWN_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item GREEN_WATER_BUCKET = registerItem("green_water_bucket", new BucketItem(ModFluids.STILL_GREEN_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item RED_WATER_BUCKET = registerItem("red_water_bucket", new BucketItem(ModFluids.STILL_RED_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BLACK_WATER_BUCKET = registerItem("black_water_bucket", new BucketItem(ModFluids.STILL_BLACK_WATER, new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item AZURITE = registerItem("azurite",
            new Item(new Item.Settings()));

    public static final Item BEAR_CLAW = registerItem("bear_claw",
            new Item(new Item.Settings().maxDamage(1024).maxCount(1)
                    .attributeModifiers(AttributeModifiersComponent.builder()
                            .add(
                                    EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                                    new EntityAttributeModifier(
                                            Identifier.of("bear_claw", "block_interaction_range"),
                                            2.5,
                                            EntityAttributeModifier.Operation.ADD_VALUE),
                                    AttributeModifierSlot.HAND)
                            .add(
                                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                                    new EntityAttributeModifier(
                                            Identifier.of("bear_claw", "attack_damage"),
                                            4.0,
                                            EntityAttributeModifier.Operation.ADD_VALUE
                                    ),
                                    AttributeModifierSlot.MAINHAND
                            )
                            .build())));

    public static final Item GLOW_UPGRADE_SMITHING_TEMPLATE = registerItem("glow_upgrade_smithing_template",
            new Item(new Item.Settings()));

    public static final Item PULSING_UPGRADE_SMITHING_TEMPLATE = registerItem("pulsing_upgrade_smithing_template",
            new Item(new Item.Settings()));

    public static final Item PURIFIED_WATER_BOTTLE = registerItem("purified_water_bottle",
            new Item(new Item.Settings().maxCount(1)));

    public static final Item EMPYREAN_POWDER = registerItem("empyrean_powder",
            new Item(new Item.Settings().maxCount(16)));

    public static final Item ADVANCED_ARROW = registerItem("advanced_arrow",
            new ArrowItem(new Item.Settings()));

    public static final Item CHILLED_BONE = registerItem("chilled_bone",
            new Item(new Item.Settings()));
    public static final Item CHILLED_BONE_MEAL = registerItem("chilled_bone_meal",
            new Item(new Item.Settings()));
    public static final Item TOXIC_BONE = registerItem("toxic_bone",
            new Item(new Item.Settings()));
    public static final Item TOXIC_BONE_MEAL = registerItem("toxic_bone_meal",
            new Item(new Item.Settings()));
    public static final Item DECREPIT_BONE = registerItem("decrepit_bone",
            new Item(new Item.Settings()));
    public static final Item DECREPIT_BONE_MEAL = registerItem("decrepit_bone_meal",
            new Item(new Item.Settings()));

    public static final Item BAT_FANG = registerItem("bat_fang",
            new Item(new Item.Settings()));
    public static final Item ENDERMITE_HEART = registerItem("endermite_heart",
            new Item(new Item.Settings()));
    public static final Item DECAYING_FLESH = registerItem("decaying_flesh",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), 1f)
                    .build()))
    );
    public static final Item SANDY_FLESH = registerItem("sandy_flesh",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), 1f)
                    .build()))
    );
    public static final Item FISH_STEW = registerItem("fish_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(8)
                    .saturationModifier(6.5f)
                    .snack()
                    .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 9000, 0), .75f)
                    .build()))
    );
    public static final Item VEGETABLE_STEW = registerItem("vegetable_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(6)
                    .saturationModifier(8f)
                    .snack()
                    .build()))
    );
    public static final Item ROTTEN_STEW = registerItem("rotten_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(5)
                    .saturationModifier(.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), .6f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 900, 0), .6f)
                    .snack()
                    .build()))
    );
    public static final Item NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor",
            new AnimalArmorItem(ArmorMaterials.NETHERITE, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));

    public static final Item POTION_POUCH = registerItem("potion_pouch",
            new PotionPouch(new Item.Settings().maxCount(1).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)));

    public static final Item LARGE_BUNDLE = registerItem("large_bundle",
            new BigBundleItem(new Item.Settings().maxCount(1), 128));
    public static final Item MASSIVE_BUNDLE = registerItem("massive_bundle",
            new BigBundleItem(new Item.Settings().maxCount(1), 256));
    public static final Item PIONEER_POUCH = registerItem("pioneer_pouch",
            new PioneerPouch(new Item.Settings().maxCount(1), 512));

    public static final Item SADDLED_GOAT_HORN = registerItem("saddled_goat_horn",
            new SaddledGoatHorn(new Item.Settings().maxCount(1)));

    public static final Item PET_RECOVERY_COMPASS = registerItem("pet_recovery_compass",
            new PetRecoveryCompass(new Item.Settings().maxCount(1)));

    public static final Item RECALL_CLOCK = registerItem("recall_clock",
            new RecallClock(new Item.Settings().maxCount(1)));

    public static final Item LLAMAS_SPIT = registerItem("llamas_spit",
            new Item(new Item.Settings()));

    public static final Item AMETHYST_DAGGER = registerItem("amethyst_dagger",
            new AmethystDagger(new Item.Settings()));

    public static final Item BROWN_BEAR_SPAWN_EGG = registerItem("brown_bear_spawn_egg",
            new SpawnEggItem(ModEntities.BROWN_BEAR, 0xa37539, 0xbf935a, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Items for " + LouisOverhaulMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.add(BLACK_WATER_BUCKET);
            entries.add(WHITE_WATER_BUCKET);
            entries.add(ORANGE_WATER_BUCKET);
            entries.add(MAGENTA_WATER_BUCKET);
            entries.add(LIGHT_BLUE_WATER_BUCKET);
            entries.add(YELLOW_WATER_BUCKET);
            entries.add(LIME_WATER_BUCKET);
            entries.add(PINK_WATER_BUCKET);
            entries.add(GRAY_WATER_BUCKET);
            entries.add(LIGHT_GRAY_WATER_BUCKET);
            entries.add(CYAN_WATER_BUCKET);
            entries.add(PURPLE_WATER_BUCKET);
            entries.add(BLUE_WATER_BUCKET);
            entries.add(BROWN_WATER_BUCKET);
            entries.add(GREEN_WATER_BUCKET);
            entries.add(RED_WATER_BUCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(BROWN_BEAR_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(AZURITE);
            entries.add(GLOW_UPGRADE_SMITHING_TEMPLATE);
            entries.add(PULSING_UPGRADE_SMITHING_TEMPLATE);
            entries.add(EMPYREAN_POWDER);
            entries.add(LLAMAS_SPIT);
            entries.add(BAT_FANG);
            entries.add(ENDERMITE_HEART);
            entries.add(DECAYING_FLESH);
            entries.add(SANDY_FLESH);
            entries.add(CHILLED_BONE);
            entries.add(TOXIC_BONE);
            entries.add(DECREPIT_BONE);
            entries.add(CHILLED_BONE_MEAL);
            entries.add(TOXIC_BONE_MEAL);
            entries.add(DECREPIT_BONE_MEAL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(NETHERITE_HORSE_ARMOR);
            entries.add(ADVANCED_ARROW);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(FISH_STEW);
            entries.add(ROTTEN_STEW);
            entries.add(VEGETABLE_STEW);
            entries.add(PURIFIED_WATER_BOTTLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(BEAR_CLAW);
            entries.add(AMETHYST_DAGGER);
            entries.add(POTION_POUCH);
            entries.add(Items.BUNDLE);
            entries.add(SADDLED_GOAT_HORN);
            entries.add(PET_RECOVERY_COMPASS);
            entries.add(RECALL_CLOCK);
            entries.add(LARGE_BUNDLE);
            entries.add(MASSIVE_BUNDLE);
            entries.add(PIONEER_POUCH);
        });
    }
}
