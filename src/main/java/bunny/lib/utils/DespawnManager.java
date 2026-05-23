package bunny.lib.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DespawnManager {
    private static final Map<Entity, Integer> hasDespawnTimer = new HashMap<>();

    public static void addDespawnTimerToEntity(Entity entity, int durationTicks) {
        hasDespawnTimer.put(entity, durationTicks);
    }

    public static void tick() {
        Iterator<Map.Entry<Entity, Integer>> iter = hasDespawnTimer.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Entity, Integer> entry = iter.next();
            int ticksLeft = entry.getValue() - 1;
            if (ticksLeft <= 0) {
                entry.getKey().remove(Entity.RemovalReason.DISCARDED);
                iter.remove();
            } else {
                entry.setValue(ticksLeft);
            }
        }
    }
}
