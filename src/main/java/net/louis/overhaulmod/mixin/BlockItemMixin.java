package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(method = "place", at = @At("HEAD"))
    private void LOM$decreaseBearClawDurability(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();

        if (player != null && !context.getWorld().isClient()) {
            if (player.getOffHandStack().getItem() == ModItems.BEAR_CLAW) {
                player.getOffHandStack().damage(1, player, EquipmentSlot.OFFHAND);
            }
            if (player.getMainHandStack().getItem() == ModItems.BEAR_CLAW) {
                player.getMainHandStack().damage(1, player, EquipmentSlot.MAINHAND);
            }
        }
    }
}
