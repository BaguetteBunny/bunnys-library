package net.louis.overhaulmod.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.entity.custom.thrown.projectile.BrickEntity;
import net.louis.overhaulmod.entity.custom.thrown.projectile.NetherBrickEntity;
import net.louis.overhaulmod.entity.custom.thrown.projectile.PurifiedWaterEntity;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.GlowLightManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

import static net.louis.overhaulmod.utils.EnchantmentUtils.hasEnchant;

public class ModUseItemEvents {
    public static void register() {
        UseItemCallback.EVENT.register(ModUseItemEvents::getLlamaSpitBottle);
        UseItemCallback.EVENT.register(ModUseItemEvents::useGlowInk);
        UseItemCallback.EVENT.register(ModUseItemEvents::useFireBlastEnchant);
        UseItemCallback.EVENT.register(ModUseItemEvents::useSeasoning);

        // Projectiles
        UseItemCallback.EVENT.register(ModUseItemEvents::useBrick);
        UseItemCallback.EVENT.register(ModUseItemEvents::useNetherBrick);
        UseItemCallback.EVENT.register(ModUseItemEvents::usePurifiedWaterBottle);
    }

    private static ActionResult getLlamaSpitBottle(PlayerEntity player, World world, Hand hand) {
        if (world.isClient()) return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.GLASS_BOTTLE)) {
            double reach = 5.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            LlamaSpitEntity targetSpit = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof LlamaSpitEntity spit)) continue;
                Box entityBox = spit.getBoundingBox().expand(0.5D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetSpit = spit;
                    }
                }
            }

            if (targetSpit != null) {
                if (!player.getAbilities().creativeMode) stack.decrement(1);
                ItemStack result = new ItemStack(ModItems.LLAMAS_SPIT);
                if (!player.getInventory().insertStack(result)) {
                    player.dropItem(result, false);
                }
                world.playSound(null, targetSpit.getBlockPos(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
                targetSpit.discard();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;

    }

    private static ActionResult useBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.BRICK) || player.getItemCooldownManager().isCoolingDown(stack) || !ModConfig.INSTANCE.enableThrowableBricks) {
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

        return ActionResult.SUCCESS;
    }

    private static ActionResult useNetherBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.NETHER_BRICK) || player.getItemCooldownManager().isCoolingDown(stack) || !ModConfig.INSTANCE.enableThrowableBricks) {
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

        return ActionResult.SUCCESS;
    }

    private static ActionResult usePurifiedWaterBottle(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(ModItems.PURIFIED_WATER_BOTTLE) || player.getItemCooldownManager().isCoolingDown(stack)) return ActionResult.PASS;

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW,
                SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        PurifiedWaterEntity purifiedWaterEntity = new PurifiedWaterEntity(world, player);
        purifiedWaterEntity.setItem(stack);
        purifiedWaterEntity.setVelocity(player, player.getPitch(), player.getYaw(), -10.0F, 1.0F, 1.0F);
        world.spawnEntity(purifiedWaterEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack, 5);

        return ActionResult.SUCCESS;
    }

    private static ActionResult useGlowInk(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.isClient() || item != Items.GLOW_INK_SAC || player.getItemCooldownManager().isCoolingDown(stack)) {
            return ActionResult.PASS;
        }

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack, 200);

        List<Entity> nearby = world.getOtherEntities(
                player,
                player.getBoundingBox().expand(12)
        );

        for (Entity e : nearby) {
            if (e instanceof LivingEntity living && !living.isGlowing()) {
                living.setGlowing(true);
                GlowLightManager.addGlowingEntity(living, 80);
            }
        }

        world.playSound(
                null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.PLAYERS,
                1.0F, 1.0F
        );

        player.swingHand(hand, true);
        return ActionResult.SUCCESS;
    }

    private static ActionResult useFireBlastEnchant(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.isClient() || player.getItemCooldownManager().isCoolingDown(stack)) return ActionResult.PASS;


        if (item instanceof FlintAndSteelItem && hasEnchant(stack, "fire_blast")) {
            player.getItemCooldownManager().set(stack, 20);
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS,
                    1.0F, 2.0F
            );

            Entity fireball = new SmallFireballEntity(world, player, player.getRotationVector());
            fireball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            world.spawnEntity(fireball);

            stack.damage(3, player, player.getPreferredEquipmentSlot(stack));
            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static ActionResult useSeasoning(PlayerEntity player, World world, Hand hand) {
        ItemStack powderStack = player.getStackInHand(Hand.OFF_HAND);
        ItemStack foodStack = player.getStackInHand(Hand.MAIN_HAND);

        if (hand != Hand.MAIN_HAND || !powderStack.isOf(ModItems.EMPYREAN_POWDER) || world.isClient() || foodStack.get(DataComponentTypes.FOOD) == null || foodStack.get(ModComponents.SEASONING) != null) return ActionResult.PASS;

        if (foodStack.getCount() <= 8) foodStack.set(ModComponents.SEASONING, ModItems.EMPYREAN_POWDER);
        else {
            foodStack.setCount(foodStack.getCount() - 8);

            ItemStack newStack = foodStack.copy();
            newStack.setCount(8);
            newStack.set(ModComponents.SEASONING, ModItems.EMPYREAN_POWDER);
            player.giveItemStack(newStack);
        }

        powderStack.decrementUnlessCreative(1, player);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1f, 2f);
        player.swingHand(hand, true);
        return ActionResult.SUCCESS;
    }
}
