package alfheimrsmoons.network;

import alfheimrsmoons.item.ItemVariantBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Proxy {
    public void preInit() {}
    public void init() {}
    public void postInit() {}

    public <T extends Comparable<T> & IStringSerializable> void registerBlockWithVariants(Block block, T[] variants) {
        registerBlockWithVariants(block, new ItemVariantBlock(block, variants), variants);
    }

    public <T extends Comparable<T> & IStringSerializable> void registerBlockWithVariants(Block block, Item item, T[] variants) {
        registerBlockWithVariants(block, item, variants, null);
    }

    public <T extends Comparable<T> & IStringSerializable> void registerBlockWithVariants(Block block, T[] variants, String base) {
        registerBlockWithVariants(block, new ItemVariantBlock(block, variants), variants, base);
    }

    public <T extends Comparable<T> & IStringSerializable> void registerBlockWithVariants(Block block, Item item, T[] variants, String base) {
        GameRegistry.register(block);
        registerItemWithVariants(item.setRegistryName(block.getRegistryName()), variants, base);
    }

    public void registerBlockWithItem(Block block) {
        registerBlockWithItem(block, new ItemBlock(block));
    }

    public void registerBlockWithItem(Block block, Item item) {
        registerBlock(block);
        registerItem(item.setRegistryName(block.getRegistryName()));
    }

    public void registerBlock(Block block) {
        GameRegistry.register(block);
    }

    public <T extends Comparable<T> & IStringSerializable> void registerItemWithVariants(Item item, T[] variants) {
        registerItemWithVariants(item, variants, null);
    }

    public <T extends Comparable<T> & IStringSerializable> void registerItemWithVariants(Item item, T[] variants, String base) {
        GameRegistry.register(item);
    }

    public void registerItem(Item item) {
        GameRegistry.register(item);
    }
}
