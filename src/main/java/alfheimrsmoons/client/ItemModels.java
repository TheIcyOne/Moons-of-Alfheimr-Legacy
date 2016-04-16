package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemModels {
    public static void register() {
        registerBlock(AMBlocks.soil);
        registerBlock(AMBlocks.shale);
        registerItem(AMItems.branch_bow);
    }

    private static void registerItem(Item itm, int subType, String identifier) {
        ModelLoader.setCustomModelResourceLocation(itm, subType, new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + identifier, "inventory"));
    }

    private static void registerBlock(Block blk, int subType, String identifier) {
        registerItem(Item.getItemFromBlock(blk), subType, identifier);
    }

    private static void registerBlock(Block blk, String identifier) {
        registerBlock(blk, 0, identifier);
    }

    private static void registerItem(Item itm, String identifier) {
        registerItem(itm, 0, identifier);
    }

    private static void registerBlock(Block blk) {
        registerBlock(blk, blk.getRegistryName().getResourcePath());
    }

    private static void registerItem(Item itm) {
        registerItem(itm, itm.getRegistryName().getResourcePath());
    }
}
