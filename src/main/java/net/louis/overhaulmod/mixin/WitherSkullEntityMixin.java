package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.utils.accessors.WitherHealthAccessor;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.WitherSkullEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WitherSkullEntity.class)
public class WitherSkullEntityMixin {
    WitherSkullEntity self = (WitherSkullEntity) (Object) this;

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"), index = 0)
    private StatusEffectInstance LOM$changeWitherAmplifier(StatusEffectInstance original) {
        if (original.getEffectType() == StatusEffects.WITHER && self.getOwner() instanceof WitherHealthAccessor we && we.isHalfHp() && ModConfig.INSTANCE.enableWitherBossPhases) {
            return new StatusEffectInstance(
                    StatusEffects.WITHER,
                    original.getDuration() / 3,
                    2,
                    original.isAmbient(),
                    original.shouldShowParticles(),
                    original.shouldShowIcon()
            );
        }
        return original;
    }
}
