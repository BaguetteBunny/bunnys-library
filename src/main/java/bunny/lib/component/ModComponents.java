package bunny.lib.component;

import com.mojang.serialization.Codec;
import bunny.lib.BunnyLib;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModComponents {
    public static final ComponentType<Integer> BUNDLE_MAX_FACTOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "bundle_max_factor"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<Integer> BUNDLE_CONTEXT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "bundle_context"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<String> AZURITE_REFINE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "azurite_refine"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
    public static final ComponentType<String> ORB_SHERD_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "orb_sherd_name"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
    public static final ComponentType<ParticleType<?>> ORB_PARTICLE_EFFECT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "orb_particle_effect"),
            ComponentType.<ParticleType<?>>builder().codec(Registries.PARTICLE_TYPE.getCodec()).build()
    );
    public static final ComponentType<Item> SEASONING = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "seasoning"),
            ComponentType.<Item>builder().codec(Registries.ITEM.getCodec()).build()
    );
    public static final ComponentType<Item> ARROW_SHAFT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "arrow_shaft"),
            ComponentType.<Item>builder().codec(Registries.ITEM.getCodec()).build()
            );
    public static final ComponentType<Item> ARROW_HEAD = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "arrow_head"),
            ComponentType.<Item>builder().codec(Registries.ITEM.getCodec()).build()
    );
    public static final ComponentType<Item> ARROW_FOOT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "arrow_foot"),
            ComponentType.<Item>builder().codec(Registries.ITEM.getCodec()).build()
    );

    public static final ComponentType<Boolean> GLOW_AND_PULSATE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "glow_and_pulsate"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static final ComponentType<Double> MOB_SPEED = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_speed"),
            ComponentType.<Double>builder().codec(Codec.DOUBLE).build()
    );

    public static final ComponentType<Double> MOB_JUMP = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_jump"),
            ComponentType.<Double>builder().codec(Codec.DOUBLE).build()
    );

    public static final ComponentType<Float> MOB_MAX_HEALTH = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_max_hp"),
            ComponentType.<Float>builder().codec(Codec.FLOAT).build()
    );

    public static final ComponentType<Float> MOB_HEALTH = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_hp"),
            ComponentType.<Float>builder().codec(Codec.FLOAT).build()
    );

    public static final ComponentType<String> MOB_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_name"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> MOB_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<ItemStack> MOB_ARMOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_armor"),
            ComponentType.<ItemStack>builder().codec(ItemStack.CODEC).build()
    );

    public static final ComponentType<Integer> HORSE_COLOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "horse_color"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<Integer> HORSE_IDENTIFIER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "horse_identifier"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<Boolean> HORSE_SADDLED = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "horse_saddled"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static final ComponentType<Boolean> MOB_IS_BABY = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_is_baby"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static final ComponentType<DyeColor> MOB_COLLAR_COLOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "mob_collar_color"),
            ComponentType.<DyeColor>builder().codec(DyeColor.CODEC).build()
    );

    public static final ComponentType<RegistryEntry<WolfVariant>> WOLF_VARIANT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "wolf_variant"),
            ComponentType.<RegistryEntry<WolfVariant>>builder().codec(WolfVariant.ENTRY_CODEC).build()
    );

    public static final ComponentType<RegistryEntry<CatVariant>> CAT_VARIANT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "cat_variant"),
            ComponentType.<RegistryEntry<CatVariant>>builder()
                    .codec(Registries.CAT_VARIANT.getEntryCodec())
                    .packetCodec(CatVariant.PACKET_CODEC)
                    .build()
    );


    public static final ComponentType<ParrotEntity.Variant> PARROT_VARIANT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "parrot_variant"),
            ComponentType.<ParrotEntity.Variant>builder().codec(ParrotEntity.Variant.CODEC).build()
    );

    public static final ComponentType<String> PET_TYPE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BunnyLib.MOD_ID, "pet_type"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(BunnyLib.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        BunnyLib.LOGGER.info("Registering Data Component Types for " + BunnyLib.MOD_ID);
    }
}