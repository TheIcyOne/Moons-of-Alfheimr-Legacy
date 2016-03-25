package alfheimrsmoons.common.block;

import alfheimrsmoons.common.fluid.FluidTest;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModBlocks {
    public static BlockSoil amSoil;
    public static BlockGrass amGrass;
    public static BlockSedimentGlass sedimentGlass;
    public static BlockSapling amSapling;
    public static BlockLog amLog;
    public static BlockPlanks amPlanks;
    public static BlockLeaves amLeaves;
    public static BlockNitroTorch nitroTorch;
    public static BlockCrystalOre crystalOre;
    public static BlockStorage storage;
    public static BlockFluidTest fluidTest;

    public static void load(FMLPreInitializationEvent event) {
        amSoil = new BlockSoil();
        amGrass = new BlockGrass();
        sedimentGlass = new BlockSedimentGlass();
        amSapling = new BlockSapling();
        amLog = new BlockLog();
        amPlanks = new BlockPlanks();
        amLeaves = new BlockLeaves();
        nitroTorch = new BlockNitroTorch();
        crystalOre = new BlockCrystalOre();
        storage = new BlockStorage();
        fluidTest = new BlockFluidTest(FluidTest.instance, Material.water);
    }
}
