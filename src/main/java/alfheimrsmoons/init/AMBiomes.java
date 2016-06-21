package alfheimrsmoons.init;

import alfheimrsmoons.world.biome.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class AMBiomes
{
    private static final Map<BiomeGenBase, BiomeGenBase> BIOMES_HILLS;

    public static final BiomeGenBase OCEAN = new BiomeGenAMOcean(new BiomeProperties("Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F)).setRegistryName("ocean");
    public static final BiomeGenBase DEEP_OCEAN = new BiomeGenAMOcean(new BiomeProperties("Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F)).setRegistryName("deep_ocean");
    public static final BiomeGenBase MEADOW = new BiomeGenMeadow(new BiomeProperties("Meadow").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("meadow");
    public static final BiomeGenBase RIVER = new BiomeGenAMRiver(new BiomeProperties("River").setBaseHeight(-0.5F).setHeightVariation(0.0F)).setRegistryName("river");
    public static final BiomeGenBase BEACH = new BiomeGenAMBeach(new BiomeProperties("Beach").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("beach");
    public static final BiomeGenBase WOODS = new BiomeGenWoods(new BiomeProperties("Woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods");
    public static final BiomeGenBase WOODS_HILLS = new BiomeGenWoods(new BiomeProperties("Woods Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods_hills");
    public static final BiomeGenBase BLUEBELL_FOREST = new BiomeGenBluebellForest(new BiomeProperties("Bluebell Forest").setBaseBiome("woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("bluebell_forest");
    public static final BiomeGenBase RUNEWOOD_FOREST = new BiomeGenRunewoodForest(new BiomeProperties("Runewood Forest").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("runewood_forest");
    public static final BiomeGenBase VELD = new BiomeGenVeld(new BiomeProperties("Veld").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()).setRegistryName("veld");

    static
    {
        BiomeManager.oceanBiomes.add(OCEAN);
        BiomeManager.oceanBiomes.add(DEEP_OCEAN);

        BiomeManager.addSpawnBiome(MEADOW);
        BiomeManager.addSpawnBiome(WOODS);
        BiomeManager.addSpawnBiome(WOODS_HILLS);
        BiomeManager.addSpawnBiome(BLUEBELL_FOREST);

        BIOMES_HILLS = new HashMap<BiomeGenBase, BiomeGenBase>();
        BIOMES_HILLS.put(OCEAN, DEEP_OCEAN);
        BIOMES_HILLS.put(WOODS, WOODS_HILLS);
    }

    public static void registerBiomes()
    {
        registerBiome(OCEAN, BiomeDictionary.Type.OCEAN);
        registerBiome(DEEP_OCEAN, BiomeDictionary.Type.OCEAN);
        registerBiome(MEADOW, PLAINS);
        registerBiome(MEADOW, BiomeDictionary.Type.OCEAN);
        registerBiome(RIVER, BiomeDictionary.Type.RIVER);
        registerBiome(BEACH, BiomeDictionary.Type.BEACH);
        registerBiome(WOODS, FOREST);
        registerBiome(WOODS_HILLS, FOREST, HILLS);
        registerBiome(BLUEBELL_FOREST, FOREST);
        registerBiome(RUNEWOOD_FOREST, FOREST);
        registerBiome(VELD, HOT, DRY, SANDY);
    }

    private static void registerBiome(BiomeGenBase biome, BiomeDictionary.Type... dictTypes)
    {
        GameRegistry.register(biome);
        BiomeDictionary.registerBiomeType(biome, dictTypes);
    }

    public static BiomeGenBase getHills(BiomeGenBase biome)
    {
        return BIOMES_HILLS.get(biome);
    }
}
