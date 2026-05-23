package bunny.lib.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import bunny.lib.component.ModComponents;
import bunny.lib.item.ModItems;
import bunny.lib.screen.AdvancedFletchingTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class AdvancedFletchingTableBlockEntity extends BlockEntity implements SidedInventory, ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int FOOT_SLOT = 1;
    private static final int SHAFT_SLOT = 2;
    private static final int HEAD_SLOT = 3;
    private static final int OUTPUT_SLOT = 0;

    Item[] FOOT_ITEMS = {Items.FEATHER, Items.PHANTOM_MEMBRANE, Items.ARMADILLO_SCUTE, Items.DRIED_KELP};
    Item[] SHAFT_ITEMS = {Items.STICK, Items.BLAZE_ROD, Items.BREEZE_ROD};
    Item[] HEAD_ITEMS = {Items.FLINT, Items.AMETHYST_SHARD, Items.ECHO_SHARD, Items.PRISMARINE_SHARD};

    public AdvancedFletchingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLETCHING_BE, pos, state);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) { return false; }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fletching Table");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AdvancedFletchingTableScreenHandler(syncId, playerInventory, this.pos);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        Item footItem = this.getStack(FOOT_SLOT).getItem();
        Item shaftItem = this.getStack(SHAFT_SLOT).getItem();
        Item headItem = this.getStack(HEAD_SLOT).getItem();

        if(hasRecipe(footItem, shaftItem, headItem)) {
            markDirty(world, pos, state);
            craftItem(footItem, shaftItem, headItem);
        } else {
            this.setStack(OUTPUT_SLOT, new ItemStack(Items.AIR, 0));
        }
    }

    private void craftItem(Item foot, Item shaft, Item head) {
        ItemStack output = new ItemStack(ModItems.ADVANCED_ARROW, 4);
        if (foot == Items.FEATHER && shaft == Items.STICK && head == Items.FLINT) output = new ItemStack(Items.ARROW, 4);

        if (foot != Items.FEATHER) output.set(ModComponents.ARROW_FOOT, foot);
        if (shaft != Items.STICK) output.set(ModComponents.ARROW_SHAFT, shaft);
        if (head != Items.FLINT) output.set(ModComponents.ARROW_HEAD, head);

        this.setStack(OUTPUT_SLOT, output);
    }

    private boolean hasRecipe(Item foot, Item shaft, Item head) {
        return Arrays.asList(FOOT_ITEMS).contains(foot) &&
                Arrays.asList(SHAFT_ITEMS).contains(shaft) &&
                Arrays.asList(HEAD_ITEMS).contains(head);
    }
}
