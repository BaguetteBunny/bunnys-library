package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.entity.ReplaceDiskEnchantmentEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplaceDiskEnchantmentEffect.class)
public class ReplaceDiskEnchantmentEffectMixin {
    ReplaceDiskEnchantmentEffect effect = (ReplaceDiskEnchantmentEffect)(Object)this;

    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void increaseRadiusAndApply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.betterFrostWalker) return;

        BlockPos blockPos = BlockPos.ofFloored(pos).add(effect.offset());
        Random random = user.getRandom();

        int originalRadius = (int)effect.radius().getValue(level);
        int originalHeight = (int)effect.height().getValue(level);

        int radius = originalRadius * 2;
        int height = originalHeight * 2;

        for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-radius, height, -radius), blockPos.add(radius, Math.min(height - 1, 0), radius))) {
            if (blockPos2.getSquaredDistanceFromCenter(pos.getX(), blockPos2.getY() + 0.5, pos.getZ()) < MathHelper.square(radius)
                    && effect.predicate().map(predicate -> predicate.test(world, blockPos2)).orElse(true)
                    && world.setBlockState(blockPos2, effect.blockState().get(random, blockPos2))) {
                effect.triggerGameEvent().ifPresent(gameEvent -> world.emitGameEvent(user, gameEvent, blockPos2));
            }
        }

        ci.cancel();
    }
}
