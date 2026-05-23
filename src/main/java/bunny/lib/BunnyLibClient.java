package bunny.lib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import bunny.lib.block.ModBlocks;
import bunny.lib.entity.ModEntities;
import bunny.lib.entity.custom.client.BearEntityModel;
import bunny.lib.entity.custom.client.BearEntityRenderer;
import bunny.lib.entity.custom.client.ChairRenderer;
import bunny.lib.fluid.ModFluids;
import bunny.lib.screen.AdvancedFletchingTableScreen;
import bunny.lib.screen.ModScreenHandlers;
import bunny.lib.utils.properties.AdvancedArrowProperty;
import bunny.lib.utils.properties.AzuriteRefineProperty;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.item.property.select.SelectProperties;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler.coloredWater;

@Environment(EnvType.CLIENT)
public class BunnyLibClient implements ClientModInitializer {
    private static int PULSE_DEGREES = 0;
    public static int getPulseDegrees() { return PULSE_DEGREES; }

    @Override
    public void onInitializeClient() {
        // Properties
        SelectProperties.ID_MAPPER.put(
                Identifier.of(BunnyLib.MOD_ID, "azurite_refine"),
                AzuriteRefineProperty.TYPE
        );
        SelectProperties.ID_MAPPER.put(
                Identifier.of(BunnyLib.MOD_ID, "atid"),
                AdvancedArrowProperty.TYPE
        );

        // Render Screen
        HandledScreens.register(ModScreenHandlers.ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER, AdvancedFletchingTableScreen::new);

        // Add Entities
        EntityModelLayerRegistry.registerModelLayer(BearEntityModel.BEAR, BearEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BROWN_BEAR, BearEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);

        // Add Block Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_RAIL, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTIC_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_MYSTIC_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COBALT_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_COBALT_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAVENDER_DANDELION, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LAVENDER_DANDELION, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHINY_CORNFLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SHINY_CORNFLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILTED_POPPY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_WILTED_POPPY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEART_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HEART_FLOWER, RenderLayer.getCutout());

        addColoredWater();

        ClientTickEvents.END_CLIENT_TICK.register(this::clientLoop);
    }

    private void clientLoop(MinecraftClient client) {
        if (client.world == null) return;
        PULSE_DEGREES = (PULSE_DEGREES + 5) % 360;
    }

    public void addColoredWater() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_WHITE_WATER, ModFluids.FLOWING_WHITE_WATER, coloredWater(0xF9FFFE));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ORANGE_WATER, ModFluids.FLOWING_ORANGE_WATER, coloredWater(0xF9801D));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MAGENTA_WATER, ModFluids.FLOWING_MAGENTA_WATER, coloredWater(0xC74EBD));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIGHT_BLUE_WATER, ModFluids.FLOWING_LIGHT_BLUE_WATER, coloredWater(0x3AB3DA));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_YELLOW_WATER, ModFluids.FLOWING_YELLOW_WATER, coloredWater(0xFED83D));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIME_WATER, ModFluids.FLOWING_LIME_WATER, coloredWater(0x80C71F));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PINK_WATER, ModFluids.FLOWING_PINK_WATER, coloredWater(0xF38BAA));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GRAY_WATER, ModFluids.FLOWING_GRAY_WATER, coloredWater(0x474F52));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIGHT_GRAY_WATER, ModFluids.FLOWING_LIGHT_GRAY_WATER, coloredWater(0x9D9D97));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_CYAN_WATER, ModFluids.FLOWING_CYAN_WATER, coloredWater(0x169C9C));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PURPLE_WATER, ModFluids.FLOWING_PURPLE_WATER, coloredWater(0x8932B8));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BROWN_WATER, ModFluids.FLOWING_BROWN_WATER, coloredWater(0x835432));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GREEN_WATER, ModFluids.FLOWING_GREEN_WATER, coloredWater(0x5E7C16));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER, coloredWater(0xB02E26));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLACK_WATER, ModFluids.FLOWING_BLACK_WATER, coloredWater(0x1D1D21));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_WHITE_WATER, ModFluids.FLOWING_WHITE_WATER,
                ModFluids.STILL_ORANGE_WATER, ModFluids.FLOWING_ORANGE_WATER,
                ModFluids.STILL_MAGENTA_WATER, ModFluids.FLOWING_MAGENTA_WATER,
                ModFluids.STILL_LIGHT_BLUE_WATER, ModFluids.FLOWING_LIGHT_BLUE_WATER,
                ModFluids.STILL_YELLOW_WATER, ModFluids.FLOWING_YELLOW_WATER,
                ModFluids.STILL_LIME_WATER, ModFluids.FLOWING_LIME_WATER,
                ModFluids.STILL_PINK_WATER, ModFluids.FLOWING_PINK_WATER,
                ModFluids.STILL_GRAY_WATER, ModFluids.FLOWING_GRAY_WATER,
                ModFluids.STILL_LIGHT_GRAY_WATER, ModFluids.FLOWING_LIGHT_GRAY_WATER,
                ModFluids.STILL_CYAN_WATER, ModFluids.FLOWING_CYAN_WATER,
                ModFluids.STILL_PURPLE_WATER, ModFluids.FLOWING_PURPLE_WATER,
                ModFluids.STILL_BROWN_WATER, ModFluids.FLOWING_BROWN_WATER,
                ModFluids.STILL_GREEN_WATER, ModFluids.FLOWING_GREEN_WATER,
                ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER,
                ModFluids.STILL_BLACK_WATER, ModFluids.FLOWING_BLACK_WATER);

    }
}
