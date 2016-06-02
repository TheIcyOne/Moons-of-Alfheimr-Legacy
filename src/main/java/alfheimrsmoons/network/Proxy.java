package alfheimrsmoons.network;

import alfheimrsmoons.item.ItemVariantBlock;
import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Proxy
{
    public void preInit() {}

    public void init() {}

    public void postInit() {}

    public <V extends IVariant<V>, B extends Block & IVariantBlock<V>> void registerBlockWithVariants(B block)
    {
        registerBlockWithVariants(block, new ItemVariantBlock<V, B>(block));
    }

    public <V extends IVariant<V>, B extends Block & IVariantBlock<V>, I extends Item & IVariantObject<V>> void registerBlockWithVariants(B block, I item)
    {
        registerBlockWithVariants(block, item, null);
    }

    public <V extends IVariant<V>, B extends Block & IVariantBlock<V>> void registerBlockWithVariants(B block, String base)
    {
        registerBlockWithVariants(block, new ItemVariantBlock<V, B>(block), base);
    }

    public <V extends IVariant<V>, B extends Block & IVariantBlock<V>, I extends Item & IVariantObject<V>> void registerBlockWithVariants(B block, I item, String base)
    {
        GameRegistry.register(block);
        item.setRegistryName(block.getRegistryName());
        registerItemWithVariants(item, base);
    }

    public void registerBlockWithItem(Block block)
    {
        registerBlockWithItem(block, new ItemBlock(block));
    }

    public void registerBlockWithItem(Block block, Item item)
    {
        registerBlock(block);
        registerItem(item.setRegistryName(block.getRegistryName()));
    }

    public void registerBlock(Block block)
    {
        GameRegistry.register(block);
    }

    public <V extends IVariant<V>, I extends Item & IVariantObject<V>> void registerItemWithVariants(I item)
    {
        registerItemWithVariants(item, null);
    }

    public <V extends IVariant<V>, I extends Item & IVariantObject<V>> void registerItemWithVariants(I item, String base)
    {
        GameRegistry.register(item);
    }

    public void registerItem(Item item)
    {
        GameRegistry.register(item);
    }
}
