package bunnys.qol.entity.custom.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ChairEntity extends Entity {
    public ChairEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    private Block initBlock = null;
    private int killTimer = 10;

    @Override
    public void tick() {
        this.baseTick();

        if (initBlock == null) {
            initBlock = this.getBlockStateAtPos().getBlock();
            return;
        }

        if (initBlock != this.getBlockStateAtPos().getBlock()) this.killTimer--;
        else this.killTimer = 10;

        if (this.killTimer <= 0)
            this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        this.remove(RemovalReason.DISCARDED);
    }
}
