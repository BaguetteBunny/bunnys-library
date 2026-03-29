package net.louis.overhaulmod.utils.properties;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class AzuriteRefineProperty implements SelectProperty<String> {

    public static final AzuriteRefineProperty INSTANCE = new AzuriteRefineProperty();

    public static final Type<AzuriteRefineProperty, String> TYPE = Type.create(
            MapCodec.unit(INSTANCE),
            Codec.STRING
    );

    @Override
    public @Nullable String getValue(ItemStack stack, @Nullable ClientWorld world,
                                     @Nullable LivingEntity user, int seed,
                                     ModelTransformationMode modelTransformationMode) {
        return stack.get(ModComponents.AZURITE_REFINE);
    }

    @Override
    public Type<AzuriteRefineProperty, String> getType() {
        return TYPE;
    }
}