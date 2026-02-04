package net.louis.overhaulmod.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class RadiusGetterUtil {

    public static Optional<LivingEntity> getNearestEntity(ServerWorld world, BlockPos pos, double radius) {
        List<LivingEntity> entities = getEntitiesInRadius(world, pos.toCenterPos(), radius);

        return entities.stream()
                .min(Comparator.comparingDouble(e -> e.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)));
    }

    public static List<LivingEntity> getEntitiesInRadius(World world, Vec3d pos, double radius) {
        Box box = new Box(
                pos.x - radius, pos.y - radius, pos.z - radius,
                pos.x + radius, pos.y + radius, pos.z + radius
        );

        return world.getEntitiesByClass(
                LivingEntity.class,
                box,
                entity -> entity.squaredDistanceTo(pos) <= radius * radius
        );
    }

    public static List<BlockPos> getBlocksInRadius(World world, BlockPos center, int radius) {
        List<BlockPos> result = new ArrayList<>();
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++)
            for (int dy = -radius; dy <= radius; dy++)
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    if (center.getSquaredDistance(pos) <= r2) result.add(pos);
                }

        return result;
    }

    public static List<BlockPos> getRandomBlockPosInRadius(World world, BlockPos center, int radius, int quantity) {
        List<BlockPos> result = new ArrayList<>();
        List<BlockPos> true_result = new ArrayList<>();
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++)
            for (int dy = -radius; dy <= radius; dy++)
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    if (center.getSquaredDistance(pos) <= r2) result.add(pos);
                }

        Random random = world.getRandom();
        for (int i = 1; i <= quantity; i++) {
            int index = random.nextInt(result.size());
            true_result.add(result.get(index));
        }

        return true_result;
    }

    public static List<BlockPos> getRandomSpawnableBlockPosInRadius(World world, BlockPos center, int radius, int quantity) {
        List<BlockPos> result = new ArrayList<>();
        List<BlockPos> true_result = new ArrayList<>();
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++)
            for (int dy = -radius; dy <= radius; dy++)
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    BlockPos posUp = center.add(dx, dy+1, dz);
                    BlockPos posUpUp = center.add(dx, dy+2, dz);

                    if (center.getSquaredDistance(pos) <= r2 && !world.getBlockState(pos).isAir() && world.getBlockState(posUp).isAir() && world.getBlockState(posUpUp).isAir()) result.add(pos);
                }

        if (result.isEmpty()) return getRandomBlockPosInRadius(world, center, radius, quantity);

        Random random = world.getRandom();
        for (int i = 1; i <= quantity; i++) {
            int index = random.nextInt(result.size());
            true_result.add(result.get(index));
        }

        return true_result;
    }

    public static void replaceBlocksInRadius(World world, BlockPos center, int radius, Map<Block, Block> replacements, boolean disableWaterLog) {
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    if (center.getSquaredDistance(pos) > r2) continue;
                    BlockState state = world.getBlockState(pos);
                    Block replacement = replacements.get(state.getBlock());

                    if (replacement != null) {
                        if (disableWaterLog)
                            try {world.setBlockState(pos, replacement.getDefaultState().with(Properties.WATERLOGGED, false));}
                            catch (Exception e) {world.setBlockState(pos, replacement.getDefaultState());}

                        else
                            world.setBlockState(pos, replacement.getDefaultState());
                    }
                }
            }
        }
    }
}
