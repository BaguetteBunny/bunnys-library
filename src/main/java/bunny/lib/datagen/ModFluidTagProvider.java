package bunny.lib.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import bunny.lib.fluid.ModFluids;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(FluidTags.WATER)
                .add(ModFluids.STILL_WHITE_WATER).add(ModFluids.FLOWING_WHITE_WATER)
                .add(ModFluids.STILL_ORANGE_WATER).add(ModFluids.FLOWING_ORANGE_WATER)
                .add(ModFluids.STILL_MAGENTA_WATER).add(ModFluids.FLOWING_MAGENTA_WATER)
                .add(ModFluids.STILL_LIGHT_BLUE_WATER).add(ModFluids.FLOWING_LIGHT_BLUE_WATER)
                .add(ModFluids.STILL_YELLOW_WATER).add(ModFluids.FLOWING_YELLOW_WATER)
                .add(ModFluids.STILL_LIME_WATER).add(ModFluids.FLOWING_LIME_WATER)
                .add(ModFluids.STILL_PINK_WATER).add(ModFluids.FLOWING_PINK_WATER)
                .add(ModFluids.STILL_GRAY_WATER).add(ModFluids.FLOWING_GRAY_WATER)
                .add(ModFluids.STILL_LIGHT_GRAY_WATER).add(ModFluids.FLOWING_LIGHT_GRAY_WATER)
                .add(ModFluids.STILL_CYAN_WATER).add(ModFluids.FLOWING_CYAN_WATER)
                .add(ModFluids.STILL_PURPLE_WATER).add(ModFluids.FLOWING_PURPLE_WATER)
                .add(ModFluids.STILL_BROWN_WATER).add(ModFluids.FLOWING_BROWN_WATER)
                .add(ModFluids.STILL_GREEN_WATER).add(ModFluids.FLOWING_GREEN_WATER)
                .add(ModFluids.STILL_RED_WATER).add(ModFluids.FLOWING_RED_WATER)
                .add(ModFluids.STILL_BLACK_WATER).add(ModFluids.FLOWING_BLACK_WATER);
    }
}