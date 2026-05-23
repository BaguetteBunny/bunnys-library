package bunnys.qol;

import bunnys.qol.entity.ModEntities;
import bunnys.qol.events.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static bunny.lib.utils.ItemManager.HEAD_EQUIPPABLE_ITEMS;

public class BunnysQol implements ModInitializer {
	public static final String MOD_ID = "bunnys-qol";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();

		ModAttackEntityEvents.register();
		ModLootTableEvents.replaceLootTables();
		ModUseBlockEvents.register();
		ModUseEntityEvents.register();
		ModUseItemEvents.register();

		addDataComponentsToExistingItems();
	}

	private void addDataComponentsToExistingItems() {
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(
					new HashSet<>(HEAD_EQUIPPABLE_ITEMS),
					(builder, item) -> builder.add(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).build())
			);
		});
	}
}