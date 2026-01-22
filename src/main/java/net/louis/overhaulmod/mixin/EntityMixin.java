package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    Entity e = (Entity) (Object) this;

    @Inject(method = "isTouchingWater", at = @At("HEAD"), cancellable = true)
    private void LOM$disableWaterCollisionForModifiedArrows(CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof ArrowEntity arrow) {
            ComponentMap components = arrow.getItemStack().getComponents();
            if (components.contains(ModComponents.ARROW_FOOT) && Items.DRIED_KELP.equals(components.get(ModComponents.ARROW_FOOT))) {
                cir.setReturnValue(false);
            }
        }
    }
}
