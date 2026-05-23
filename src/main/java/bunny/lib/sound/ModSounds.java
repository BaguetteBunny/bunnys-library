package bunny.lib.sound;

import bunny.lib.BunnyLib;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent SADDLED_GOAT_HORN_USE = registerSound("saddled_goat_horn_use");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(BunnyLib.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {
        BunnyLib.LOGGER.info("Registering Mod Sounds for " + BunnyLib.MOD_ID);
    }
}