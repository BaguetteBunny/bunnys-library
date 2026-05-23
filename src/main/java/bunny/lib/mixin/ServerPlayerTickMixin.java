package bunny.lib.mixin;

import bunny.lib.config.ModConfig;
import bunny.lib.item.ModItems;
import bunny.lib.utils.GlowLightManager;
import bunny.lib.utils.accessors.LastTickHolder;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerTickMixin implements LastTickHolder {
    ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
    @Unique @Nullable private BlockPos lastLightPos = null;
    @Unique private boolean lastTick = false;

    @Override
    public boolean getLastTick() {
        return this.lastTick;
    }

    @Override
    public void setLastTick(boolean t) {
        this.lastTick = t;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$allowRecallClock(CallbackInfo ci) {
        if (this.player.getHealth() < this.player.getMaxHealth()) {
            this.player.getItemCooldownManager().set(new ItemStack(ModItems.RECALL_CLOCK.asItem()), 100);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void LOM$dynamicLighting(CallbackInfo ci) {
        if (!ModConfig.INSTANCE.enableDynamicLighting && this.lastLightPos == null) return;

        ServerWorld serverWorld = this.player.getServerWorld();
        Integer lightLevel = hasLightItem(this.player);
        if (lightLevel == null) lightLevel = 0;

        BlockPos playerPos = this.player.getBlockPos();
        BlockPos upPos = playerPos.up();

        if (this.lastLightPos != null) {
            serverWorld.setBlockState(this.lastLightPos, Blocks.AIR.getDefaultState());
            this.lastLightPos = null;
        }

        BlockPos lightPos = null;
        if (serverWorld.getBlockState(playerPos).isAir()) {
            lightPos = playerPos;
        } else if (serverWorld.getBlockState(upPos).isAir()) {
            lightPos = upPos;
        }

        if (lightPos != null && serverWorld.getBlockState(lightPos) != Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, lightLevel) && !this.lastTick) {
            if (lightLevel > 0) {
                serverWorld.setBlockState(lightPos, Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, lightLevel));
                this.lastLightPos = lightPos;
            }
            else
                serverWorld.setBlockState(lightPos, Blocks.AIR.getDefaultState());}
    }

    private Integer hasLightItem(ServerPlayerEntity player) {
        Integer level = GlowLightManager.ITEM_LIGHT_LEVELS.get(player.getMainHandStack().getItem());
        if (level != null) return level;
        return GlowLightManager.ITEM_LIGHT_LEVELS.get(player.getOffHandStack().getItem());
    }
}
