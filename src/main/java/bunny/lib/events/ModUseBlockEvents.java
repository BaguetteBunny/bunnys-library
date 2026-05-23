package bunny.lib.events;

import bunny.lib.config.ModConfig;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.player.*;
import bunny.lib.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.*;

public class ModUseBlockEvents {
    public static void register() {
        UseBlockCallback.EVENT.register(ModUseBlockEvents::useDecrepitBonemealOnCrops);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::retexturePlayerHead);
    }

    private static ActionResult useDecrepitBonemealOnCrops(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (world.isClient() || !heldItem.isOf(ModItems.INFUSED_BONE_MEAL)) {
            return ActionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        Block targetBlock = blockState.getBlock();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        // Case 3: NETHER WART
        if (blockState.contains(NetherWartBlock.AGE)) {
            int age = blockState.get(NetherWartBlock.AGE);
            if (age < 3) {
                world.setBlockState(pos, blockState.with(NetherWartBlock.AGE, age + 1), Block.NOTIFY_LISTENERS);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                        SoundCategory.PLAYERS, 1.0F, 0.5F);
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        x,
                        y,
                        z,
                        10,
                        0.5, 0.5, 0.5,
                        0.2
                );
                return ActionResult.SUCCESS_SERVER;
            }
        }

        // Case 4: CHORUS PLANT
        Random random = Random.create();
        if (targetBlock instanceof ChorusFlowerBlock) {
            int age = blockState.get(ChorusFlowerBlock.AGE);
            if (age < 5) {
                ChorusFlowerBlock.generate(world, pos, random, 20);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                        SoundCategory.PLAYERS, 1.0F, 0.5F);
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        x,
                        y,
                        z,
                        10,
                        0.5, 0.5, 0.5,
                        0.2
                );
                return ActionResult.SUCCESS_SERVER;
            }
        }
        return ActionResult.PASS;
    }
    private static ActionResult retexturePlayerHead(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient() || !ModConfig.INSTANCE.retexturePlayerHead) return ActionResult.PASS;

        ItemStack heldItem = player.getStackInHand(hand);
        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos);

        if (heldItem.isOf(Items.NAME_TAG) && isPlayerHead(blockState)) {
            Text nameTagText = heldItem.get(DataComponentTypes.CUSTOM_NAME);

            if (nameTagText != null) {
                String playerName = nameTagText.getString();

                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof SkullBlockEntity skullEntity) {
                    updateSkullTexture(skullEntity, playerName, world);

                    if (!player.getAbilities().creativeMode) {heldItem.decrement(1);}
                    player.sendMessage(Text.literal("Changed head texture to: " + playerName), true);
                    return ActionResult.SUCCESS_SERVER;
                }
            } else {
                player.sendMessage(Text.literal("Name tag must have a custom name!"), true);
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    }


    private static boolean isPlayerHead(BlockState blockState) {
        return blockState.isOf(Blocks.PLAYER_HEAD) || blockState.isOf(Blocks.PLAYER_WALL_HEAD);
    }

    private static void updateSkullTexture(SkullBlockEntity skullEntity, String playerName, World world) {
        GameProfile profile = new GameProfile(Uuids.getOfflinePlayerUuid(playerName), playerName);

        SkullBlockEntity.fetchProfileByName(playerName).thenAccept(optionalProfile -> {
            world.getServer().execute(() -> {
                ProfileComponent profileComponent;
                if (optionalProfile.isPresent()) {
                    GameProfile gameProfile = optionalProfile.get();
                    profileComponent = new ProfileComponent(
                            Optional.of(gameProfile.getName()),
                            Optional.of(gameProfile.getId()),
                            gameProfile.getProperties()
                    );
                } else {
                    profileComponent = new ProfileComponent(
                            Optional.of(profile.getName()),
                            Optional.of(profile.getId()),
                            profile.getProperties()
                    );
                }

                skullEntity.setOwner(profileComponent);
                skullEntity.markDirty();
                world.updateListeners(skullEntity.getPos(), world.getBlockState(skullEntity.getPos()),
                        world.getBlockState(skullEntity.getPos()), 3);
            });
        });
    }

    public static void dropBlockWithFortune(World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack tool) {
        if (world instanceof ServerWorld serverWorld) {
            List<ItemStack> drops = Block.getDroppedStacks(state, serverWorld, pos, null, player, tool);
            for (ItemStack drop : drops) Block.dropStack(world, pos, drop);
        }
    }
}

