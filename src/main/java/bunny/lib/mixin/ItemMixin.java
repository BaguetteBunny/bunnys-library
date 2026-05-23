package bunny.lib.mixin;

import bunny.lib.component.ModComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    // Add tooltip to arrows
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    public void LOM$appendArrowTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        Item foot = stack.get(ModComponents.ARROW_FOOT);
        Item shaft = stack.get(ModComponents.ARROW_SHAFT);
        Item head = stack.get(ModComponents.ARROW_HEAD);
        if (foot != null || shaft != null || head != null) tooltip.add(Text.translatable("tooltip.arrow.empty"));
        if (foot != null) {
            tooltip.add(Text.translatable("tooltip.arrow.foot", foot.getName())
                    .styled(style -> style.withColor(getColorForItem(foot))));
        }
        if (shaft != null) {
            tooltip.add(Text.translatable("tooltip.arrow.shaft", shaft.getName())
                    .styled(style -> style.withColor(getColorForItem(shaft))));
        }
        if (head != null) {
            tooltip.add(Text.translatable("tooltip.arrow.head", head.getName())
                    .styled(style -> style.withColor(getColorForItem(head))));
        }
        if (foot != null || shaft != null || head != null) tooltip.add(Text.translatable("tooltip.arrow.empty"));
    }

    private TextColor getColorForItem(Item item) {
        if (item == Items.PHANTOM_MEMBRANE) return TextColor.fromRgb(0xc2c4a3);
        if (item == Items.BREEZE_ROD) return TextColor.fromRgb(0x6fa6b0);
        if (item == Items.BLAZE_ROD) return TextColor.fromRgb(0xdb895c);
        if (item == Items.AMETHYST_SHARD) return TextColor.fromRgb(0xde78d9);
        if (item == Items.ECHO_SHARD) return TextColor.fromRgb(0x272757);
        if (item == Items.PRISMARINE_SHARD) return TextColor.fromRgb(0x71d9e2);
        if (item == Items.ARMADILLO_SCUTE) return TextColor.fromRgb(0xa06460);
        if (item == Items.DRIED_KELP) return TextColor.fromRgb(0x615c50);
        return TextColor.fromFormatting(Formatting.WHITE);
    }

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void LOM$changeStewEatTime(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() == Items.MUSHROOM_STEW || stack.getItem() == Items.RABBIT_STEW || stack.getItem() == Items.BEETROOT_SOUP) {
            cir.setReturnValue(16);
        }
    }
}
