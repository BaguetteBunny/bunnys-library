package bunny.lib.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TradeOffers.class)
public class TradeOffersMixin {
    @Shadow @Final @Mutable private static Int2ObjectMap<TradeOffers.Factory[]> WANDERING_TRADER_TRADES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void LOM$replaceWanderingTraderTrades(CallbackInfo ci) {
        WANDERING_TRADER_TRADES.put(1, new TradeOffers.Factory[]{
                new TradeOffers.BuyItemFactory(Items.GOLD_BLOCK, 1, 3, 5, 1),
                new TradeOffers.SellItemFactory(Items.IRON_BLOCK, 1, 3, 5, 1),
                new TradeOffers.SellItemFactory(Items.DIAMOND_BLOCK, 1, 3, 5, 1)
        });
    }

}
