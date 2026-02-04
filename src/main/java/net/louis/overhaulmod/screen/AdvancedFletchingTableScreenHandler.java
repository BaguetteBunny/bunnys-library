package net.louis.overhaulmod.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;

public class AdvancedFletchingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public AdvancedFletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos));
    }

    public AdvancedFletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);
        this.addSlot(new Slot(inventory, 1, 36, 52) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }
        });

        this.addSlot(new Slot(inventory, 2, 55, 35) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }
        });

        this.addSlot(new Slot(inventory, 3, 73, 17) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }
        });
        this.addSlot(new Slot(inventory, 0, 130, 35) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                inventory.removeStack(1, 1);
                inventory.removeStack(2, 1);
                inventory.removeStack(3, 1);
                this.markDirty();
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (slot.inventory == this.inventory && slot.getIndex() == 0) {
                int f = slots.get(0).getStack().getCount();
                int s = slots.get(1).getStack().getCount();
                int h = slots.get(2).getStack().getCount();
                int amount = Math.min(f, Math.min(s, h));

                this.inventory.removeStack(1, amount);
                this.inventory.removeStack(2, amount);
                this.inventory.removeStack(3, amount);
                this.inventory.markDirty();

                Item resultItem = originalStack.getItem();
                ComponentMap components = originalStack.getComponents();
                int resultPerCraft = 4;

                for (int i = 0; i < amount; i++) {
                    ItemStack resultStack = new ItemStack(resultItem, resultPerCraft);
                    resultStack.applyComponentsFrom(components);
                    if (!player.getInventory().insertStack(resultStack)) player.dropItem(resultStack, false);
                }

                slot.setStack(ItemStack.EMPTY);
                return ItemStack.EMPTY;
            }

            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }

            } else {
                if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();
        }
        return newStack;
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        if (!(button == 1 && slotIndex == 3) && !(slotIndex >= 0 && slotIndex < this.slots.size() && actionType == SlotActionType.QUICK_MOVE && this.slots.get(slotIndex).inventory == player.getInventory() && !this.slots.get(slotIndex).getStack().isEmpty() && ItemStack.areItemsAndComponentsEqual(this.slots.get(slotIndex).getStack(), this.slots.get(3).getStack()))) super.onSlotClick(slotIndex, button, actionType, player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
