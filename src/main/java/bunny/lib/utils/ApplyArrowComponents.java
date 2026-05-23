package bunny.lib.utils;

import bunny.lib.component.ModComponents;
import bunny.lib.mixin.accessor.PersistentProjectileEntityAccessor;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ApplyArrowComponents {
    public static void applyArrowComponentAbilities(ArrowEntity arrow, ItemStack stack) {
        ComponentMap components = stack.getComponents();
        double totalDamage = 2.f;

        if (components.contains(ModComponents.ARROW_FOOT)) {
            if (Items.PHANTOM_MEMBRANE.equals(components.get(ModComponents.ARROW_FOOT))) {
                arrow.setVelocity(arrow.getVelocity().normalize().multiply(2.25f));}

            if (Items.ARMADILLO_SCUTE.equals(components.get(ModComponents.ARROW_FOOT))) {
                DespawnManager.addDespawnTimerToEntity(arrow, 200);}
        }

        if (components.contains(ModComponents.ARROW_SHAFT)) {
            if (Items.BREEZE_ROD.equals(components.get(ModComponents.ARROW_SHAFT))) {
                arrow.setNoGravity(true);
                DespawnManager.addDespawnTimerToEntity(arrow, 1200);}

            if (Items.BLAZE_ROD.equals(components.get(ModComponents.ARROW_SHAFT))) {
                totalDamage += 1.2f;}
        }

        if (components.contains(ModComponents.ARROW_HEAD)) {
            if (Items.AMETHYST_SHARD.equals(components.get(ModComponents.ARROW_HEAD))) {
                ((PersistentProjectileEntityAccessor) arrow).callSetPierceLevel((byte) 5);}
            if (Items.ECHO_SHARD.equals(components.get(ModComponents.ARROW_HEAD))) {
                totalDamage -= .75f;}
        }

        arrow.setDamage(totalDamage);
    }
}
