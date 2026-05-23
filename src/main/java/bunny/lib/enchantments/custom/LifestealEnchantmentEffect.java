package bunny.lib.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public record LifestealEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LifestealEnchantmentEffect> CODEC = MapCodec.unit(LifestealEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        LivingEntity attacker = context.owner();
        int cappedLevel = Math.min(level, 5);

        if (user instanceof LivingEntity victim && !victim.isAlive() && attacker != null && attacker.isAlive()) {
            float healthHealed = cappedLevel * victim.getMaxHealth() / 20;
            float newHealth = Math.min(attacker.getHealth() + healthHealed, attacker.getMaxHealth());
            attacker.setHealth(newHealth);
            world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.f, 1.f);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
