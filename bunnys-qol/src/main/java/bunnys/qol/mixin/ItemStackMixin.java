package bunnys.qol.mixin;

import bunny.lib.utils.ItemManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    ItemStack stack = (ItemStack)(Object)this;

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void bunny$addEquippableTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (!ItemManager.HEAD_EQUIPPABLE_ITEMS.contains(stack.getItem())) return;

        List<Text> tooltip = cir.getReturnValue();
        tooltip.add(1, Text.literal("Equippable")
                .formatted(Formatting.GRAY));
    }

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void bunny$overrideMaxStacks(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        Item item = self.getItem();

        Integer override = ItemManager.MAX_STACK_OVERRIDES.get(item);
        if (override != null) cir.setReturnValue(override);
    }
}
