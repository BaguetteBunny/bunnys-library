package net.louis.overhaulmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AdvancedFletchingTableScreenHandler> ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(LouisOverhaulMod.MOD_ID, "advanced_fletching_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(AdvancedFletchingTableScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        LouisOverhaulMod.LOGGER.info("Registering Screen Handlers for " + LouisOverhaulMod.MOD_ID);
    }
}
