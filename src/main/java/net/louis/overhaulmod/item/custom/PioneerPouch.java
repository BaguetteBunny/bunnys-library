package net.louis.overhaulmod.item.custom;

import net.louis.overhaulmod.utils.BundleContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.consume.UseAction;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PioneerPouch extends BundleItem {
    public PioneerPouch(Settings settings, Identifier openFrontTexture, Identifier openBackTexture) {
        super(openFrontTexture, openBackTexture, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockPos pos = context.getBlockPos();
        Direction side = context.getSide();

        if (player == null) return ActionResult.FAIL;

        BundleContentsComponent contents = stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);

        if (contents.isEmpty()) return ActionResult.FAIL;

        List<ItemStack> weightedBlocks = new ArrayList<>();
        for (ItemStack itemStack : contents.iterate()) {
            if (itemStack.getItem() instanceof BlockItem) {
                for (int i = 0; i < itemStack.getCount(); i++) {
                    weightedBlocks.add(itemStack);
                }
            }
        }

        if (weightedBlocks.isEmpty()) return ActionResult.FAIL;

        ItemStack selectedStack = weightedBlocks.get(world.getRandom().nextInt(weightedBlocks.size()));
        BlockItem blockItem = (BlockItem) selectedStack.getItem();

        BlockPos placePos = pos.offset(side);
        BlockState blockState = blockItem.getBlock().getDefaultState();

        if (world.canPlayerModifyAt(player, placePos) && world.getBlockState(placePos).isReplaceable()) {
            if (!world.isClient()) {
                world.setBlockState(placePos, blockState, Block.NOTIFY_ALL);
                world.playSound(null, placePos, blockState.getSoundGroup().getPlaceSound(),
                        SoundCategory.BLOCKS, 1.0F, 1.0F);

                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(contents);

                List<ItemStack> newStacks = new ArrayList<>();
                boolean decremented = false;

                for (ItemStack itemStack : contents.iterate()) {
                    if (!decremented && ItemStack.areItemsEqual(itemStack, selectedStack)) {
                        if (itemStack.getCount() > 1) {
                            ItemStack copy = itemStack.copy();
                            copy.decrement(1);
                            newStacks.add(copy);
                        }
                        decremented = true;
                    } else {
                        newStacks.add(itemStack.copy());
                    }
                }

                BundleContentsComponent newContents = new BundleContentsComponent(newStacks);
                stack.set(DataComponentTypes.BUNDLE_CONTENTS, newContents);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return ActionResult.FAIL;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 0;
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        BundleContext.set(stack);
        try {
            return super.onStackClicked(stack, slot, clickType, player);
        } finally {
            BundleContext.clear();
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        BundleContext.set(stack);
        try {
            return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
        } finally {
            BundleContext.clear();
        }
    }
}
