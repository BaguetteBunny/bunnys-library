package bunnys.qol.mixin;

import bunny.lib.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    LivingEntity self = (LivingEntity) (Object) this;

    @Inject(method = "getAttackDistanceScalingFactor", at = @At("RETURN"), cancellable = true)
    private void LOM$injectedHeadDetectionReduction(Entity entity, CallbackInfoReturnable<Double> cir) {
        double original = cir.getReturnValue();
        ItemStack head = self.getEquippedStack(EquipmentSlot.HEAD);

        if (entity != null) {
            if (entity.getType().equals(EntityType.SKELETON) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.STRAY) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.BOGGED) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.ZOMBIE) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.HUSK) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.DROWNED) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIE_VILLAGER) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN_BRUTE) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIFIED_PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.CREEPER) && head.isOf(Items.CREEPER_HEAD)) {
                cir.setReturnValue((double) ModConfig.INSTANCE.decreaseMobHeadDetectionRange);
            } else {
                cir.setReturnValue(original);
            }
        }
    }
}

