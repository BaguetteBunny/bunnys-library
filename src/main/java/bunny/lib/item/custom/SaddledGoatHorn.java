package bunny.lib.item.custom;

import bunny.lib.component.ModComponents;
import bunny.lib.config.ModConfig;
import bunny.lib.mixin.accessor.HorseAccessor;
import bunny.lib.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class SaddledGoatHorn extends Item {
    public SaddledGoatHorn(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.getItemCooldownManager().isCoolingDown(stack)) return ActionResult.FAIL;

        user.getItemCooldownManager().set(stack, 60);
        if (!(entity instanceof HorseEntity horse && horse.isTame()) || (stack.get(ModComponents.MOB_UUID) != null)) {
            entity.playSound(SoundEvents.ENTITY_WANDERING_TRADER_NO, 2f, 0.5f);
            return ActionResult.FAIL;
        }

        HorseMarking marking = horse.getMarking();
        int identifier = marking.getId();

        HorseColor color = horse.getVariant();
        int colorId = color.getId();

        stack.set(ModComponents.MOB_NAME, horse.getName().getString());
        stack.set(ModComponents.MOB_UUID, horse.getUuidAsString());
        stack.set(ModComponents.MOB_HEALTH, horse.getHealth());
        stack.set(ModComponents.MOB_MAX_HEALTH, horse.getMaxHealth());
        stack.set(ModComponents.MOB_SPEED, getMovementSpeed(horse));
        stack.set(ModComponents.MOB_JUMP, getJumpHeight(horse));
        stack.set(ModComponents.HORSE_IDENTIFIER, identifier);
        stack.set(ModComponents.HORSE_COLOR, colorId);
        stack.set(ModComponents.HORSE_SADDLED, horse.isSaddled());

        ItemStack armor = horse.getBodyArmor();
        if (!armor.isEmpty() && armor.getItem() != Items.AIR && armor.getCount() > 0) {
            stack.set(ModComponents.MOB_ARMOR, armor.copy());
        }

        entity.remove(Entity.RemovalReason.DISCARDED);
        entity.playSound(SoundEvents.BLOCK_SHULKER_BOX_OPEN, 2f, 2.0f);

        if (!user.getEntityWorld().isClient()) {
            user.sendMessage(Text.of("Horse's soul saved to your horn!"), true);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(ModComponents.MOB_UUID) != null) {
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.literal("Stored Horse: " + stack.get(ModComponents.MOB_NAME)).formatted(Formatting.BLUE));
            tooltip.add(Text.literal(" "));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    private double getMovementSpeed(HorseEntity horse) {
        return Objects.requireNonNull(horse.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)).getBaseValue();
    }
    private double getJumpHeight(HorseEntity horse) {
        return Objects.requireNonNull(horse.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)).getBaseValue();
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player.getItemCooldownManager().isCoolingDown(stack)) {
            return ActionResult.PASS;
        }
        String uuidStr = stack.get(ModComponents.MOB_UUID);
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer && uuidStr != null && !uuidStr.isEmpty()) {
            world.playSound(null, player.getBlockPos(), ModSounds.SADDLED_GOAT_HORN_USE, SoundCategory.PLAYERS);

            player.getItemCooldownManager().set(stack, ModConfig.INSTANCE.saddledGoatHornCooldownInSeconds * 20);

            HorseEntity horse = EntityType.HORSE.create(world, SpawnReason.MOB_SUMMONED);
            if (horse != null) {
                // Position
                horse.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());

                // Base Stats
                horse.setHealth(stack.get(ModComponents.MOB_HEALTH));
                horse.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(stack.get(ModComponents.MOB_MAX_HEALTH));
                horse.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(stack.get(ModComponents.MOB_SPEED));
                horse.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).setBaseValue(stack.get(ModComponents.MOB_JUMP));

                // Identity
                horse.setCustomNameVisible(false);
                String name = stack.get(ModComponents.MOB_NAME);
                if (!Objects.equals(name, "Horse")) {
                    horse.setCustomName(Text.literal(name));
                    horse.setCustomNameVisible(true);
                }

                horse.setUuid(UUID.fromString(uuidStr));

                // Appearance
                HorseColor horseColorEnum = HorseColor.byId(stack.get(ModComponents.HORSE_COLOR));
                HorseMarking horseMarkingEnum = HorseMarking.byIndex(stack.get(ModComponents.HORSE_IDENTIFIER));
                ((HorseAccessor) horse).callSetHorseVariant(horseColorEnum, horseMarkingEnum);

                horse.setTame(true);

                if (Boolean.TRUE.equals(stack.get(ModComponents.HORSE_SADDLED))) {
                    ItemStack saddle = new ItemStack(Items.SADDLE);
                    horse.saddle(saddle, SoundCategory.NEUTRAL);
                }

                ItemStack horseArmor = stack.get(ModComponents.MOB_ARMOR);
                if (horseArmor != null && !horseArmor.isEmpty() && horseArmor.getCount() > 0 && horseArmor.getItem() != Items.AIR) {
                    horse.getArmorInventory().setStack(0, horseArmor);
                }

                horse.setTame(true);
                horse.setOwnerUuid(player.getUuid());
                world.spawnEntity(horse);
                stack.remove(ModComponents.MOB_UUID);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }
}

