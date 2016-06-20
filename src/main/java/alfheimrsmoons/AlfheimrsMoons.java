package alfheimrsmoons;

import alfheimrsmoons.init.*;
import alfheimrsmoons.network.Proxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AlfheimrsMoons.MOD_ID, name = AlfheimrsMoons.MOD_NAME, version = AlfheimrsMoons.MOD_VERSION)
public class AlfheimrsMoons
{
    public static final String MOD_ID = "alfheimrsmoons";
    public static final String MOD_NAME = "Álfheimr's Moons";
    public static final String MOD_VERSION = "@VERSION@";
    
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(AMBlocks.YGGDRASIL_LEAVES);
        }
    };

    @Instance(MOD_ID)
    public static AlfheimrsMoons instance;
    @SidedProxy(clientSide = "alfheimrsmoons.client.ProxyClient", serverSide = "alfheimrsmoons.network.ProxyServer")
    public static Proxy proxy;
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        AMConfig.load(event.getSuggestedConfigurationFile());
        AMBlocks.registerBlocks();
        AMItems.registerItems();
        AMRecipes.addRecipes();
        AMEntities.registerEntities();
        AMBiomes.registerBiomes();
        AMDimensions.registerDimensions();
        MinecraftForge.EVENT_BUS.register(new AMEventHandler());
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}
