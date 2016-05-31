package alfheimrsmoons;

import alfheimrsmoons.init.*;
import alfheimrsmoons.network.Proxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AlfheimrsMoons.MOD_ID, name = AlfheimrsMoons.MOD_NAME, version = AlfheimrsMoons.MOD_VERSION)
public class AlfheimrsMoons
{
    public static final String MOD_ID = "alfheimrsmoons";
    public static final String MOD_NAME = "Álfheimr's Moons";
    public static final String MOD_VERSION = "@VERSION@";

    @Instance(MOD_ID)
    public static AlfheimrsMoons instance;
    @SidedProxy(clientSide = "alfheimrsmoons.client.ProxyClient", serverSide = "alfheimrsmoons.network.ProxyServer")
    public static Proxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
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
