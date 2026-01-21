package net.louis.overhaulmod.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ColoredNameTagItem extends NameTagItem {
    private final int textColor;

    public ColoredNameTagItem(Settings settings, int color) {
        super(settings);
        this.textColor = color;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Text colorlessText = (Text)stack.get(DataComponentTypes.CUSTOM_NAME);

        if (colorlessText != null && !(entity instanceof PlayerEntity)) {
            if (!user.getWorld().isClient && entity.isAlive()) {
                Text text = colorlessText.copy().withColor(this.textColor);
                entity.setCustomName(text);

                if (entity instanceof MobEntity) {
                    MobEntity mobEntity = (MobEntity)entity;
                    mobEntity.setPersistent();
                }

                stack.decrement(1);
            }

            return ActionResult.success(user.getWorld().isClient);
        } else {
            return ActionResult.PASS;
        }
    }
}
