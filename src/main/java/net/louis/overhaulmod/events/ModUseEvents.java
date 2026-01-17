package net.louis.overhaulmod.events;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.player.*;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.entity.ModEntities;
import net.louis.overhaulmod.entity.custom.misc.ChairEntity;
import net.louis.overhaulmod.entity.custom.thrown.projectile.BrickEntity;
import net.louis.overhaulmod.entity.custom.thrown.projectile.NetherBrickEntity;
import net.louis.overhaulmod.entity.custom.thrown.projectile.PurifiedWaterEntity;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.mixin.accessor.ArmorStandEntityAccessor;
import net.louis.overhaulmod.mixin.accessor.BrushableBlockEntityAccessor;
import net.louis.overhaulmod.utils.EnchantmentUtils;
import net.louis.overhaulmod.utils.GlowManager;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static net.louis.overhaulmod.utils.EnchantmentUtils.hasEnchant;

public class ModUseEvents {
    public static void registerMain() {
        UseBlockCallback.EVENT.register(ModUseEvents::useOnSuspiciousBlock);
        UseBlockCallback.EVENT.register(ModUseEvents::oxidizeCopperWithClock);
        UseBlockCallback.EVENT.register(ModUseEvents::retexturePlayerHead);
        UseBlockCallback.EVENT.register(ModUseEvents::useBonemealOnOtherCrops);
        UseBlockCallback.EVENT.register(ModUseEvents::useDecrepitBonemealOnCrops);
        UseBlockCallback.EVENT.register(ModUseEvents::rcHarvest);
        UseBlockCallback.EVENT.register(ModUseEvents::sitOnSaddleUse);

        UseItemCallback.EVENT.register(ModUseEvents::getLlamaSpitBottle);
        UseItemCallback.EVENT.register(ModUseEvents::useGlowInk);
        UseItemCallback.EVENT.register(ModUseEvents::useFireBlastEnchant);
        UseItemCallback.EVENT.register(ModUseEvents::useSeasoning);

        UseEntityCallback.EVENT.register(ModUseEvents::changeArmorStandVariant);
        UseEntityCallback.EVENT.register(ModUseEvents::dyeShulkers);
        UseEntityCallback.EVENT.register(ModUseEvents::useBrushOnDyedShulkers);
        UseEntityCallback.EVENT.register(ModUseEvents::useChilledBonemealOnAnimal);

        AttackEntityCallback.EVENT.register(ModUseEvents::featherDamageFreeKB);

        AttackBlockCallback.EVENT.register(ModUseEvents::magicTouchEvent);
    }

