package mod.linguardium.tickingstorage.blocks;

import mod.linguardium.tickingstorage.TickingStorage;
import mod.linguardium.tickingstorage.blocks.entities.TickingStorageEntityBase;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TickingStorageBase extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(TickingStorage.MOD_ID,"ticking_storage_base");

    public TickingStorageBase(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.CONSUME;

        BlockEntity be = world.getBlockEntity(pos);
        if (be!=null && be instanceof TickingStorageEntityBase) {
            ContainerProviderRegistry.INSTANCE.openContainer(TickingStorage.INVENTORY_SCREEN_ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        return ActionResult.SUCCESS;
    }
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateHorizontalAdjacent(pos, this);
            }

            super.onBlockRemoved(state, world, pos, newState, moved);
        }
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // this gets called after the entity has already been created, lets take this opportunity to set the owner so that we know who to impersonate
        BlockEntity be = world.getBlockEntity(pos);
        if (be != null && be instanceof TickingStorageEntityBase) {
            TickingStorageEntityBase tseb = (TickingStorageEntityBase)be;
            tseb.setOwner(placer);
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
