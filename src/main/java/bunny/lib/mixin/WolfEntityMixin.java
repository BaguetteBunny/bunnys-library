package bunny.lib.mixin;

import bunny.lib.component.ModComponents;
import bunny.lib.item.custom.PetRecoveryCompass;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void LOM$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        WolfEntity wolf = (WolfEntity) (Object) this;
        ItemStack itemInHand = player.getStackInHand(hand);

        if (itemInHand.getItem() instanceof PetRecoveryCompass) {
            if (wolf.isTamed() && wolf.getOwner() == player) {
                if (!wolf.getEntityWorld().isClient()) {
                    itemInHand.set(ModComponents.PET_TYPE, "Wolf");
                    itemInHand.set(ModComponents.MOB_NAME, wolf.getName().getString());
                    itemInHand.set(ModComponents.MOB_UUID, wolf.getUuidAsString());
                    itemInHand.set(ModComponents.MOB_IS_BABY, wolf.isBaby());
                    itemInHand.set(ModComponents.MOB_COLLAR_COLOR, wolf.getCollarColor());
                    RegistryEntry<WolfVariant> variant = wolf.getVariant();
                    itemInHand.set(ModComponents.WOLF_VARIANT, variant);
                    itemInHand.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);
                    player.sendMessage(Text.of("Wolf's soul saved to your compass!"), true);
                    wolf.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 2f, 2f);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                wolf.playSound(SoundEvents.ENTITY_WANDERING_TRADER_NO, 2f, 2f);
            }
        }
    }
}
