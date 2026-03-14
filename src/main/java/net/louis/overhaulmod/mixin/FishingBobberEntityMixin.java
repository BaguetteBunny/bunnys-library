package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.enchantments.ModEnchantmentEffects;
import net.louis.overhaulmod.enchantments.ModEnchantments;
import net.louis.overhaulmod.interfaces.FishingBobberEntityAccessor;
import net.louis.overhaulmod.utils.EnchantmentUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    FishingBobberEntity self = (FishingBobberEntity) (Object) this;

    @Unique private int reelingEnchantLevel;
    @Unique private int doubleHookEnchantLevel;

    @Inject(method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;IILnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void LOM$setEnchantsOnInit(PlayerEntity thrower, World world, int luckBonus, int waitTimeReductionTicks, ItemStack stack, CallbackInfo ci) {
        this.reelingEnchantLevel = EnchantmentUtils.returnEnchantLevel(stack, "reeling");
        this.doubleHookEnchantLevel = EnchantmentUtils.returnEnchantLevel(stack, "double_hook");
    }

    @Inject(method = "pullHookedEntity", at = @At("HEAD"), cancellable = true)
    protected void LOM$extraHookFromReeling(Entity entity, CallbackInfo ci) {
        double multiplier = ((this.reelingEnchantLevel / 2.0) + 1) * 0.1;
        Entity owner = self.getOwner();

        if (owner != null) {
            Vec3d vec3d = new Vec3d(
                    owner.getX() - self.getX(),
                    owner.getY() - self.getY(),
                    owner.getZ() - self.getZ()
            ).multiply(multiplier);
            entity.setVelocity(entity.getVelocity().add(vec3d));
        }
        ci.cancel();
    }
}
