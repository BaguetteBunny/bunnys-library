package net.louis.overhaulmod.utils.properties;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class AdvancedArrowProperty implements SelectProperty<Integer> {
    public static final AdvancedArrowProperty INSTANCE = new AdvancedArrowProperty();
    public static final Type<AdvancedArrowProperty, Integer> TYPE = Type.create(MapCodec.unit(INSTANCE), Codec.INT);

    @Override
    public @Nullable Integer getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        int total = 0;
        Item foot  = stack.get(ModComponents.ARROW_FOOT);
        Item shaft = stack.get(ModComponents.ARROW_SHAFT);
        Item head  = stack.get(ModComponents.ARROW_HEAD);

        if (head == Items.AMETHYST_SHARD)   total += 1;
        if (head == Items.ECHO_SHARD)        total += 2;
        if (head == Items.PRISMARINE_SHARD)  total += 3;

        if (shaft == Items.BLAZE_ROD)        total += 10;
        if (shaft == Items.BREEZE_ROD)       total += 20;

        if (foot == Items.PHANTOM_MEMBRANE)  total += 100;
        if (foot == Items.DRIED_KELP)        total += 200;
        if (foot == Items.ARMADILLO_SCUTE)   total += 300;

        return total;
    }

    @Override
    public Type<AdvancedArrowProperty, Integer> getType() {
        return TYPE;
    }
}
