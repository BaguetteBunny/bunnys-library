package bunny.lib.events;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import bunny.lib.BunnyLib;
import bunny.lib.config.ModConfig;
import bunny.lib.item.ModItems;
import bunny.lib.utils.EnchantmentUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.conversion.EntityConversionType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BoggedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtOps;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ModUseEntityEvents {
    public static void register() {
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useTailoringShears);

        // Stews
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useMushroomStew);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useRabbitStew);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useFishStew);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useRottenStew);
        UseEntityCallback.EVENT.register(ModUseEntityEvents::useSuspiciousStew);
    }

    private static ActionResult useTailoringShears(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (world.isClient() || player.getStackInHand(hand).getItem() != Items.SHEARS) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);

        // 1. Sheep
        if (entity instanceof SheepEntity sheep && sheep.isShearable()) {
            int doubledLevel = 2*EnchantmentUtils.returnEnchantLevel(stack, "tailoring");

            if (doubledLevel > 0) {
                Item wool = Registries.ITEM.get(Identifier.ofVanilla(sheep.getColor().getName() + "_wool"));

                for (int i = 0; i < doubledLevel; i++)
                    if (world.getRandom().nextInt(2) == 1)
                        sheep.dropStack((ServerWorld) world, new ItemStack(wool), 1.0F);
            }
        }

        // 2. Mooshroom
        if (entity instanceof MooshroomEntity mooshroom) {
            int tripledLevel = 3*EnchantmentUtils.returnEnchantLevel(stack, "tailoring");

            if (tripledLevel > 0) {
                Item mushroom = mooshroom.getVariant() == MooshroomEntity.Type.BROWN ? Items.BROWN_MUSHROOM : Items.RED_MUSHROOM;

                for (int i = 0; i < tripledLevel; i++)
                    if (world.getRandom().nextInt(2) == 1)
                        mooshroom.dropStack((ServerWorld) world, new ItemStack(mushroom), 1.0F);
            }
        }

        // 3. Bogged
        if (entity instanceof BoggedEntity bogged && bogged.isShearable()) {
            int tripledLevel = 3*EnchantmentUtils.returnEnchantLevel(stack, "tailoring");

            if (tripledLevel > 0) {
                for (int i = 0; i < tripledLevel; i++)
                    if (world.getRandom().nextInt(2) == 1) {
                        Item mushroom = world.getRandom().nextInt(2) == 1 ? Items.BROWN_MUSHROOM : Items.RED_MUSHROOM;
                        bogged.dropStack((ServerWorld) world, new ItemStack(mushroom), 1.0F);
                    }
            }
        }

        return ActionResult.PASS;
    }


    private static final Identifier RARE_UPSCALE_ID = Identifier.of(BunnyLib.MOD_ID, "rare_upscale_id");

    public static void stewFail(Entity e, World world) {
        world.playSound(null, e.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                SoundCategory.PLAYERS, 1.0F, 2.0F);
        ((ServerWorld) world).spawnParticles(
                ParticleTypes.SMOKE,
                e.getX() + 0,
                e.getY() + 1,
                e.getZ() + 0,
                10,
                0.5, 0.5, 0.5,
                0.05
        );
    }

    public static void stewSuccess(Entity e, World world, SoundEvent sound, float pitch, ParticleEffect particle) {
        world.playSound(null, e.getBlockPos(), sound,
                SoundCategory.PLAYERS, 1.0F, 2.0F);
        ((ServerWorld) world).spawnParticles(
                particle,
                e.getX() + 0,
                e.getY() + 1,
                e.getZ() + 0,
                10,
                0.5, 0.5, 0.5,
                0.2
        );
    }

    private static ActionResult useMushroomStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient() || !ModConfig.INSTANCE.enableStewMobEffects || !stack.isOf(Items.MUSHROOM_STEW) || entity.getClass() != CowEntity.class) return ActionResult.PASS;


        CowEntity targetCow = (CowEntity) entity;
        player.swingHand(hand, true);
        if (!player.getAbilities().creativeMode) stack.decrement(1);


        if ((int)(Math.random() * 11) == 1) {
            stewSuccess(targetCow, world, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 2, ParticleTypes.HEART);
            MooshroomEntity mooshroom = (MooshroomEntity) getTransformedEntity(targetCow, EntityType.MOOSHROOM, world);
            mooshroom.setBaby(targetCow.isBaby());

            world.spawnEntity(mooshroom);
            targetCow.discard();

        } else stewFail(targetCow, world);

        return ActionResult.SUCCESS_SERVER;
    }

    private static ActionResult useRabbitStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !stack.isOf(Items.RABBIT_STEW) || !ModConfig.INSTANCE.enableStewMobEffects) return ActionResult.PASS;

        if (entity.getClass() == RabbitEntity.class) {
            RabbitEntity targetRabbit = (RabbitEntity) entity;
            player.swingHand(hand, true);
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            if ((int) (Math.random() * 5) == 1) {
                stewSuccess(targetRabbit, world, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 2, ParticleTypes.ANGRY_VILLAGER);
                targetRabbit.setVariant(RabbitEntity.RabbitType.EVIL);
            } else stewFail(targetRabbit, world);
            return ActionResult.SUCCESS_SERVER;

        }
        return ActionResult.PASS;
    }

    private static ActionResult useFishStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !stack.isOf(ModItems.FISH_STEW) || !ModConfig.INSTANCE.enableStewMobEffects) return ActionResult.PASS;

        if (entity.getClass() == DolphinEntity.class) {
            DolphinEntity targetDolphin = (DolphinEntity) entity;
            player.swingHand(hand, true);
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            if ((int)(Math.random() * 3) == 1) {
                stewSuccess(targetDolphin, world, SoundEvents.ENTITY_DOLPHIN_SPLASH, .5f, ParticleTypes.DOLPHIN);
                stewSuccess(targetDolphin, world, SoundEvents.ENTITY_DOLPHIN_PLAY, .5f, ParticleTypes.DOLPHIN);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 36000, 0));
            }
            else stewFail(targetDolphin, world);
            return ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.PASS;
    }

    private static ActionResult useRottenStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !stack.isOf(ModItems.ROTTEN_STEW) || !ModConfig.INSTANCE.enableStewMobEffects) return ActionResult.PASS;

        if (entity.getClass() == ZombieEntity.class) {
            ZombieEntity targetZombie = (ZombieEntity) entity;
            player.swingHand(hand, true);
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            if ((int) (Math.random() * 200) == 1) {
                stewSuccess(targetZombie, world, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, .5f, ParticleTypes.ELECTRIC_SPARK);
                stewSuccess(targetZombie, world, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, .5f, ParticleTypes.ELECTRIC_SPARK);
                Objects.requireNonNull(targetZombie.getAttributeInstance(EntityAttributes.SCALE)).setBaseValue(6);
                Objects.requireNonNull(targetZombie.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)).setBaseValue(50);
                Objects.requireNonNull(targetZombie.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)).setBaseValue(0.5);
                targetZombie.setCustomName(Text.of(targetZombie.getCustomName() + " the Giant"));
            } else stewFail(targetZombie, world);
            return ActionResult.SUCCESS_SERVER;
        }

        if (entity.getClass() == VillagerEntity.class) {
            VillagerEntity targetVillager = (VillagerEntity) entity;
            player.swingHand(hand, true);
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            if ((int)(Math.random() * 5) == 1) {
                stewSuccess(targetVillager, world, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, .5f, ParticleTypes.ANGRY_VILLAGER);
                stewSuccess(targetVillager, world, SoundEvents.ENTITY_VILLAGER_NO, .5f, ParticleTypes.ANGRY_VILLAGER);
                world.spawnEntity(getTransformedVillager(targetVillager, world));
                targetVillager.discard();
            } else stewFail(targetVillager, world);
            return ActionResult.SUCCESS_SERVER;
        }

        return ActionResult.PASS;
    }

    private static ActionResult useSuspiciousStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient() || !ModConfig.INSTANCE.enableSusStewRNG || !stack.isOf(Items.SUSPICIOUS_STEW) || !(entity instanceof LivingEntity)) return ActionResult.PASS;

        SuspiciousStewEffectsComponent suspiciousStewEffectsComponent = stack.getOrDefault(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffectsComponent.DEFAULT);
        String effect = suspiciousStewEffectsComponent.effects().getFirst().effect().getIdAsString();

        // Gather Entity Type
        EntityType<? extends LivingEntity> randomType = null;
        if (effect.equals("minecraft:poison") && TRANSFORMABLE_ZOMBIES.contains(entity.getType())) {
            randomType = TRANSFORMABLE_ZOMBIES.get(world.getRandom().nextInt(TRANSFORMABLE_ZOMBIES.size()));
        } else if (effect.equals("minecraft:regeneration") && TRANSFORMABLE_SMALL_FLYING.contains(entity.getType())) {
            randomType = TRANSFORMABLE_SMALL_FLYING.get(world.getRandom().nextInt(TRANSFORMABLE_SMALL_FLYING.size()));
        } else if (effect.equals("minecraft:fire_resistance") && TRANSFORMABLE_FIRE.contains(entity.getType())) {
            randomType = TRANSFORMABLE_FIRE.get(world.getRandom().nextInt(TRANSFORMABLE_FIRE.size()));
        } else if (effect.equals("minecraft:blindness") && TRANSFORMABLE_SQUID.contains(entity.getType())) {
            randomType = TRANSFORMABLE_SQUID.get(world.getRandom().nextInt(TRANSFORMABLE_SQUID.size()));
        } else if (effect.equals("minecraft:saturation") && TRANSFORMABLE_FISH.contains(entity.getType())) {
            randomType = TRANSFORMABLE_FISH.get(world.getRandom().nextInt(TRANSFORMABLE_FISH.size()));
        } else if (effect.equals("minecraft:jump_boost") && TRANSFORMABLE_SLIMES.contains(entity.getType())) {
            randomType = TRANSFORMABLE_SLIMES.get(world.getRandom().nextInt(TRANSFORMABLE_SLIMES.size()));
        } else if (effect.equals("minecraft:night_vision") && TRANSFORMABLE_SKELETON.contains(entity.getType())) {
            randomType = TRANSFORMABLE_SKELETON.get(world.getRandom().nextInt(TRANSFORMABLE_SKELETON.size()));
        } else if (effect.equals("minecraft:weakness") && TRANSFORMABLE_PILLAGER.contains(entity.getType())) {
            randomType = TRANSFORMABLE_PILLAGER.get(world.getRandom().nextInt(TRANSFORMABLE_PILLAGER.size()));
        } else if (effect.equals("minecraft:wither") && TRANSFORMABLE_MISC.contains(entity.getType())) {
            randomType = TRANSFORMABLE_MISC.get(world.getRandom().nextInt(TRANSFORMABLE_MISC.size()));
        }


        if (randomType != null) {
            LivingEntity newEntity = getTransformedEntity((LivingEntity) entity, randomType, world);
            world.spawnEntity(newEntity);
            if (world.getRandom().nextInt(100) == 1 && newEntity.getClass() != ZombieEntity.class) makeBigger(newEntity);

            stewSuccess(entity, world, SoundEvents.BLOCK_BREWING_STAND_BREW, .5f, ParticleTypes.WITCH);
            player.swingHand(hand, true);
            entity.discard();
            if (!player.getAbilities().creativeMode) stack.decrement(1);
            return ActionResult.SUCCESS_SERVER;
        } else return ActionResult.FAIL;
    }

    public static LivingEntity getTransformedEntity(LivingEntity living, EntityType<? extends LivingEntity> newType, World world) {
        LivingEntity newEntity = newType.create(world, SpawnReason.CONVERSION);

        assert newEntity != null;
        newEntity.refreshPositionAndAngles(living.getX(), living.getY(), living.getZ(), living.getYaw(), living.getPitch());
        newEntity.setCustomName(living.getCustomName());
        newEntity.setCustomNameVisible(living.isCustomNameVisible());
        newEntity.setHealth(living.getHealth());
        newEntity.setCurrentHand(living.getActiveHand());
        newEntity.setAbsorptionAmount(living.getAbsorptionAmount());
        newEntity.setAttacker(living.getAttacker());
        newEntity.setBodyYaw(living.getBodyYaw());
        newEntity.setFireTicks(living.getFireTicks());
        newEntity.setFrozenTicks(living.getFrozenTicks());
        newEntity.setInvulnerable(living.isInvulnerable());
        newEntity.setInvisible(living.isInvisible());
        newEntity.setSilent(living.isSilent());
        newEntity.setPortalCooldown(living.getPortalCooldown());

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = living.getEquippedStack(slot);
            if (!stack.isEmpty()) newEntity.equipStack(slot, stack.copy());
        }

        for (StatusEffectInstance effect : living.getStatusEffects()) newEntity.addStatusEffect(effect);

        return newEntity;
    }

    public static LivingEntity getTransformedVillager(VillagerEntity living, World world) {
        living.convertTo(
                EntityType.ZOMBIE_VILLAGER,
                new EntityConversionContext(EntityConversionType.SINGLE, true, true, living.getScoreboardTeam()),
                SpawnReason.CONVERSION,

                zombie -> {
                    zombie.setPersistent();
                    zombie.setVillagerData(living.getVillagerData());
                    zombie.setGossipData(living.getGossip().serialize(NbtOps.INSTANCE));
                    zombie.setOfferData(living.getOffers().copy());
                    zombie.setExperience(living.getExperience());
                    for (StatusEffectInstance effect : living.getStatusEffects()) zombie.addStatusEffect(effect);
                }
        );

        return living;
    }

    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_SLIMES = List.of(
            EntityType.SLIME,
            EntityType.MAGMA_CUBE
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_MISC = List.of(
            EntityType.CREEPER,
            EntityType.PIG
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_FISH = List.of(
            EntityType.TROPICAL_FISH,
            EntityType.COD,
            EntityType.SALMON,
            EntityType.PUFFERFISH
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_SKELETON = List.of(
            EntityType.SKELETON,
            EntityType.STRAY,
            EntityType.BOGGED
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_SQUID = List.of(
            EntityType.SQUID,
            EntityType.GLOW_SQUID
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_FIRE = List.of(
            EntityType.PIGLIN,
            EntityType.PIGLIN_BRUTE,
            EntityType.ZOMBIFIED_PIGLIN
    );
    private static final List<EntityType<? extends ZombieEntity>> TRANSFORMABLE_ZOMBIES = List.of(
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.DROWNED
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_SMALL_FLYING = List.of(
            EntityType.ALLAY,
            EntityType.VEX
    );
    private static final List<EntityType<? extends LivingEntity>> TRANSFORMABLE_PILLAGER = List.of(
            EntityType.EVOKER,
            EntityType.PILLAGER,
            EntityType.VINDICATOR,
            EntityType.WITCH
    );
    public static void makeBigger(LivingEntity entity) {
        if (entity == null) return;

        EntityAttributeInstance scaleAttr = entity.getAttributes().getCustomInstance(EntityAttributes.SCALE);
        if (scaleAttr == null) return;

        if (scaleAttr.getModifier(RARE_UPSCALE_ID) != null) scaleAttr.removeModifier(RARE_UPSCALE_ID);
        scaleAttr.addPersistentModifier(
                new EntityAttributeModifier(RARE_UPSCALE_ID, 2.75, EntityAttributeModifier.Operation.ADD_VALUE)
        );
    }
}
