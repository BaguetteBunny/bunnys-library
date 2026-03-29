package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.louis.overhaulmod.LouisOverhaulModClient.getPulseDegrees;

@Mixin(EquipmentRenderer.class)
public class EquipmentRendererMixin {

    @ModifyVariable(
            method = "render(Lnet/minecraft/item/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/render/entity/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private int LOM$modifyLightForGlowingArmor(
            int light,
            EquipmentModel.LayerType layerType,
            RegistryKey<EquipmentAsset> assetKey,
            Model model,
            ItemStack stack,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers
    ) {
        Boolean shouldPulsate = stack.get(ModComponents.GLOW_AND_PULSATE);

        if (shouldPulsate == null) return light;

        if (!shouldPulsate) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        else return 200 - (int) (Math.pow(Math.cos(Math.toRadians(getPulseDegrees())), 2) * 100);
    }
}
