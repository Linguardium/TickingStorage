package mod.linguardium.tickingstorage.blocks;

import mod.linguardium.tickingstorage.TickingStorage;
import mod.linguardium.tickingstorage.blocks.entities.TickingBarrelEntity;
import mod.linguardium.tickingstorage.blocks.entities.TickingMediumBarrelEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class TickingMediumBarrel extends TickingStorageBase {
    public static final Identifier ID = new Identifier(TickingStorage.MOD_ID,"ticking_medium_barrel");

    public TickingMediumBarrel(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new TickingMediumBarrelEntity();
    }

}
