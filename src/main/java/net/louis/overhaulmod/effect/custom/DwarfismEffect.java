package net.louis.overhaulmod.effect.custom;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class DwarfismEffect extends StatusEffect {
    private static final Identifier MODIFIER_ID = Identifier.of(LouisOverhaulMod.MOD_ID, "dwarfism_scale");

    public DwarfismEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(AttributeContainer attributes, int amplifier) {
        EntityAttributeInstance scale = attributes.getCustomInstance(EntityAttributes.SCALE);
        if (scale != null) {
            scale.addPersistentModifier(
                    new EntityAttributeModifier(
                            MODIFIER_ID,
                            -0.25 * (amplifier + 1),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    )
            );
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributes) {
        EntityAttributeInstance scale = attributes.getCustomInstance(EntityAttributes.SCALE);
        if (scale != null) {
            if (scale.getModifier(MODIFIER_ID) != null) {
                scale.removeModifier(MODIFIER_ID);
            }
        }
    }
}
