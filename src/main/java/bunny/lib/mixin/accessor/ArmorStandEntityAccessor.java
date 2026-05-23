package bunny.lib.mixin.accessor;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorStandEntity.class)
public interface ArmorStandEntityAccessor {
    @Invoker("setSmall")
    void callSetSmall(boolean small);

    @Invoker("onBreak")
    void callonBreak(ServerWorld world, DamageSource damageSource);
}