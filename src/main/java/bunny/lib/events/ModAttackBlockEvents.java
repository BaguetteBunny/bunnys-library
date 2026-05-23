package bunny.lib.events;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import bunny.lib.utils.EnchantmentUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModAttackBlockEvents {
    public static void register() {
        AttackBlockCallback.EVENT.register(ModAttackBlockEvents::magicTouchEvent);
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
