package net.louis.overhaulmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.ModEntities;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.custom.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems {
    public static final Item WHITE_WATER_BUCKET = registerItem("white_water_bucket", settings -> new BucketItem(ModFluids.STILL_WHITE_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item ORANGE_WATER_BUCKET = registerItem("orange_water_bucket", settings -> new BucketItem(ModFluids.STILL_ORANGE_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item MAGENTA_WATER_BUCKET = registerItem("magenta_water_bucket", settings -> new BucketItem(ModFluids.STILL_MAGENTA_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIGHT_BLUE_WATER_BUCKET = registerItem("light_blue_water_bucket", settings -> new BucketItem(ModFluids.STILL_LIGHT_BLUE_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item YELLOW_WATER_BUCKET = registerItem("yellow_water_bucket", settings -> new BucketItem(ModFluids.STILL_YELLOW_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIME_WATER_BUCKET = registerItem("lime_water_bucket", settings -> new BucketItem(ModFluids.STILL_LIME_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item PINK_WATER_BUCKET = registerItem("pink_water_bucket", settings -> new BucketItem(ModFluids.STILL_PINK_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item GRAY_WATER_BUCKET = registerItem("gray_water_bucket", settings -> new BucketItem(ModFluids.STILL_GRAY_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIGHT_GRAY_WATER_BUCKET = registerItem("light_gray_water_bucket", settings -> new BucketItem(ModFluids.STILL_LIGHT_GRAY_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item CYAN_WATER_BUCKET = registerItem("cyan_water_bucket", settings -> new BucketItem(ModFluids.STILL_CYAN_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item PURPLE_WATER_BUCKET = registerItem("purple_water_bucket", settings -> new BucketItem(ModFluids.STILL_PURPLE_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BROWN_WATER_BUCKET = registerItem("brown_water_bucket", settings -> new BucketItem(ModFluids.STILL_BROWN_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item GREEN_WATER_BUCKET = registerItem("green_water_bucket", settings -> new BucketItem(ModFluids.STILL_GREEN_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item RED_WATER_BUCKET = registerItem("red_water_bucket", settings -> new BucketItem(ModFluids.STILL_RED_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BLACK_WATER_BUCKET = registerItem("black_water_bucket", settings -> new BucketItem(ModFluids.STILL_BLACK_WATER, settings.maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item LIGHT_GRAY_NAME_TAG = registerItem("light_gray_name_tag", settings -> new ColoredNameTagItem(settings, 10329495));
    public static final Item GRAY_NAME_TAG = registerItem("gray_name_tag", settings -> new ColoredNameTagItem(settings, 4673362));
    public static final Item BLACK_NAME_TAG = registerItem("black_name_tag", settings -> new ColoredNameTagItem(settings, 1908001));
    public static final Item BROWN_NAME_TAG = registerItem("brown_name_tag", settings -> new ColoredNameTagItem(settings, 8606770));
    public static final Item RED_NAME_TAG = registerItem("red_name_tag", settings -> new ColoredNameTagItem(settings, 15597568));
    public static final Item ORANGE_NAME_TAG = registerItem("orange_name_tag", settings -> new ColoredNameTagItem(settings, 16351261));
    public static final Item YELLOW_NAME_TAG = registerItem("yellow_name_tag", settings -> new ColoredNameTagItem(settings, 16701501));
    public static final Item LIME_NAME_TAG = registerItem("lime_name_tag", settings -> new ColoredNameTagItem(settings, 8439583));
    public static final Item GREEN_NAME_TAG = registerItem("green_name_tag", settings -> new ColoredNameTagItem(settings, 6192150));
    public static final Item CYAN_NAME_TAG = registerItem("cyan_name_tag", settings -> new ColoredNameTagItem(settings, 1481884));
    public static final Item LIGHT_BLUE_NAME_TAG = registerItem("light_blue_name_tag", settings -> new ColoredNameTagItem(settings, 3847130));
    public static final Item BLUE_NAME_TAG = registerItem("blue_name_tag", settings -> new ColoredNameTagItem(settings, 3949738));
    public static final Item PURPLE_NAME_TAG = registerItem("purple_name_tag", settings -> new ColoredNameTagItem(settings, 8991416));
    public static final Item MAGENTA_NAME_TAG = registerItem("magenta_name_tag", settings -> new ColoredNameTagItem(settings, 13061821));
    public static final Item PINK_NAME_TAG = registerItem("pink_name_tag", settings -> new ColoredNameTagItem(settings, 15961002));

    public static final Item WISP_NAME_TAG = registerItem("wisp_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 6999775, 7570825));
    public static final Item PEACH_NAME_TAG = registerItem("peach_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 14958198, 16750922));
    public static final Item RADIOACTIVE_NAME_TAG = registerItem("radioactive_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 8887944, 2939728));
    public static final Item FRAGRANT_NAME_TAG = registerItem("fragrant_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 11268523, 16747190));
    public static final Item AQUAMARINE_NAME_TAG = registerItem("aquamarine_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 62207, 5029509));
    public static final Item VOLCANIC_NAME_TAG = registerItem("volcanic_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 10327959, 11418432));
    public static final Item BLOSSOM_NAME_TAG = registerItem("blossom_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 16711898, 9850992));
    public static final Item CATACLYSM_NAME_TAG = registerItem("cataclysm_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 2890267, 9719120));
    public static final Item IMMOLATION_NAME_TAG = registerItem("immolation_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 5980195, 11615241));
    public static final Item IRIDESCENT_NAME_TAG = registerItem("iridescent_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 10672383, 15255290));
    public static final Item PRIMORDIAL_NAME_TAG = registerItem("primordial_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 11062654, 16777184));
    public static final Item ASHEN_NAME_TAG = registerItem("ashen_name_tag", settings -> new ColoredNameTagItem(settings.rarity(Rarity.EPIC).fireproof(), 10132122, 4473924));

    public static final Item AZURITE = registerItem("azurite", Item::new);

    public static final Item BEAR_CLAW = registerItem("bear_claw",
            settings -> new Item(settings.maxDamage(1024).maxCount(1)
                    .attributeModifiers(AttributeModifiersComponent.builder()
                            .add(
                                    EntityAttributes.BLOCK_INTERACTION_RANGE,
                                    new EntityAttributeModifier(
                                            Identifier.of("bear_claw", "block_interaction_range"),
                                            2.5,
                                            EntityAttributeModifier.Operation.ADD_VALUE),
                                    AttributeModifierSlot.HAND)
                            .add(
                                    EntityAttributes.ATTACK_DAMAGE,
                                    new EntityAttributeModifier(
                                            Identifier.of("bear_claw", "attack_damage"),
                                            4.0,
                                            EntityAttributeModifier.Operation.ADD_VALUE
                                    ),
                                    AttributeModifierSlot.MAINHAND
                            )
                            .build())));

    public static final Item GLOW_UPGRADE_SMITHING_TEMPLATE = registerItem("glow_upgrade_smithing_template",
            Item::new);

    public static final Item PULSING_UPGRADE_SMITHING_TEMPLATE = registerItem("pulsing_upgrade_smithing_template",
            settings -> new Item(settings.rarity(Rarity.UNCOMMON)));

    public static final Item PURIFIED_WATER_BOTTLE = registerItem("purified_water_bottle",
            settings -> new Item(settings.maxCount(1).rarity(Rarity.RARE)));

    public static final Item EMPYREAN_POWDER = registerItem("empyrean_powder",
            settings -> new Item(settings.maxCount(16)));

    public static final Item ADVANCED_ARROW = registerItem("advanced_arrow",
            ArrowItem::new);

    public static final Item CHILLED_BONE = registerItem("chilled_bone",
            Item::new);
    public static final Item CHILLED_BONE_MEAL = registerItem("chilled_bone_meal",
            Item::new);
    public static final Item TOXIC_BONE = registerItem("toxic_bone",
            Item::new);
    public static final Item TOXIC_BONE_MEAL = registerItem("toxic_bone_meal",
            Item::new);
    public static final Item DECREPIT_BONE = registerItem("decrepit_bone",
            Item::new);
    public static final Item DECREPIT_BONE_MEAL = registerItem("decrepit_bone_meal",
            Item::new);

    public static final Item BAT_FANG = registerItem("bat_fang",
            Item::new);
    public static final Item ENDERMITE_HEART = registerItem("endermite_heart",
            Item::new);
    public static final Item DECAYING_FLESH = registerItem("decaying_flesh",
            settings -> new Item(settings.food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .build(),
                    ConsumableComponent
                            .builder()
                            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900), 1f))
                            .build()
            ))
    );

    public static final Item SANDY_FLESH = registerItem("sandy_flesh",
            settings -> new Item(settings.food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .build(),
                    ConsumableComponent
                            .builder()
                            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900), 1f))
                            .build()
            ))
    );

    public static final Item FISH_STEW = registerItem("fish_stew",
            settings -> new Item(settings.maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(8)
                    .saturationModifier(6.5f)
                    .build(),
                    ConsumableComponent
                            .builder()
                            .consumeSeconds(0.8f)
                            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 9000), .75f))
                            .build()
            ))
    );

    public static final Item VEGETABLE_STEW = registerItem("vegetable_stew",
            settings -> new Item(settings.maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(6)
                    .saturationModifier(8f)
                    .build(),
                    ConsumableComponent.builder().consumeSeconds(0.8f).build())));

    public static final Item ROTTEN_STEW = registerItem("rotten_stew",
            settings -> new Item(settings.maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(5)
                    .saturationModifier(.1f)
                    .build(),
                    ConsumableComponent
                            .builder()
                            .consumeSeconds(0.8f)
                            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900), .6f))
                            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 900), .6f))
                            .build()
            ))
    );

    public static final Item POTION_POUCH = registerItem("potion_pouch",
            settings -> new PotionPouch(settings.maxCount(1).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT),
                    Identifier.of(LouisOverhaulMod.MOD_ID, "item/potion_pouch.png"),
                    Identifier.of(LouisOverhaulMod.MOD_ID, "item/potion_pouch.png")));

    public static final Item LARGE_BUNDLE = registerItem("large_bundle",
            settings -> new BigBundleItem(settings.maxCount(1), 128));
    public static final Item MASSIVE_BUNDLE = registerItem("massive_bundle",
            settings -> new BigBundleItem(settings.maxCount(1), 256));
    public static final Item PIONEER_POUCH = registerItem("pioneer_pouch",
            settings -> new PioneerPouch(settings.maxCount(1), 512));

    public static final Item SADDLED_GOAT_HORN = registerItem("saddled_goat_horn",
            settings -> new SaddledGoatHorn(settings.maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item PET_RECOVERY_COMPASS = registerItem("pet_recovery_compass",
            settings -> new PetRecoveryCompass(settings.maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item RECALL_CLOCK = registerItem("recall_clock",
            settings -> new RecallClock(settings.maxCount(1)));

    public static final Item LLAMAS_SPIT = registerItem("llamas_spit",
            settings -> new Item(settings.rarity(Rarity.UNCOMMON)));

    public static final Item AMETHYST_DAGGER = registerItem("amethyst_dagger",
            AmethystDagger::new);

    public static final Item BROWN_BEAR_SPAWN_EGG = registerItem("brown_bear_spawn_egg",
            settings -> new SpawnEggItem(ModEntities.BROWN_BEAR, 0xa37539, 0xbf935a, settings));

    public static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name)))));
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
            entries.add(ADVANCED_ARROW);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(FISH_STEW);
            entries.add(ROTTEN_STEW);
            entries.add(VEGETABLE_STEW);
            entries.add(PURIFIED_WATER_BOTTLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(LIGHT_GRAY_NAME_TAG);
            entries.add(GRAY_NAME_TAG);
            entries.add(BLACK_NAME_TAG);
            entries.add(BROWN_NAME_TAG);
            entries.add(RED_NAME_TAG);
            entries.add(ORANGE_NAME_TAG);
            entries.add(YELLOW_NAME_TAG);
            entries.add(LIME_NAME_TAG);
            entries.add(GREEN_NAME_TAG);
            entries.add(CYAN_NAME_TAG);
            entries.add(LIGHT_BLUE_NAME_TAG);
            entries.add(BLUE_NAME_TAG);
            entries.add(PURPLE_NAME_TAG);
            entries.add(MAGENTA_NAME_TAG);
            entries.add(PINK_NAME_TAG);

            entries.add(WISP_NAME_TAG);
            entries.add(PEACH_NAME_TAG);
            entries.add(RADIOACTIVE_NAME_TAG);
            entries.add(FRAGRANT_NAME_TAG);
            entries.add(AQUAMARINE_NAME_TAG);
            entries.add(ASHEN_NAME_TAG);
            entries.add(BLOSSOM_NAME_TAG);
            entries.add(CATACLYSM_NAME_TAG);
            entries.add(IMMOLATION_NAME_TAG);
            entries.add(IRIDESCENT_NAME_TAG);
            entries.add(PRIMORDIAL_NAME_TAG);
            entries.add(VOLCANIC_NAME_TAG);

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
