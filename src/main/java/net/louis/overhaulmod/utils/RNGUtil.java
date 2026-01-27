package net.louis.overhaulmod.utils;

import net.minecraft.world.World;

public class RNGUtil {
    public static boolean oneIn(World world, int i) {return (world.getRandom().nextBetween(0, i)) == 0;}
}
