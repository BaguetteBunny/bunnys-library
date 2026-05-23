package bunny.lib.mixin;

import bunny.lib.utils.EnchantmentUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    PlayerEntity self = (PlayerEntity) (Object) this;

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    private void LOM$disableBasedOffRecovery(ItemStack shield, CallbackInfo ci) {
        int recoveryLevel = EnchantmentUtils.returnEnchantLevel(shield, "recovery");

        if (recoveryLevel > 0) {
            int cd = Math.max(10, 100-14*recoveryLevel);

            self.getItemCooldownManager().set(shield, cd);
            self.clearActiveItem();
            self.getWorld().sendEntityStatus(self, (byte)30);
            ci.cancel();
        }
    }
}
