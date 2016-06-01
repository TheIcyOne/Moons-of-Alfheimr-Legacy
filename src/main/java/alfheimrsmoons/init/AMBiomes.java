package alfheimrsmoons.init;

import alfheimrsmoons.world.biome.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class AMBiomes
{
    private static final Map<BiomeType, List<BiomeEntry>> BIOMES = Maps.newHashMap();
    private static final Map<BiomeGenBase, BiomeGenBase> BIOMES_HILLS = Maps.newHashMap();

    public static final BiomeGenBase ocean = new BiomeGenAMOcean(new BiomeProperties("Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F)).setRegistryName("ocean");
    public static final BiomeGenBase deep_ocean = new BiomeGenAMOcean(new BiomeProperties("Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F)).setRegistryName("deep_ocean");
    public static final BiomeGenBase woods = new BiomeGenWoods(new BiomeProperties("Woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods");
    public static final BiomeGenBase woods_hills = new BiomeGenWoods(new BiomeProperties("Woods Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods_hills");
    public static final BiomeGenBase river = new BiomeGenAMRiver(new BiomeProperties("River").setBaseHeight(-0.5F).setHeightVariation(0.0F)).setRegistryName("river");
    public static final BiomeGenBase beach = new BiomeGenAMBeach(new BiomeProperties("Beach").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)).setRegistryName("beach");

    public static void registerBiomes()
    {
        registerBiome(ocean, OCEAN);
        registerBiome(deep_ocean, OCEAN);
        BiomeType[] types = {BiomeType.COOL, BiomeType.WARM /* Delete: */, BiomeType.DESERT, BiomeType.ICY};
        int[] weights = {10, 10 /* Delete: */, 10, 10};
        registerBiome(woods, types, weights, FOREST);
        registerBiome(woods_hills, FOREST, HILLS);
        registerBiome(river, RIVER);
        registerBiome(beach, BEACH);

        BiomeManager.oceanBiomes.add(ocean);
        BiomeManager.oceanBiomes.add(deep_ocean);

        BiomeManager.addSpawnBiome(woods);
        BiomeManager.addSpawnBiome(woods_hills);

        BIOMES_HILLS.put(ocean, deep_ocean);
        BIOMES_HILLS.put(woods, woods_hills);
    }

    private static void registerBiome(BiomeGenBase biome, BiomeDictionary.Type... dictTypes)
    {
        GameRegistry.register(biome);
        BiomeDictionary.registerBiomeType(biome, dictTypes);
    }

    private static void registerBiome(BiomeGenBase biome, BiomeType type, int weight, BiomeDictionary.Type... dictTypes)
    {
        registerBiome(biome, dictTypes);
        addBiome(type, new BiomeEntry(biome, weight));
    }

    private static void registerBiome(BiomeGenBase biome, BiomeType[] types, int[] weights, BiomeDictionary.Type... dictTypes)
    {
        registerBiome(biome, dictTypes);

        for (int i = 0; i < types.length && i < weights.length; i++)
        {
            addBiome(types[i], new BiomeEntry(biome, weights[i]));
        }
    }

    public static void addBiome(BiomeType type, BiomeEntry entry)
    {
        BiomeManager.addBiome(type, entry);

        List<BiomeEntry> list = BIOMES.get(type);

        if (list == null)
        {
            list = new ArrayList<BiomeEntry>();
            BIOMES.put(type, list);
        }

        list.add(entry);
    }

    public static ImmutableList<BiomeEntry> getBiomes(BiomeType type)
    {
        List<BiomeEntry> list = BIOMES.get(type);
        return list != null ? ImmutableList.copyOf(list) : null;
    }

    public static BiomeGenBase getHills(BiomeGenBase biome)
    {
        return BIOMES_HILLS.get(biome);
    }
}
