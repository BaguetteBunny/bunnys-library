package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.utils.AdvancementUtil;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

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
                        Text.literal("Wither Mastery"),
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
    }
}
