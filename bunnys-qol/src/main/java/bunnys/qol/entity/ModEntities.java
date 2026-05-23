package bunnys.qol.entity;

import bunnys.qol.BunnysQol;
import bunnys.qol.entity.custom.misc.ChairEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModEntities {
    private static final RegistryKey<EntityType<?>> CHAIR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BunnysQol.MOD_ID, "chair_entity"));

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BunnysQol.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(1.0f, 0.49f).build(CHAIR_KEY));

    public static void registerModEntities() {
        BunnysQol.LOGGER.info("Registering Mod Entities for " + BunnysQol.MOD_ID);
    }
}