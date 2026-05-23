package bunny.lib.mixin.accessor;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(WolfEntity.class)
public interface WolfAccessor {
    @Invoker("setCollarColor")
    void callSetCollarColor(DyeColor color);
}
