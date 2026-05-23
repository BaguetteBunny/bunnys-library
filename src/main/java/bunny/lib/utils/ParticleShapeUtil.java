package bunny.lib.utils;

import net.minecraft.particle.ParticleEffect;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector4d;

public class ParticleShapeUtil {

    public static <T extends ParticleEffect> void drawUniformCircle(T particle, Vec3d centerPos, float radius, ServerWorld world, int count, Vector4d deltaSpeed) {
        for (double x = -radius; x <= radius; x += 0.5) {
            for (double z = -radius; z <= radius; z += 0.5) {
                if (x*x + z*z <= radius*radius) {
                    world.spawnParticles(
                            particle,
                            centerPos.getX() + x,
                            centerPos.getY() + 1,
                            centerPos.getZ() + z,
                            count, deltaSpeed.w, deltaSpeed.x, deltaSpeed.y, deltaSpeed.z);
                }
            }
        }
    }

    public static <T extends ParticleEffect> void drawCircle(T particle, Vec3d centerPos, float radius, ServerWorld world, int count, Vector4d deltaSpeed) {
        for (double x = -radius; x <= radius; x += 0.5) {
            for (double z = -radius; z <= radius; z += 0.5) {
                if (x*x + z*z <= radius*radius) {
                    world.spawnParticles(
                            particle,
                            centerPos.getX() + x + world.random.nextDouble(),
                            centerPos.getY() + 1 + world.random.nextDouble(),
                            centerPos.getZ() + z + world.random.nextDouble(),
                            count, deltaSpeed.w, deltaSpeed.x, deltaSpeed.y, deltaSpeed.z);
                }
            }
        }
    }
}
