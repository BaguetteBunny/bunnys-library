package net.louis.overhaulmod.events;

import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.enchantments.ModEnchantments;
import net.louis.overhaulmod.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModLootTableEvents {
    private static final Identifier BAT_ID = Identifier.ofVanilla("entities/bat");
    private static final Identifier ENDERMITE_ID = Identifier.ofVanilla("entities/endermite");
    private static final Identifier HUSK_ID = Identifier.ofVanilla("entities/husk");
    private static final Identifier STRAY_ID = Identifier.ofVanilla("entities/stray");
    private static final Identifier WARDEN_ID = Identifier.ofVanilla("entities/warden");
    private static final Identifier DROWNED_ID = Identifier.ofVanilla("entities/drowned");
    private static final Identifier BOGGED_ID = Identifier.ofVanilla("entities/bogged");
    private static final Identifier WITHER_SKELETON_ID = Identifier.ofVanilla("entities/wither_skeleton");
    private static final Identifier WITHER_ID = Identifier.ofVanilla("entities/wither");

    public static void replaceLootTables() {
        LootTableEvents.REPLACE.register((key, lootManager, source, registry) -> {
            RegistryKey<Enchantment> lootingKey = Enchantments.LOOTING;
            RegistryWrapper<Enchantment> enchantmentRegistry = registry.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            RegistryEntry<Enchantment> lootingEntry = enchantmentRegistry.getOrThrow(lootingKey);

            if (LootTables.SNIFFER_DIGGING_GAMEPLAY.equals(key)) {
                LootTable.Builder table = LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(Items.TORCHFLOWER_SEEDS))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(Items.PITCHER_POD))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.MYSTIC_ROSE))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.COBALT_FLOWER))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.LAVENDER_DANDELION))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.WILTED_POPPY))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.SHINY_CORNFLOWER))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModBlocks.HEART_FLOWER))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )

                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.2f))
                                .with(ItemEntry.builder(ModItems.EMPYREAN_POWDER))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        );

                return table.build();
            }

            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder customPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(RandomChanceLootCondition.builder(1f)) // 100% Drop Rate
                        .with(ItemEntry.builder(Items.ECHO_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(32.0f, 48.0f)).build())
                        .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                return LootTable.builder().pool(customPool).build();
            }

            if (HUSK_ID.equals(key.getValue())) {
                LootTable.Builder table = LootTable.builder()
                        // Sandy Flesh
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(ModItems.SANDY_FLESH))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Gold Ingot
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.008333f,0.003333f))
                                .with(ItemEntry.builder(Items.GOLD_INGOT))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Beetroot
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.008333f,0.003333f))
                                .with(ItemEntry.builder(Items.BEETROOT))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Cactus
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.008333f,0.003333f))
                                .with(ItemEntry.builder(Items.CACTUS))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()));
                return table.build();
            }

            if (DROWNED_ID.equals(key.getValue())) {
                LootTable.Builder table = LootTable.builder()
                        // Decaying Flesh
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(ModItems.DECAYING_FLESH))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Copper Ingot
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.008333f,0.003333f))
                                .with(ItemEntry.builder(Items.COPPER_INGOT))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Nautilus Shell
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.008333f,0.003333f))
                                .with(ItemEntry.builder(Items.NAUTILUS_SHELL))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()));
                return table.build();
            }

            if (STRAY_ID.equals(key.getValue())) {
                LootTable.Builder table = LootTable.builder()
                        // Chilled Bone
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(ModItems.CHILLED_BONE))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Arrow
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(Items.ARROW))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Slowness Arrow
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.5f,0.15f))
                                .with(ItemEntry.builder(Items.TIPPED_ARROW).apply(SetPotionLootFunction.builder(Potions.SLOWNESS)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()));
                return table.build();
            }

            if (BOGGED_ID.equals(key.getValue())) {
                LootTable.Builder table = LootTable.builder()
                        // Toxic Bone
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(ModItems.TOXIC_BONE))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Arrow
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(Items.ARROW))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Poison Arrow
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.5f,0.15f))
                                .with(ItemEntry.builder(Items.TIPPED_ARROW).apply(SetPotionLootFunction.builder(Potions.POISON)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()));
                return table.build();
            }

            if (WITHER_SKELETON_ID.equals(key.getValue())) {
                LootTable.Builder table = LootTable.builder()
                        // Decrepit Bone
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(ModItems.DECREPIT_BONE))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Coal
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(Items.COAL))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build()))

                        // Wither Skeleton Skull
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registry, 0.025f,0.01f))
                                .with(ItemEntry.builder(Items.WITHER_SKELETON_SKULL))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build()));

                return table.build();
            }
            return null;
        });
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            // Make bats drop bat fangs
            if (BAT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(RandomChanceLootCondition.builder(0.50f)) // 50% Drop Rate
                        .with(ItemEntry.builder(ModItems.BAT_FANG))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build())
                        .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            // Make endermite drop heart
            if (ENDERMITE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(RandomChanceLootCondition.builder(0.50f)) // 50% Drop Rate
                        .with(ItemEntry.builder(ModItems.ENDERMITE_HEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            // Make wither drop more nether stars
            if (WITHER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .with(ItemEntry.builder(Items.NETHER_STAR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f)).build())
                        .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(0.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            // Add Fire Blast to Nether Chests
            if (LootTables.NETHER_BRIDGE_CHEST.equals(key)) {
                RegistryEntry<Enchantment> fireStrike = registry.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.FIRE_BLAST);

                ItemEnchantmentsComponent.Builder enchantmentBuilder =
                        new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
                enchantmentBuilder.add(fireStrike, 1);

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.012f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetComponentsLootFunction.builder(DataComponentTypes.STORED_ENCHANTMENTS, enchantmentBuilder.build())));

                tableBuilder.pool(poolBuilder);
            }

            // Add Magic Touch to Plains Village
            if (LootTables.VILLAGE_PLAINS_CHEST.equals(key)) {
                RegistryEntry<Enchantment> magicTouch = registry.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.MAGIC_TOUCH);

                ItemEnchantmentsComponent.Builder enchantmentBuilder =
                        new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
                enchantmentBuilder.add(magicTouch, 1);

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetComponentsLootFunction.builder(DataComponentTypes.STORED_ENCHANTMENTS, enchantmentBuilder.build())));

                tableBuilder.pool(poolBuilder);
            }

            // Add Purified Water to Desert Well & Temples
            if (LootTables.DESERT_WELL_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.333f))
                        .with(ItemEntry.builder(ModItems.PURIFIED_WATER_BOTTLE).weight(1));
                tableBuilder.pool(poolBuilder);
            }
            if (LootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.125f))
                        .with(ItemEntry.builder(ModItems.PURIFIED_WATER_BOTTLE).weight(1));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}

