package mod.linguardium.tickingstorage;

import mod.linguardium.tickingstorage.blocks.TickingBarrel;
import mod.linguardium.tickingstorage.blocks.entities.TickingBarrelEntity;
import mod.linguardium.tickingstorage.blocks.TickingLargeBarrel;
import mod.linguardium.tickingstorage.blocks.entities.TickingLargeBarrelEntity;
import mod.linguardium.tickingstorage.blocks.initBlocks;
import mod.linguardium.tickingstorage.inventory.TickingInventoryController;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.BlockContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TickingStorage implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "tickingstorage";
    public static final String MOD_NAME = "Ticking Storage";

    public static final Identifier INVENTORY_SCREEN_ID = new Identifier(MOD_ID,"ticking_inventory_screen");


    public static final TestTickingItem TESTITEM = new TestTickingItem(new Item.Settings().maxCount(1).group(ItemGroup.MISC));
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        initBlocks.init();

        ContainerProviderRegistry.INSTANCE.registerFactory(INVENTORY_SCREEN_ID, (syncId, id, player, buf) -> new TickingInventoryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"test_item"),TESTITEM);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}