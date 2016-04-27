package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemModels {
    public static void registerModels() {
        registerBlock(AMBlocks.soil);
        registerBlock(AMBlocks.shale);
        registerBlock(AMBlocks.rune_log, "rune_log");
        registerBlock(AMBlocks.planks, BlockAMPlanks.EnumType.RUNE.getMetadata(), "rune_planks");
        registerBlockWithStateMapper(AMBlocks.planks, new StateMap.Builder().withName(BlockAMPlanks.VARIANT).withSuffix("_planks").build());
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

    private static void registerBlockWithStateMapper(Block block, IStateMapper mapper) {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
}
