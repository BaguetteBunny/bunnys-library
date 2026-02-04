package net.louis.overhaulmod.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Shadow @Final @Mutable public static Item MUSHROOM_STEW;
    @Shadow @Final @Mutable public static Item BEETROOT_SOUP;
    @Shadow @Final @Mutable public static Item RABBIT_STEW;

    @Shadow @Final @Mutable public static Item SNOWBALL;
    @Shadow @Final @Mutable public static Item EGG;
    @Shadow @Final @Mutable public static Item HONEY_BOTTLE;
    @Shadow @Final @Mutable public static Item ARMOR_STAND;
    @Shadow @Final @Mutable public static Item SNIFFER_EGG;
    @Shadow @Final @Mutable public static Item DRAGON_EGG;

    @Shadow @Final @Mutable public static Item MINECART;
    @Shadow @Final @Mutable public static Item CHEST_MINECART;
    @Shadow @Final @Mutable public static Item FURNACE_MINECART;
    @Shadow @Final @Mutable public static Item TNT_MINECART;
    @Shadow @Final @Mutable public static Item HOPPER_MINECART;
    @Shadow @Final @Mutable public static Item COMMAND_BLOCK_MINECART;

    @Shadow @Final @Mutable public static Item WHITE_BED;
    @Shadow @Final @Mutable public static Item ORANGE_BED;
    @Shadow @Final @Mutable public static Item MAGENTA_BED;
    @Shadow @Final @Mutable public static Item LIGHT_BLUE_BED;
    @Shadow @Final @Mutable public static Item YELLOW_BED;
    @Shadow @Final @Mutable public static Item LIME_BED;
    @Shadow @Final @Mutable public static Item PINK_BED;
    @Shadow @Final @Mutable public static Item GRAY_BED;
    @Shadow @Final @Mutable public static Item LIGHT_GRAY_BED;
    @Shadow @Final @Mutable public static Item CYAN_BED;
    @Shadow @Final @Mutable public static Item PURPLE_BED;
    @Shadow @Final @Mutable public static Item BLUE_BED;
    @Shadow @Final @Mutable public static Item BROWN_BED;
    @Shadow @Final @Mutable public static Item GREEN_BED;
    @Shadow @Final @Mutable public static Item RED_BED;
    @Shadow @Final @Mutable public static Item BLACK_BED;

    @Shadow @Final @Mutable public static Item WHITE_BANNER;
    @Shadow @Final @Mutable public static Item ORANGE_BANNER;
    @Shadow @Final @Mutable public static Item MAGENTA_BANNER;
    @Shadow @Final @Mutable public static Item LIGHT_BLUE_BANNER;
    @Shadow @Final @Mutable public static Item YELLOW_BANNER;
    @Shadow @Final @Mutable public static Item LIME_BANNER;
    @Shadow @Final @Mutable public static Item PINK_BANNER;
    @Shadow @Final @Mutable public static Item GRAY_BANNER;
    @Shadow @Final @Mutable public static Item LIGHT_GRAY_BANNER;
    @Shadow @Final @Mutable public static Item CYAN_BANNER;
    @Shadow @Final @Mutable public static Item PURPLE_BANNER;
    @Shadow @Final @Mutable public static Item BLUE_BANNER;
    @Shadow @Final @Mutable public static Item BROWN_BANNER;
    @Shadow @Final @Mutable public static Item GREEN_BANNER;
    @Shadow @Final @Mutable public static Item RED_BANNER;
    @Shadow @Final @Mutable public static Item BLACK_BANNER;

    @Shadow @Final @Mutable public static Item SHULKER_BOX;
    @Shadow @Final @Mutable public static Item WHITE_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item ORANGE_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item MAGENTA_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item LIGHT_BLUE_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item YELLOW_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item LIME_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item PINK_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item GRAY_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item LIGHT_GRAY_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item CYAN_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item PURPLE_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item BLUE_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item BROWN_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item GREEN_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item RED_SHULKER_BOX;
    @Shadow @Final @Mutable public static Item BLACK_SHULKER_BOX;

    @Shadow @Final @Mutable public static Item OAK_SIGN;
    @Shadow @Final @Mutable public static Item SPRUCE_SIGN;
    @Shadow @Final @Mutable public static Item BIRCH_SIGN;
    @Shadow @Final @Mutable public static Item JUNGLE_SIGN;
    @Shadow @Final @Mutable public static Item ACACIA_SIGN;
    @Shadow @Final @Mutable public static Item CHERRY_SIGN;
    @Shadow @Final @Mutable public static Item DARK_OAK_SIGN;
    @Shadow @Final @Mutable public static Item MANGROVE_SIGN;
    @Shadow @Final @Mutable public static Item BAMBOO_SIGN;
    @Shadow @Final @Mutable public static Item CRIMSON_SIGN;
    @Shadow @Final @Mutable public static Item WARPED_SIGN;
    @Shadow @Final @Mutable public static Item OAK_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item SPRUCE_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item BIRCH_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item JUNGLE_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item ACACIA_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item CHERRY_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item DARK_OAK_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item MANGROVE_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item BAMBOO_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item CRIMSON_HANGING_SIGN;
    @Shadow @Final @Mutable public static Item WARPED_HANGING_SIGN;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void LOM$changeItemStacks(CallbackInfo ci) {
        Registry.register(Registries.ITEM, Identifier.ofVanilla("mushroom_stew"),
                MUSHROOM_STEW = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                        .build(),
                        ConsumableComponent
                                .builder()
                                .consumeSeconds(0.8f)
                                .build()
                )
        ));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("beetroot_soup"),
                BEETROOT_SOUP = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                                .build(),
                        ConsumableComponent
                                .builder()
                                .consumeSeconds(0.8f)
                                .build()
                )
                ));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("rabbit_stew"),
                RABBIT_STEW = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(10)
                        .saturationModifier(0.6f)
                                .build(),
                        ConsumableComponent
                                .builder()
                                .consumeSeconds(0.8f)
                                .build()
                )
                ));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("snowball"),
                SNOWBALL = new SnowballItem(new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("egg"),
                EGG = new EggItem(new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("honey_bottle"),
                HONEY_BOTTLE = new Item(new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE).food(FoodComponents.HONEY_BOTTLE).maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("armor_stand"),
                ARMOR_STAND = new ArmorStandItem(new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("dragon_egg"),
                DRAGON_EGG = new BlockItem(Blocks.DRAGON_EGG, new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("sniffer_egg"),
                SNIFFER_EGG = new BlockItem(Blocks.SNIFFER_EGG, new Item.Settings().maxCount(16)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("shulker_box"),
                SHULKER_BOX = new BlockItem(Blocks.SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("shulker_box"),
                SHULKER_BOX = new BlockItem(Blocks.SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("white_shulker_box"),
                WHITE_SHULKER_BOX = new BlockItem(Blocks.WHITE_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("orange_shulker_box"),
                ORANGE_SHULKER_BOX = new BlockItem(Blocks.ORANGE_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("magenta_shulker_box"),
                MAGENTA_SHULKER_BOX = new BlockItem(Blocks.MAGENTA_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_blue_shulker_box"),
                LIGHT_BLUE_SHULKER_BOX = new BlockItem(Blocks.LIGHT_BLUE_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("yellow_shulker_box"),
                YELLOW_SHULKER_BOX = new BlockItem(Blocks.YELLOW_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("lime_shulker_box"),
                LIME_SHULKER_BOX = new BlockItem(Blocks.LIME_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("pink_shulker_box"),
                PINK_SHULKER_BOX = new BlockItem(Blocks.PINK_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("gray_shulker_box"),
                GRAY_SHULKER_BOX = new BlockItem(Blocks.GRAY_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_gray_shulker_box"),
                LIGHT_GRAY_SHULKER_BOX = new BlockItem(Blocks.LIGHT_GRAY_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("cyan_shulker_box"),
                CYAN_SHULKER_BOX = new BlockItem(Blocks.CYAN_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("purple_shulker_box"),
                PURPLE_SHULKER_BOX = new BlockItem(Blocks.PURPLE_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("blue_shulker_box"),
                BLUE_SHULKER_BOX = new BlockItem(Blocks.BLUE_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("brown_shulker_box"),
                BROWN_SHULKER_BOX = new BlockItem(Blocks.BROWN_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("green_shulker_box"),
                GREEN_SHULKER_BOX = new BlockItem(Blocks.GREEN_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("red_shulker_box"),
                RED_SHULKER_BOX = new BlockItem(Blocks.RED_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("black_shulker_box"),
                BLACK_SHULKER_BOX = new BlockItem(Blocks.BLACK_SHULKER_BOX, new Item.Settings().maxCount(16).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("white_banner"),
                WHITE_BANNER = new BannerItem(Blocks.WHITE_BANNER, Blocks.WHITE_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("orange_banner"),
                ORANGE_BANNER = new BannerItem(Blocks.ORANGE_BANNER, Blocks.ORANGE_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("magenta_banner"),
                MAGENTA_BANNER = new BannerItem(Blocks.MAGENTA_BANNER, Blocks.MAGENTA_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_blue_banner"),
                LIGHT_BLUE_BANNER = new BannerItem(Blocks.LIGHT_BLUE_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("yellow_banner"),
                YELLOW_BANNER = new BannerItem(Blocks.YELLOW_BANNER, Blocks.YELLOW_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("lime_banner"),
                LIME_BANNER = new BannerItem(Blocks.LIME_BANNER, Blocks.LIME_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("pink_banner"),
                PINK_BANNER = new BannerItem(Blocks.PINK_BANNER, Blocks.PINK_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("gray_banner"),
                GRAY_BANNER = new BannerItem(Blocks.GRAY_BANNER, Blocks.GRAY_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_gray_banner"),
                LIGHT_GRAY_BANNER = new BannerItem(Blocks.LIGHT_GRAY_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("cyan_banner"),
                CYAN_BANNER = new BannerItem(Blocks.CYAN_BANNER, Blocks.CYAN_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("purple_banner"),
                PURPLE_BANNER = new BannerItem(Blocks.PURPLE_BANNER, Blocks.PURPLE_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("blue_banner"),
                BLUE_BANNER = new BannerItem(Blocks.BLUE_BANNER, Blocks.BLUE_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("brown_banner"),
                BROWN_BANNER = new BannerItem(Blocks.BROWN_BANNER, Blocks.BROWN_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("green_banner"),
                GREEN_BANNER = new BannerItem(Blocks.GREEN_BANNER, Blocks.GREEN_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("red_banner"),
                RED_BANNER = new BannerItem(Blocks.RED_BANNER, Blocks.RED_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("black_banner"),
                BLACK_BANNER = new BannerItem(Blocks.BLACK_BANNER, Blocks.BLACK_WALL_BANNER, new Item.Settings().maxCount(64).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("white_bed"),
                WHITE_BED = new BedItem(Blocks.WHITE_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("orange_bed"),
                ORANGE_BED = new BedItem(Blocks.ORANGE_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("magenta_bed"),
                MAGENTA_BED = new BedItem(Blocks.MAGENTA_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_blue_bed"),
                LIGHT_BLUE_BED = new BedItem(Blocks.LIGHT_BLUE_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("yellow_bed"),
                YELLOW_BED = new BedItem(Blocks.YELLOW_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("lime_bed"),
                LIME_BED = new BedItem(Blocks.LIME_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("pink_bed"),
                PINK_BED = new BedItem(Blocks.PINK_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("gray_bed"),
                GRAY_BED = new BedItem(Blocks.GRAY_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("light_gray_bed"),
                LIGHT_GRAY_BED = new BedItem(Blocks.LIGHT_GRAY_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("cyan_bed"),
                CYAN_BED = new BedItem(Blocks.CYAN_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("purple_bed"),
                PURPLE_BED = new BedItem(Blocks.PURPLE_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("blue_bed"),
                BLUE_BED = new BedItem(Blocks.BLUE_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("brown_bed"),
                BROWN_BED = new BedItem(Blocks.BROWN_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("green_bed"),
                GREEN_BED = new BedItem(Blocks.GREEN_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("red_bed"),
                RED_BED = new BedItem(Blocks.RED_BED, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("black_bed"),
                BLACK_BED = new BedItem(Blocks.BLACK_BED, new Item.Settings().maxCount(16)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("minecart"),
                MINECART = new MinecartItem(EntityType.MINECART, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("chest_minecart"),
                CHEST_MINECART = new MinecartItem(EntityType.CHEST_MINECART, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("tnt_minecart"),
                TNT_MINECART = new MinecartItem(EntityType.TNT_MINECART, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("furnace_minecart"),
                FURNACE_MINECART = new MinecartItem(EntityType.FURNACE_MINECART, new Item.Settings().maxCount(16)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("command_block_minecart"),
                COMMAND_BLOCK_MINECART = new MinecartItem(EntityType.COMMAND_BLOCK_MINECART, new Item.Settings().maxCount(16).rarity(Rarity.EPIC)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("hopper_minecart"),
                HOPPER_MINECART = new MinecartItem(EntityType.CHEST_MINECART, new Item.Settings().maxCount(16)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("oak_sign"),
                OAK_SIGN = new SignItem(Blocks.OAK_SIGN, Blocks.OAK_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("spruce_sign"),
                SPRUCE_SIGN = new SignItem(Blocks.SPRUCE_SIGN, Blocks.SPRUCE_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("birch_sign"),
                BIRCH_SIGN = new SignItem(Blocks.BIRCH_SIGN, Blocks.BIRCH_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("jungle_sign"),
                JUNGLE_SIGN = new SignItem(Blocks.JUNGLE_SIGN, Blocks.JUNGLE_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("acacia_sign"),
                ACACIA_SIGN = new SignItem(Blocks.ACACIA_SIGN, Blocks.ACACIA_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("dark_oak_sign"),
                DARK_OAK_SIGN = new SignItem(Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("mangrove_sign"),
                MANGROVE_SIGN = new SignItem(Blocks.MANGROVE_SIGN, Blocks.MANGROVE_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("cherry_sign"),
                CHERRY_SIGN = new SignItem(Blocks.CHERRY_SIGN, Blocks.CHERRY_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("bamboo_sign"),
                BAMBOO_SIGN = new SignItem(Blocks.BAMBOO_SIGN, Blocks.BAMBOO_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("crimson_sign"),
                CRIMSON_SIGN = new SignItem(Blocks.CRIMSON_SIGN, Blocks.CRIMSON_WALL_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("warped_sign"),
                WARPED_SIGN = new SignItem(Blocks.WARPED_SIGN, Blocks.WARPED_WALL_SIGN, new Item.Settings().maxCount(64)));

        Registry.register(Registries.ITEM, Identifier.ofVanilla("oak_hanging_sign"),
                OAK_HANGING_SIGN = new HangingSignItem(Blocks.OAK_HANGING_SIGN, Blocks.OAK_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("spruce_hanging_sign"),
                SPRUCE_HANGING_SIGN = new HangingSignItem(Blocks.SPRUCE_HANGING_SIGN, Blocks.SPRUCE_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("birch_hanging_sign"),
                BIRCH_HANGING_SIGN = new HangingSignItem(Blocks.BIRCH_HANGING_SIGN, Blocks.BIRCH_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("jungle_hanging_sign"),
                JUNGLE_HANGING_SIGN = new HangingSignItem(Blocks.JUNGLE_HANGING_SIGN, Blocks.JUNGLE_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("acacia_hanging_sign"),
                ACACIA_HANGING_SIGN = new HangingSignItem(Blocks.ACACIA_HANGING_SIGN, Blocks.ACACIA_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("dark_oak_hanging_sign"),
                DARK_OAK_HANGING_SIGN = new HangingSignItem(Blocks.DARK_OAK_HANGING_SIGN, Blocks.DARK_OAK_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("mangrove_hanging_sign"),
                MANGROVE_HANGING_SIGN = new HangingSignItem(Blocks.MANGROVE_HANGING_SIGN, Blocks.MANGROVE_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("cherry_hanging_sign"),
                CHERRY_HANGING_SIGN = new HangingSignItem(Blocks.CHERRY_HANGING_SIGN, Blocks.CHERRY_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("bamboo_hanging_sign"),
                BAMBOO_HANGING_SIGN = new HangingSignItem(Blocks.BAMBOO_HANGING_SIGN, Blocks.BAMBOO_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("crimson_hanging_sign"),
                CRIMSON_HANGING_SIGN = new HangingSignItem(Blocks.CRIMSON_HANGING_SIGN, Blocks.CRIMSON_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
        Registry.register(Registries.ITEM, Identifier.ofVanilla("warped_hanging_sign"),
                WARPED_HANGING_SIGN = new HangingSignItem(Blocks.WARPED_HANGING_SIGN, Blocks.WARPED_WALL_HANGING_SIGN, new Item.Settings().maxCount(64)));
    }
}
