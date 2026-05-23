package bunny.lib.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class GroundedEffect extends StatusEffect {
    public GroundedEffect(StatusEffectCategory category, int color){
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!world.isClient() && !entity.isOnGround() && !(entity instanceof PlayerEntity player && disallowedPlayerStates(player))) {
            entity.addVelocity(0, -0.025, 0);
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    private boolean disallowedPlayerStates(PlayerEntity player) {
        return player.isSpectator()
                || player.isSwimming()
                || player.isPushedByFluids()
                || player.isDescending()
                || player.isUsingRiptide()
                || player.getAbilities().allowFlying;
    }
}
