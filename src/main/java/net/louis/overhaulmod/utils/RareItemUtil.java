package net.louis.overhaulmod.utils;

import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.item.custom.ColoredNameTagItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.joml.Vector4d;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static net.louis.overhaulmod.item.custom.ColoredNameTagItem.*;
import static net.louis.overhaulmod.utils.ParticleShapeUtil.*;

public class RareItemUtil {
    public static void spawnRareNametag(Item item, PlayerEntity player, BlockPos loc) {
        if (!(item instanceof ColoredNameTagItem nameTag)) return;
        World world = player.getEntityWorld();

        // Spawn Item
        ItemStack nameTagStack = new ItemStack(nameTag, 1);
        ItemEntity nameTagItemEntity = new ItemEntity(world, loc.getX(), loc.getY() + 0.5, loc.getZ(), nameTagStack, 0, 0, 0);
        nameTagItemEntity.setPickupDelay(30);
        world.spawnEntity(nameTagItemEntity);

        // Particle Effects
        ServerWorld serverWorld = Objects.requireNonNull(player.getServer()).getWorld(world.getRegistryKey());

        ParticleEffect firstRingParticles = new DustParticleEffect(nameTag.firstTextColor, 1.0F);
        drawCircle(firstRingParticles, loc.toCenterPos(), 5, serverWorld, 1, new Vector4d(0,0,0,0));

        ParticleEffect secondRingParticles = new DustParticleEffect(nameTag.secondTextColor,  1.0F);
        drawCircle(secondRingParticles, loc.toCenterPos(), 10, serverWorld, 1, new Vector4d(0,0,0,0));

        // SFX
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 16.0f, .5f);

        // Message
        Text colorlessObtainText = Text.of("You've obtained: ");
        Text colorlessNameTagText = Text.of(nameTag.getName().getString());

        Text obtainText = colorlessObtainText.copy().formatted(Formatting.GOLD);
        Text nameTagText = setGradient(colorlessNameTagText, nameTag.firstTextColor, nameTag.secondTextColor);

        Text finalText = obtainText.copy().append(nameTagText);
        player.sendMessage(finalText, true);
    }

    public static final Set<Item> RARE_NAME_TAGS = Set.of(
            ModItems.IRIDESCENT_NAME_TAG,
            ModItems.VOLCANIC_NAME_TAG,
            ModItems.RADIOACTIVE_NAME_TAG,
            ModItems.ASHEN_NAME_TAG,
            ModItems.FRAGRANT_NAME_TAG,
            ModItems.BLOSSOM_NAME_TAG,
            ModItems.PRIMORDIAL_NAME_TAG,
            ModItems.PEACH_NAME_TAG,
            ModItems.CATACLYSM_NAME_TAG,
            ModItems.IMMOLATION_NAME_TAG,
            ModItems.AQUAMARINE_NAME_TAG,
            ModItems.WISP_NAME_TAG
    );

    public static boolean oneIn(World world, int i) {return (world.getRandom().nextBetween(0, i)) == 0;}

}
