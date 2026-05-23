package bunnys.qol.events;

import bunny.lib.config.ModConfig;
import bunnys.qol.entity.ModEntities;
import bunnys.qol.entity.custom.misc.ChairEntity;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

import static bunny.lib.utils.EnchantmentUtils.hasEnchant;

public class ModUseBlockEvents {
    public static void register() {
        UseBlockCallback.EVENT.register(ModUseBlockEvents::useOnSuspiciousBlock);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::oxidizeCopperWithClock);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::useBonemealOnOtherCrops);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::rcHarvest);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::sitOnSaddleUse);
        UseBlockCallback.EVENT.register(ModUseBlockEvents::shearsStopGrowth);
    }

    private static ActionResult shearsStopGrowth(PlayerEntity player, World world, Hand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getStackInHand(hand);

        if (ModConfig.INSTANCE.shearsStopGrowth && !world.isClient() && stack.getItem() == Items.SHEARS) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState blockState = world.getBlockState(pos);

            boolean validBlockState = blockState.contains(SugarCaneBlock.AGE) || blockState.contains(BambooBlock.AGE) || blockState.contains(CactusBlock.AGE) || blockState.getBlock() == Blocks.BAMBOO_SAPLING;

            if (validBlockState && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
                if (world.getBlockState(pos.up()).getBlock() == Blocks.AIR) world.setBlockState(pos.up(), Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, 0), Block.NOTIFY_LISTENERS);
                else if (world.getBlockState(pos.up()).getBlock() == Blocks.LIGHT) world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                else return ActionResult.PASS;

                world.playSound(null, pos, SoundEvents.BLOCK_GROWING_PLANT_CROP,
                        SoundCategory.PLAYERS, 1.0F, 1.F);

                return ActionResult.SUCCESS_SERVER;
            }
        }

        return ActionResult.PASS;
    }

    private static ActionResult oxidizeCopperWithClock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient() && stack.getItem() == Items.CLOCK || !ModConfig.INSTANCE.oxidizeCopperWithClock) {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            Map<Block, Block> oxidationMap = Map.ofEntries(
                    Map.entry(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
                    Map.entry(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
                    Map.entry(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

                    Map.entry(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
                    Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
                    Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

                    Map.entry(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

                    Map.entry(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
                    Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
                    Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

                    Map.entry(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

                    Map.entry(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

                    Map.entry(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

                    Map.entry(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
                    Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
                    Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

                    Map.entry(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
            );

            BlockState clickedState = world.getBlockState(pos);
            Block clickedBlock = clickedState.getBlock();

            if (oxidationMap.containsKey(clickedBlock)) {
                if (clickedBlock instanceof DoorBlock && clickedState.get(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
                    return ActionResult.PASS;
                }
            }

            if (oxidationMap.containsKey(block)) {
                Block nextStage = oxidationMap.get(block);
                BlockState newState = nextStage.getDefaultState();

                if (state.contains(Properties.WATERLOGGED)) {
                    newState = newState.with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED));
                }

                world.setBlockState(pos, newState);
                if (Random.create().nextInt(20) == 0) {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1f, 2f);
                    stack.decrement(1);
                } else {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1f, 2f);
                }

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.WAX_OFF,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        20,
                        0.5, 0.5, 0.5,
                        0.075
                );

                return ActionResult.SUCCESS_SERVER;
            }
        }

        return ActionResult.PASS;
    }

    private static ActionResult useBonemealOnOtherCrops(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (world.isClient() || !heldItem.isOf(Items.BONE_MEAL) || !ModConfig.INSTANCE.useBonemealOnOtherCrops) {
            return ActionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        Block targetBlock = blockState.getBlock();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        // Case 1: CACTUS
        if (blockState.contains(CactusBlock.AGE)) {
            BlockPos checkUpwardPos = pos;
            for (int i = 0; i < 2; i++) {
                checkUpwardPos = checkUpwardPos.up();

                if (world.isAir(checkUpwardPos)) {
                    if (world.getBlockState(pos.down()).contains(CactusBlock.AGE)) {
                        break;
                    }
                    world.setBlockState(checkUpwardPos, targetBlock.getDefaultState());
                    world.setBlockState(checkUpwardPos, blockState.with(CactusBlock.AGE, 0), Block.NOTIFY_LISTENERS);
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
        }

        // Case 2: SUGAR CANE
        if (blockState.contains(SugarCaneBlock.AGE)) {
            BlockPos checkUpwardPos = pos;
            for (int i = 0; i < 2; i++) {
                checkUpwardPos = checkUpwardPos.up();

                if (world.isAir(checkUpwardPos)) {
                    if (world.getBlockState(pos.down()).contains(SugarCaneBlock.AGE)) {
                        break;
                    }
                    world.setBlockState(checkUpwardPos, targetBlock.getDefaultState());
                    world.setBlockState(checkUpwardPos, blockState.with(SugarCaneBlock.AGE, 0), Block.NOTIFY_LISTENERS);
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
        }

        // Case 5: VINES
        if (targetBlock instanceof VineBlock) {
            BlockPos checkDownPos = pos;
            for (int j = 0; j < 9; j++) {
                checkDownPos = checkDownPos.down();
                if (world.isAir(checkDownPos)) {
                    world.setBlockState(checkDownPos, blockState, Block.NOTIFY_ALL);
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
                else if (!world.getBlockState(pos).isOf(Blocks.VINE)) {
                    break;
                }
            }
        }
        return ActionResult.PASS;
    }

    private static ActionResult useOnSuspiciousBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !player.isSneaking() || stack.isEmpty() || !ModConfig.INSTANCE.useOnSusSand) return ActionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BrushableBlockEntity && ((BrushableBlockEntity) blockEntity).getItem().isEmpty()) {
            ((bunnys.qol.mixin.BrushableBlockEntityAccessor) blockEntity).bunny$setItem(stack.copy());
            stack.decrement(stack.getCount());

            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_PLACE,
                    SoundCategory.PLAYERS, 2.0F, 0.5F);

            player.swingHand(hand, true);
            return ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.PASS;
    }

    public static ActionResult rcHarvest(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient() || !ModConfig.INSTANCE.enableRcHarvest) return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if ((!player.getMainHandStack().isEmpty() || !player.getOffHandStack().isEmpty()) && !(player.getMainHandStack().getItem() instanceof HoeItem)) return ActionResult.PASS;
        if (hasEnchant(stack, "tilling")) return ActionResult.PASS;

        BlockState replanted = null;
        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block instanceof CropBlock crop && crop.isMature(state) && !(block instanceof BeetrootsBlock)) {
            dropBlockWithFortune(world, pos, state, player, stack);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            replanted = crop.getDefaultState().with(CropBlock.AGE, 0);

        } else if (block instanceof BeetrootsBlock beetroot && beetroot.isMature(state)) {
            dropBlockWithFortune(world, pos, state, player, stack);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            replanted = beetroot.getDefaultState();
        }

        if (replanted != null) {
            if (item instanceof HoeItem hoe) {
                stack.damage(1, player, EquipmentSlot.MAINHAND);
                player.incrementStat(Stats.USED.getOrCreateStat(hoe));
            }

            world.setBlockState(pos, replanted, Block.NOTIFY_ALL);
            player.incrementStat(Stats.MINED.getOrCreateStat(block));

            world.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundCategory.BLOCKS,
                    0.75F,
                   (world.getRandom().nextFloat() * 0.2F + 1.5F)
            );
            player.swingHand(Hand.MAIN_HAND, true);
            return ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.PASS;
    }

    public static ActionResult sitOnSaddleUse(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient() || !ModConfig.INSTANCE.enableSitting) return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (item != Items.SADDLE || player.getItemCooldownManager().isCoolingDown(stack)) return ActionResult.PASS;

        if (isValidSittableBlock(state)) {
            player.getItemCooldownManager().set(stack, 10);

            Entity entity;
            List<ChairEntity> entities = world.getEntitiesByType(ModEntities.CHAIR, new Box(pos), chair -> true);
            if(entities.isEmpty()) {
                entity = ModEntities.CHAIR.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            } else {
                entity = entities.getFirst();
            }

            player.startRiding(entity);
            world.playSound(null, pos, SoundEvents.ENTITY_HORSE_SADDLE, SoundCategory.PLAYERS, 5, 2f);

            player.swingHand(Hand.MAIN_HAND, true);
            return ActionResult.SUCCESS_SERVER;
        } return ActionResult.PASS;
    }

    public static void dropBlockWithFortune(World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack tool) {
        if (world instanceof ServerWorld serverWorld) {
            List<ItemStack> drops = Block.getDroppedStacks(state, serverWorld, pos, null, player, tool);
            for (ItemStack drop : drops) Block.dropStack(world, pos, drop);
        }
    }

    private static boolean isValidSittableBlock(BlockState state) {
        if (state.getBlock() instanceof StairsBlock) return state.get(StairsBlock.HALF) == BlockHalf.BOTTOM;
        if (state.getBlock() instanceof SlabBlock) return state.get(SlabBlock.TYPE) == SlabType.BOTTOM;
        return state.getBlock() instanceof BedBlock;
    }
}

