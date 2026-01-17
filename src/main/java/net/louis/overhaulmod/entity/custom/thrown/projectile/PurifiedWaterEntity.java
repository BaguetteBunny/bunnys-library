package net.louis.overhaulmod.entity.custom.thrown.projectile;

import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.ParticleShapeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.joml.Vector4d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.louis.overhaulmod.utils.RadiusGetterUtil.*;

public class PurifiedWaterEntity extends ThrownItemEntity {
    public PurifiedWaterEntity(EntityType<? extends PurifiedWaterEntity> entityType, World world) {
        super(entityType, world);
    }

    public PurifiedWaterEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public PurifiedWaterEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    private static Map<Block, Block> PURIFIED_WATER_BLOCK_TRANSFORM = new HashMap<>();
    static {
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN);

        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.SCULK, Blocks.DEEPSLATE_LAPIS_ORE);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.DEAD_BUSH, Blocks.FERN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.POTTED_DEAD_BUSH, Blocks.POTTED_FERN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.GILDED_BLACKSTONE, Blocks.BLACKSTONE);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.REINFORCED_DEEPSLATE, Blocks.DEEPSLATE);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.CRYING_OBSIDIAN, Blocks.OBSIDIAN);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.NETHER_GOLD_ORE, Blocks.NETHERRACK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(Blocks.NETHER_QUARTZ_ORE, Blocks.NETHERRACK);

        PURIFIED_WATER_BLOCK_TRANSFORM.put(ModBlocks.DECREPIT_BONE_BLOCK, Blocks.BONE_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(ModBlocks.CHILLED_BONE_BLOCK, Blocks.BONE_BLOCK);
        PURIFIED_WATER_BLOCK_TRANSFORM.put(ModBlocks.TOXIC_BONE_BLOCK, Blocks.BONE_BLOCK);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PURIFIED_WATER_BOTTLE;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(!itemStack.isEmpty() && !itemStack.isOf(this.getDefaultItem())
                ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack)
                : ParticleTypes.CRIT);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();
            World world = this.getWorld();

            for (int i = 0; i < 8; i++) {
                world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        World world = this.getWorld();
        BlockPos pos = null;
        if (hitResult instanceof BlockHitResult blockHitResult)
            pos = blockHitResult.getBlockPos();
        else if (hitResult instanceof EntityHitResult entityHitResult)
            pos = entityHitResult.getEntity().getSteppingPos();

        assert pos != null;
        Vec3d vec3d = pos.toCenterPos();

        world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.F, 2.0F);
        world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.F, 0.5F);

        ParticleShapeUtil.drawCircle(ParticleTypes.EFFECT, vec3d, 3F, (ServerWorld) world, 1, new Vector4d(0,0,0,0));

        // Damage/heal all mobs in radius
        List<LivingEntity> mobs = getEntitiesInRadius(world, vec3d, 5);
        for (LivingEntity entity : mobs) {
            entity.extinguish();
            if (entity instanceof HostileEntity)
                entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);
            if (entity instanceof AnimalEntity)
                entity.heal(4.f);
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                int totalDamageDealt = serverPlayer.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_DEALT));
                int totalDamageAbsorbed =
                        serverPlayer.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_BLOCKED_BY_SHIELD))
                        + serverPlayer.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_ABSORBED));
                if (totalDamageDealt/20 > totalDamageAbsorbed && totalDamageDealt > 10000) {
                    serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 4));
                    serverPlayer.damage(this.getDamageSources().thrown(this, this.getOwner()), 8);
                }
                else if (totalDamageDealt/10 > totalDamageAbsorbed && totalDamageDealt > 1000) {
                    serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
                    serverPlayer.damage(this.getDamageSources().thrown(this, this.getOwner()), 5);
                }
                else if (totalDamageDealt/5 > totalDamageAbsorbed && totalDamageDealt > 100) {
                    serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
                    serverPlayer.damage(this.getDamageSources().thrown(this, this.getOwner()), 2);
                }
                else {
                    serverPlayer.heal(2);
                }


            }
        }

        // Replace blocks in radius
        replaceBlocksInRadius(world, pos, 5, PURIFIED_WATER_BLOCK_TRANSFORM, true);

        // Explode in Nether
        if (!world.isClient && world.getDimension().ultrawarm() && world.getGameRules().getBoolean(GameRules.PROJECTILES_CAN_BREAK_BLOCKS)) {
            world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 5.0F, true, World.ExplosionSourceType.BLOCK);
            this.remove(RemovalReason.DISCARDED);
            return;
        }

        super.onCollision(hitResult);
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
        this.discard();
    }
}
