package mod.linguardium.tickingstorage.inventory;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class TickingInventoryScreen extends CottonInventoryScreen<TickingInventoryController> {
    public TickingInventoryScreen(TickingInventoryController container, PlayerEntity player) {
        super(container, player);
    }
}
