package bunny.lib.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import bunny.lib.BunnyLib;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AdvancedFletchingTableScreenHandler> ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(BunnyLib.MOD_ID, "advanced_fletching_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(AdvancedFletchingTableScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        BunnyLib.LOGGER.info("Registering Screen Handlers for " + BunnyLib.MOD_ID);
    }
}
