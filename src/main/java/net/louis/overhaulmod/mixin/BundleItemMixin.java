package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.interfaces.BundleBuilderAccessor;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BundleItem.class)
public class BundleItemMixin {
    @ModifyVariable(method = "onStackClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/BundleContentsComponent$Builder;add(Lnet/minecraft/screen/slot/Slot;Lnet/minecraft/entity/player/PlayerEntity;)I"), ordinal = 0)
    private BundleContentsComponent.Builder LOM$setBundleContextForSlot(BundleContentsComponent.Builder builder, ItemStack stack) {
        ((BundleBuilderAccessor) builder).LOM$setBundleStack(stack);
        return builder;
    }

    @ModifyVariable(method = "onClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/BundleContentsComponent$Builder;add(Lnet/minecraft/item/ItemStack;)I"), ordinal = 0)
    private BundleContentsComponent.Builder LOM$setBundleContextForStack(BundleContentsComponent.Builder builder, ItemStack stack) {
        ((BundleBuilderAccessor) builder).LOM$setBundleStack(stack);
        return builder;
    }
}