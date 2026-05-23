package bunny.lib.fluid.colors;

import bunny.lib.fluid.BaseColoredFluid;
import bunny.lib.fluid.ModFluids;
import bunny.lib.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class PurpleWaterFluid extends BaseColoredFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_PURPLE_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_PURPLE_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.PURPLE_WATER_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModFluids.PURPLE_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_PURPLE_WATER || fluid == ModFluids.FLOWING_PURPLE_WATER;
    }

    @Override
    public int getSPColor() {
        return 0x8932B8;
    }

    public static class Flowing extends PurpleWaterFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Still extends PurpleWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
