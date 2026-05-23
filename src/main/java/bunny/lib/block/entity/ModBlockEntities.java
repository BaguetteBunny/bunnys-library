package bunny.lib.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import bunny.lib.BunnyLib;
import bunny.lib.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AdvancedFletchingTableBlockEntity> FLETCHING_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(BunnyLib.MOD_ID, "fletching_be"),
                    FabricBlockEntityTypeBuilder.create(AdvancedFletchingTableBlockEntity::new, ModBlocks.ADVANCED_FLETCHING_TABLE).build(null));

    public static void registerBlockEntities() {
        BunnyLib.LOGGER.info("Registering Block Entities for " + BunnyLib.MOD_ID);
    }
}
