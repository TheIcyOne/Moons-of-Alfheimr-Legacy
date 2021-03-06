package alfheimrsmoons.init;

import alfheimrsmoons.world.biome.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class AMBiomes
{
    private static final Map<Biome, Biome> BIOMES_HILLS;

    public static final Biome LAKE = new BiomeOceanAM(new BiomeProperties("Lake").setBaseHeight(-1.0F).setHeightVariation(0.1F)).setRegistryName("lake");
    public static final Biome DEEP_LAKE = new BiomeOceanAM(new BiomeProperties("Deep Lake").setBaseHeight(-1.8F).setHeightVariation(0.1F)).setRegistryName("deep_lake");
    public static final Biome STREAM = new BiomeRiverAM(new BiomeProperties("Stream").setBaseHeight(-0.5F).setHeightVariation(0.0F)).setRegistryName("stream");
    public static final Biome BEACH = new BiomeBeachAM(new BiomeProperties("Shore").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("shore");
    public static final Biome MEADOW = new BiomeMeadow(new BiomeProperties("Meadow").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("meadow");
    public static final Biome WOODS = new BiomeWoods(new BiomeProperties("Woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods");
    public static final Biome WOODS_HILLS = new BiomeWoods(new BiomeProperties("Woods Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods_hills");
    public static final Biome BLUEBELL_FOREST = new BiomeBluebellForest(new BiomeProperties("Bluebell Forest").setBaseBiome("woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("bluebell_forest");
    public static final Biome RUNEWOOD_FOREST = new BiomeRunewoodForest(new BiomeProperties("Runewood Forest").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("runewood_forest");
    public static final Biome FLOODED_FOREST = new BiomeFloodedForest(new Biome.BiomeProperties("Flooded Forest").setBaseHeight(-0.2F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.9F)).setRegistryName("flooded_forest");
    public static final Biome VELD = new BiomeVeld(new BiomeProperties("Veld").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()).setRegistryName("veld");

    static
    {
        BiomeManager.oceanBiomes.add(LAKE);
        BiomeManager.oceanBiomes.add(DEEP_LAKE);

        BiomeManager.addSpawnBiome(MEADOW);
        BiomeManager.addSpawnBiome(WOODS);
        BiomeManager.addSpawnBiome(WOODS_HILLS);
        BiomeManager.addSpawnBiome(BLUEBELL_FOREST);

        BIOMES_HILLS = new HashMap<>();
        BIOMES_HILLS.put(LAKE, DEEP_LAKE);
        BIOMES_HILLS.put(WOODS, WOODS_HILLS);
    }

    public static void registerBiomes()
    {
        registerBiome(LAKE, BiomeDictionary.Type.OCEAN);
        registerBiome(DEEP_LAKE, BiomeDictionary.Type.OCEAN);
        registerBiome(STREAM, BiomeDictionary.Type.RIVER);
        registerBiome(BEACH, BiomeDictionary.Type.BEACH);
        registerBiome(MEADOW, PLAINS);
        registerBiome(WOODS, FOREST);
        registerBiome(WOODS_HILLS, FOREST, HILLS);
        registerBiome(BLUEBELL_FOREST, FOREST);
        registerBiome(RUNEWOOD_FOREST, FOREST);
        registerBiome(FLOODED_FOREST, WET, SWAMP);
        registerBiome(VELD, HOT, DRY, SANDY);
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... dictTypes)
    {
        GameRegistry.register(biome);
        BiomeDictionary.registerBiomeType(biome, dictTypes);
    }

    public static Biome getHills(Biome biome)
    {
        return BIOMES_HILLS.getOrDefault(biome, biome);
    }
}
