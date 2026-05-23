package bunnys.qol.events;

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
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetComponentsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModLootTableEvents {
    private static final Identifier HUSK_ID = Identifier.ofVanilla("entities/husk");
    private static final Identifier WARDEN_ID = Identifier.ofVanilla("entities/warden");
    private static final Identifier DROWNED_ID = Identifier.ofVanilla("entities/drowned");

    public static void replaceLootTables() {
        LootTableEvents.REPLACE.register((key, lootManager, source, registry) -> {

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
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH))
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
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH))
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
            return null;
        });
    }
}

