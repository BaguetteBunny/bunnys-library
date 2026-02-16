package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.RepairableComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.louis.overhaulmod.utils.ToolArmorUtil.getToolMaterialItem;
import static net.louis.overhaulmod.utils.ToolArmorUtil.isNetheriteArmor;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin extends ForgingScreenHandler {
    public SmithingScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, ForgingSlotsManager forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At("RETURN"))
    private void LOM$modifyTemplateSlot(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci) {
        SmithingScreenHandler handler = (SmithingScreenHandler)(Object)this;
        Slot templateSlot = handler.slots.get(0);

        handler.slots.set(0, new Slot(templateSlot.inventory, 0, templateSlot.x, templateSlot.y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return super.canInsert(stack) || isValidSmithingItem(stack);
            }
        });
    }

    @Inject(method = "canInsertIntoSlot", at = @At("HEAD"), cancellable = true)
    private void LOM$allowQuickMoveCustomTemplates(ItemStack stack, Slot slot, CallbackInfoReturnable<Boolean> cir) {
        if (slot.id == 0 && (isValidSmithingItem(stack))) cir.setReturnValue(true);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$handleGlowingTrimCrafting(CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack base = this.input.getStack(1);
        ItemStack addition = this.input.getStack(2);

        if (!addition.isEmpty() && addition.getItem() == ModItems.AZURITE && !base.isEmpty() && base.get(DataComponentTypes.TRIM) != null && base.getItem() instanceof ArmorItem armorItem) {
            Item azuriteMat = getToolMaterialItem(addition.getOrDefault(ModComponents.AZURITE_REFINE, ""));
            if (azuriteMat == null) return;
            if (!base.canRepairWith(azuriteMat.getDefaultStack()) && !(isNetheriteArmor(armorItem) && azuriteMat == Items.NETHERITE_SCRAP)) return;

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

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$handleBundleTrimCrafting(CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack bundle = this.input.getStack(1);
        ItemStack ingredient = this.input.getStack(2);

        if (ingredient.isEmpty() && !bundle.isEmpty() && bundle.getItem() instanceof BundleItem && bundle.get(ModComponents.BUNDLE_MAX_FACTOR) == null) {
            ItemStack result = bundle.copy();

            int factor;
            if (template.isOf(ModItems.BIG_BUNDLE_UPGRADE_SMITHING_TEMPLATE)) factor = 2;
            else if (template.isOf(ModItems.TITANIC_BUNDLE_UPGRADE_SMITHING_TEMPLATE)) factor = 4;
            else return;

            Text name = result.getName();
            if (name == result.getItemName()) {

                Text newName;
                if (template.isOf(ModItems.BIG_BUNDLE_UPGRADE_SMITHING_TEMPLATE)) newName = Text.of("Large ").copy().append(name);
                else if (template.isOf(ModItems.TITANIC_BUNDLE_UPGRADE_SMITHING_TEMPLATE)) newName = Text.of("Titanic ").copy().append(name);
                else newName = name;

                result.set(DataComponentTypes.ITEM_NAME, newName);
            }

            result.set(ModComponents.BUNDLE_MAX_FACTOR, factor);

            this.output.setStack(0, result);
            this.sendContentUpdates();
            ci.cancel();
        }
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void LOM$consumeGlowingTrimItems(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack base = this.input.getStack(1);
        ItemStack addition = this.input.getStack(2);

        if ((template.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE) || template.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE)) && !addition.isEmpty() && addition.getItem() == ModItems.AZURITE && !base.isEmpty() && base.get(DataComponentTypes.TRIM) != null && base.getItem() instanceof ArmorItem armorItem) {
            Item azuriteMat = getToolMaterialItem(addition.getOrDefault(ModComponents.AZURITE_REFINE, ""));
            if (azuriteMat == null) return;
            if (!base.canRepairWith(azuriteMat.getDefaultStack()) && !(isNetheriteArmor(armorItem) && azuriteMat == Items.NETHERITE_SCRAP)) return;

            this.context.run((world, pos) -> {
                template.decrement(1);
                addition.decrement(1);
            });
        }
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void LOM$consumeBundleTrimItems(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        ItemStack template = this.input.getStack(0);
        ItemStack bundle = this.input.getStack(1);
        ItemStack addition = this.input.getStack(2);

        if ((template.isOf(ModItems.BIG_BUNDLE_UPGRADE_SMITHING_TEMPLATE) ||template.isOf(ModItems.TITANIC_BUNDLE_UPGRADE_SMITHING_TEMPLATE)) && bundle.getItem() instanceof BundleItem && bundle.get(ModComponents.BUNDLE_MAX_FACTOR) == null) {
            this.context.run((world, pos) -> {
                template.decrement(1);
            });
        }
    }

    private static boolean isValidSmithingItem(ItemStack stack) {
        return (
                stack.isOf(ModItems.PULSING_UPGRADE_SMITHING_TEMPLATE) ||
                        stack.isOf(ModItems.GLOW_UPGRADE_SMITHING_TEMPLATE) ||
                        stack.isOf(ModItems.BIG_BUNDLE_UPGRADE_SMITHING_TEMPLATE) ||
                        stack.isOf(ModItems.TITANIC_BUNDLE_UPGRADE_SMITHING_TEMPLATE)
        );
    }
}
