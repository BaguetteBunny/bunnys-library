package net.louis.overhaulmod.entity.custom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.entity.custom.living.BearVariant;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class BearRenderState extends LivingEntityRenderState {
    public BearVariant variant;
    public float warningAnimationProgress;
}

