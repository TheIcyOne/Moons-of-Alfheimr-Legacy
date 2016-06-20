package alfheimrsmoons.init;

import alfheimrsmoons.world.biome.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class AMBiomes
{
    private static final Map<BiomeType, List<BiomeEntry>> BIOMES;
    private static final Map<BiomeGenBase, BiomeGenBase> BIOMES_HILLS;

    public static final BiomeGenBase ocean = new BiomeGenAMOcean(new BiomeProperties("Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F)).setRegistryName("ocean");
    public static final BiomeGenBase deep_ocean = new BiomeGenAMOcean(new BiomeProperties("Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F)).setRegistryName("deep_ocean");
    public static final BiomeGenBase river = new BiomeGenAMRiver(new BiomeProperties("River").setBaseHeight(-0.5F).setHeightVariation(0.0F)).setRegistryName("river");
    public static final BiomeGenBase beach = new BiomeGenAMBeach(new BiomeProperties("Beach").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("beach");
    public static final BiomeGenBase woods = new BiomeGenWoods(new BiomeProperties("Woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods");
    public static final BiomeGenBase woods_hills = new BiomeGenWoods(new BiomeProperties("Woods Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods_hills");
    public static final BiomeGenBase bluebell_forest = new BiomeGenBluebellForest(new BiomeProperties("Bluebell Forest").setBaseBiome("woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("bluebell_forest");
    public static final BiomeGenBase runewood_forest = new BiomeGenRunewoodForest(new BiomeProperties("Runewood Forest").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("runewood_forest");
    public static final BiomeGenBase veld = new BiomeGenVeld(new BiomeProperties("Veld").setBaseHeight(0.125F).setHeightVariation(0.0F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()).setRegistryName("veld");

    static
    {
        BiomeManager.oceanBiomes.add(ocean);
        BiomeManager.oceanBiomes.add(deep_ocean);

        BiomeManager.addSpawnBiome(woods);
        BiomeManager.addSpawnBiome(woods_hills);
        BiomeManager.addSpawnBiome(bluebell_forest);

        BIOMES_HILLS = new HashMap<BiomeGenBase, BiomeGenBase>();
        BIOMES_HILLS.put(ocean, deep_ocean);
        BIOMES_HILLS.put(woods, woods_hills);

        BIOMES = setupBiomes();
    }

    public static void registerBiomes()
    {
        registerBiome(ocean, OCEAN);
        registerBiome(deep_ocean, OCEAN);
        registerBiome(river, RIVER);
        registerBiome(beach, BEACH);
        registerBiome(woods, FOREST);
        registerBiome(woods_hills, FOREST, HILLS);
        registerBiome(bluebell_forest, FOREST);
        registerBiome(runewood_forest, FOREST);
        registerBiome(veld, HOT, DRY, SANDY);
    }

    private static void registerBiome(BiomeGenBase biome, BiomeDictionary.Type... dictTypes)
    {
        GameRegistry.register(biome);
        BiomeDictionary.registerBiomeType(biome, dictTypes);
    }

    private static HashMap<BiomeType, List<BiomeEntry>> setupBiomes()
    {
        HashMap<BiomeType, List<BiomeEntry>> biomes = new HashMap<BiomeType, List<BiomeEntry>>();

        List<BiomeEntry> list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(woods, 10));
        list.add(new BiomeEntry(runewood_forest, 10));//TODO generate only at spawn
        biomes.put(BiomeType.WARM, list);

        list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(woods, 10));
        biomes.put(BiomeType.COOL, list);

        list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(woods, 10));//TODO delete placeholder
        biomes.put(BiomeType.ICY, list);

        list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(veld, 30));
        biomes.put(BiomeType.DESERT, list);

        return biomes;
    }

    public static List<BiomeEntry> getBiomes(BiomeType type)
    {
        return BIOMES.get(type);
    }

    public static BiomeGenBase getHills(BiomeGenBase biome)
    {
        return BIOMES_HILLS.get(biome);
    }
}
