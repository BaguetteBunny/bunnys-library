package bunnys.qol.events;

import bunny.lib.config.ModConfig;
import bunny.lib.utils.EnchantmentUtils;
import bunny.lib.utils.RadiusGetterUtil;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModAttackEntityEvents {
    public static void register() {
        AttackEntityCallback.EVENT.register(ModAttackEntityEvents::featherDamageFreeKB);
    }

    private static ActionResult featherDamageFreeKB(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.FEATHER) && !world.isClient && entity instanceof LivingEntity e && ModConfig.INSTANCE.enableFeatherAttack) {
            if (entity.timeUntilRegen > 0) return ActionResult.FAIL;
            player.swingHand(hand, true);

            double knockbackRes = e.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE);
            double strength = (knockbackRes > 1 || knockbackRes < 0) ? 0 : 0.4*(1-knockbackRes);

            e.takeKnockback(strength, player.getX() - entity.getX(), player.getZ() - entity.getZ());
            e.timeUntilRegen = 10;
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }
}
