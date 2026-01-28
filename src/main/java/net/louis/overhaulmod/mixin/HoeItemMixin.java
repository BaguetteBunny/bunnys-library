package net.louis.overhaulmod.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.louis.overhaulmod.events.ModUseBlockEvents.dropBlockWithFortune;
import static net.louis.overhaulmod.utils.EnchantmentUtils.HOE_TILLABLE_MAP;
import static net.louis.overhaulmod.utils.EnchantmentUtils.hasEnchant;

@Mixin(HoeItem.class)
public class HoeItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void LOM$tilling3x3(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {

        ItemStack hoe = ctx.getStack();
        if (!hasEnchant(hoe, "tilling")) return;

        World world = ctx.getWorld();
        if (world.isClient()) return;

        BlockPos center = ctx.getBlockPos();
        BlockState centerInMap = HOE_TILLABLE_MAP.get(world.getBlockState(center).getBlock());
        PlayerEntity player = ctx.getPlayer();
        if (player == null) return;

        boolean success = false;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = center.add(dx, 0, dz);
                BlockState state = world.getBlockState(pos);

                Block block = state.getBlock();

                BlockState flattened = HOE_TILLABLE_MAP.get(block);
                if (centerInMap != null && flattened != null && !world.getBlockState(pos.up()).isIn(BlockTags.LEAVES) && (world.getBlockState(pos.up()).isIn(BlockTags.REPLACEABLE_BY_TREES) || world.getBlockState(pos.up()).isIn(BlockTags.FLOWERS) || world.getBlockState(pos.up()).isAir())) {
                    world.setBlockState(pos, flattened, Block.NOTIFY_ALL_AND_REDRAW);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, flattened));

                    if (block == Blocks.ROOTED_DIRT) Block.dropStack(world, pos, ctx.getSide(), new ItemStack(Items.HANGING_ROOTS));
                    hoe.damage(1, player, LivingEntity.getSlotForHand(ctx.getHand()));
                    success = true;
                }
                else if (centerInMap == null && block instanceof CropBlock crop && crop.isMature(state) && !(block instanceof BeetrootsBlock)) {
                    dropBlockWithFortune(world, pos, state, player, ctx.getStack());
                    world.setBlockState(pos, crop.getDefaultState().with(CropBlock.AGE, 0), Block.NOTIFY_ALL);
                    hoe.damage(1, player, LivingEntity.getSlotForHand(ctx.getHand()));
                    success = true;
                }
                else if (centerInMap == null && block instanceof BeetrootsBlock beetroot && beetroot.isMature(state)) {
                    dropBlockWithFortune(world, pos, state, player, ctx.getStack());
                    world.setBlockState(pos, beetroot.getDefaultState(), Block.NOTIFY_ALL);
                    player.incrementStat(Stats.MINED.getOrCreateStat(block));
                    hoe.damage(1, player, LivingEntity.getSlotForHand(ctx.getHand()));
                    success = true;
                }
            }
        }

        if (success) {
            if (centerInMap != null)
                world.playSound(null, center, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            else
                world.playSound(null, center, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 0.75F, (world.getRandom().nextFloat() * 0.2F + 1.5F));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
