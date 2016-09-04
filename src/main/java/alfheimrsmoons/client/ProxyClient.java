package alfheimrsmoons.client;

import alfheimrsmoons.client.renderer.ColorGrass;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.network.Proxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import zaggy1024.util.ModelUtils;

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
    public Side getSide()
    {
        return Side.CLIENT;
    }

    @Override
    public void preInit()
    {
        RenderFactory.registerRenders();
    }

    @Override
    public void init()
    {
        ColorGrass grassColor = new ColorGrass();
        registerItemBlockColor(grassColor, AMBlocks.GRASSY_SOIL);

        for (Block block : AMBlocks.SEDGES.getBlocks())
        {
            registerItemBlockColor(grassColor, block);
        }
    }

    @Override
    public void registerItem(Item item, boolean doModel)
    {
        super.registerItem(item, doModel);

        if (doModel)
        {
            ModelUtils.registerModel(item, item.getRegistryName());
        }
    }

    private static <C extends IBlockColor & IItemColor> void registerItemBlockColor(C color, Block... blocks)
    {
        getMinecraft().getBlockColors().registerBlockColorHandler(color, blocks);
        getMinecraft().getItemColors().registerItemColorHandler(color, blocks);
    }

    private static void registerBlockColor(IBlockColor color, Block... blocks)
    {
        getMinecraft().getBlockColors().registerBlockColorHandler(color, blocks);
    }

    private static void registerItemColor(IItemColor color, Item... items)
    {
        getMinecraft().getItemColors().registerItemColorHandler(color, items);
    }
}
