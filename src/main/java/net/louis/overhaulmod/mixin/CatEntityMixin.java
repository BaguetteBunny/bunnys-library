package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.custom.PetRecoveryCompass;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.passive.CatEntity;
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

@Mixin(CatEntity.class)
public class CatEntityMixin {

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void LOM$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        CatEntity cat = (CatEntity) (Object) this;
        ItemStack itemInHand = player.getStackInHand(hand);

        if (itemInHand.getItem() instanceof PetRecoveryCompass) {
            if (cat.isTamed() && cat.getOwner() == player) {
                if (!cat.getEntityWorld().isClient()) {
                    itemInHand.set(ModComponents.PET_TYPE, "Cat");
                    itemInHand.set(ModComponents.MOB_NAME, cat.getName().getString());
                    itemInHand.set(ModComponents.MOB_UUID, cat.getUuidAsString());
                    itemInHand.set(ModComponents.MOB_IS_BABY, cat.isBaby());
                    itemInHand.set(ModComponents.CAT_VARIANT, cat.getVariant());
                    itemInHand.set(ModComponents.MOB_COLLAR_COLOR, cat.getCollarColor());
                    itemInHand.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);

                    player.sendMessage(Text.of("Cat's soul saved to your compass!"), true);
                    cat.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 2f, 2f);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                cat.playSound(SoundEvents.ENTITY_WANDERING_TRADER_NO, 2f, 2f);
            }
        }
    }
}

