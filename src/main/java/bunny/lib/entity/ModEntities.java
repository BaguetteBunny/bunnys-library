package bunny.lib.entity;

import bunny.lib.BunnyLib;
import bunny.lib.entity.custom.living.BearEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModEntities {
    private static final RegistryKey<EntityType<?>> BROWN_BEAR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BunnyLib.MOD_ID, "brown_bear"));

    public static final EntityType<BearEntity> BROWN_BEAR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "brown_bear"),
            EntityType.Builder.create(BearEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.4f, 1.4f).build(BROWN_BEAR_KEY));

    public static void registerModEntities() {
        BunnyLib.LOGGER.info("Registering Mod Entities for " + BunnyLib.MOD_ID);
    }
}