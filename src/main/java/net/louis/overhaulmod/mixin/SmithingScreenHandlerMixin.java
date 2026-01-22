package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin extends ForgingScreenHandler {

    public SmithingScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At("RETURN"))
    private void LOM$modifyTemplateSlot(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci) {
        SmithingScreenHandler handler = (SmithingScreenHandler)(Object)this;
        Slot templateSlot = handler.slots.get(0);

        handler.slots.set(0, new Slot(templateSlot.inventory, 0, templateSlot.x, templateSlot.y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return super.canInsert(stack) ||
                        stack.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE) ||
                        stack.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE);
            }
        });
    }

    @Inject(method = "canInsertIntoSlot", at = @At("HEAD"), cancellable = true)
    private void LOM$allowQuickMoveCustomTemplates(ItemStack stack, Slot slot, CallbackInfoReturnable<Boolean> cir) {
        if (slot.id == 0 && (stack.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE) || stack.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE)))
            cir.setReturnValue(true);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$handleGlowingTrimCrafting(CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack base = this.input.getStack(1);
        ItemStack addition = this.input.getStack(2);

        if (addition.isEmpty() && !base.isEmpty() && base.get(DataComponentTypes.TRIM) != null) {
            if (template.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE)) {
                ItemStack result = base.copy();
                result.set(ModComponents.GLOW_AND_PULSATE, false);

                this.output.setStack(0, result);
                this.sendContentUpdates();
                ci.cancel();
            }
            if (template.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE)) {
                ItemStack result = base.copy();
                result.set(ModComponents.GLOW_AND_PULSATE, true);

                this.output.setStack(0, result);
                this.sendContentUpdates();
                ci.cancel();
            }
        }
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void LOM$consumeGlowingTrimItems(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack base = this.input.getStack(1);
        ItemStack addition = this.input.getStack(2);

        if ((template.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE) ||template.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE)) && addition.isEmpty() && !base.isEmpty() && base.get(DataComponentTypes.TRIM) != null) {
            this.context.run((world, pos) -> {
                template.decrement(1);
            });
        }
    }
}
