package net.louis.overhaulmod.effect;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.effect.custom.DwarfismEffect;
import net.louis.overhaulmod.effect.custom.GigantismEffect;
import net.louis.overhaulmod.effect.custom.GroundedEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> GROUNDED = registerStatusEffect("grounded",
            new GroundedEffect(StatusEffectCategory.HARMFUL, 0xa67655)
                    .addAttributeModifier(EntityAttributes.MOVEMENT_SPEED,
                            Identifier.of(LouisOverhaulMod.MOD_ID, "grounded"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> GIGANTISM = registerStatusEffect("gigantism",
            new GigantismEffect(StatusEffectCategory.HARMFUL, 0xCF368D));
    public static final RegistryEntry<StatusEffect> DWARFISM = registerStatusEffect("dwarfism",
            new DwarfismEffect(StatusEffectCategory.BENEFICIAL, 0x36CFC5));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LouisOverhaulMod.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Effects for " + LouisOverhaulMod.MOD_ID);
    }
}
