package bunny.lib.events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import bunny.lib.utils.EnchantmentUtils;
import bunny.lib.utils.RadiusGetterUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        AttackEntityCallback.EVENT.register(ModAttackEntityEvents::activateShieldBash);
    }

    private static ActionResult activateShieldBash(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack s = player.getStackInHand(Hand.MAIN_HAND);
        if (!(s.getItem() instanceof ShieldItem)
                || !EnchantmentUtils.hasEnchant(s, "shield_bash")
                || player.getItemCooldownManager().isCoolingDown(s)
        ) return ActionResult.PASS;

        player.getItemCooldownManager().set(s, 140);
        List<Entity> entities = RadiusGetterUtil.getEntitiesInRadius(world, player.getBlockPos().toCenterPos(), 5);

        for (Entity e : entities) {
            Vec3d push = e.getPos().subtract(player.getPos()).normalize().multiply(2);
            e.addVelocity(push.x, push.y, push.z);
            e.velocityModified = true;
        }

        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.PLAYERS, 2.f, 2.f);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 2.f, 2.f);

        for (int i = 1; i < 4; i++) {
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), 0, 0, i);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), 0, 0, -i);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), i, 0, 0);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), i, 0, i);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), i, 0, -i);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), -i, 0, 0);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), -i, 0, i);
            world.addParticle(ParticleTypes.CRIT, player.getBlockX(), player.getBlockY() + 0.5, player.getBlockZ(), -i, 0, -i);
        }

        return ActionResult.SUCCESS;
    }
}
