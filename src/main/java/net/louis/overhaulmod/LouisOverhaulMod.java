package net.louis.overhaulmod;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.block.entity.ModBlockEntities;
import net.louis.overhaulmod.cauldron.ModCauldron;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.enchantments.ModEnchantmentEffects;
import net.louis.overhaulmod.entity.ModEntities;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.louis.overhaulmod.events.*;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.mixin.accessor.PointOfInterestTypesAccessor;
import net.louis.overhaulmod.potion.ModPotions;
import net.louis.overhaulmod.screen.ModScreenHandlers;
import net.louis.overhaulmod.sound.ModSounds;
import net.louis.overhaulmod.utils.DespawnManager;
import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.louis.overhaulmod.utils.GlowLightManager;
import net.louis.overhaulmod.events.ModLootTableEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EnchantableComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.louis.overhaulmod.utils.ItemManager.ENCHANTABLE;
import static net.louis.overhaulmod.utils.ItemManager.HEAD_EQUIPPABLE_ITEMS;

public class LouisOverhaulMod implements ModInitializer {
	public static final String MOD_ID = "louis-overhaul-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfig.load();

		EnchantmentCapRegistry.register();

		ModSounds.registerSounds();
		ModComponents.registerDataComponentTypes();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModBlocks.registerModBlocks();
		ModFluids.register();
		ModItems.registerModItems();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModCauldron.registerBehaviors();
		ModEnchantmentEffects.registerEnchantmentEffects();

		ModLootTableEvents.modifyLootTables();
		ModLootTableEvents.replaceLootTables();

		ClientPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			client.execute(() -> {
				System.out.println("=== CLIENT RECIPE CHECK ===");
				System.out.println("Recipe manager: " + client.world.getRecipeManager().getClass());
			});
		});

		// All Events
		ModAttackEntityEvents.register();
		ModAttackBlockEvents.register();
		ModBreakEvents.register();
		ModServerLivingEntityEvents.register();
		ModServerPlayConnectionEvents.register();
		ModUseBlockEvents.register();
		ModUseEntityEvents.register();
		ModUseItemEvents.register();

		// Misc
		registerDispenserProjectles();
		tickGlobal();
		replaceFletcherPOI();
		addDataComponentsToExistingItems();

		// Mob
		registerSpawning();
		ModEntities.registerModEntities();
		registerMobAttributes();


	}

	private void tickGlobal() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			GlowLightManager.tick();
			DespawnManager.tick();
		});
	}

	private void replaceFletcherPOI() {
		RegistryEntry<PointOfInterestType> fletcherPOI = Registries.POINT_OF_INTEREST_TYPE.getOrThrow(PointOfInterestTypes.FLETCHER);
		Set<BlockState> states = ImmutableSet.copyOf(ModBlocks.ADVANCED_FLETCHING_TABLE.getStateManager().getStates());
		Map<BlockState, RegistryEntry<PointOfInterestType>> poiMap = PointOfInterestTypesAccessor.getPoiStatesToType();

		for (BlockState state : states) poiMap.put(state, fletcherPOI);
	}

	private void registerDispenserProjectles() {
		DispenserBlock.registerProjectileBehavior(ModItems.ADVANCED_ARROW);
	}

	private void registerSpawning() {
		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(BiomeKeys.CHERRY_GROVE, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.RIVER, BiomeKeys.FLOWER_FOREST),
				SpawnGroup.CREATURE, ModEntities.BROWN_BEAR, 10, 1, 2);
		SpawnRestriction.register(ModEntities.BROWN_BEAR, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);

	}

	private void registerMobAttributes() {
		FabricDefaultAttributeRegistry.register(ModEntities.BROWN_BEAR, BearEntity.createBrownBearAttributes());
	}

	private void addDataComponentsToExistingItems() {
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(
                    new HashSet<>(HEAD_EQUIPPABLE_ITEMS),
					(builder, item) -> builder.add(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).build())
			);
		});

		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(
					new HashSet<>(ENCHANTABLE),
					(builder, item) -> builder.add(DataComponentTypes.ENCHANTABLE, new EnchantableComponent(10))
			);
		});
	}
}