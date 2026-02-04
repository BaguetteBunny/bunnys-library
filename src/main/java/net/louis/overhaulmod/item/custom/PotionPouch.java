package net.louis.overhaulmod.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.PotionItem;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PotionPouch extends BundleItem {

    public PotionPouch(Item.Settings settings, Identifier open, Identifier closed) {
        super(open, closed, settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!otherStack.isOf(Items.POTION)) {
            return false;
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        BundleContentsComponent contents = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (contents == null) return false;

        ItemStack slotStack = slot.getStack();
        BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(contents);
        if (clickType == ClickType.LEFT && !slotStack.isEmpty()) {
            int inserted = builder.add(slot, player);

            if (inserted > 0) {
                playInsertSound(player);
            } else {
                playInsertFailSound(player);
            }

            stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());
            onContentChanged(player);
            return true;
        }

        if (clickType == ClickType.RIGHT && slotStack.isEmpty()) {
            ItemStack removed = builder.removeSelected();

            if (removed != null) {
                ItemStack remainder = slot.insertStack(removed);

                if (!remainder.isEmpty()) {
                    builder.add(remainder);
                } else {
                    playRemoveOneSound(player);
                }
            }

            stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());
            onContentChanged(player);
            return true;
        }

        return false;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (consumeSelectedPotionItem(itemStack, user)) {
            this.playPotionConsumeSound(user);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    private static boolean consumeSelectedPotionItem(ItemStack stack, PlayerEntity player) {
        BundleContentsComponent contents = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (contents == null || contents.isEmpty()) return false;


        BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(contents);
        ItemStack selected = builder.build().get(0);

        if (selected == null || !(selected.getItem() instanceof PotionItem)) return false;

        PotionContentsComponent potion = selected.get(DataComponentTypes.POTION_CONTENTS);
        if (potion != null) {
            for (StatusEffectInstance effect : potion.getEffects()) player.addStatusEffect(new StatusEffectInstance(effect));
        }

        builder.removeSelected();
        stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

        return true;
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playPotionConsumeSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_GENERIC_DRINK.value(), 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private static void playInsertFailSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT_FAIL, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private void onContentChanged(PlayerEntity user) {
        ScreenHandler screenHandler = user.currentScreenHandler;
        if (screenHandler != null) screenHandler.onContentChanged(user.getInventory());
    }

}


