package bunny.lib.events;

import bunny.lib.block.ModBlocks;
import bunny.lib.enchantments.ModEnchantments;
import bunny.lib.item.ModItems;
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
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModLootTableEvents {
    private static final Identifier BAT_ID = Identifier.ofVanilla("entities/bat");
    private static final Identifier ENDERMITE_ID = Identifier.ofVanilla("entities/endermite");
    private static final Identifier WITHER_ID = Identifier.ofVanilla("entities/wither");

    public static void replaceLootTables() {
        LootTableEvents.REPLACE.register((key, lootManager, source, registry) -> {
            RegistryKey<Enchantment> lootingKey = Enchantments.LOOTING;
            RegistryWrapper<Enchantment> enchantmentRegistry = registry.getOrThrow(RegistryKeys.ENCHANTMENT);

            if (LootTables.SNIFFER_DIGGING_GAMEPLAY.equals(key)) {
                LootTable.Builder table = LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.2f))
                                .with(ItemEntry.builder(Items.TORCHFLOWER_SEEDS))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                        )
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.2f))
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
                        );

                return table.build();
            }
            return null;
        });
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {

            // Make BT Drop Sea Conqueror
            if (LootTables.BURIED_TREASURE_CHEST.equals(key)) {
                RegistryEntry<Enchantment> sc = registry.getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.SEA_CONQUEROR);

                ItemEnchantmentsComponent.Builder enchantmentBuilder =
                        new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
                enchantmentBuilder.add(sc, 1);

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetComponentsLootFunction.builder(DataComponentTypes.STORED_ENCHANTMENTS, enchantmentBuilder.build())));

                tableBuilder.pool(poolBuilder);
            }

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
                RegistryEntry<Enchantment> fireStrike = registry.getOrThrow(RegistryKeys.ENCHANTMENT)
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
                RegistryEntry<Enchantment> magicTouch = registry.getOrThrow(RegistryKeys.ENCHANTMENT)
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

