package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.*;

public class ItemModels {
    public static void registerModels() {
        registerBlock(AMBlocks.soil);
        registerBlock(AMBlocks.grassy_soil);
        registerBlock(AMBlocks.sediment);
        registerBlock(AMBlocks.shale);
        registerBlockWithVariants(AMBlocks.ore, BlockAMOre.EnumType.values, "ore");
        registerBlockWithVariants(AMBlocks.log, AMBlocks.log.types, "log");
        registerBlockWithVariants(AMBlocks.log2, AMBlocks.log2.types, "log");
        registerBlockWithVariants(AMBlocks.leaves, AMBlocks.leaves.types, "leaves", BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE);
        registerBlockWithVariants(AMBlocks.leaves2, AMBlocks.leaves2.types, "leaves", BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE);
        registerBlockWithVariants(AMBlocks.planks, BlockAMPlanks.EnumType.values, "planks");
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

    private static <T extends Comparable<T> & IStringSerializable> void registerBlockWithVariants(Block block, T[] variants, final String name, final IProperty... ignored) {
        ResourceLocation[] variantNames = new ResourceLocation[variants.length];
        for (int meta = 0; meta < variants.length; meta++) {
            T variant = variants[meta];
            String id = variant.getName() + "_" + name;
            registerBlock(block, meta, id);
            variantNames[meta] = new ResourceLocation(AlfheimrsMoons.MOD_ID, id);
        }
        ModelLoader.registerItemVariants(Item.getItemFromBlock(block), variantNames);

        if (!name.equals(block.delegate.name().getResourcePath())) {
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    Map<IProperty<?>, Comparable<?>> properties = new HashMap<IProperty<?>, Comparable<?>>(state.getProperties());
                    for (IProperty<?> property : ignored) {
                        properties.remove(property);
                    }
                    return new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + name, getPropertyString(properties));
                }
            });
        } else if (ignored.length > 0) {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(ignored).build());
        }
    }
}
