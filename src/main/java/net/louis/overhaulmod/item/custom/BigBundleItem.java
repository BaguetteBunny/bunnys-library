package net.louis.overhaulmod.item.custom;

import net.louis.overhaulmod.component.CustomBundleTooltipData;
import net.louis.overhaulmod.component.CustomBundleContentsComponent;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.math.Fraction;

import java.util.List;
import java.util.Optional;

public class BigBundleItem extends Item {
    private static final int ITEM_BAR_COLOR = 6711039;
    int max;

    public BigBundleItem(Settings settings, int max_slot) {
        super(settings);
        this.max = max_slot;
    }

    public static void registerBigBundlePredicate(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.of("filled"),
                (stack, world, entity, seed) -> {
                    CustomBundleContentsComponent component = stack.getOrDefault(ModComponents.CUSTOM_BUNDLE_CONTENTS, null);
                    return (component != null && !component.isEmpty()) ? 1.0f : 0.0f;
                }
        );
    }

    public float getAmountFilled(ItemStack stack) {
        CustomBundleContentsComponent bundleContentsComponent = stack.getOrDefault(ModComponents.CUSTOM_BUNDLE_CONTENTS, new CustomBundleContentsComponent(List.of(), this.max));
        return bundleContentsComponent.getOccupancy().floatValue();
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent == null) bundleContentsComponent = new CustomBundleContentsComponent(List.of(), this.max);
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            ItemStack itemStack = slot.getStack();
            CustomBundleContentsComponent.Builder builder = new CustomBundleContentsComponent.Builder(bundleContentsComponent);
            if (itemStack.isEmpty()) {
                this.playRemoveOneSound(player);
                ItemStack itemStack2 = builder.removeFirst();
                if (itemStack2 != null) {
                    ItemStack itemStack3 = slot.insertStack(itemStack2);
                    builder.add(itemStack3);
                }
            } else if (itemStack.getItem().canBeNested()) {
                int i = builder.add(slot, player);
                if (i > 0) {
                    this.playInsertSound(player);
                }
            }

            stack.set(ModComponents.CUSTOM_BUNDLE_CONTENTS, builder.build());
            return true;
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent == null) bundleContentsComponent = new CustomBundleContentsComponent(List.of(), this.max);
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player)) {
            CustomBundleContentsComponent.Builder builder = new CustomBundleContentsComponent.Builder(bundleContentsComponent);
            if (otherStack.isEmpty()) {
                ItemStack itemStack = builder.removeFirst();
                if (itemStack != null) {
                    this.playRemoveOneSound(player);
                    cursorStackReference.set(itemStack);
                }
            } else {
                int i = builder.add(otherStack);
                if (i > 0) {
                    this.playInsertSound(player);
                }
            }

            stack.set(ModComponents.CUSTOM_BUNDLE_CONTENTS, builder.build());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (dropAllBundledItems(itemStack, user)) {
            this.playDropContentsSound(user);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        CustomBundleContentsComponent bundleContentsComponent = stack.getOrDefault(ModComponents.CUSTOM_BUNDLE_CONTENTS, new CustomBundleContentsComponent(List.of(), this.max));
        return bundleContentsComponent.getOccupancy().compareTo(Fraction.ZERO) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        CustomBundleContentsComponent bundleContentsComponent = stack.getOrDefault(ModComponents.CUSTOM_BUNDLE_CONTENTS, new CustomBundleContentsComponent(List.of(), this.max));
        return Math.min(1 + MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), 12), 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    private boolean dropAllBundledItems(ItemStack stack, PlayerEntity player) {
        CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent != null && !bundleContentsComponent.isEmpty()) {
            stack.set(ModComponents.CUSTOM_BUNDLE_CONTENTS, new CustomBundleContentsComponent(List.of(), this.max));
            if (player instanceof ServerPlayerEntity) {
                bundleContentsComponent.iterateCopy().forEach(stackx -> player.dropItem(stackx, true));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            int i = MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), this.max);
            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", i, this.max).formatted(Formatting.GRAY));
        }
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return !stack.contains(DataComponentTypes.HIDE_TOOLTIP) && !stack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP)
                ? Optional.ofNullable(stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS)).map(CustomBundleTooltipData::new)
                : Optional.empty();
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        CustomBundleContentsComponent bundleContentsComponent = entity.getStack().get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            entity.getStack().set(ModComponents.CUSTOM_BUNDLE_CONTENTS, new CustomBundleContentsComponent(List.of(), this.max));
            ItemUsage.spawnItemContents(entity, bundleContentsComponent.iterateCopy());
        }
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }
}
