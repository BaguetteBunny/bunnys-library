package bunny.lib.mixin;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static bunny.lib.utils.CauldronBehaviorHooks.*;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {

    @Inject(method = "registerBehavior", at = @At("RETURN"))
    private static void LOM$addCustomCauldronBehaviors(CallbackInfo ci) {
        Map<Item, CauldronBehavior> addMap = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();

        // Candles
        addMap.put(Items.BLACK_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.BLUE_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.BROWN_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.CYAN_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.GRAY_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.GREEN_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_BLUE_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_GRAY_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.LIME_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.MAGENTA_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.ORANGE_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.PINK_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.PURPLE_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.RED_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.YELLOW_CANDLE, CLEAN_BLOCK);
        addMap.put(Items.WHITE_CANDLE, CLEAN_BLOCK);

        // CARPETS
        addMap.put(Items.BLACK_CARPET, CLEAN_BLOCK);
        addMap.put(Items.BLUE_CARPET, CLEAN_BLOCK);
        addMap.put(Items.BROWN_CARPET, CLEAN_BLOCK);
        addMap.put(Items.CYAN_CARPET, CLEAN_BLOCK);
        addMap.put(Items.GRAY_CARPET, CLEAN_BLOCK);
        addMap.put(Items.GREEN_CARPET, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_BLUE_CARPET, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_GRAY_CARPET, CLEAN_BLOCK);
        addMap.put(Items.LIME_CARPET, CLEAN_BLOCK);
        addMap.put(Items.MAGENTA_CARPET, CLEAN_BLOCK);
        addMap.put(Items.ORANGE_CARPET, CLEAN_BLOCK);
        addMap.put(Items.PINK_CARPET, CLEAN_BLOCK);
        addMap.put(Items.PURPLE_CARPET, CLEAN_BLOCK);
        addMap.put(Items.RED_CARPET, CLEAN_BLOCK);
        addMap.put(Items.YELLOW_CARPET, CLEAN_BLOCK);
        addMap.put(Items.WHITE_CARPET, CLEAN_BLOCK);

        // Wool
        addMap.put(Items.BLACK_WOOL, CLEAN_BLOCK);
        addMap.put(Items.BLUE_WOOL, CLEAN_BLOCK);
        addMap.put(Items.BROWN_WOOL, CLEAN_BLOCK);
        addMap.put(Items.CYAN_WOOL, CLEAN_BLOCK);
        addMap.put(Items.GRAY_WOOL, CLEAN_BLOCK);
        addMap.put(Items.GREEN_WOOL, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_BLUE_WOOL, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_GRAY_WOOL, CLEAN_BLOCK);
        addMap.put(Items.LIME_WOOL, CLEAN_BLOCK);
        addMap.put(Items.MAGENTA_WOOL, CLEAN_BLOCK);
        addMap.put(Items.ORANGE_WOOL, CLEAN_BLOCK);
        addMap.put(Items.PINK_WOOL, CLEAN_BLOCK);
        addMap.put(Items.PURPLE_WOOL, CLEAN_BLOCK);
        addMap.put(Items.RED_WOOL, CLEAN_BLOCK);
        addMap.put(Items.YELLOW_WOOL, CLEAN_BLOCK);

        // Glazed Terracotta
        addMap.put(Items.BLACK_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.BLUE_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.BROWN_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.CYAN_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.GRAY_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.GREEN_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.LIGHT_BLUE_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.LIGHT_GRAY_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.LIME_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.MAGENTA_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.ORANGE_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.PINK_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.PURPLE_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.RED_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);
        addMap.put(Items.YELLOW_GLAZED_TERRACOTTA, CLEAN_GLAZED_TERRACOTTA);

        // Terracotta
        addMap.put(Items.BLACK_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.BLUE_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.BROWN_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.CYAN_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.GRAY_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.GREEN_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_BLUE_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_GRAY_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.LIME_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.MAGENTA_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.ORANGE_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.PINK_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.PURPLE_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.RED_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.YELLOW_TERRACOTTA, CLEAN_BLOCK);
        addMap.put(Items.WHITE_TERRACOTTA, CLEAN_BLOCK);

        // Concrete Powder
        addMap.put(Items.BLACK_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.BLUE_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.BROWN_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.CYAN_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.GRAY_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.GREEN_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.LIGHT_BLUE_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.LIGHT_GRAY_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.LIME_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.MAGENTA_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.ORANGE_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.PINK_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.PURPLE_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.RED_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);
        addMap.put(Items.YELLOW_CONCRETE_POWDER, CLEAN_CONCRETE_POWDER);

        // Concrete
        addMap.put(Items.BLACK_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.BLUE_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.BROWN_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.CYAN_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.GRAY_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.GREEN_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.LIGHT_BLUE_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.LIGHT_GRAY_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.LIME_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.MAGENTA_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.ORANGE_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.PINK_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.PURPLE_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.RED_CONCRETE, CLEAN_CONCRETE);
        addMap.put(Items.YELLOW_CONCRETE, CLEAN_CONCRETE);

        // Glass
        addMap.put(Items.BLACK_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.BLUE_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.BROWN_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.CYAN_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.GRAY_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.GREEN_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.LIGHT_BLUE_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.LIGHT_GRAY_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.LIME_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.MAGENTA_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.ORANGE_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.PINK_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.PURPLE_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.RED_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.YELLOW_STAINED_GLASS, CLEAN_GLASS);
        addMap.put(Items.WHITE_STAINED_GLASS, CLEAN_GLASS);

        // Glass Panes
        addMap.put(Items.BLACK_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.BLUE_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.BROWN_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.CYAN_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.GRAY_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.GREEN_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.LIGHT_BLUE_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.LIGHT_GRAY_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.LIME_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.MAGENTA_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.ORANGE_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.PINK_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.PURPLE_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.RED_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.YELLOW_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);
        addMap.put(Items.WHITE_STAINED_GLASS_PANE, CLEAN_GLASS_PANE);

        // Beds
        addMap.put(Items.BLACK_BED, CLEAN_BLOCK);
        addMap.put(Items.BLUE_BED, CLEAN_BLOCK);
        addMap.put(Items.BROWN_BED, CLEAN_BLOCK);
        addMap.put(Items.CYAN_BED, CLEAN_BLOCK);
        addMap.put(Items.GRAY_BED, CLEAN_BLOCK);
        addMap.put(Items.GREEN_BED, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_BLUE_BED, CLEAN_BLOCK);
        addMap.put(Items.LIGHT_GRAY_BED, CLEAN_BLOCK);
        addMap.put(Items.LIME_BED, CLEAN_BLOCK);
        addMap.put(Items.MAGENTA_BED, CLEAN_BLOCK);
        addMap.put(Items.ORANGE_BED, CLEAN_BLOCK);
        addMap.put(Items.PINK_BED, CLEAN_BLOCK);
        addMap.put(Items.PURPLE_BED, CLEAN_BLOCK);
        addMap.put(Items.RED_BED, CLEAN_BLOCK);
        addMap.put(Items.YELLOW_BED, CLEAN_BLOCK);
    }
}
