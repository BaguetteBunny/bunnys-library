package net.louis.overhaulmod.entity;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.louis.overhaulmod.entity.custom.misc.ChairEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModEntities {
    private static final RegistryKey<EntityType<?>> BROWN_BEAR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, "brown_bear"));
    private static final RegistryKey<EntityType<?>> CHAIR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, "chair_entity"));

    public static final EntityType<BearEntity> BROWN_BEAR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "brown_bear"),
            EntityType.Builder.create(BearEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.4f, 1.4f).build(BROWN_BEAR_KEY));

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(1.0f, 0.49f).build(CHAIR_KEY));


    public static void registerModEntities() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Entities for " + LouisOverhaulMod.MOD_ID);
    }
}