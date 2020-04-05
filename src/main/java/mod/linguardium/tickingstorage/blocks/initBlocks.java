package mod.linguardium.tickingstorage.blocks;

import mod.linguardium.tickingstorage.blocks.entities.TickingBarrelEntity;
import mod.linguardium.tickingstorage.blocks.entities.TickingLargeBarrelEntity;
import mod.linguardium.tickingstorage.blocks.entities.TickingMediumBarrelEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static mod.linguardium.tickingstorage.TickingStorage.MOD_ID;

public class initBlocks {

    public static final TickingBarrel TICKING_BARREL = new TickingBarrel(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).strength(2.5F,2.5F).build());
    public static final BlockEntityType<TickingBarrelEntity> TICKING_BARREL_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE,new Identifier(MOD_ID,"ticking_barrel_entity"), BlockEntityType.Builder.create(TickingBarrelEntity::new,TICKING_BARREL).build(null));

    public static final TickingMediumBarrel TICKING_MEDIUM_BARREL = new TickingMediumBarrel(FabricBlockSettings.copy(TICKING_BARREL).build());
    public static final BlockEntityType<TickingMediumBarrelEntity> TICKING_MEDIUM_BARREL_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE,new Identifier(MOD_ID,"ticking_medium_barrel_entity"), BlockEntityType.Builder.create(TickingMediumBarrelEntity::new,TICKING_MEDIUM_BARREL).build(null));

    public static final TickingLargeBarrel TICKING_LARGE_BARREL = new TickingLargeBarrel(FabricBlockSettings.copy(TICKING_BARREL).build());
    public static final BlockEntityType<TickingLargeBarrelEntity> TICKING_LARGE_BARREL_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE,new Identifier(MOD_ID,"ticking_large_barrel_entity"), BlockEntityType.Builder.create(TickingLargeBarrelEntity::new,TICKING_LARGE_BARREL).build(null));

    public static void init() {

        Registry.register(Registry.BLOCK,TickingBarrel.ID,TICKING_BARREL);
        Registry.register(Registry.ITEM, TickingBarrel.ID, new BlockItem(TICKING_BARREL, new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.BLOCK, TickingMediumBarrel.ID,TICKING_MEDIUM_BARREL);
        Registry.register(Registry.ITEM, TickingMediumBarrel.ID, new BlockItem(TICKING_MEDIUM_BARREL, new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.BLOCK, TickingLargeBarrel.ID,TICKING_LARGE_BARREL);
        Registry.register(Registry.ITEM, TickingLargeBarrel.ID, new BlockItem(TICKING_LARGE_BARREL, new Item.Settings().group(ItemGroup.MISC)));


    }
}
