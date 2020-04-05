package mod.linguardium.tickingstorage;

import mod.linguardium.tickingstorage.inventory.TickingInventoryController;
import mod.linguardium.tickingstorage.inventory.TickingInventoryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;

import static mod.linguardium.tickingstorage.TickingStorage.INVENTORY_SCREEN_ID;

public class TickingStorageClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(INVENTORY_SCREEN_ID, (syncId, identifier, player, buf) -> new TickingInventoryScreen(new TickingInventoryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
    }
}
