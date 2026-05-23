package bunny.lib.mixin;

import bunny.lib.component.ModComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
    ProjectileEntity self = (ProjectileEntity) (Object) this;

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    protected void LOM$breakOnCollision(HitResult hitResult, CallbackInfo ci) {
        World world = self.getEntityWorld();

        if (
                !world.isClient && self instanceof ArrowEntity arrow &&
                        hitResult.getType() == HitResult.Type.BLOCK &&
                        arrow.getItemStack().getComponents().contains(ModComponents.ARROW_HEAD) &&
                        Items.PRISMARINE_SHARD.equals(arrow.getItemStack().getComponents().get(ModComponents.ARROW_HEAD))
        ) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (block.getBlastResistance() < 6) {
                world.breakBlock(pos, true, self);
                world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3.0F, 0.5F);
                ci.cancel();
            }
        }
    }

    @Inject(method = "onCollision", at = @At("TAIl"), cancellable = true)
    protected void LOM$disableEntityIFrames(HitResult hitResult, CallbackInfo ci) {
        World world = self.getEntityWorld();
        if (
                !world.isClient && self instanceof ArrowEntity arrow &&
                        hitResult.getType() == HitResult.Type.ENTITY &&
                        arrow.getItemStack().getComponents().contains(ModComponents.ARROW_FOOT) &&
                        Items.ARMADILLO_SCUTE.equals(arrow.getItemStack().getComponents().get(ModComponents.ARROW_FOOT))
        ) {
            EntityHitResult entityHitResult = (EntityHitResult)hitResult;
            Entity entity = entityHitResult.getEntity();
            entity.timeUntilRegen = 1;
        }
    }
}
