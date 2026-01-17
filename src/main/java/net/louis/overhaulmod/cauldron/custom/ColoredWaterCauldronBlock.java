package net.louis.overhaulmod.cauldron.custom;

import com.mojang.serialization.MapCodec;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.utils.FluidColors;
import net.louis.overhaulmod.utils.enums.FluidType;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColoredWaterCauldronBlock extends AbstractCauldronBlock {
    @Override protected MapCodec<? extends AbstractCauldronBlock> getCodec() {return null;}
    public static final EnumProperty<FluidType> FLUID_TYPE = EnumProperty.of("fluid_type", FluidType.class);
    public static final CauldronBehavior.CauldronBehaviorMap COLORED_WATER_BEHAVIOR = CauldronBehavior.createMap("colored_water_cauldron_behavior");

    public ColoredWaterCauldronBlock(Settings settings) {
        super(settings, COLORED_WATER_BEHAVIOR);
        this.setDefaultState(this.stateManager.getDefaultState().with(FLUID_TYPE, FluidType.WHITE));
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FLUID_TYPE);
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.9;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) return;

        if (entity instanceof LivingEntity living) {
            boolean success = false;
            Iterable<ItemStack> armors = living.getAllArmorItems();
            for (ItemStack a : armors) {
                if (a.getItem() instanceof ArmorItem armor && armor.getMaterial() == ArmorMaterials.LEATHER) {
                    a.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(FluidColors.getColor(state.get(FLUID_TYPE)), false));
                    success = true;
                }
            }

            if (!success) return;
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.FALLING_WATER,
                        pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                        10, 0.3, 0.2, 0.3, 0.1
                );
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = stack.getItem();
        CauldronBehavior cauldronBehavior = (CauldronBehavior)this.behaviorMap.map().get(item);
        if (world.isClient) return cauldronBehavior.interact(state, world, pos, player, hand, stack);
        ItemStack newStack = null;
        boolean dyeWater = false;

        // 1. Tagged Items
        if (stack.isIn(ItemTags.WOOL)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getWoolItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (stack.isIn(ItemTags.WOOL_CARPETS)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getCarpetItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (stack.isIn(ItemTags.TERRACOTTA)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getTerracottaItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (stack.isIn(ItemTags.BEDS)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getBedItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (stack.isIn(ItemTags.CANDLES)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getCandleItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}

        // 2. Non Tagged Items
        else if (FluidColors.GLAZED_TERRACOTTA_ITEM.containsValue(item)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getGlazedTerracottaItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (FluidColors.CONCRETE_ITEM.containsValue(item)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getConcreteItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (FluidColors.CONCRETE_POWDER_ITEM.containsValue(item)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getConcretePowderItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (FluidColors.STAINED_GLASS_ITEM.containsValue(item)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getStainedGlassItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (FluidColors.STAINED_GLASS_PANE_ITEM.containsValue(item)) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getStainedGlassPaneItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}
        else if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {newStack = new ItemStack(RegistryEntry.of(FluidColors.getShulkerBoxItem(state.get(ColoredWaterCauldronBlock.FLUID_TYPE))), stack.getCount(), stack.getComponentChanges());}

        // 3. Dyeable Items
        else if ((item instanceof ArmorItem armor && armor.getMaterial() == ArmorMaterials.LEATHER)
                || (item instanceof AnimalArmorItem)) {
            newStack = stack.copy();
            newStack.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(FluidColors.getColor(state.get(FLUID_TYPE)), false));
        }

        // 4. Dye Items
        else if (item instanceof DyeItem) {
            newStack = stack.copy();
            for (var entry : FluidColors.DYE_ITEM.entrySet()) {
                DyeColor color = entry.getValue();
                Item dyeItem = DyeItem.byColor(color);

                if (dyeItem == item && !world.isClient()) {
                    world.setBlockState(pos, ModBlocks.COLORED_WATER_CAULDRON.getDefaultState().with(ColoredWaterCauldronBlock.FLUID_TYPE, entry.getKey()));
                    world.playSound(null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    newStack.decrementUnlessCreative(1, player);
                    player.swingHand(hand, true);
                    player.setStackInHand(hand, newStack);
                    dyeWater = true;
                    break;
                }
            }
        }

        if (newStack != null && !dyeWater) {
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.FALLING_WATER,
                        pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                        10, 0.3, 0.2, 0.3, 0.1
                );
            }
            player.swingHand(hand, true);
            player.setStackInHand(hand, newStack);
        }

        return cauldronBehavior.interact(state, world, pos, player, hand, stack);
    }
}
