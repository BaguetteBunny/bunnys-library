package net.louis.overhaulmod.cauldron;

import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.cauldron.custom.ColoredWaterCauldronBlock;
import net.louis.overhaulmod.cauldron.custom.DragonBreathCauldronBlock;
import net.louis.overhaulmod.cauldron.custom.HoneyCauldronBlock;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.utils.FluidColors;
import net.louis.overhaulmod.utils.enums.FluidType;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.ItemActionResult;
import net.minecraft.world.event.GameEvent;

import static net.louis.overhaulmod.cauldron.custom.ColoredWaterCauldronBlock.FLUID_TYPE;

public class ModCauldron {
    public static void registerDragonBreathCauldron() {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(Items.DRAGON_BREATH, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                world.setBlockState(pos, ModBlocks.DRAGON_BREATH_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(GameEvent.FLUID_PLACE, pos, GameEvent.Emitter.of(player, state));
            }
            return ItemActionResult.success(world.isClient);
        });

        // Filling an existing db cauldron (level < 3)
        DragonBreathCauldronBlock.DB_CAULDRON_BEHAVIOR.map().put(Items.DRAGON_BREATH, (state, world, pos, player, hand, stack) -> {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (level < 3) {
                if (!world.isClient) {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level + 1));
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.5F);
                    world.emitGameEvent(GameEvent.FLUID_PLACE, pos, GameEvent.Emitter.of(player, state));
                }
                return ItemActionResult.success(world.isClient);
            }
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        });

        // Using an empty bottle on a db cauldron removes one level
        DragonBreathCauldronBlock.DB_CAULDRON_BEHAVIOR.map().put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (!world.isClient) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.DRAGON_BREATH)));
                if (level > 1) {
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.5F);
                world.emitGameEvent(GameEvent.FLUID_PICKUP, pos, GameEvent.Emitter.of(player, state));
            }
            return ItemActionResult.success(world.isClient);
        });
    }

    public static void registerHoneyCauldron() {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(Items.HONEY_BOTTLE, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                world.setBlockState(pos, ModBlocks.HONEY_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(GameEvent.FLUID_PLACE, pos, GameEvent.Emitter.of(player, state));
            }
            return ItemActionResult.success(world.isClient);
        });

        // Filling an existing honey cauldron (level < 3)
        HoneyCauldronBlock.HONEY_CAULDRON_BEHAVIOR.map().put(Items.HONEY_BOTTLE, (state, world, pos, player, hand, stack) -> {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (level < 3) {
                if (!world.isClient) {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level + 1));
                    world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(GameEvent.FLUID_PLACE, pos, GameEvent.Emitter.of(player, state));
                }
                return ItemActionResult.success(world.isClient);
            }
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        });

        // Using an empty bottle on a honey cauldron removes one level
        HoneyCauldronBlock.HONEY_CAULDRON_BEHAVIOR.map().put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (!world.isClient) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.HONEY_BOTTLE)));
                if (level > 1) {
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
                world.playSound(null, pos, SoundEvents.ITEM_HONEY_BOTTLE_DRINK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(GameEvent.FLUID_PICKUP, pos, GameEvent.Emitter.of(player, state));
            }
            return ItemActionResult.success(world.isClient);
        });
    }

    public static void registerColoredCauldrons() {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.BLACK));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.WHITE));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.BROWN));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.RED));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.GREEN));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.LIGHT_BLUE));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.GRAY));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.LIGHT_GRAY));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.LIME));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.PURPLE));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.MAGENTA));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.CYAN));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.YELLOW));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.ORANGE));}
            return ItemActionResult.success(world.isClient);});

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(FLUID_TYPE, FluidType.PINK));}
            return ItemActionResult.success(world.isClient);});

        for (var entry : FluidColors.DYE_ITEM.entrySet()) {
            DyeColor color = entry.getValue();
            FluidType fluidType = entry.getKey();
            Item dyeItem = DyeItem.byColor(color);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(dyeItem, (state, world, pos, player, hand, stack) -> {
                if (!world.isClient) {
                    world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(ColoredWaterCauldronBlock.FLUID_TYPE, fluidType));
                    world.playSound(null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    stack.decrementUnlessCreative(1, player);
                }
                return ItemActionResult.success(world.isClient);
            });
        }

        // Empty Cauldron
        ColoredWaterCauldronBlock.COLORED_WATER_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                FluidType type = state.get(FLUID_TYPE);
                if (type == FluidType.BLACK) player.setStackInHand(hand, new ItemStack(ModItems.BLACK_WATER_BUCKET));
                else if (type == FluidType.WHITE) player.setStackInHand(hand, new ItemStack(ModItems.WHITE_WATER_BUCKET));
                else if (type == FluidType.GRAY) player.setStackInHand(hand, new ItemStack(ModItems.GRAY_WATER_BUCKET));
                else if (type == FluidType.GREEN) player.setStackInHand(hand, new ItemStack(ModItems.GREEN_WATER_BUCKET));
                else if (type == FluidType.LIGHT_BLUE) player.setStackInHand(hand, new ItemStack(ModItems.LIGHT_BLUE_WATER_BUCKET));
                else if (type == FluidType.LIGHT_GRAY) player.setStackInHand(hand, new ItemStack(ModItems.LIGHT_GRAY_WATER_BUCKET));
                else if (type == FluidType.MAGENTA) player.setStackInHand(hand, new ItemStack(ModItems.MAGENTA_WATER_BUCKET));
                else if (type == FluidType.YELLOW) player.setStackInHand(hand, new ItemStack(ModItems.YELLOW_WATER_BUCKET));
                else if (type == FluidType.PINK) player.setStackInHand(hand, new ItemStack(ModItems.PINK_WATER_BUCKET));
                else if (type == FluidType.PURPLE) player.setStackInHand(hand, new ItemStack(ModItems.PURPLE_WATER_BUCKET));
                else if (type == FluidType.LIME) player.setStackInHand(hand, new ItemStack(ModItems.LIME_WATER_BUCKET));
                else if (type == FluidType.RED) player.setStackInHand(hand, new ItemStack(ModItems.RED_WATER_BUCKET));
                else if (type == FluidType.ORANGE) player.setStackInHand(hand, new ItemStack(ModItems.ORANGE_WATER_BUCKET));
                else if (type == FluidType.BROWN) player.setStackInHand(hand, new ItemStack(ModItems.BROWN_WATER_BUCKET));
                else if (type == FluidType.CYAN) player.setStackInHand(hand, new ItemStack(ModItems.CYAN_WATER_BUCKET));
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ItemActionResult.success(world.isClient);
        });
    }

    public static void registerBehaviors() {
        registerHoneyCauldron();
        registerDragonBreathCauldron();
        registerColoredCauldrons();
    }
}
