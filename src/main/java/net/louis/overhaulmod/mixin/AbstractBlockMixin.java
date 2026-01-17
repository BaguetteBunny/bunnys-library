package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.config.ModConfig;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.louis.overhaulmod.item.custom.AmethystDagger.DROPPABLES;
import static net.louis.overhaulmod.item.custom.AmethystDagger.UNDROPPABLES;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void ignoreHorseLeafCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getBlock() instanceof LeavesBlock && context instanceof EntityShapeContext esc && ModConfig.INSTANCE.disableHorseLeafCollision) {
            Entity entity = esc.getEntity();
            if (entity instanceof HorseEntity && ((HorseEntity) entity).isTame()) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void ignoreWitherCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getBlock() == Blocks.BEDROCK && context instanceof EntityShapeContext esc && ModConfig.INSTANCE.disableWitherBedrockCollision) {
            Entity entity = esc.getEntity();
            if (entity instanceof WitherEntity) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }
}



