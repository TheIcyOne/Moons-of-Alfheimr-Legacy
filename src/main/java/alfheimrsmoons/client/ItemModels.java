package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemModels {
    public static void registerModels() {
        registerBlock(AMBlocks.soil);
        registerBlock(AMBlocks.shale);
        registerBlockWithStateMapper(AMBlocks.log, AMBlocks.log.variant, "_log");
        registerBlockWithStateMapper(AMBlocks.planks, BlockAMPlanks.VARIANT, "_planks");
        registerItem(AMItems.branch_bow);
        registerItem(AMItems.rock_arrow);
    }

    private static void registerItem(Item item, int metadata, String identifier) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + identifier, "inventory"));
    }

    private static void registerBlock(Block block, int metadata, String identifier) {
        registerItem(Item.getItemFromBlock(block), metadata, identifier);
    }

    private static void registerBlock(Block block, String identifier) {
        registerBlock(block, 0, identifier);
    }

    private static void registerItem(Item item, String identifier) {
        registerItem(item, 0, identifier);
    }

    private static void registerBlock(Block block) {
        registerBlock(block, block.getRegistryName().getResourcePath());
    }

    private static void registerItem(Item item) {
        registerItem(item, item.getRegistryName().getResourcePath());
    }

    private static <T extends Comparable<T>> void registerBlockWithStateMapper(Block block, IProperty<T> property, String suffix) {
        for (T value : property.getAllowedValues()) {
            int meta = block.getMetaFromState(block.getDefaultState().withProperty(property, value));
            String id = property.getName(value) + suffix;
            registerBlock(block, meta, id);
        }
        ModelLoader.setCustomStateMapper(block, new StateMap.Builder().withName(property).withSuffix(suffix).build());
    }
}
