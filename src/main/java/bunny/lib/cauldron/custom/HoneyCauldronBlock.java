package bunny.lib.cauldron.custom;

import bunny.lib.config.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class HoneyCauldronBlock extends LeveledCauldronBlock {
    public static final CauldronBehavior.CauldronBehaviorMap HONEY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("honey_cauldron_behavior");

    public HoneyCauldronBlock(Settings settings) {
        super(Biome.Precipitation.NONE, HONEY_CAULDRON_BEHAVIOR, settings);
        setDefaultState(getDefaultState().with(LEVEL, 1));
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient()) return;
        int level = state.get(LEVEL);

        if (entity instanceof LivingEntity living && level > 0 && !living.getStatusEffects().isEmpty() && ModConfig.INSTANCE.enableHoneyClearEffects) {
            living.clearStatusEffects();
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 2.0F);
            if (level > 1) world.setBlockState(pos, state.with(LEVEL, level - 1));
            else world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.DRIPPING_HONEY,
                        pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                        10, 0.3, 0.2, 0.3, 0.1
                );
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
