package net.louis.overhaulmod.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {
    public static Screen create(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Louis Overhaul Mod Config"));

        ConfigEntryBuilder entry = builder.entryBuilder();

        var enchantmentModule = builder.getOrCreateCategory(Text.of("Enchantment Module"));
        enchantmentModule.addEntry(entry.startBooleanToggle(
                Text.of("Grindstone Removes Enchant One by One"),
                ModConfig.INSTANCE.grindstoneRemoveEnchantsOneByOne).setSaveConsumer(v -> ModConfig.INSTANCE.grindstoneRemoveEnchantsOneByOne = v).build());
        enchantmentModule.addEntry(entry.startBooleanToggle(
                Text.of("Obfuscate Curses"),
                ModConfig.INSTANCE.obfuscateCurses).setSaveConsumer(v -> ModConfig.INSTANCE.obfuscateCurses = v).build());
        enchantmentModule.addEntry(entry.startBooleanToggle(
                Text.of("Show Enchantment Descriptions"),
                ModConfig.INSTANCE.addEnchantmentDescriptions).setSaveConsumer(v -> ModConfig.INSTANCE.addEnchantmentDescriptions = v).build());
        enchantmentModule.addEntry(entry.startBooleanToggle(
                Text.of("Enhanced Frost Walker"),
                ModConfig.INSTANCE.betterFrostWalker).setSaveConsumer(v -> ModConfig.INSTANCE.betterFrostWalker = v).build());


        var entityModule = builder.getOrCreateCategory(Text.of("Entity Module"));
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Horses go Through Leaves"),
                ModConfig.INSTANCE.disableHorseLeafCollision).setSaveConsumer(v -> ModConfig.INSTANCE.disableHorseLeafCollision = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Enderpearl Teleports Horse while Riding"),
                ModConfig.INSTANCE.enderpearlTeleportsHorses).setSaveConsumer(v -> ModConfig.INSTANCE.enderpearlTeleportsHorses = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Endermite Teleports Player on Hit"),
                ModConfig.INSTANCE.endermiteTeleportPlayerOnHit).setSaveConsumer(v -> ModConfig.INSTANCE.endermiteTeleportPlayerOnHit = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("No Item Frame Interaction if on a Chest"),
                ModConfig.INSTANCE.disableItemFrameInteractionIfChest).setSaveConsumer(v -> ModConfig.INSTANCE.disableItemFrameInteractionIfChest = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Dyeable and Brushable Shulkers"),
                ModConfig.INSTANCE.dyeShulkerAndBrush).setSaveConsumer(v -> ModConfig.INSTANCE.dyeShulkerAndBrush = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Allow Armor Stand Modifications"),
                ModConfig.INSTANCE.changeArmorstand).setSaveConsumer(v -> ModConfig.INSTANCE.changeArmorstand = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Enable Volatile and Random Ridden Pig Speed"),
                ModConfig.INSTANCE.randomPigSpeed).setSaveConsumer(v -> ModConfig.INSTANCE.randomPigSpeed = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("More Aggressive Polar Bears"),
                ModConfig.INSTANCE.moreAggressivePolarBears).setSaveConsumer(v -> ModConfig.INSTANCE.moreAggressivePolarBears = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Stronger Wither Stats"),
                ModConfig.INSTANCE.strongerWither).setSaveConsumer(v -> ModConfig.INSTANCE.strongerWither = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Enable Wither Boss Phases"),
                ModConfig.INSTANCE.enableWitherBossPhases).setSaveConsumer(v -> ModConfig.INSTANCE.enableWitherBossPhases = v).build());
        entityModule.addEntry(entry.startBooleanToggle(
                Text.of("Withers go Through Bedrock"),
                ModConfig.INSTANCE.disableWitherBedrockCollision).setSaveConsumer(v -> ModConfig.INSTANCE.disableWitherBedrockCollision = v).build());
        entityModule.addEntry(entry.startLongSlider(
                Text.of("Mob Detection Range when wearing their Head"),
                ModConfig.INSTANCE.decreaseMobHeadDetectionRange,
                0,
                35
        ).setSaveConsumer(v -> ModConfig.INSTANCE.decreaseMobHeadDetectionRange = v).build());


        var potionModule = builder.getOrCreateCategory(Text.of("Potion Module"));
        potionModule.addEntry(entry.startBooleanToggle(
                Text.of("Dragon Breath Cauldron Clense Curses"),
                ModConfig.INSTANCE.enableCurseClensing).setSaveConsumer(v -> ModConfig.INSTANCE.enableCurseClensing = v).build());
        potionModule.addEntry(entry.startBooleanToggle(
                Text.of("Dragon Breath Cauldron Turns Potions into Lingering Potions"),
                ModConfig.INSTANCE.enableLingeringTransform).setSaveConsumer(v -> ModConfig.INSTANCE.enableLingeringTransform = v).build());
        potionModule.addEntry(entry.startBooleanToggle(
                Text.of("Honey Cauldron Clears All Effects"),
                ModConfig.INSTANCE.enableHoneyClearEffects).setSaveConsumer(v -> ModConfig.INSTANCE.enableHoneyClearEffects = v).build());
        potionModule.addEntry(entry.startBooleanToggle(
                Text.of("Tipped Arrows drop Lingering Potion on Hit"),
                ModConfig.INSTANCE.doLingeringDropOnHit).setSaveConsumer(v -> ModConfig.INSTANCE.doLingeringDropOnHit = v).build());


        var stewModule = builder.getOrCreateCategory(Text.of("Stew Module"));
        stewModule.addEntry(entry.startBooleanToggle(
                Text.of("Stew Usable on Mobs"),
                ModConfig.INSTANCE.enableStewMobEffects).setSaveConsumer(v -> ModConfig.INSTANCE.enableStewMobEffects = v).build());
        stewModule.addEntry(entry.startBooleanToggle(
                Text.of("Suspicious Stew Mob Randomizer"),
                ModConfig.INSTANCE.enableSusStewRNG).setSaveConsumer(v -> ModConfig.INSTANCE.enableSusStewRNG = v).build());


        var miscModule = builder.getOrCreateCategory(Text.of("Misc Module"));
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Sitting with Saddles"),
                ModConfig.INSTANCE.enableSitting).setSaveConsumer(v -> ModConfig.INSTANCE.enableSitting = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Right Click Harvesting"),
                ModConfig.INSTANCE.enableRcHarvest).setSaveConsumer(v -> ModConfig.INSTANCE.enableRcHarvest = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Throwable Bricks"),
                ModConfig.INSTANCE.enableThrowableBricks).setSaveConsumer(v -> ModConfig.INSTANCE.enableThrowableBricks = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Oxidize Copper with Clock"),
                ModConfig.INSTANCE.oxidizeCopperWithClock).setSaveConsumer(v -> ModConfig.INSTANCE.oxidizeCopperWithClock = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Put Items in Suspicious Blocks"),
                ModConfig.INSTANCE.useOnSusSand).setSaveConsumer(v -> ModConfig.INSTANCE.useOnSusSand = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Damageless Feathers"),
                ModConfig.INSTANCE.enableFeatherAttack).setSaveConsumer(v -> ModConfig.INSTANCE.enableFeatherAttack = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Retexturable Player Heads with Nametags"),
                ModConfig.INSTANCE.retexturePlayerHead).setSaveConsumer(v -> ModConfig.INSTANCE.retexturePlayerHead = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Bonemeal More Crops"),
                ModConfig.INSTANCE.useBonemealOnOtherCrops).setSaveConsumer(v -> ModConfig.INSTANCE.useBonemealOnOtherCrops = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Feather Falling disables Crop Trampling"),
                ModConfig.INSTANCE.disableCropTrampleWithFeatherFalling).setSaveConsumer(v -> ModConfig.INSTANCE.disableCropTrampleWithFeatherFalling = v).build());


        builder.setSavingRunnable(ModConfig::save);
        return builder.build();
    }
}
