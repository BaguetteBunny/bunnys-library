package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.mixin.accessor.ArmorStandEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ArmorStandMixin {
    ArmorStandEntity stand = (ArmorStandEntity) (Object) this;

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void LOM$onArmorStandDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!stand.isRemoved()
                && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)
                && stand.isInvisible()) {
            ((ArmorStandEntityAccessor) stand).callonBreak(world, source);
            stand.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Inject(method = "onBreak", at = @At("TAIL"))
    private void LOM$onArmorStandBreak(ServerWorld world, DamageSource source, CallbackInfo ci) {
        if (stand.shouldShowArms()) {
            ItemStack stack = new ItemStack(Items.STICK);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
        if (stand.isInvisible()) {
            ItemStack stack = new ItemStack(Items.PHANTOM_MEMBRANE);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), stack);
            world.spawnEntity(itemEntity);

            ItemStack selfStack = new ItemStack(Items.ARMOR_STAND);
            ItemEntity selfItemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), selfStack);
            world.spawnEntity(selfItemEntity);
        }
        if (stand.isGlowing()) {
            ItemStack stack = new ItemStack(Items.GLOW_INK_SAC);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
    }
}
