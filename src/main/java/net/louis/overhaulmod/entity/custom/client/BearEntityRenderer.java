package net.louis.overhaulmod.entity.custom.client;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.louis.overhaulmod.entity.custom.living.BearVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class BearEntityRenderer extends MobEntityRenderer<BearEntity, BearRenderState, BearEntityModel> {
    private static final Map<BearVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BearVariant.class), map -> {
                map.put(BearVariant.DEFAULT,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/brown_bear.png"));
                map.put(BearVariant.SILLY,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/silly_brown_bear.png"));
                map.put(BearVariant.SILLIEST,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/silliest_brown_bear.png"));
            });


    public BearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BearEntityModel(context.getPart(EntityModelLayers.POLAR_BEAR)), 0.9F);
    }

    @Override
    public void render(BearRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(0.9f, 0.9f, 0.9f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public BearRenderState createRenderState() {
        return new BearRenderState();
    }

    @Override
    public Identifier getTexture(BearRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void updateRenderState(BearEntity livingEntity, BearRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
