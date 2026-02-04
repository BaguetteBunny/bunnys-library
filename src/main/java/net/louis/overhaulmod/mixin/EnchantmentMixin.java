package net.louis.overhaulmod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "canBeCombined", at = @At("HEAD"), cancellable = true)
    private static void LOM$removeAllMutexesExceptSilkTouchFortune(RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second, CallbackInfoReturnable<Boolean> cir) {
        if (first.equals(second)) return;

        String firstName = first.getKey().get().getValue().getPath();
        String secondName = second.getKey().get().getValue().getPath();

        if ((firstName.equals("silk_touch") && secondName.equals("fortune")) || (firstName.equals("fortune") && secondName.equals("silk_touch"))) {
            cir.setReturnValue(false);
            return;
        }

        cir.setReturnValue(true);
    }

    @Inject(method = "modifyDamageProtection", at = @At("HEAD"), cancellable = true)
    private void LOM$limitProtectionToMeleeOnly(ServerWorld world, int level, ItemStack stack, Entity user, DamageSource damageSource, MutableFloat damageProtection, CallbackInfo ci) {
        Enchantment thisEnchant = (Enchantment)(Object)this;

        String enchantId = world.getRegistryManager()
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getId(thisEnchant)
                .getPath();

        if (enchantId.equals("protection") && !isMeleeDamage(damageSource)) ci.cancel();

        if (enchantId.equals("blast_protection")) {
            if (damageSource.isIn(DamageTypeTags.IS_EXPLOSION)) return;
            if (isMagicDamage(damageSource)) {
                float currentProtection = damageProtection.getValue();
                float additionalProtection = level * 2.0f;
                damageProtection.setValue(currentProtection + additionalProtection);
                ci.cancel();
                return;
            }
            ci.cancel();
        }

        if (enchantId.equals("fire_protection")) {
            if (damageSource.isIn(DamageTypeTags.IS_FIRE)) return;

            if (isEnvironmentalDamage(damageSource)) {
                float currentProtection = damageProtection.getValue();
                float additionalProtection = level * 2.0f; // Same as fire protection
                damageProtection.setValue(currentProtection + additionalProtection);
                ci.cancel();
                return;
            }
            ci.cancel();
        }
    }

    @Inject(method = "modifyDamage", at = @At("HEAD"), cancellable = true)
    private void LOM$changeEnchantDamageModifier(ServerWorld world, int level, ItemStack stack, Entity user, DamageSource damageSource, MutableFloat damage, CallbackInfo ci) {
        Enchantment thisEnchant = (Enchantment)(Object)this;
        Entity target = user;

        String enchantId = world.getRegistryManager()
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getId(thisEnchant)
                .getPath();

        if (enchantId.equals("sharpness")) {
            float currentDamage = damage.getValue();
            float bonusDamage = 0.4f * level + 0.5f;
            damage.setValue(currentDamage + bonusDamage);
            ci.cancel();
        }

        if (enchantId.equals("power") && damageSource.isOf(DamageTypes.ARROW)) {
            float currentDamage = damage.getValue();
            float bonusDamage = 0.4f * level + 0.5f;
            damage.setValue(currentDamage + bonusDamage);
            ci.cancel();
        }

        if (enchantId.equals("illagers_bane") && isIllager(target)) {
            float currentDamage = damage.getValue();
            float bonusDamage = 2.5f * level;
            damage.setValue(currentDamage + bonusDamage);
            ci.cancel();
        }

        if (enchantId.equals("bane_of_arthropods")) {
            if (isArthropodOrFireMob(target) || hasElytra(target)) {
                float currentDamage = damage.getValue();
                float bonusDamage = 2.5f * level;
                damage.setValue(currentDamage + bonusDamage);
            }
            ci.cancel();
        }

        if (enchantId.equals("impaling")) {
            if (shouldImpalingWork(world, target)) {
                float currentDamage = damage.getValue();
                float bonusDamage = 2.5f * level;
                damage.setValue(currentDamage + bonusDamage);
            }
            ci.cancel();
        }

        if (enchantId.equals("giant_killer") && target instanceof LivingEntity living) {
            if (living.getHealth() > 20) {
                float damageBoost = Math.min(level * (living.getHealth() - 20.f) / 10.f, 10.f);
                damage.setValue(damage.getValue() + damageBoost);
            }
            ci.cancel();
        }
    }

    @Inject(method = "isSupportedItem", at = @At("HEAD"), cancellable = true)
    private void LOM$expandEnchantmentCompatibility(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment thisEnchant = (Enchantment)(Object)this;
        Item item = stack.getItem();
        String enchantName = thisEnchant.description().getString().toLowerCase();
        if (enchantName.contains("bane of arthropods")) {
            if (item instanceof MaceItem || item instanceof TridentItem ||
                    item instanceof BowItem || item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("smite")) {
            if (item instanceof MaceItem || item instanceof TridentItem ||
                    item instanceof BowItem || item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("looting")) {
            if (item instanceof MaceItem || item instanceof TridentItem ||
                    item instanceof BowItem || item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("fire aspect")) {
            if (item instanceof TridentItem || item instanceof MaceItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("punch")) {
            if (item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("knockback")) {
            if (item instanceof TridentItem || item instanceof MaceItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("flame")) {
            if (item instanceof CrossbowItem || item instanceof TridentItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("power")) {
            if (item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("illager's bane")) {
            if (item instanceof MaceItem || item instanceof TridentItem ||
                    item instanceof BowItem || item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("sharpness")) {
            if (item instanceof MaceItem || item instanceof TridentItem ||
                    item instanceof BowItem || item instanceof CrossbowItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("impaling")) {
            if (item instanceof MaceItem || item instanceof SwordItem ||
                    item instanceof BowItem || item instanceof CrossbowItem || item instanceof AxeItem) {
                cir.setReturnValue(true);
            }
        }

        if (enchantName.contains("breach")) {
            if (item instanceof AxeItem || item instanceof TridentItem) {
                cir.setReturnValue(true);
            }
        }
    }

    private boolean isArthropodOrFireMob(Entity entity) {
        if (entity.getType().isIn(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) return true;

        return entity instanceof BlazeEntity || entity instanceof BreezeEntity ||
                entity.getType() == EntityType.BLAZE || entity.getType() == EntityType.BREEZE;
    }

    private boolean hasElytra(Entity entity) {
        if (!(entity instanceof LivingEntity le)) return false;
        return le.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA);
    }

    private boolean shouldImpalingWork(ServerWorld world, Entity target) {
        RegistryEntry<Biome> biome = world.getBiome(target.getBlockPos());

        if (!biome.isIn(BiomeTags.IS_OVERWORLD) || biome.matchesId(BiomeKeys.SAVANNA.getValue()) || biome.matchesId(BiomeKeys.DESERT.getValue())) return false;
        if (target.isTouchingWater() || target.isSubmergedInWater() || target instanceof DrownedEntity) return true;

        if (world.isRaining()) {
            BlockPos headPos = BlockPos.ofFloored(target.getX(), target.getEyeY(), target.getZ());
            if (world.isSkyVisible(headPos)) return true;
        }
        return false;
    }

    private boolean isIllager(Entity entity) {
        return entity instanceof IllagerEntity ||
                entity.getType() == EntityType.PILLAGER ||
                entity.getType() == EntityType.VINDICATOR ||
                entity.getType() == EntityType.EVOKER ||
                entity.getType() == EntityType.VEX ||
                entity.getType() == EntityType.WITCH ||
                entity.getType() == EntityType.ILLUSIONER ||
                entity.getType() == EntityType.RAVAGER;
    }

    private boolean isMeleeDamage(DamageSource source) {
        return source.getAttacker() != null &&
                source.getSource() == source.getAttacker() &&
                !source.isIn(DamageTypeTags.IS_PROJECTILE) &&
                !source.isIn(DamageTypeTags.IS_FIRE) &&
                !source.isIn(DamageTypeTags.IS_EXPLOSION) &&
                !source.isIn(DamageTypeTags.BYPASSES_ARMOR);
    }

    private boolean isMagicDamage(DamageSource source) {
        return source.isIn(DamageTypeTags.WITCH_RESISTANT_TO) ||
                source.isOf(DamageTypes.MAGIC) ||
                source.isOf(DamageTypes.INDIRECT_MAGIC) ||
                source.isOf(DamageTypes.THORNS) ||
                source.isOf(DamageTypes.DRAGON_BREATH) ||
                source.isOf(DamageTypes.SONIC_BOOM) ||
                source.isOf(DamageTypes.CRAMMING) ||
                (source.getSource() != null &&
                        (source.getSource().getType().toString().contains("guardian") ||
                                source.getSource().getType().toString().contains("evoker_fangs")));
    }

    private boolean isEnvironmentalDamage(DamageSource source) {
        return source.isOf(DamageTypes.CACTUS) ||
                source.isOf(DamageTypes.SWEET_BERRY_BUSH) ||
                source.isOf(DamageTypes.FREEZE) ||
                source.isOf(DamageTypes.STALAGMITE) ||
                source.isOf(DamageTypes.FALLING_BLOCK) ||
                source.isOf(DamageTypes.FALLING_ANVIL) ||
                source.isOf(DamageTypes.FLY_INTO_WALL) ||
                source.isOf(DamageTypes.OUT_OF_WORLD) ||
                source.isOf(DamageTypes.OUTSIDE_BORDER) ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.STARVE) ||
                source.isOf(DamageTypes.CAMPFIRE) ||
                source.isOf(DamageTypes.FIREWORKS) ||
                (source.getAttacker() == null && source.getSource() == null);
    }
}