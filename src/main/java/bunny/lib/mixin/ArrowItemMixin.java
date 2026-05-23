package bunny.lib.mixin;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static bunny.lib.utils.ApplyArrowComponents.applyArrowComponentAbilities;

@Mixin(ArrowItem.class)
public class ArrowItemMixin extends Item implements ProjectileItem {
    public ArrowItemMixin(Item.Settings settings) {
        super(settings);
    }

     /**
     * @author BaguetteBunny @ BunnyLib
     * @reason Apply component abilities to arrows
     */
    @Overwrite
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        ArrowEntity arrowEntity = new ArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        applyArrowComponentAbilities(arrowEntity, stack);
        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return arrowEntity;
    }
}
