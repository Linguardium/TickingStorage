package mod.linguardium.tickingstorage;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

public class TestTickingItem extends Item {
    int i = 0;
    public TestTickingItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if( world.random.nextInt(100 ) < 5 ) {
            entity.sendMessage(new LiteralText("oh Boy! " + String.valueOf(i)));
            i++;
            if (i > 9)
                i = 0;
        }
    }
}
