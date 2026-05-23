package bunny.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GlowLightManager {
    private static final Map<LivingEntity, Integer> glowingEntities = new HashMap<>();

    public static void addGlowingEntity(LivingEntity entity, int durationTicks) {
        glowingEntities.put(entity, durationTicks);
    }

    public static void tick() {
        Iterator<Map.Entry<LivingEntity, Integer>> iter = glowingEntities.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LivingEntity, Integer> entry = iter.next();
            int ticksLeft = entry.getValue() - 1;
            if (ticksLeft <= 0) {
                entry.getKey().setGlowing(false);
                iter.remove();
            } else {
                entry.setValue(ticksLeft);
            }
        }
    }

    public static final HashMap<Item, Integer> ITEM_LIGHT_LEVELS = new HashMap<>() {{
        put(Items.BEACON, 15);
        put(Items.OCHRE_FROGLIGHT, 15);
        put(Items.VERDANT_FROGLIGHT, 15);
        put(Items.PEARLESCENT_FROGLIGHT, 15);
        put(Items.GLOWSTONE, 15);
        put(Items.JACK_O_LANTERN, 15);
        put(Items.LANTERN, 15);
        put(Items.LAVA_BUCKET, 15);
        put(Items.CAMPFIRE, 15);
        put(Items.SEA_LANTERN, 15);
        put(Items.SHROOMLIGHT, 15);
        put(Items.END_ROD, 14);
        put(Items.TORCH, 12);
        put(Items.CRYING_OBSIDIAN, 10);
        put(Items.SOUL_CAMPFIRE, 10);
        put(Items.SOUL_LANTERN, 10);
        put(Items.SOUL_TORCH, 10);
        put(Items.ENCHANTING_TABLE, 7);
        put(Items.GLOW_BERRIES, 7);
        put(Items.ENDER_CHEST, 7);
        put(Items.GLOW_LICHEN, 7);
        put(Items.REDSTONE_TORCH, 7);
        put(Items.SCULK_CATALYST, 6);
        put(Items.AMETHYST_CLUSTER, 5);
        put(Items.LARGE_AMETHYST_BUD, 4);
        put(Items.MAGMA_BLOCK, 3);
        put(Items.MEDIUM_AMETHYST_BUD, 2);
        put(Items.BREWING_STAND, 1);
        put(Items.BROWN_MUSHROOM, 1);
        put(Items.CALIBRATED_SCULK_SENSOR, 1);
        put(Items.DRAGON_EGG, 1);
        put(Items.END_PORTAL_FRAME, 1);
        put(Items.SCULK_SENSOR, 1);
        put(Items.SMALL_AMETHYST_BUD, 1);
    }};
}

