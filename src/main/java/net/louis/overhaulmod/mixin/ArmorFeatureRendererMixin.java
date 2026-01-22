package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.LouisOverhaulModClient;
import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.louis.overhaulmod.LouisOverhaulModClient.getPulseDegrees;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {
    @Shadow @Final private SpriteAtlasTexture armorTrimsAtlas;
    @Unique private static final ThreadLocal<LivingEntity> CURRENT_ENTITY = new ThreadLocal<>();
    @Unique private static final ThreadLocal<EquipmentSlot> CURRENT_SLOT = new ThreadLocal<>();

    @Inject(method = "renderArmor", at = @At("HEAD"))
    private void LOM$captureEntity(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci
    ) {
        CURRENT_ENTITY.set(entity);
        CURRENT_SLOT.set(armorSlot);
    }

    @Inject(method = "renderArmor", at = @At("RETURN"))
    private void LOM$cleanupEntity(CallbackInfo ci) {
        CURRENT_ENTITY.remove();
        CURRENT_SLOT.remove();
    }

    @Inject(method = "renderTrim", at = @At("HEAD"), cancellable = true)
    private void LOM$makeTrimsGlowBasedOnComponent(RegistryEntry armorMaterial, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorTrim trim, BipedEntityModel model, boolean leggings, CallbackInfo ci) {
        LivingEntity entity = CURRENT_ENTITY.get();
        EquipmentSlot slot = CURRENT_SLOT.get();

        if (entity == null || slot == null) return;
        ItemStack armorStack = entity.getEquippedStack(slot);
        Boolean shouldPulsate = armorStack.get(ModComponents.GLOW_AND_PULSATE);

        int finalLight;

        if (shouldPulsate == null) finalLight = light;
        else if (!shouldPulsate) finalLight = LightmapTextureManager.MAX_LIGHT_COORDINATE;
        else finalLight = 200 - (int) (Math.pow(Math.cos(Math.toRadians(getPulseDegrees())), 2) * 100);

        Sprite sprite = this.armorTrimsAtlas.getSprite(leggings ? trim.getLeggingsModelId(armorMaterial) : trim.getGenericModelId(armorMaterial));
        VertexConsumer vertexConsumer = sprite.getTextureSpecificVertexConsumer(vertexConsumers.getBuffer(net.minecraft.client.render.TexturedRenderLayers.getArmorTrims(trim.getPattern().value().decal())));
        model.render(matrices, vertexConsumer, finalLight, OverlayTexture.DEFAULT_UV);
        ci.cancel();
    }
}
