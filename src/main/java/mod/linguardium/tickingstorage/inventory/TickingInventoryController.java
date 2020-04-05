package mod.linguardium.tickingstorage.inventory;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import mod.linguardium.tickingstorage.blocks.entities.TickingStorageEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class TickingInventoryController extends CottonCraftingController {

    public TickingInventoryController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(null, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        int rows = (int)Math.floor(blockInventory.getInvSize()/9);
        int h =(rows+5) * 15;
        root.setSize(160, h);
        WLabel lTitle = new WLabel(getInventoryName(context));
        root.add(lTitle,0,0);
        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0,9,rows);
        root.add(itemSlot, 0, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 2+rows);

        root.validate(this);
    }





    public static Text getInventoryName(BlockContext ctx) {
        return (Text)ctx.run((world, pos) -> {
            BlockEntity be = world.getBlockEntity(pos);
            Block b = world.getBlockState(pos).getBlock();
            if (be instanceof TickingStorageEntityBase) {
                return ((TickingStorageEntityBase) be).getContainerName();
            }
            return new LiteralText("Container");
        }).orElse(new LiteralText("Container"));
    }
}
