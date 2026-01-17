package net.louis.overhaulmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/overhaul.json");

    public static ModConfig INSTANCE = new ModConfig();

    // Enchantment Section
    public boolean grindstoneRemoveEnchantsOneByOne = true;
    public boolean obfuscateCurses = true;
    public boolean addEnchantmentDescriptions = true;
    public boolean betterFrostWalker = true;

    // Entity Section
    public boolean disableHorseLeafCollision = true;
    public boolean disableWitherBedrockCollision = true;
    public boolean enderpearlTeleportsHorses = true;
    public boolean endermiteTeleportPlayerOnHit = true;
    public boolean dyeShulkerAndBrush = true;
    public boolean disableItemFrameInteractionIfChest = true;
    public boolean changeArmorstand = true;
    public boolean randomPigSpeed = true;
    public boolean moreAggressivePolarBears = true;
    public boolean strongerWither = true;
    public boolean enableWitherBossPhases = true;
    public long decreaseMobHeadDetectionRange = 0;
    public int saddledGoatHornCooldownInSeconds = 5;

    // Stew Section
    public boolean enableSusStewRNG = true;
    public boolean enableStewMobEffects = true;

    // Potion Section
    public boolean enableCurseClensing = true;
    public boolean enableLingeringTransform = true;
    public boolean enableHoneyClearEffects = true;
    public boolean doLingeringDropOnHit = true;

    // Misc Section
    public boolean enableSitting = true;
    public boolean enableRcHarvest = true;
    public boolean enableThrowableBricks = true;
    public boolean oxidizeCopperWithClock = true;
    public boolean useOnSusSand = true;
    public boolean retexturePlayerHead = true;
    public boolean useBonemealOnOtherCrops = true;
    //public boolean useGlowInk = true;
    public boolean enableFeatherAttack = true;
    public boolean disableCropTrampleWithFeatherFalling = true;
    public boolean sculkCatalystBloomsEchoShards = true;

    // --- load ---
    public static void load() {
        try {
            if (!FILE.exists()) {
                save();
                return;
            }
            INSTANCE = GSON.fromJson(new FileReader(FILE), ModConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- save ---
    public static void save() {
        try {
            FILE.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(FILE);
            GSON.toJson(INSTANCE, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
