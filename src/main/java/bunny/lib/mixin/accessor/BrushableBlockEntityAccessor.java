package bunny.lib.mixin.accessor;

import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrushableBlockEntity.class)
public interface BrushableBlockEntityAccessor {
    @Accessor("item")
    void setItem(ItemStack stack);
}
