package bunny.lib.item.custom;

import bunny.lib.component.ModComponents;
import bunny.lib.mixin.accessor.CatAccessor;
import bunny.lib.mixin.accessor.WolfAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class PetRecoveryCompass extends Item {
    public PetRecoveryCompass(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(ModComponents.MOB_UUID) != null) {
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.literal("Stored Pet: " + stack.get(ModComponents.MOB_NAME)).formatted(Formatting.BLUE));
            tooltip.add(Text.literal("Type: " + stack.get(ModComponents.PET_TYPE)).formatted(Formatting.DARK_GRAY));
            tooltip.add(Text.literal(" "));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (world.getBlockState(pos).isOf(Blocks.LODESTONE)) {
            String uuidString = stack.get(ModComponents.MOB_UUID);

            if (uuidString != null && !uuidString.isEmpty()) {
                try {
                    UUID petUUID = UUID.fromString(uuidString);
                    if (isPetDead(world, petUUID)) {
                        if (!world.isClient && world instanceof ServerWorld serverWorld) {
                            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world, SpawnReason.SPAWN_ITEM_USE);
                            double x = pos.getX() + 0.5;
                            double y = pos.getY() + 1;
                            double z = pos.getZ() + 0.5;
                            assert lightning != null;
                            lightning.refreshPositionAfterTeleport(x, y, z);
                            world.spawnEntity(lightning);

                            String petType = stack.get(ModComponents.PET_TYPE);
                            if (Objects.equals(petType, "Cat")) {
                                CatEntity pet = EntityType.CAT.create(world, SpawnReason.MOB_SUMMONED);
                                assert pet != null;
                                assert player != null;

                                pet.setTamed(true, true);
                                pet.setUuid(UUID.fromString(uuidString));
                                pet.setOwnerUuid(player.getUuid());
                                String targetName = stack.get(ModComponents.MOB_NAME);

                                assert targetName != null;
                                pet.setCustomName(Text.literal(targetName));
                                if (!targetName.equals("Cat")) {
                                    pet.setCustomNameVisible(true);
                                }

                                pet.setBaby(Boolean.TRUE.equals(stack.get(ModComponents.MOB_IS_BABY)));
                                pet.setVariant(stack.get(ModComponents.CAT_VARIANT));
                                ((CatAccessor) pet).callSetCollarColor(stack.get(ModComponents.MOB_COLLAR_COLOR));

                                pet.refreshPositionAndAngles(x, y, z, -player.getYaw(), 90.0f);
                                world.spawnEntity(pet);
                                pet.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 5, false, false));

                            } else if (Objects.equals(petType, "Parrot")) {
                                ParrotEntity pet = EntityType.PARROT.create(world, SpawnReason.MOB_SUMMONED);
                                assert pet != null;
                                assert player != null;

                                pet.setTamed(true, true);
                                pet.setUuid(UUID.fromString(uuidString));
                                pet.setOwnerUuid(player.getUuid());
                                String targetName = stack.get(ModComponents.MOB_NAME);
                                pet.setVariant(stack.get(ModComponents.PARROT_VARIANT));

                                assert targetName != null;
                                pet.setCustomName(Text.literal(targetName));
                                if (!targetName.equals("Parrot")) {pet.setCustomNameVisible(true);}

                                pet.refreshPositionAndAngles(x, y, z, -player.getYaw(), 90.0f);
                                world.spawnEntity(pet);
                                pet.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 5, false, false));

                            } else {
                                WolfEntity pet = EntityType.WOLF.create(world, SpawnReason.MOB_SUMMONED);
                                assert pet != null;
                                assert player != null;

                                pet.setTamed(true, true);
                                pet.setUuid(UUID.fromString(uuidString));
                                pet.setOwnerUuid(player.getUuid());
                                String targetName = stack.get(ModComponents.MOB_NAME);

                                assert targetName != null;
                                pet.setCustomName(Text.literal(targetName));
                                if (!targetName.equals("Wolf")) {pet.setCustomNameVisible(true);}

                                pet.setBaby(Boolean.TRUE.equals(stack.get(ModComponents.MOB_IS_BABY)));
                                pet.setVariant(stack.get(ModComponents.WOLF_VARIANT));
                                ((WolfAccessor) pet).callSetCollarColor(stack.get(ModComponents.MOB_COLLAR_COLOR));

                                pet.refreshPositionAndAngles(x, y, z, -player.getYaw(), 90.0f);
                                world.spawnEntity(pet);
                                pet.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 5, false, false));
                            }

                            player.setStackInHand(context.getHand(), ItemStack.EMPTY);
                            world.playSound(null, pos, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.BLOCKS, 1f, 1f);
                            world.playSound(null, pos, SoundEvents.ITEM_TOTEM_USE, SoundCategory.BLOCKS, 1f, 1f);
                        }

                        return ActionResult.SUCCESS;
                    }
                } catch (IllegalArgumentException e) {
                }
            } else {
                world.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.BLOCKS, 1f, 1f);
            }
        }

        return ActionResult.PASS;
    }
    private boolean isPetDead(World world, UUID petUUID) {
        if (world instanceof ServerWorld serverWorld) {
            return serverWorld.getEntity(petUUID) == null;
        }
        return false;
    }
}

