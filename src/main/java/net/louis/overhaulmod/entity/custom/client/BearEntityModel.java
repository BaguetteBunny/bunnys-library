package net.louis.overhaulmod.entity.custom.client;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.PolarBearEntityRenderState;
import net.minecraft.util.Identifier;

public class BearEntityModel extends EntityModel<BearRenderState> {
    public BearEntityModel(ModelPart root) {
        super(root);
    }

    public static final EntityModelLayer BEAR = new EntityModelLayer(Identifier.of(LouisOverhaulMod.MOD_ID, "bear"), "main");

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(
                EntityModelPartNames.HEAD,
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 7.0F)
                        .uv(0, 44)
                        .cuboid(EntityModelPartNames.MOUTH, -2.5F, 1.0F, -6.0F, 5.0F, 3.0F, 3.0F)
                        .uv(26, 0)
                        .cuboid(EntityModelPartNames.RIGHT_EAR, -4.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F)
                        .uv(26, 0)
                        .mirrored()
                        .cuboid(EntityModelPartNames.LEFT_EAR, 2.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F),
                ModelTransform.pivot(0.0F, 10.0F, -16.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create().uv(0, 19).cuboid(-5.0F, -13.0F, -7.0F, 14.0F, 14.0F, 11.0F).uv(39, 0).cuboid(-4.0F, -25.0F, -7.0F, 12.0F, 12.0F, 10.0F),
                ModelTransform.of(-2.0F, 9.0F, 12.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
        );
        int i = 10;
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(50, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F);
        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(-4.5F, 14.0F, 6.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(4.5F, 14.0F, 6.0F));
        ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(50, 40).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F);
        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder2, ModelTransform.pivot(-3.5F, 14.0F, -8.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder2, ModelTransform.pivot(3.5F, 14.0F, -8.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }
}
