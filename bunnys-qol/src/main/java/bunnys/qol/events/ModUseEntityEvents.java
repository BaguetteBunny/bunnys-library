package bunnys.qol.events;

import bunny.lib.config.ModConfig;
import bunnys.qol.mixin.ArmorStandEntityAccessor;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.*;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public class ModUseEntityEvents {
    public static void register() {
        UseEntityCallback.EVENT.register(ModUseEntityEvents::changeArmorStandVariant);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::dyeShulkers);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useBrushOnDyedShulkers);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useBlazePowderOnStriders);
    }

    private static ActionResult useBlazePowderOnStriders(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!world.isClient() && player.getStackInHand(hand).getItem() == Items.BLAZE_POWDER && entity instanceof StriderEntity striderEntity && !striderEntity.isCold() && ModConfig.INSTANCE.overchargedStriders) {
            striderEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 9, true, true));

            world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_LAVA_POP,
                    SoundCategory.NEUTRAL, 2.0F, 0.2F);
            world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_STRIDER_HAPPY,
                    SoundCategory.NEUTRAL, 2.0F, 0.5F);
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    entity.getX() + 0,
                    entity.getY() + 1,
                    entity.getZ() + 0,
                    10,
                    0.5, 0.5, 0.5,
                    0.05
            );

            return ActionResult.SUCCESS_SERVER;
        }

        return ActionResult.PASS;
    }

    private static ActionResult changeArmorStandVariant(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getMainHandStack();
        if (world.isClient() || !player.isSneaking() || !(entity instanceof ArmorStandEntity stand)  || !ModConfig.INSTANCE.changeArmorstand) {
            return ActionResult.PASS;
        }

        if (stack.isOf(Items.STICK) && !stand.shouldShowArms()) {
            stand.setShowArms(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.getItem() instanceof AxeItem && !stand.isSmall()) {
            ((ArmorStandEntityAccessor) stand).bunny$callSetSmall(true);
            stack.damage(1, player, EquipmentSlot.MAINHAND);
        } else if (stack.isOf(Items.PHANTOM_MEMBRANE) && !stand.isInvisible()) {
            stand.setInvisible(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.isOf(Items.GLOW_INK_SAC) && !stand.isGlowing()) {
            stand.setGlowing(true);
            stack.decrementUnlessCreative(1, player);
        }
        else {
            ArrayList<ItemStack> playerItems = new ArrayList<>();
            playerItems.add(player.getEquippedStack(EquipmentSlot.HEAD));
            playerItems.add(player.getEquippedStack(EquipmentSlot.CHEST));
            playerItems.add(player.getEquippedStack(EquipmentSlot.LEGS));
            playerItems.add(player.getEquippedStack(EquipmentSlot.FEET));
            playerItems.add(player.getEquippedStack(EquipmentSlot.MAINHAND));

            player.equipStack(EquipmentSlot.HEAD, stand.getEquippedStack(EquipmentSlot.HEAD));
            player.equipStack(EquipmentSlot.CHEST, stand.getEquippedStack(EquipmentSlot.CHEST));
            player.equipStack(EquipmentSlot.LEGS, stand.getEquippedStack(EquipmentSlot.LEGS));
            player.equipStack(EquipmentSlot.FEET, stand.getEquippedStack(EquipmentSlot.FEET));

            stand.equipStack(EquipmentSlot.HEAD, playerItems.get(0));
            stand.equipStack(EquipmentSlot.CHEST, playerItems.get(1));
            stand.equipStack(EquipmentSlot.LEGS, playerItems.get(2));
            stand.equipStack(EquipmentSlot.FEET, playerItems.get(3));

            if (stand.shouldShowArms()) {
                player.equipStack(EquipmentSlot.MAINHAND, stand.getEquippedStack(EquipmentSlot.MAINHAND));
                stand.equipStack(EquipmentSlot.MAINHAND, playerItems.get(4));
            }
        }

        world.playSound(null, stand.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS_SERVER;
    }

    private static ActionResult dyeShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient() || !(stack.getItem() instanceof DyeItem dyeItem) || !ModConfig.INSTANCE.dyeShulkerAndBrush) return ActionResult.PASS;

        if (entity instanceof ShulkerEntity shulker) {
            stack.decrementUnlessCreative(1, player);
            shulker.setVariant(Optional.ofNullable(dyeItem.getColor()));

            player.swingHand(hand, true);
            return ActionResult.SUCCESS_SERVER;
        }

        return ActionResult.PASS;
    }

    private static ActionResult useBrushOnDyedShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (hand != Hand.MAIN_HAND  || !ModConfig.INSTANCE.dyeShulkerAndBrush) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient
                || !(stack.getItem() instanceof BrushItem)
                || !(entity instanceof ShulkerEntity shulker)
                || shulker.getVariant().isEmpty()
                || entityHitResult == null
        ) return ActionResult.PASS;

        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        world.playSound(null, x, y, z, SoundEvents.ENTITY_ARMADILLO_BRUSH, SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        if (!player.isCreative()) stack.damage(8, player, EquipmentSlot.MAINHAND);

        Optional<DyeColor> variant = shulker.getVariant();
        DyeColor color = variant.get();
        Item dyeItem = DyeItem.byColor(color);
        ItemStack dyeStack = new ItemStack(dyeItem);

        ItemEntity dyeItemEntity = new ItemEntity(world, x, y+0.4, z, dyeStack);
        world.spawnEntity(dyeItemEntity);

        player.swingHand(hand, true);
        return ActionResult.SUCCESS_SERVER;
    }
}
