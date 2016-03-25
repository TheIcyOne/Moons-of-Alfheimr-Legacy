package alfheimrsmoons;

import alfheimrsmoons.common.AMAchievementPage;
import alfheimrsmoons.common.CommonProxy;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.command.CommandDimension;
import alfheimrsmoons.common.handler.BucketEventHandler;
import alfheimrsmoons.common.item.ModItems;
import alfheimrsmoons.common.recipes.ModRecipes;
import alfheimrsmoons.lib.AMCreativeTab;
import alfheimrsmoons.lib.AMFuelHandler;
import alfheimrsmoons.world.gen.WorldGenAMOres;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(name = AlfheimrsMoons.NAME, modid = AlfheimrsMoons.MODID, version = AlfheimrsMoons.VERSION)
public class AlfheimrsMoons {
    public static final String NAME = "Álfheimr's Moons";
    public static final String MODID = "alfheimrsmoons";
    public static final String VERSION = "0.1";
    public static final int DIM_ID = 42;

    @Instance(value = AlfheimrsMoons.MODID)
    public static AlfheimrsMoons instance;

    @SidedProxy(clientSide = "alfheimrsmoons.client.ClientProxy", serverSide = "alfheimrsmoons.common.CommonProxy")
    public static CommonProxy proxy;

    public AMCreativeTab creativeTab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        creativeTab = new AMCreativeTab();

        ModBlocks.load(event);
        ModItems.load(event);

        MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
        // GameRegistry.registerWorldGenerator(new
        // WorldGenAMTrees(ModBlocks.amLog, ModBlocks.amLeaves, 1, 1, false, 5,
        // false), 2);
        GameRegistry.registerWorldGenerator(new WorldGenAMOres(), 2);

        AchievementPage.registerAchievementPage(new AMAchievementPage());

        // TODO move ModBlocks.load and ModItems.load into proxy
        AlfheimrsMoons.proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerFuelHandler(new AMFuelHandler());
        ModRecipes.register();
        AlfheimrsMoons.proxy.registerModels();

        AlfheimrsMoons.proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        AlfheimrsMoons.proxy.postInit();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDimension());
    }
}
