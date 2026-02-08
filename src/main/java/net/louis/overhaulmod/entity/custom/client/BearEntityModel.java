package net.louis.overhaulmod.entity.custom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BearEntityModel extends QuadrupedEntityModel<BearRenderState> {
    protected final ModelPart head;
    protected final ModelPart body;
    protected final ModelPart rightHindLeg;
    protected final ModelPart leftHindLeg;
    protected final ModelPart rightFrontLeg;
    protected final ModelPart leftFrontLeg;

    public BearEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
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

    public void setAngles(BearRenderState bearRenderState) {
        super.setAngles(bearRenderState);
        float f = bearRenderState.warningAnimationProgress * bearRenderState.warningAnimationProgress;
        float g = bearRenderState.ageScale;
        float h = bearRenderState.baby ? 0.44444445F : 1.0F;
        ModelPart var10000 = this.body;
        var10000.pitch -= f * (float)Math.PI * 0.35F;
        var10000 = this.body;
        var10000.pivotY += f * g * 2.0F;
        var10000 = this.rightFrontLeg;
        var10000.pivotY -= f * g * 20.0F;
        var10000 = this.rightFrontLeg;
        var10000.pivotZ += f * g * 4.0F;
        var10000 = this.rightFrontLeg;
        var10000.pitch -= f * (float)Math.PI * 0.45F;
        this.leftFrontLeg.pivotY = this.rightFrontLeg.pivotY;
        this.leftFrontLeg.pivotZ = this.rightFrontLeg.pivotZ;
        var10000 = this.leftFrontLeg;
        var10000.pitch -= f * (float)Math.PI * 0.45F;
        var10000 = this.head;
        var10000.pivotY -= f * h * 24.0F;
        var10000 = this.head;
        var10000.pivotZ += f * h * 13.0F;
        var10000 = this.head;
        var10000.pitch += f * (float)Math.PI * 0.15F;
    }
}
