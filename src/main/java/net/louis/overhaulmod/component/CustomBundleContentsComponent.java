package net.louis.overhaulmod.component;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.screen.slot.Slot;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.minecraft.item.ItemStack.areEqual;

public final class CustomBundleContentsComponent implements TooltipData {
    public static final CustomBundleContentsComponent DEFAULT = new CustomBundleContentsComponent(List.of(), 64);
    public static final Codec<CustomBundleContentsComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ItemStack.CODEC.listOf().fieldOf("items").forGetter(c -> c.stacks),
                    Codec.INT.fieldOf("max_slots").forGetter(c -> c.maximumSlots)
            ).apply(instance, CustomBundleContentsComponent::new)
    );

    private static final int ADD_TO_NEW_SLOT = -1;
    final List<ItemStack> stacks;
    final Fraction occupancy;
    final int maximumSlots;
    private static final Fraction NESTED_BUNDLE_OCCUPANCY = Fraction.getFraction(1, 16);

    CustomBundleContentsComponent(List<ItemStack> stacks, Fraction occupancy, int max) {
        this.stacks = stacks;
        this.occupancy = occupancy;
        this.maximumSlots = max;
    }

    public CustomBundleContentsComponent(List<ItemStack> stacks, int maximum_slots) {
        this(stacks, calculateOccupancy(stacks, maximum_slots), maximum_slots);
    }

    private static Fraction calculateOccupancy(List<ItemStack> stacks, int max) {
        Fraction fraction = Fraction.ZERO;

        for (ItemStack itemStack : stacks) fraction = fraction.add(getOccupancy(itemStack, max).multiplyBy(Fraction.getFraction(itemStack.getCount(), 1)));

        return fraction;
    }

    static Fraction getOccupancy(ItemStack stack, int max) {
        CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            return NESTED_BUNDLE_OCCUPANCY.add(bundleContentsComponent.getOccupancy());
        } else {
            List<BeehiveBlockEntity.BeeData> list = stack.getOrDefault(DataComponentTypes.BEES, List.of());
            if (stack.getMaxCount() == 1)
                return Fraction.getFraction(1, 8+4*log2(((double) max /64)));
            return !list.isEmpty() ? Fraction.ONE : Fraction.getFraction(1, max);
        }
    }

    public boolean stacksEqual(List<ItemStack> left, List<ItemStack> right) {
        if (left.size() != right.size()) {
            return false;
        } else {
            for(int i = 0; i < left.size(); ++i) {
                if (!areEqual((ItemStack)left.get(i), (ItemStack)right.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public ItemStack get(int index) {
        return (ItemStack)this.stacks.get(index);
    }

    public int getMaximumSlots() {
        return this.maximumSlots;
    }

    public Stream<ItemStack> stream() {
        return this.stacks.stream().map(ItemStack::copy);
    }

    public Iterable<ItemStack> iterate() {
        return this.stacks;
    }

    public List<ItemStack> getStacks() {
        return stacks;
    }

    public Iterable<ItemStack> iterateCopy() {
        return Lists.<ItemStack, ItemStack>transform(this.stacks, ItemStack::copy);
    }

    public int size() {
        return this.stacks.size();
    }

    public Fraction getOccupancy() {
        return this.occupancy;
    }

    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    public String toString() {
        return "CustomBundleContents" + this.stacks;
    }

    public static class Builder {
        public final List<ItemStack> stacks;
        private Fraction occupancy;
        private final int max;

        public Builder(CustomBundleContentsComponent base) {
            this.stacks = new ArrayList<>(base.stacks);
            this.occupancy = base.occupancy;
            this.max = base.maximumSlots;
        }

        public CustomBundleContentsComponent.Builder clear() {
            this.stacks.clear();
            this.occupancy = Fraction.ZERO;
            return this;
        }

        private int addInternal(ItemStack stack) {
            if (!stack.isStackable()) {
                return -1;
            } else {
                for (int i = 0; i < this.stacks.size(); i++) {
                    if (ItemStack.areItemsAndComponentsEqual((ItemStack)this.stacks.get(i), stack)) {
                        return i;
                    }
                }

                return -1;
            }
        }

        public int add(ItemStack stack) {
            if (isMaxed(stack)) return 0;
            if (!stack.isEmpty() && stack.getItem().canBeNested()) {
                if (this.occupancy.compareTo(Fraction.ONE) >= 0) {
                    return 0;
                }

                Fraction itemOccupancy = CustomBundleContentsComponent.getOccupancy(stack, this.max);
                Fraction remainingSpace = Fraction.ONE.subtract(this.occupancy);

                int maxItemsThatFit = remainingSpace.divideBy(itemOccupancy).intValue();
                int i = Math.min(Math.min(stack.getCount(), maxItemsThatFit), stack.getMaxCount());

                if (i == 0) {
                    return 0;
                } else {
                    this.occupancy = this.occupancy.add(itemOccupancy.multiplyBy(Fraction.getFraction(i, 1)));
                    int j = this.addInternal(stack);
                    if (j != -1) {
                        ItemStack itemStack = (ItemStack)this.stacks.remove(j);
                        ItemStack itemStack2 = itemStack.copyWithCount(itemStack.getCount() + i);
                        stack.decrement(i);
                        this.stacks.add(0, itemStack2);
                    } else {
                        this.stacks.add(0, stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        public int add(Slot slot, PlayerEntity player) {
            ItemStack itemStack = slot.getStack();
            return this.add(slot.takeStackRange(itemStack.getCount(), this.max, player));
        }

        private boolean isMaxed(ItemStack stack) {
            for (ItemStack storedStack : this.stacks) {
                if (storedStack.getItem() == stack.getItem() && storedStack.getMaxCount() < (stack.getCount() + storedStack.getCount())) {
                    return true;
                }
            }
            return false;
        }

        @Nullable
        public ItemStack removeFirst() {
            if (this.stacks.isEmpty()) {
                return null;
            } else {
                ItemStack itemStack = ((ItemStack)this.stacks.remove(0)).copy();
                this.occupancy = this.occupancy.subtract(CustomBundleContentsComponent.getOccupancy(itemStack, this.max).multiplyBy(Fraction.getFraction(itemStack.getCount(), 1)));
                return itemStack;
            }
        }

        @Nullable
        public ItemStack removeOneFromIndex(int idx) {
            if (this.stacks.isEmpty() || idx < 0 || idx >= this.stacks.size()) return null;


            ItemStack stackAtIndex = this.stacks.get(idx);
            if (stackAtIndex.isEmpty()) return null;


            ItemStack singleItem = stackAtIndex.copy();
            singleItem.setCount(1);
            stackAtIndex.decrement(1);

            if (stackAtIndex.isEmpty()) this.stacks.remove(idx);

            this.occupancy = this.occupancy.subtract(CustomBundleContentsComponent.getOccupancy(singleItem, this.max));
            return singleItem;
        }

        public Fraction getOccupancy() {
            return this.occupancy;
        }

        public CustomBundleContentsComponent build() {
            return new CustomBundleContentsComponent(List.copyOf(this.stacks), this.occupancy, this.max);
        }
    }

    public static int log2(double N) {
        return (int) (Math.log(N) / Math.log(2));
    }
}
