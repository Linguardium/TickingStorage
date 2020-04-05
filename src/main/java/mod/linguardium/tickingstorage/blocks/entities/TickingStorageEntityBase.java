package mod.linguardium.tickingstorage.blocks.entities;

import mod.linguardium.tickingstorage.TickingStorage;
import mod.linguardium.tickingstorage.inventory.ImplementedInventory;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.GenericContainer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import org.apache.logging.log4j.Level;

import java.util.UUID;


public class TickingStorageEntityBase extends BlockEntity implements Tickable, ImplementedInventory, BlockEntityClientSerializable {

    protected DefaultedList<ItemStack> inventory;
    protected UUID owner;
    protected String ownerName;
    protected int invSize = 0;

    public TickingStorageEntityBase(BlockEntityType<?> type, int size) {
        super(type);
        invSize = NormalizeSize(size);
        if (invSize != size) {
            TickingStorage.log(Level.ERROR, "Inventory size mismatch. Ticking inventories must be divisible by 9 and no larger than 54.");
        }
        inventory = DefaultedList.ofSize(invSize, ItemStack.EMPTY);
    }
    public void setOwner(LivingEntity newOwner) {
            owner = newOwner.getUuid();
            ownerName = newOwner.getDisplayName().asString();
    }
    public static int NormalizeSize(int size) {
        int iSize = (int) Math.floor(size / 9.0F) * 9;
        if (iSize < 9)
            iSize =9;
        else if (iSize > 54)
            iSize=54;
        return iSize;
    }

    public Text getContainerName() {
        return new TranslatableText("gui.tickingstorage.ticking_barrel.title", ownerName);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int getInvSize() {
        return invSize;
    }

    @Override
    public void tick() {
        if (world != null) {
            // if there is an owner and they are in the list of players
            if (owner != null && world.getPlayerByUuid(owner) != null) {
                // check if user's display name changed
                if (!world.getPlayerByUuid(owner).getDisplayName().asString().equals(ownerName)) {
                    ownerName = world.getPlayerByUuid(owner).getDisplayName().asString();
                }
                for (int i = 0; i < inventory.size(); i++) {
                    if (!inventory.get(i).isEmpty()) {
                        inventory.get(i).inventoryTick(world, world.getPlayerByUuid(owner), i, false);
                    }
                }
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("size", invSize);
        tag.putString("ownerName", ownerName);
        tag.putUuid("owner", owner);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        invSize = tag.getInt("size");
        this.inventory = DefaultedList.ofSize(this.getInvSize(), ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
        owner = tag.getUuid("owner");
        if (owner.getMostSignificantBits() == 0 && owner.getLeastSignificantBits() == 0) {
            owner = null;
        }
        ownerName = tag.getString("ownerName");
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.fromTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return this.toTag(tag);
    }
}