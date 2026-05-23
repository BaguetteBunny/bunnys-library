package bunny.lib.utils.enums;


import net.minecraft.util.StringIdentifiable;

public enum FluidType implements StringIdentifiable {
    BLACK, WHITE, ORANGE, RED, MAGENTA, PINK, PURPLE, GREEN,
    LIME, LIGHT_BLUE, BLUE, GRAY, LIGHT_GRAY, BROWN, YELLOW, CYAN;

    @Override
    public String asString() {
        return this.name().toLowerCase();
    }
}
