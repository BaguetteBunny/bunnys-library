package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public class ItemFrameMixin {

    @Inject(method = "onBreak", at = @At("TAIL"))
    private void LOM$dropMembraneOnBreak(Entity entity, CallbackInfo ci) {
        ItemFrameEntity frame = (ItemFrameEntity) (Object) this;
        World world = frame.getWorld();

        if (!world.isClient && frame.isInvisible()) {
            ItemStack stack = new ItemStack(Items.PHANTOM_MEMBRANE);
            ItemEntity itemEntity = new ItemEntity(world, frame.getX(), frame.getY() + 0.1, frame.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void LOM$ignoreItemFrameIfChest(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        ItemFrameEntity frame = ((ItemFrameEntity) (Object) this);

        World world = player.getWorld();
        BlockPos chestPos = frame.getAttachedBlockPos().offset(frame.getFacing().getOpposite());
        BlockState state = world.getBlockState(chestPos);
        Block block = state.getBlock();

        if (frame.getHeldItemStack() != ItemStack.EMPTY && !player.isSneaking() && isChest(block) && ModConfig.INSTANCE.disableItemFrameInteractionIfChest) {
            ActionResult result = state.onUse(world, player, new BlockHitResult(
                    player.getPos(),
                    frame.getFacing().getOpposite(),
                    chestPos, false)
            );

            if (result.isAccepted() && !world.isClient()) {
                player.swingHand(hand, true);
                cir.setReturnValue(result);
                cir.cancel();
            }
        }

        if (!player.isSneaking() || !stack.isOf(Items.PHANTOM_MEMBRANE) || frame.isInvisible()) return;

        frame.setInvisible(true);
        stack.damage(1, player, EquipmentSlot.MAINHAND);
        frame.getWorld().playSound(null, frame.getBlockPos(),
                SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);

        cir.setReturnValue(ActionResult.SUCCESS);
        cir.cancel();
    }

    private boolean isChest(Block block) {
        return (block == Blocks.CHEST || block == Blocks.TRAPPED_CHEST || block == Blocks.ENDER_CHEST);
    }
}
