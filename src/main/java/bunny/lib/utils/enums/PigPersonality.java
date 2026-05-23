package bunny.lib.utils.enums;

import net.minecraft.world.World;

import java.util.random.RandomGenerator;

public enum PigPersonality {
    // ID
    // LOWER VOL - UPPER VOL
    // LOWER MIN - UPPER MIN
    // LOWER MAX - UPPER MAX
    // VOL CST

    NORMAL(0, 0.085f, 0.2f, 0.75f, 1, 3, 4, 100),
    STRONG(1, 0.085f, 0.2f, 0.9f, 1, 4, 5, 75),
    WEAK(2, 0.1f, 0.2f, 0.3f, 0.8f, 1.5f, 2.3f, 125),
    VOLATILE(3, 0.25f, 0.55f, 0.75f, 1, 3, 4, 100),
    CONSISTENT(4, 0.06f, 0.09f, 0.75f, 1, 3, 4, 100);

    private final int id;
    private final float lesserVolatility;
    private final float upperVolatility;
    private final float lesserMinRange;
    private final float upperMinRange;
    private final float lesserMaxRange;
    private final float upperMaxRange;
    private final int volatilityConstant;

    PigPersonality(int id, float lesserVolatility, float upperVolatility, float lesserMinRange, float upperMinRange, float lesserMaxRange, float upperMaxRange, int volatilityConstant) {
        this.id = id;
        this.lesserVolatility = lesserVolatility;
        this.upperVolatility = upperVolatility;

        this.lesserMinRange = lesserMinRange;
        this.upperMinRange = upperMinRange;

        this.lesserMaxRange = lesserMaxRange;
        this.upperMaxRange = upperMaxRange;

        this.volatilityConstant = volatilityConstant;
    }

    public float getId() {
        return this.id;
    }

    public float getVolatility() {
        return RandomGenerator.getDefault().nextFloat(lesserVolatility, upperVolatility);
    }

    public float getMinRange() {
        return RandomGenerator.getDefault().nextFloat(lesserMinRange, upperMinRange);
    }

    public float getMaxRange() {
        return RandomGenerator.getDefault().nextFloat(lesserMaxRange, upperMaxRange);
    }

    public int getVolatilityCst() {
        return this.volatilityConstant;
    }

    public static PigPersonality random(World world) {
        PigPersonality[] values = values();
        return values[world.getRandom().nextInt(values.length)];
    }
}
