package net.louis.overhaulmod.item.custom;

import net.louis.overhaulmod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.louis.overhaulmod.utils.RareItemUtil.RARE_NAME_TAGS;

public class ColoredNameTagItem extends NameTagItem {
    public final int firstTextColor;
    public final int secondTextColor;

    public ColoredNameTagItem(Settings settings, int firstTextColor, int secondTextColor) {
        super(settings);
        this.firstTextColor = firstTextColor;
        this.secondTextColor = secondTextColor;
    }

    public ColoredNameTagItem(Settings settings, int color) {
        super(settings);
        this.firstTextColor = color;
        this.secondTextColor = color;

    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Text colorlessText = (Text)stack.get(DataComponentTypes.CUSTOM_NAME);

        if (colorlessText != null && !(entity instanceof PlayerEntity)) {
            if (!user.getWorld().isClient && entity.isAlive()) {

                if (this.firstTextColor != this.secondTextColor)
                    entity.setCustomName(setGradient(colorlessText, this.firstTextColor, this.secondTextColor));
                else if (Objects.equals(colorlessText.getString(), "Ysabella"))
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
                    entity.setCustomName(colorlessText.copy().withColor(this.firstTextColor));

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

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!RARE_NAME_TAGS.contains(stack.getItem())) return;
        Identifier id = Registries.ITEM.getId(stack.getItem());
        String descKey = "item." + id.getNamespace() + "." + id.getPath() + ".desc";
        tooltip.add(Text.literal("• ").append(Text.translatable(descKey)).formatted(Formatting.GRAY));
    }

    public static Text setGradient(Text text, int firstColor, int secondColor) {
        String textStr = text.getString();

        ArrayList<Integer> firstRGB = intToRGB(firstColor);
        ArrayList<Integer> secondRGB = intToRGB(secondColor);
        ArrayList<Integer> currentRGB = new ArrayList<>(firstRGB);

        double rStep = (secondRGB.get(0) - firstRGB.get(0)) / (double) textStr.length();
        double gStep = (secondRGB.get(1) - firstRGB.get(1)) / (double) textStr.length();
        double bStep = (secondRGB.get(2) - firstRGB.get(2)) / (double) textStr.length();

        MutableText finalText = (MutableText) Text.of(String.valueOf(textStr.charAt(0))).copy().withColor(firstColor);

        for (int i = 1 ; i < textStr.length() ; i++) {
            currentRGB.set(0, (int) (currentRGB.get(0) + rStep));
            currentRGB.set(1, (int) (currentRGB.get(1) + gStep));
            currentRGB.set(2, (int) (currentRGB.get(2) + bStep));
            int newColor = RGBtoInt(currentRGB);

            char character = textStr.charAt(i);
            Text newText = Text.of(String.valueOf(character)).copy().withColor(newColor);

            finalText.append(newText);
        }
        return finalText;
    }

    public static ArrayList<Integer> intToRGB(int color) {
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(color / 65536);
        colors.add((color % 65536) / 256);
        colors.add(color % 256);

        return colors;
    }

    public static int RGBtoInt(ArrayList<Integer> colors) {
        return colors.get(0) * 65536 + colors.get(1) * 256 + colors.get(2);
    }
}
