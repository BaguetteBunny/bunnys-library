package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.AdvancementUtil;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry entryToMastery = Advancement.Builder.create()
                .display(
                        Items.DIAMOND_SWORD,
                        Text.literal("Mastery"),
                        Text.literal("Obtain 64 Nether Stars"),
                        AdvancementUtil.ELITE,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("got_lots_nether_stars", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create()
                                .items(registryLookup.getOrThrow(RegistryKeys.ITEM), Items.NETHER_STAR)
                                .count(NumberRange.IntRange.atLeast(64))
                                .build()
                ))
                .build(consumer, LouisOverhaulMod.MOD_ID + "/got_lots_nether_stars");

        // Nametags
        AdvancementEntry nametagCollector = Advancement.Builder.create()
                .display(
                        Items.NAME_TAG,
                        Text.literal("Name Tag Collector"),
                        Text.literal("Obtain a Rare Name Tag"),
                        AdvancementUtil.ELITE,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(entryToMastery)
                .criterion("got_aquamarine",
                        InventoryChangedCriterion.Conditions.items(ModItems.AQUAMARINE_NAME_TAG))
                .criterion("got_cataclysm",
                        InventoryChangedCriterion.Conditions.items(ModItems.CATACLYSM_NAME_TAG))
                .criterion("got_immolation",
                        InventoryChangedCriterion.Conditions.items(ModItems.IMMOLATION_NAME_TAG))
                .criterion("got_ashen",
                        InventoryChangedCriterion.Conditions.items(ModItems.ASHEN_NAME_TAG))
                .criterion("got_peach",
                        InventoryChangedCriterion.Conditions.items(ModItems.PEACH_NAME_TAG))
                .criterion("got_wisp",
                        InventoryChangedCriterion.Conditions.items(ModItems.WISP_NAME_TAG))
                .criterion("got_primordial",
                        InventoryChangedCriterion.Conditions.items(ModItems.PRIMORDIAL_NAME_TAG))
                .criterion("got_iridescent",
                        InventoryChangedCriterion.Conditions.items(ModItems.IRIDESCENT_NAME_TAG))
                .criterion("got_radioactive",
                        InventoryChangedCriterion.Conditions.items(ModItems.RADIOACTIVE_NAME_TAG))
                .criterion("got_fragrant",
                        InventoryChangedCriterion.Conditions.items(ModItems.FRAGRANT_NAME_TAG))
                .criterion("got_volcanic",
                        InventoryChangedCriterion.Conditions.items(ModItems.VOLCANIC_NAME_TAG))
                .criterion("got_blossom",
                        InventoryChangedCriterion.Conditions.items(ModItems.BLOSSOM_NAME_TAG))
                .requirements(AdvancementRequirements.anyOf(List.of(
                        "got_aquamarine",
                        "got_cataclysm",
                        "got_immolation",
                        "got_ashen",
                        "got_peach",
                        "got_wisp",
                        "got_primordial",
                        "got_iridescent",
                        "got_radioactive",
                        "got_fragrant",
                        "got_volcanic",
                        "got_blossom"
                )))
                .build(consumer, LouisOverhaulMod.MOD_ID + "/obtain_rare_name_tag");

        AdvancementEntry nametagMastery = Advancement.Builder.create()
                .display(
                        Items.NAME_TAG,
                        Text.literal("Name Tag Mastery"),
                        Text.literal("Obtain All Rare Name Tag"),
                        AdvancementUtil.ELITE,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(nametagCollector)
                .criterion("got_aquamarine",
                        InventoryChangedCriterion.Conditions.items(ModItems.AQUAMARINE_NAME_TAG))
                .criterion("got_cataclysm",
                        InventoryChangedCriterion.Conditions.items(ModItems.CATACLYSM_NAME_TAG))
                .criterion("got_immolation",
                        InventoryChangedCriterion.Conditions.items(ModItems.IMMOLATION_NAME_TAG))
                .criterion("got_ashen",
                        InventoryChangedCriterion.Conditions.items(ModItems.ASHEN_NAME_TAG))
                .criterion("got_peach",
                        InventoryChangedCriterion.Conditions.items(ModItems.PEACH_NAME_TAG))
                .criterion("got_wisp",
                        InventoryChangedCriterion.Conditions.items(ModItems.WISP_NAME_TAG))
                .criterion("got_primordial",
                        InventoryChangedCriterion.Conditions.items(ModItems.PRIMORDIAL_NAME_TAG))
                .criterion("got_iridescent",
                        InventoryChangedCriterion.Conditions.items(ModItems.IRIDESCENT_NAME_TAG))
                .criterion("got_radioactive",
                        InventoryChangedCriterion.Conditions.items(ModItems.RADIOACTIVE_NAME_TAG))
                .criterion("got_fragrant",
                        InventoryChangedCriterion.Conditions.items(ModItems.FRAGRANT_NAME_TAG))
                .criterion("got_volcanic",
                        InventoryChangedCriterion.Conditions.items(ModItems.VOLCANIC_NAME_TAG))
                .criterion("got_blossom",
                        InventoryChangedCriterion.Conditions.items(ModItems.BLOSSOM_NAME_TAG))
                .requirements(AdvancementRequirements.allOf(List.of(
                        "got_aquamarine",
                        "got_cataclysm",
                        "got_immolation",
                        "got_ashen",
                        "got_peach",
                        "got_wisp",
                        "got_primordial",
                        "got_iridescent",
                        "got_radioactive",
                        "got_fragrant",
                        "got_volcanic",
                        "got_blossom"
                )))
                .build(consumer, LouisOverhaulMod.MOD_ID + "/obtain_all_rare_name_tag");
    }
}
