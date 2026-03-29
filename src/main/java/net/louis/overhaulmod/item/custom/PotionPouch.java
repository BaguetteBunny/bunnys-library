package net.louis.overhaulmod.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PotionPouch extends BundleItem {
    public PotionPouch(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BundleContentsComponent contents = stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);

        if (contents.isEmpty()) return ActionResult.FAIL;

        ItemStack firstItem = contents.get(0);

        if (!firstItem.isEmpty()) {
            PotionContentsComponent potionContents = firstItem.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContents != null) {
                if (!world.isClient()) {
                    ServerWorld serverWorld = (ServerWorld) world;
                    potionContents.forEachEffect(effect -> {
                        if (effect.getEffectType().value().isInstant()) {
                            effect.getEffectType().value().applyInstantEffect(serverWorld, user, user, user, effect.getAmplifier(), 1.0);
                        } else {
                            user.addStatusEffect(effect);
                        }
                    });
                }

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,
                        0.5F, world.random.nextFloat() * 0.1F + 0.9F);

                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(contents);
                builder.removeSelected();
                stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

                user.getItemCooldownManager().set(stack, 20);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.FAIL;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 0;
    }
}


