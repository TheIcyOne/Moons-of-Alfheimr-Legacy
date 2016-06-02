package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.network.Proxy;
import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ProxyClient extends Proxy
{
    @Override
    public void preInit()
    {
        RenderFactory.registerRenders();

        ModelLoader.setCustomStateMapper(AMBlocks.log2, new CustomStateMapper("log"));
        ModelLoader.setCustomStateMapper(AMBlocks.leaves, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(AMBlocks.leaves2, new CustomStateMapper("leaves", BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE));
    }

    @Override
    public <V extends IVariant<V>, I extends Item & IVariantObject<V>> void registerItemWithVariants(I item, String base)
    {
        super.registerItemWithVariants(item, base);
        String suffix = base != null ? "_" + base : "";
        V[] variants = item.getVariants();
        ResourceLocation[] variantNames = new ResourceLocation[variants.length];
        for (int meta = 0; meta < variants.length; meta++)
        {
            V variant = variants[meta];
            String name = variant.getName();
            String id = !name.equals("normal") ? name + suffix : base;
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

    public static RenderItem getRenderItem()
    {
        return FMLClientHandler.instance().getClient().getRenderItem();
    }
}
