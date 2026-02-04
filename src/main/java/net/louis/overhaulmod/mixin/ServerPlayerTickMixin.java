package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerTickMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$allowRecallClock(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        if (player.getHealth() < player.getMaxHealth()) {
            player.getItemCooldownManager().set(new ItemStack(ModItems.RECALL_CLOCK.asItem()), 100);
        }
    }
}
