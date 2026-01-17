package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
    private void preventTrampleIfFeatherFalling(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (entity instanceof LivingEntity living && ModConfig.INSTANCE.disableCropTrampleWithFeatherFalling) {
            ItemStack boots = living.getEquippedStack(EquipmentSlot.FEET);
            if (boots.isEmpty()) return;

            RegistryWrapper<Enchantment> enchantmentRegistry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            RegistryKey<Enchantment> featherFallingKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.ofVanilla("feather_falling"));
            RegistryEntry<Enchantment> featherFallingEntry = enchantmentRegistry.getOrThrow(featherFallingKey);

            if (EnchantmentHelper.getLevel(featherFallingEntry, boots) > 0) ci.cancel();
        }
    }
}