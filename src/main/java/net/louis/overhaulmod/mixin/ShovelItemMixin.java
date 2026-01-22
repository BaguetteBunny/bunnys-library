package net.louis.overhaulmod.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.louis.overhaulmod.utils.EnchantmentUtils.SHOVEL_TILLABLE_MAP;
import static net.louis.overhaulmod.utils.EnchantmentUtils.hasEnchant;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void LOM$tilling3x3(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {

        ItemStack shovel = ctx.getStack();
        if (!hasEnchant(shovel, "tilling")) return;

        World world = ctx.getWorld();
        if (world.isClient()) return;

        BlockPos center = ctx.getBlockPos();
        BlockState centerState = world.getBlockState(center);
        PlayerEntity player = ctx.getPlayer();
        if (player == null) return;

        int successCount = 9;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = center.add(dx, 0, dz);
                BlockState state = world.getBlockState(pos);

                Block block = state.getBlock();

                BlockState flattened = SHOVEL_TILLABLE_MAP.get(block);
                if (flattened != null && !world.getBlockState(pos.up()).isIn(BlockTags.LEAVES) && (world.getBlockState(pos.up()).isIn(BlockTags.REPLACEABLE_BY_TREES) || world.getBlockState(pos.up()).isIn(BlockTags.FLOWERS) || world.getBlockState(pos.up()).isAir())) {
                    world.setBlockState(pos, flattened, Block.NOTIFY_ALL_AND_REDRAW);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, flattened));
                }
                else if (block instanceof CampfireBlock) {
                    CampfireBlock.extinguish(player, world, pos, state);
                    world.setBlockState(pos, state.with(Properties.LIT, false), Block.NOTIFY_ALL_AND_REDRAW);
                }
                else if (block instanceof SnowBlock && centerState.getBlock() instanceof SnowBlock)
                    world.breakBlock(pos, true, player);

                else successCount--;
            }
        }

        if (successCount > 0) {
            world.playSound(null, center, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
            shovel.damage(successCount, player, LivingEntity.getSlotForHand(ctx.getHand()));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
