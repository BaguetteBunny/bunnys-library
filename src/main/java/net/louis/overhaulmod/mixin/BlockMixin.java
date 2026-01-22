package net.louis.overhaulmod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.louis.overhaulmod.utils.EnchantmentUtils.SMELTING_TRANSFORM_MAP;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static void LOM$injectAutoSmelt(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> drops = cir.getReturnValue();

        if (tool.isEmpty()) return;
        ItemEnchantmentsComponent enchantments = tool.get(DataComponentTypes.ENCHANTMENTS);
        if (enchantments == null) return;

        boolean hasAutoSmelt = false;
        for (var entry : enchantments.getEnchantmentEntries()) {
            String enchantId = world.getRegistryManager()
                    .get(RegistryKeys.ENCHANTMENT)
                    .getId(entry.getKey().value())
                    .toString();

            if (enchantId.contains("smelting")) {
                hasAutoSmelt = true;
                break;
            }
        }

        if (!hasAutoSmelt) return;

        Block minedBlock = state.getBlock();
        if (SMELTING_TRANSFORM_MAP.containsKey(minedBlock)) {
            Item transformedItem = SMELTING_TRANSFORM_MAP.get(minedBlock);
            int totalCount = drops.stream().mapToInt(ItemStack::getCount).sum();

            cir.setReturnValue(List.of(new ItemStack(transformedItem, totalCount)));
        }
    }
}
