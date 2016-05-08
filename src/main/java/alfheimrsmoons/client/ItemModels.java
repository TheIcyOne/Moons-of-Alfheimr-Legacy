package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemModels {
    public static void registerModels() {
        registerBlock(AMBlocks.soil);
        registerBlock(AMBlocks.grassy_soil);
        registerBlock(AMBlocks.sediment);
        registerBlock(AMBlocks.shale);
        registerBlockWithVariants(AMBlocks.ore, BlockAMOre.VARIANT, "ore");
        registerBlockWithVariants(AMBlocks.log, AMBlocks.log.variant, "log");
        registerBlockWithVariants(AMBlocks.log2, AMBlocks.log2.variant, "log");
        registerBlockWithVariants(AMBlocks.planks, BlockAMPlanks.VARIANT, "planks");
        registerBlock(AMBlocks.rune_bookshelf);
        registerItem(AMItems.branch);
        registerItem(AMItems.ore_drop, BlockAMOre.EnumType.NITRO.getMetadata(), "nitro_powder");
        registerItem(AMItems.ore_drop, BlockAMOre.EnumType.KASOLITE.getMetadata(), "kasolite_crystal");
        registerItem(AMItems.ore_drop, BlockAMOre.EnumType.LOREIUM.getMetadata(), "loreium_ingot");
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

    private static <T extends Comparable<T>> void registerBlockWithVariants(Block block, IProperty<T> property, final String baseName) {
        for (T value : property.getAllowedValues()) {
            int meta = block.getMetaFromState(block.getDefaultState().withProperty(property, value));
            String id = property.getName(value) + "_" + baseName;
            registerBlock(block, meta, id);
        }

        String blockName = block.getRegistryName().getResourcePath();
        if (!blockName.equals(baseName)) {
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + baseName, getPropertyString(state.getProperties()));
                }
            });
        }
    }
}
