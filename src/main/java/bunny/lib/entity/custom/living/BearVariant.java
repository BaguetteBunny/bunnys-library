package bunny.lib.entity.custom.living;

import java.util.Arrays;
import java.util.Comparator;

public enum BearVariant {
    DEFAULT(0),
    SILLY(1),
    SILLIEST(2);

    private static final BearVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BearVariant::getId)).toArray(BearVariant[]::new);
    private final int id;

    BearVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BearVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
