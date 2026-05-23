package bunnys.qol.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static bunnys.qol.datagen.ModRecipeProvider.MUSIC_DISC;

@Mixin(Item.class)
public abstract class ItemMixin {
    Item item = (Item)(Object)this;

    @Inject(method = "getRecipeRemainder", at = @At("HEAD"), cancellable = true)
    private void bunny$returnMusicDiscAfterDupe(CallbackInfoReturnable<ItemStack> cir) {
        if (MUSIC_DISC.contains(item)) { cir.setReturnValue(new ItemStack(item)); }
    }
}