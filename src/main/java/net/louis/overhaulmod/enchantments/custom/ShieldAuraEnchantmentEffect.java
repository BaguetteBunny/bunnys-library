package net.louis.overhaulmod.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record ShieldAuraEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ShieldAuraEnchantmentEffect> CODEC = MapCodec.unit(ShieldAuraEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof PlayerEntity player
                && player.isBlocking()
                && (player.getStatusEffect(StatusEffects.RESISTANCE) == null || player.getStatusEffect(StatusEffects.RESISTANCE).getDuration() <= 2)
        ) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2, 0, true, true));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
