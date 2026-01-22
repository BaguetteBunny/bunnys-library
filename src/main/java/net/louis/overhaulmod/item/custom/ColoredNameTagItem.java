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

import java.util.Objects;

public class ColoredNameTagItem extends NameTagItem {
    private int textColor;

    public ColoredNameTagItem(Settings settings, int color) {
        super(settings);
        this.textColor = color;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Text colorlessText = (Text)stack.get(DataComponentTypes.CUSTOM_NAME);

        if (colorlessText != null && !(entity instanceof PlayerEntity)) {
            if (!user.getWorld().isClient && entity.isAlive()) {

                if (Objects.equals(colorlessText.getString(), "Ysabella"))
                    entity.setCustomName(colorlessText.copy().withColor(8388608));
                else if (Objects.equals(colorlessText.getString(), "Dream"))
                    entity.setCustomName(colorlessText.copy().withColor(43315));
                else if (Objects.equals(colorlessText.getString(), "BaguetteBunny"))
                    entity.setCustomName(colorlessText.copy().withColor(16714856));
                else if (Objects.equals(colorlessText.getString(), "sdantaray"))
                    entity.setCustomName(colorlessText.copy().withColor(65535));
                else if (Objects.equals(colorlessText.getString(), "TheCryingGrim"))
                    entity.setCustomName(colorlessText.copy().withColor(14863280));
                else if (Objects.equals(colorlessText.getString(), "Dawk1203"))
                    entity.setCustomName(colorlessText.copy().withColor(15511040));
                else
                    entity.setCustomName(colorlessText.copy().withColor(this.textColor));

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
