package bunnys.qol.events;

import bunny.lib.config.ModConfig;
import bunnys.qol.entity.custom.thrown.projectile.BrickEntity;
import bunnys.qol.entity.custom.thrown.projectile.NetherBrickEntity;
import bunnys.qol.entity.custom.thrown.projectile.ResinBrickEntity;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static bunny.lib.utils.ItemManager.HEAD_EQUIPPABLE_ITEMS;

public class ModUseItemEvents {
    public static void register() {
        UseItemCallback.EVENT.register(ModUseItemEvents::cancelItemEquipIfHelmed);

        // Projectiles
        UseItemCallback.EVENT.register(ModUseItemEvents::useBrick);
        UseItemCallback.EVENT.register(ModUseItemEvents::useNetherBrick);
        UseItemCallback.EVENT.register(ModUseItemEvents::useResinBrick);
    }

    private static ActionResult cancelItemEquipIfHelmed(PlayerEntity player, World world, Hand hand) {
        if (player.hasStackEquipped(EquipmentSlot.HEAD) && HEAD_EQUIPPABLE_ITEMS.contains(player.getStackInHand(hand).getItem())) {
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    private static ActionResult useBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient() || !stack.isOf(Items.BRICK) || player.getItemCooldownManager().isCoolingDown(stack) || !ModConfig.INSTANCE.enableThrowableBricks) {
            return ActionResult.PASS;
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        BrickEntity brickEntity = new BrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack, 20);

        return ActionResult.SUCCESS_SERVER;
    }

    private static ActionResult useNetherBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient() || !stack.isOf(Items.NETHER_BRICK) || player.getItemCooldownManager().isCoolingDown(stack) || !ModConfig.INSTANCE.enableThrowableBricks) {
            return ActionResult.PASS;
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        NetherBrickEntity brickEntity = new NetherBrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack, 20);

        return ActionResult.SUCCESS_SERVER;
    }

    private static ActionResult useResinBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient() || !stack.isOf(Items.RESIN_BRICK) || player.getItemCooldownManager().isCoolingDown(stack) || !ModConfig.INSTANCE.enableThrowableBricks) {
            return ActionResult.PASS;
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        ResinBrickEntity brickEntity = new ResinBrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack, 20);

        return ActionResult.SUCCESS_SERVER;
    }
}
