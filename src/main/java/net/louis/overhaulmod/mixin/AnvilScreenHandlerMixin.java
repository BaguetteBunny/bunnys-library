package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.item.custom.FilledParticleOrb;
import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.louis.overhaulmod.utils.ItemManager;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.ForgingSlotsManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.louis.overhaulmod.utils.ToolArmorUtil.*;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow private Property levelCost;
    @Unique private int azuriteToConsume = 0;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, ForgingSlotsManager forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void LOM$checkEnchantmentCapOnCombine(CallbackInfo ci) {
        if (this.output.isEmpty() && !ModConfig.INSTANCE.allowEnchantmentCaps) return;

        int cap = EnchantmentCapRegistry.getCap(this.output.getStack(0).getItem());
        ItemEnchantmentsComponent enchantments = this.output.getStack(0).get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && enchantments.getSize() > cap) {
            this.output.setStack(0, ItemStack.EMPTY);
        }
    }

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void LOM$preventEnchantmentCapExceed(CallbackInfo ci) {
        ItemStack outputStack = this.output.getStack(0);

        if (outputStack.isEmpty() && !ModConfig.INSTANCE.allowEnchantmentCaps) return;

        int cap = EnchantmentCapRegistry.getCap(outputStack.getItem());
        if (cap == Integer.MAX_VALUE) return;

        ItemEnchantmentsComponent enchantments = outputStack.get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && enchantments.getSize() > cap) {
            this.output.setStack(0, ItemStack.EMPTY);
        }
    }

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void LOM$zeroCostForBooks(CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);

        if (leftStack.getItem() == Items.ENCHANTED_BOOK && rightStack.getItem() == Items.ENCHANTED_BOOK)
            this.levelCost.set(0);
    }

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    protected void LOM$takeZeroCostBook(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.getItem() == Items.ENCHANTED_BOOK && rightStack.getItem() == Items.ENCHANTED_BOOK && !player.getEntityWorld().isClient())
            cir.setReturnValue(true);

    }

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void LOM$force30LevelsIfBothItemsEnchanted(CallbackInfo ci) {
        ItemStack first = this.input.getStack(0);
        ItemStack second = this.input.getStack(1);

        if (!first.isEmpty() && !second.isEmpty()
                && first.hasEnchantments() && second.hasEnchantments()
                && !first.isOf(Items.ENCHANTED_BOOK) && !second.isOf(Items.ENCHANTED_BOOK)) {
            levelCost.set(30);
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$customAnvilMechanics(CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.isEmpty() || leftStack.getItem() == Items.ENCHANTED_BOOK) return;
        if (leftStack.isDamaged() && !rightStack.isEmpty() && rightStack.getItem() != ModItems.AZURITE && rightStack.getItem() != Items.ENCHANTED_BOOK) {
            ci.cancel();
            return;
        }

        // Case 1: Enchantment Book Application
        if (rightStack.getItem() == Items.ENCHANTED_BOOK) {
            ItemStack result = leftStack.copy();
            ItemEnchantmentsComponent bookEnchants = rightStack.get(DataComponentTypes.STORED_ENCHANTMENTS);

            if (bookEnchants != null && !bookEnchants.isEmpty()) {
                ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(
                        result.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
                );

                int cost = 0;
                ItemEnchantmentsComponent finalEnchants = result.get(DataComponentTypes.ENCHANTMENTS);
                int successCounter = finalEnchants != null ? finalEnchants.getSize() : 0;
                boolean successAboveCap = ModConfig.INSTANCE.allowEnchantmentCaps && successCounter >= EnchantmentCapRegistry.getCap(result.getItem());

                // Calculate cost and apply enchantments
                for (var entry : bookEnchants.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    if (!enchantment.value().isSupportedItem(leftStack) || successAboveCap) continue;

                    int level = entry.getIntValue();
                    int weight = enchantment.value().getWeight();

                    int enchantCost = (weight > 5) ? 5 * level : (10 - weight) * level;

                    cost += enchantCost;
                    successCounter++;
                    builder.set(enchantment, level);
                }

                result.set(DataComponentTypes.ENCHANTMENTS, builder.build());

                // Cap at 999
                if (cost > 999) cost = 999;

                if (finalEnchants != null && successCounter == finalEnchants.getSize()) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    ci.cancel();
                    return;
                }

                this.output.setStack(0, result);
                this.levelCost.set(cost);
                ci.cancel();
                return;
            }
        }

        // Case 2: Item Repair with Azurite
        if (rightStack.getItem() == ModItems.AZURITE) {
            if (!leftStack.isDamaged()) return;

            ItemStack result = leftStack.copy();
            int damage = leftStack.getDamage();

            // Base durability per Azurite
            int baseDurability = isToolOrWeapon(leftStack.getItem()) ? 400 : 100;

            ItemEnchantmentsComponent enchantments = leftStack.get(DataComponentTypes.ENCHANTMENTS);
            boolean hasCurse = false;
            int enchantmentPenalty = 0;

            if (enchantments != null && !enchantments.isEmpty()) {
                for (var entry : enchantments.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    int level = entry.getIntValue();

                    if (enchantment.isIn(EnchantmentTags.CURSE)) {
                        hasCurse = true;
                    } else {
                        int weight = enchantment.value().getWeight();
                        enchantmentPenalty += (10 - weight) * level * (baseDurability/100) * 3 / 4;
                    }
                }
            }

            if (enchantmentPenalty >= 75 && baseDurability == 100) enchantmentPenalty = 75;
            else if (enchantmentPenalty >= 300) enchantmentPenalty = 300;

            // Apply curse multiplier
            if (hasCurse) baseDurability /= 2;

            // Apply azurite reinforced multiplier
            String hasReinforcement = rightStack.getOrDefault(ModComponents.AZURITE_REFINE, "");
            Item repairItem = getToolMaterialItem(hasReinforcement);
            if (repairItem != null && leftStack.isDamageable() && (leftStack.canRepairWith(new ItemStack(repairItem))) || isNetheriteRepairable(leftStack, repairItem))
                baseDurability *= 2;

            // Compute final durability
            int finalDurabilityPerAzurite = Math.max(1, baseDurability - enchantmentPenalty);
            int azuriteCount = rightStack.getCount();
            int totalRepair = finalDurabilityPerAzurite * azuriteCount;
            int actualRepair = Math.min(totalRepair, damage);

            if (actualRepair == damage)
                this.azuriteToConsume = (int) Math.ceil((double) damage / finalDurabilityPerAzurite);
            else
                this.azuriteToConsume = azuriteCount;

            if (actualRepair > 0) {
                result.setDamage(damage - actualRepair);

                this.output.setStack(0, result);
                this.levelCost.set(0);
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$handleOrb(CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (!leftStack.isOf(ModItems.EMPTY_PARTICLE_ORB)) return;

        Item rightItem = rightStack.getItem();

        if (ItemManager.SHERDS.contains(rightItem)) {
            ItemStack filledOrb = new ItemStack(ModItems.FILLED_PARTICLE_ORB, 1);

            filledOrb.set(ModComponents.ORB_SHERD_NAME, rightItem.getName().getString());
            filledOrb.set(ModComponents.ORB_PARTICLE_EFFECT, ItemManager.SHERD_PARTICLE.get(rightItem));

            this.output.setStack(0, filledOrb);
            ci.cancel();
        }
    }

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    protected void LOM$takeOrbOut(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        if (player.getEntityWorld().isClient()) return;

        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.getItem() == ModItems.EMPTY_PARTICLE_ORB && ItemManager.SHERDS.contains(rightStack.getItem()))
            cir.setReturnValue(true);
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"), cancellable = true)
    protected void LOM$onTakeOrbOut(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (player.getEntityWorld().isClient()) return;

        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.getItem() == ModItems.EMPTY_PARTICLE_ORB && ItemManager.SHERDS.contains(rightStack.getItem())) {
            this.input.setStack(0, ItemStack.EMPTY);
            this.input.getStack(1).setCount(this.input.getStack(1).getCount() - 1);
            this.context.run((world, pos) -> {
                BlockState blockState = world.getBlockState(pos);
                if (!player.isInCreativeMode() && blockState.isIn(BlockTags.ANVIL) && player.getRandom().nextFloat() < 0.12F) {
                    BlockState blockState2 = AnvilBlock.getLandingState(blockState);
                    if (blockState2 == null) {
                        world.removeBlock(pos, false);
                        world.syncWorldEvent(1029, pos, 0);
                    } else {
                        world.setBlockState(pos, blockState2, 2);
                        world.syncWorldEvent(1030, pos, 0);
                    }
                } else {
                    world.syncWorldEvent(1030, pos, 0);
                }

            });
            ci.cancel();
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void LOM$handleAzuriteToolMaterialCombination(CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (!leftStack.isOf(ModItems.AZURITE)) return;

        String material = getToolMaterialString(rightStack);

        if (material != null) {
            ItemStack result = leftStack.copy();
            result.set(ModComponents.AZURITE_REFINE, material);

            this.output.setStack(0, result);
            ci.cancel();
        }
    }

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    protected void LOM$takeAzuriteOutOrRepair(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        if (player.getEntityWorld().isClient()) return;
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.getItem() == ModItems.AZURITE && getToolMaterialString(rightStack) != null)
            cir.setReturnValue(true);
        if (rightStack.getItem() == ModItems.AZURITE && leftStack.isDamaged()) {
            cir.setReturnValue(true);
        }
    }
    @Inject(method = "onTakeOutput", at = @At("HEAD"), cancellable = true)
    protected void LOM$repairWithAzurite(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (player.getEntityWorld().isClient()) return;
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (rightStack.getItem() == ModItems.AZURITE && leftStack.isDamaged()) {
            this.input.setStack(0, ItemStack.EMPTY);
            this.input.getStack(1).setCount(this.input.getStack(1).getCount() - this.azuriteToConsume);
            this.context.run((world, pos) -> {
                BlockState blockState = world.getBlockState(pos);
                if (!player.isInCreativeMode() && blockState.isIn(BlockTags.ANVIL) && player.getRandom().nextFloat() < 0.12F) {
                    BlockState blockState2 = AnvilBlock.getLandingState(blockState);
                    if (blockState2 == null) {
                        world.removeBlock(pos, false);
                        world.syncWorldEvent(1029, pos, 0);
                    } else {
                        world.setBlockState(pos, blockState2, 2);
                        world.syncWorldEvent(1030, pos, 0);
                    }
                } else {
                    world.syncWorldEvent(1030, pos, 0);
                }

            });
            ci.cancel();
        }
    }

}
