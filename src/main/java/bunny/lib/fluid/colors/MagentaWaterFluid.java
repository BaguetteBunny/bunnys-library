package bunny.lib.fluid.colors;

import bunny.lib.fluid.BaseColoredFluid;
import bunny.lib.fluid.ModFluids;
import bunny.lib.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class MagentaWaterFluid extends BaseColoredFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_MAGENTA_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_MAGENTA_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.MAGENTA_WATER_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModFluids.MAGENTA_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_MAGENTA_WATER || fluid == ModFluids.FLOWING_MAGENTA_WATER;
    }

    @Override
    public int getSPColor() {
        return 0xC74EBD;
    }

    public static class Flowing extends MagentaWaterFluid {
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

    public static class Still extends MagentaWaterFluid {
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
