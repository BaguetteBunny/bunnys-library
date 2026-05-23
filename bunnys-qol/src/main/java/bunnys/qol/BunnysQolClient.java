package bunnys.qol;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import bunnys.qol.entity.custom.client.ChairRenderer;
import bunnys.qol.entity.ModEntities;

@Environment(EnvType.CLIENT)
public class BunnysQolClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);
    }
}
