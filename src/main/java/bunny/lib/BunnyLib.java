package bunny.lib;

import bunny.lib.events.*;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import bunny.lib.block.ModBlocks;
import bunny.lib.block.entity.ModBlockEntities;
import bunny.lib.cauldron.ModCauldron;
import bunny.lib.component.ModComponents;
import bunny.lib.config.ModConfig;
import bunny.lib.effect.ModEffects;
import bunny.lib.enchantments.ModEnchantmentEffects;
import bunny.lib.entity.ModEntities;
import bunny.lib.entity.custom.living.BearEntity;
import bunny.lib.fluid.ModFluids;
import bunny.lib.item.ModItems;
import bunny.lib.mixin.accessor.PointOfInterestTypesAccessor;
import bunny.lib.potion.ModPotions;
import bunny.lib.screen.ModScreenHandlers;
import bunny.lib.sound.ModSounds;
import bunny.lib.utils.DespawnManager;
import bunny.lib.utils.EnchantmentCapRegistry;
import bunny.lib.utils.GlowLightManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EnchantableComponent;
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

import static bunny.lib.utils.ItemManager.ENCHANTABLE;

public class BunnyLib implements ModInitializer {
	public static final String MOD_ID = "bunny-lib";
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
					new HashSet<>(ENCHANTABLE),
					(builder, item) -> builder.add(DataComponentTypes.ENCHANTABLE, new EnchantableComponent(10))
			);
		});
	}
}