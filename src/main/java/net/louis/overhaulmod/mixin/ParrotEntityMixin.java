package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.custom.PetRecoveryCompass;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParrotEntity.class)
public class ParrotEntityMixin {

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void LOM$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ParrotEntity parrot = (ParrotEntity) (Object) this;
        ItemStack itemInHand = player.getStackInHand(hand);

        if (itemInHand.getItem() instanceof PetRecoveryCompass) {
            if (parrot.isTamed() && parrot.getOwner() == player) {
                if (!parrot.getWorld().isClient) {
                    itemInHand.set(ModComponents.PET_TYPE, "Parrot");
                    itemInHand.set(ModComponents.MOB_NAME, parrot.getName().getString());
                    itemInHand.set(ModComponents.MOB_UUID, parrot.getUuidAsString());
                    itemInHand.set(ModComponents.PARROT_VARIANT, parrot.getVariant()); // Color variant
                    itemInHand.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);

                    player.sendMessage(Text.of("Parrot's soul saved to your compass!"), true);
                    parrot.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 2f, 2f);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                parrot.playSound(SoundEvents.ENTITY_WANDERING_TRADER_NO, 2f, 2f);
            }
        }
    }
}

