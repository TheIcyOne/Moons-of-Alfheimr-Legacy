package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.client.renderer.ColorGrass;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.network.Proxy;
import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ProxyClient extends Proxy
{
    public static Minecraft getMinecraft()
    {
        return FMLClientHandler.instance().getClient();
    }

    public static RenderItem getRenderItem()
    {
        return getMinecraft().getRenderItem();
    }

    @Override
    public void preInit()
    {
        RenderFactory.registerRenders();

        ModelLoader.setCustomStateMapper(AMBlocks.log2, new CustomStateMapper("log"));
        ModelLoader.setCustomStateMapper(AMBlocks.leaves, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(AMBlocks.leaves2, new CustomStateMapper("leaves", BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE));
    }

    @Override
    public void init()
    {
        registerItemBlockColor(new ColorGrass(), AMBlocks.grassy_soil, AMBlocks.sedge);
    }

    @Override
    public <V extends IVariant<V>, I extends Item & IVariantObject<V>> void registerItemWithVariants(I item, String base)
    {
        super.registerItemWithVariants(item, base);
        registerItemWithVariants(item, item.getVariants(), base);
    }

    public <V extends IVariant<V>> void registerItemWithVariants(Item item, V[] variants, String base)
    {
        String prefix = "";
        String suffix = "";
        
        if (base != null)
        {
            if (base.endsWith("_"))
            {
                prefix = base;
                base = base.substring(0, base.length() - 1);
            }
            else if (base.startsWith("_"))
            {
                suffix = base;
                base = base.substring(1);
            }
            else
            {
                AlfheimrsMoons.logger.error("Registered variants for %s with unknown affix %s", item.getRegistryName(), base);
            }
        }
        
        registerItemWithVariants(item, variants, base, prefix, suffix);
    }

    public <V extends IVariant<V>> void registerItemWithVariants(Item item, V[] variants, String base, String prefix, String suffix)
    {
        ResourceLocation[] variantNames = new ResourceLocation[variants.length];

        for (int meta = 0; meta < variants.length; meta++)
        {
            V variant = variants[meta];
            String name = variant.getName();
            String id;

            if (name.equals("normal"))
            {
                id = base;
            }
            else
            {
                id = prefix + name + suffix;
            }

            registerItem(item, meta, id);

            variantNames[meta] = new ResourceLocation(AlfheimrsMoons.MOD_ID, id);
        }

        ModelLoader.registerItemVariants(item, variantNames);
    }

    @Override
    public void registerItem(Item item)
    {
        super.registerItem(item);
        registerItem(item, item.getRegistryName().getResourcePath());
    }

    public void registerItem(Item item, String identifier)
    {
        registerItem(item, 0, identifier);
    }

    public void registerItem(Item item, int metadata, String identifier)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + identifier, "inventory"));
    }

    private <C extends IBlockColor & IItemColor> void registerItemBlockColor(C color, Block... blocks)
    {
        getMinecraft().getBlockColors().registerBlockColorHandler(color, blocks);
        getMinecraft().getItemColors().registerItemColorHandler(color, blocks);
    }

    private void registerBlockColor(IBlockColor color, Block... blocks)
    {
        getMinecraft().getBlockColors().registerBlockColorHandler(color, blocks);
    }

    private void registerItemColor(IItemColor color, Item... items)
    {
        getMinecraft().getItemColors().registerItemColorHandler(color, items);
    }
}
