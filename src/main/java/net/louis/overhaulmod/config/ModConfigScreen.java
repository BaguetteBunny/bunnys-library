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
                Text.of("Player Armor Stand Modifications"),
                ModConfig.INSTANCE.changeArmorstand).setSaveConsumer(v -> ModConfig.INSTANCE.changeArmorstand = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Throwable Bricks"),
                ModConfig.INSTANCE.enableThrowableBricks).setSaveConsumer(v -> ModConfig.INSTANCE.enableThrowableBricks = v).build());
        miscModule.addEntry(entry.startBooleanToggle(
                Text.of("Dyeable and Brushable Shulkers"),
                ModConfig.INSTANCE.dyeShulkerAndBrush).setSaveConsumer(v -> ModConfig.INSTANCE.dyeShulkerAndBrush = v).build());
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

        builder.setSavingRunnable(ModConfig::save);
        return builder.build();
    }
}