    public static void registerProjectileItems() {
        UseItemCallback.EVENT.register(ModUseEvents::useBrick);
        UseItemCallback.EVENT.register(ModUseEvents::useNetherBrick);
        UseItemCallback.EVENT.register(ModUseEvents::usePurifiedWaterBottle);
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

                return ActionResult.SUCCESS;
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
                    return ActionResult.SUCCESS;
                }
            } else {
                player.sendMessage(Text.literal("Name tag must have a custom name!"), true);
                return ActionResult.FAIL;
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
                    return ActionResult.SUCCESS;
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
                    return ActionResult.SUCCESS;
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
                    return ActionResult.SUCCESS;
                }
                else if (!world.getBlockState(pos).isOf(Blocks.VINE)) {
                    break;
                }
            }
        }
        return ActionResult.PASS;
    }

    private static ActionResult useDecrepitBonemealOnCrops(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (world.isClient() || !heldItem.isOf(ModItems.DECREPIT_BONE_MEAL)) {
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
                return ActionResult.SUCCESS;
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
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    private static TypedActionResult<ItemStack> getLlamaSpitBottle(PlayerEntity player, World world, Hand hand) {
        if (world.isClient()) return TypedActionResult.pass(player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.GLASS_BOTTLE)) {
            double reach = 5.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            LlamaSpitEntity targetSpit = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof LlamaSpitEntity spit)) continue;
                Box entityBox = spit.getBoundingBox().expand(0.5D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetSpit = spit;
                    }
                }
            }

            if (targetSpit != null) {
                if (!player.getAbilities().creativeMode) stack.decrement(1);
                ItemStack result = new ItemStack(ModItems.LLAMAS_SPIT);
                if (!player.getInventory().insertStack(result)) {
                    player.dropItem(result, false);
                }
                world.playSound(null, targetSpit.getBlockPos(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
                targetSpit.discard();
                return TypedActionResult.success(stack, world.isClient());
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));

    }

    private static TypedActionResult<ItemStack> useBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.BRICK) || player.getItemCooldownManager().isCoolingDown(Items.BRICK) || !ModConfig.INSTANCE.enableThrowableBricks) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        BrickEntity brickEntity = new BrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(Items.BRICK, 20);

        return TypedActionResult.success(stack, world.isClient());
    }

    private static TypedActionResult<ItemStack> useNetherBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.NETHER_BRICK) || player.getItemCooldownManager().isCoolingDown(Items.NETHER_BRICK) || !ModConfig.INSTANCE.enableThrowableBricks) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        NetherBrickEntity brickEntity = new NetherBrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(Items.NETHER_BRICK, 20);

        return TypedActionResult.success(stack, world.isClient());
    }

    private static TypedActionResult<ItemStack> usePurifiedWaterBottle(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(ModItems.PURIFIED_WATER_BOTTLE) || player.getItemCooldownManager().isCoolingDown(ModItems.PURIFIED_WATER_BOTTLE)) return TypedActionResult.pass(player.getStackInHand(hand));

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW,
                SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        PurifiedWaterEntity purifiedWaterEntity = new PurifiedWaterEntity(world, player);
        purifiedWaterEntity.setItem(stack);
        purifiedWaterEntity.setVelocity(player, player.getPitch(), player.getYaw(), -10.0F, 1.0F, 1.0F);
        world.spawnEntity(purifiedWaterEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(ModItems.PURIFIED_WATER_BOTTLE, 5);

        return TypedActionResult.success(stack, world.isClient());
    }

    private static ActionResult changeArmorStandVariant(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getMainHandStack();
        if (world.isClient() || !player.isSneaking() || !(entity instanceof ArmorStandEntity stand)  || !ModConfig.INSTANCE.changeArmorstand) {
            return ActionResult.PASS;
        }

        if (stack.isOf(Items.STICK) && !stand.shouldShowArms()) {
            stand.setShowArms(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.getItem() instanceof AxeItem && !stand.isSmall()) {
            ((ArmorStandEntityAccessor) stand).callSetSmall(true);
            stack.damage(1, player, EquipmentSlot.MAINHAND);
        } else if (stack.isOf(Items.PHANTOM_MEMBRANE) && !stand.isInvisible()) {
            stand.setInvisible(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.isOf(Items.GLOW_INK_SAC) && !stand.isGlowing()) {
            stand.setGlowing(true);
            stack.decrementUnlessCreative(1, player);
        }
        else {
            ArrayList<ItemStack> playerItems = new ArrayList<>();
            playerItems.add(player.getEquippedStack(EquipmentSlot.HEAD));
            playerItems.add(player.getEquippedStack(EquipmentSlot.CHEST));
            playerItems.add(player.getEquippedStack(EquipmentSlot.LEGS));
            playerItems.add(player.getEquippedStack(EquipmentSlot.FEET));
            playerItems.add(player.getEquippedStack(EquipmentSlot.MAINHAND));

            player.equipStack(EquipmentSlot.HEAD, stand.getEquippedStack(EquipmentSlot.HEAD));
            player.equipStack(EquipmentSlot.CHEST, stand.getEquippedStack(EquipmentSlot.CHEST));
            player.equipStack(EquipmentSlot.LEGS, stand.getEquippedStack(EquipmentSlot.LEGS));
            player.equipStack(EquipmentSlot.FEET, stand.getEquippedStack(EquipmentSlot.FEET));

            stand.equipStack(EquipmentSlot.HEAD, playerItems.get(0));
            stand.equipStack(EquipmentSlot.CHEST, playerItems.get(1));
            stand.equipStack(EquipmentSlot.LEGS, playerItems.get(2));
            stand.equipStack(EquipmentSlot.FEET, playerItems.get(3));

            if (stand.shouldShowArms()) {
                player.equipStack(EquipmentSlot.MAINHAND, stand.getEquippedStack(EquipmentSlot.MAINHAND));
                stand.equipStack(EquipmentSlot.MAINHAND, playerItems.get(4));
            }
        }

        world.playSound(null, stand.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }

    private static TypedActionResult<ItemStack> useGlowInk(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.isClient() || item != Items.GLOW_INK_SAC || player.getItemCooldownManager().isCoolingDown(item)) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack.getItem(), 200);

        List<Entity> nearby = world.getOtherEntities(
                player,
                player.getBoundingBox().expand(12)
        );

        for (Entity e : nearby) {
            if (e instanceof LivingEntity living && !living.isGlowing()) {
                living.setGlowing(true);
                GlowManager.addGlowingEntity(living, 80);
            }
        }

        world.playSound(
                null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.PLAYERS,
                1.0F, 1.0F
        );

        player.swingHand(hand, true);
        return TypedActionResult.success(stack, world.isClient());
    }

    private static TypedActionResult<ItemStack> useFireBlastEnchant(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.isClient() || player.getItemCooldownManager().isCoolingDown(item)) return TypedActionResult.pass(stack);


        if (item instanceof FlintAndSteelItem && hasEnchant(stack, "fire_blast")) {
            player.getItemCooldownManager().set(stack.getItem(), 20);
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS,
                    1.0F, 2.0F
            );

            Entity fireball = new SmallFireballEntity(world, player, player.getRotationVector());
            fireball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            world.spawnEntity(fireball);

            stack.damage(3, player, player.getPreferredEquipmentSlot(stack));
            player.swingHand(hand, true);
            return TypedActionResult.success(stack, world.isClient());
        }
        return TypedActionResult.pass(stack);
    }

    private static TypedActionResult<ItemStack> useSeasoning(PlayerEntity player, World world, Hand hand) {
        ItemStack powderStack = player.getStackInHand(Hand.OFF_HAND);
        ItemStack foodStack = player.getStackInHand(Hand.MAIN_HAND);

        if (hand != Hand.MAIN_HAND || !powderStack.isOf(ModItems.EMPYREAN_POWDER) || world.isClient() || foodStack.get(DataComponentTypes.FOOD) == null || foodStack.get(ModComponents.SEASONING) != null) return TypedActionResult.pass(powderStack);

        if (foodStack.getCount() <= 8) foodStack.set(ModComponents.SEASONING, ModItems.EMPYREAN_POWDER);
        else {
            foodStack.setCount(foodStack.getCount() - 8);

            ItemStack newStack = foodStack.copy();
            newStack.setCount(8);
            newStack.set(ModComponents.SEASONING, ModItems.EMPYREAN_POWDER);
            player.giveItemStack(newStack);
        }

        powderStack.decrementUnlessCreative(1, player);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1f, 2f);
        player.swingHand(hand, true);
        return TypedActionResult.success(powderStack);
    }

    private static ActionResult dyeShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient() || !(stack.getItem() instanceof DyeItem dyeItem) || !ModConfig.INSTANCE.dyeShulkerAndBrush) return ActionResult.PASS;

        if (entity instanceof ShulkerEntity shulker) {
            stack.decrementUnlessCreative(1, player);
            shulker.setVariant(Optional.ofNullable(dyeItem.getColor()));

            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private static ActionResult useBrushOnDyedShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (hand != Hand.MAIN_HAND  || !ModConfig.INSTANCE.dyeShulkerAndBrush) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient
                || !(stack.getItem() instanceof BrushItem)
                || !(entity instanceof ShulkerEntity shulker)
                || shulker.getVariant().isEmpty()
                || entityHitResult == null
        ) return ActionResult.PASS;

        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        world.playSound(null, x, y, z, SoundEvents.ENTITY_ARMADILLO_BRUSH, SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        if (!player.isCreative()) stack.damage(8, player, EquipmentSlot.MAINHAND);

        Optional<DyeColor> variant = shulker.getVariant();
        DyeColor color = variant.get();
        Item dyeItem = DyeItem.byColor(color);
        ItemStack dyeStack = new ItemStack(dyeItem);

        ItemEntity dyeItemEntity = new ItemEntity(world, x, y+0.4, z, dyeStack);
        world.spawnEntity(dyeItemEntity);

        player.swingHand(hand, true);
        return ActionResult.SUCCESS;
    }

    private static ActionResult useChilledBonemealOnAnimal(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (hand != Hand.MAIN_HAND) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient
                || stack.getItem() != ModItems.CHILLED_BONE_MEAL
                || !(entity instanceof PassiveEntity animal)
                || !animal.isBaby()
                || entityHitResult == null
        ) return ActionResult.PASS;

        double x = animal.getX();
        double y = animal.getY();
        double z = animal.getZ();


        if (animal.getBreedingAge() < -24000) {
            animal.setBreedingAge(-24000);
            world.playSound(null, x, y, z, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.NEUTRAL,
                    0.75F,
                    (world.getRandom().nextFloat() * 0.2F + 0.5F)
            );
        } else {
            animal.setBreedingAge(Integer.MIN_VALUE);
            world.playSound(null, x, y, z, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL,
                    0.75F,
                    (world.getRandom().nextFloat() * 0.2F + 0.5F)
            );
        }

        stack.decrementUnlessCreative(1, player);
        player.swingHand(hand, true);
        return ActionResult.CONSUME;
    }

    private static ActionResult useOnSuspiciousBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !player.isSneaking() || stack.isEmpty() || !ModConfig.INSTANCE.useOnSusSand) return ActionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BrushableBlockEntity && ((BrushableBlockEntity) blockEntity).getItem().isEmpty()) {
            ((BrushableBlockEntityAccessor) blockEntity).setItem(stack.copy());
            stack.decrement(stack.getCount());

            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_PLACE,
                    SoundCategory.PLAYERS, 2.0F, 0.5F);

            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static ActionResult featherDamageFreeKB(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.FEATHER) && !world.isClient && entity instanceof LivingEntity e && ModConfig.INSTANCE.enableFeatherAttack) {
            if (entity.timeUntilRegen > 0) return ActionResult.FAIL;
            player.swingHand(hand, true);

            double knockbackRes = e.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            double strength = (knockbackRes > 1 || knockbackRes < 0) ? 0 : 0.4*(1-knockbackRes);

            e.takeKnockback(strength, player.getX() - entity.getX(), player.getZ() - entity.getZ());
            e.timeUntilRegen = 10;
            return ActionResult.FAIL;
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
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public static ActionResult sitOnSaddleUse(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient() || !ModConfig.INSTANCE.enableSitting) return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (item != Items.SADDLE || player.getItemCooldownManager().isCoolingDown(item)) return ActionResult.PASS;

        if (isValidSittableBlock(state)) {
            player.getItemCooldownManager().set(stack.getItem(), 10);

            Entity entity;
            List<ChairEntity> entities = world.getEntitiesByType(ModEntities.CHAIR, new Box(pos), chair -> true);
            if(entities.isEmpty()) {
                entity = ModEntities.CHAIR.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            } else {
                entity = entities.get(0);
            }

            player.startRiding(entity);

            player.swingHand(Hand.MAIN_HAND, true);
            return ActionResult.SUCCESS;
        } return ActionResult.PASS;
    }

    public static ActionResult magicTouchEvent(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        ItemStack stack = player.getMainHandStack();
        BlockState bs = world.getBlockState(pos);

        if (world.isClient() || !(world instanceof ServerWorld) || !EnchantmentUtils.hasEnchant(stack, "magic_touch")) return ActionResult.PASS;

        Block bl = bs.getBlock();
        ItemStack drop = new ItemStack(bl);
        boolean doDrop = true;

        if (bl instanceof PlayerSkullBlock) {
            BlockEntity be = world.getBlockEntity(pos);
            ProfileComponent profileComponent = getProfileComponent(be);
            if (profileComponent != null) drop.set(DataComponentTypes.PROFILE, profileComponent);

        } else if (requiresSilkMagicTouch(bl)) {
            if (!EnchantmentUtils.hasEnchant(stack, "silk_touch")) doDrop = false;

        } else if (!(notRequiresSilkMagicTouch(bl))) return ActionResult.PASS;


        world.breakBlock(pos, !doDrop, player);
        player.incrementStat(Stats.MINED.getOrCreateStat(bl));
        player.addExhaustion(0.005F);
        stack.damage(1, player, EquipmentSlot.MAINHAND);

        if (doDrop)
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop));

        return ActionResult.SUCCESS;
    }



    private static @Nullable ProfileComponent getProfileComponent(BlockEntity be) {
        ProfileComponent profileComponent = null;
        if (be instanceof SkullBlockEntity skull) {

            ProfileComponent stored = skull.getOwner();
            if (stored != null) {
                profileComponent = new ProfileComponent(
                        stored.name(),
                        stored.id(),
                        stored.properties()
                );
            }
        }
        return profileComponent;
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

    private static boolean isValidSittableBlock(BlockState state) {
        if (state.getBlock() instanceof StairsBlock) return state.get(StairsBlock.HALF) == BlockHalf.BOTTOM;
        if (state.getBlock() instanceof SlabBlock) return state.get(SlabBlock.TYPE) == SlabType.BOTTOM;
        return state.getBlock() instanceof BedBlock;
    }

    private static boolean requiresSilkMagicTouch(Block bl) {
        return (
                bl instanceof StainedGlassBlock
                        || bl instanceof StainedGlassPaneBlock
                        || bl == Blocks.GLASS
                        || bl == Blocks.GLASS_PANE
                        || bl == Blocks.TURTLE_EGG
                        || bl == Blocks.SEA_LANTERN
                        || bl == Blocks.GLOWSTONE
                        || bl == Blocks.BUDDING_AMETHYST
                        || bl == Blocks.AMETHYST_CLUSTER
                        || bl == Blocks.SMALL_AMETHYST_BUD
                        || bl == Blocks.MEDIUM_AMETHYST_BUD
                        || bl == Blocks.LARGE_AMETHYST_BUD
        );
    }

    private static boolean notRequiresSilkMagicTouch(Block bl) {
        return (
                bl == Blocks.REDSTONE_LAMP
                        || bl == Blocks.BEACON
                        || bl == Blocks.OCHRE_FROGLIGHT
                        || bl == Blocks.PEARLESCENT_FROGLIGHT
                        || bl == Blocks.HONEYCOMB_BLOCK
                        || bl == Blocks.SNIFFER_EGG
                        || bl == Blocks.HEAVY_CORE
                        || (bl instanceof SkullBlock && !(bl instanceof PlayerSkullBlock))
        );
    }
}

