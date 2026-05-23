package bunny.lib.item.custom;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.Set;


public class RecallClock extends Item {
    public RecallClock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            user.setStackInHand(hand, ItemStack.EMPTY);
            user.fallDistance = 0.0f;
            BlockPos spawnPos = serverPlayer.getSpawnPointPosition();

            RegistryKey<World> spawnDim = serverPlayer.getSpawnPointDimension();

            if (spawnPos != null && spawnDim != null) {
                ServerWorld spawnWorld = serverPlayer.getServer().getWorld(spawnDim);
                if (spawnWorld != null) {
                    BlockState spawnBlock = spawnWorld.getBlockState(spawnPos);

                    if (spawnBlock.getBlock() instanceof BedBlock || spawnBlock.getBlock() instanceof RespawnAnchorBlock) {
                        teleportPlayer(serverPlayer, spawnWorld, spawnPos, "Teleported to your spawn point!");
                        return ActionResult.CONSUME;
                    }
                }
            }
            ServerWorld spawnWorld = serverPlayer.getServer().getOverworld();
            spawnPos = spawnWorld.getSpawnPos();
            teleportPlayer(serverPlayer, spawnWorld, spawnPos, "No spawn point found. Teleported to world spawn!");
            return ActionResult.CONSUME;
        }
        return ActionResult.SUCCESS;
    }

    private void teleportPlayer(ServerPlayerEntity player, ServerWorld targetWorld, BlockPos targetPos, String message) {
        player.getEntityWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                SoundCategory.PLAYERS, 2.0f, 0.5f);

        player.teleport(targetWorld, targetPos.getX() + 0.5, targetPos.getY() + 1.0, targetPos.getZ() + 0.5, PositionFlag.DELTA,
                player.getYaw(), player.getPitch(), false);

        targetWorld.playSound(null, targetPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                SoundCategory.PLAYERS, 2.0f, 2.0f);

        player.sendMessage(Text.literal(message), true);
    }
}

