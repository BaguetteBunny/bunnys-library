package bunny.lib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import bunny.lib.BunnyLib;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorMaterial.class)
public class ArmorMaterialMixin {
    ArmorMaterial self = (ArmorMaterial) (Object) this;

    @ModifyReturnValue(method = "createAttributeModifiers", at = @At("RETURN"))
    private AttributeModifiersComponent LOM$addChainmailKnockbackResistance(AttributeModifiersComponent original, EquipmentType equipmentType) {
        if (self == ArmorMaterials.CHAIN) {
            EquipmentSlot slot = equipmentType.getEquipmentSlot();
            AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();

            for (AttributeModifiersComponent.Entry entry : original.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());

            builder.add(
                    EntityAttributes.KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(
                            Identifier.of(BunnyLib.MOD_ID, "chainmail_kb_resistance"),
                            0.25, EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.forEquipmentSlot(slot)
            );
            return builder.build();
        }
        return original;
    }
}
