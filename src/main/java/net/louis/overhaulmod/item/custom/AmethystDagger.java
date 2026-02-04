package net.louis.overhaulmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class AmethystDagger extends Item {
    public AmethystDagger(Settings settings) {
        super(settings);
    }

    private static final Map<Block, Block> DEFROSTABLES = Map.ofEntries(
            Map.entry(Blocks.BLUE_ICE, Blocks.PACKED_ICE),
            Map.entry(Blocks.PACKED_ICE, Blocks.ICE)
    );

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack daggerStack = user.getMainHandStack();
        ItemStack offHand = user.getOffHandStack();

        if (offHand.isEmpty() || world.isClient()) return ActionResult.PASS;

        boolean success = true;
        Block offhandBlock = Block.getBlockFromItem(offHand.getItem());

        if (DEFROSTABLES.containsKey(offhandBlock)) {
            Block newBlock = DEFROSTABLES.get(offhandBlock);
            user.getInventory().offerOrDrop(new ItemStack(newBlock, 8));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 2.0F);
            daggerStack.damage(3, user, EquipmentSlot.MAINHAND);
        }

        else if (offHand.getItem().equals(Items.FILLED_MAP)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.MAP, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else if (offHand.getItem().equals(Items.WRITTEN_BOOK)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.WRITABLE_BOOK, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else if (offHand.getItem().equals(Items.PLAYER_HEAD)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.PLAYER_HEAD, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else success = false;


        if (success) {
            offHand.decrement(1);
            daggerStack.damage(1, user, EquipmentSlot.MAINHAND);
            user.swingHand(Hand.MAIN_HAND, true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}