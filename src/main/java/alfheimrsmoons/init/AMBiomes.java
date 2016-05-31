package alfheimrsmoons.init;

import alfheimrsmoons.world.biome.BiomeGenWoods;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AMBiomes
{
    private static final Map<BiomeType, List<BiomeEntry>> biomes = Maps.newHashMap();

    public static final BiomeGenBase woods = new BiomeGenWoods(new BiomeGenBase.BiomeProperties("Woods").setTemperature(0.7F).setRainfall(0.8F)).setRegistryName("woods");

    public static void registerBiomes()
    {
        BiomeType[] types = {BiomeType.COOL, BiomeType.WARM /* Delete: */ , BiomeType.DESERT, BiomeType.ICY};
        int[] weights = {10, 10 /* Delete: */ , 10, 10};
        registerBiome(woods, types, weights);
    }

    private static void registerBiome(BiomeGenBase biome)
    {
        GameRegistry.register(biome);
    }

    private static void registerBiome(BiomeGenBase biome, BiomeType type, int weight)
    {
        registerBiome(biome);
        addBiome(type, new BiomeEntry(biome, weight));
    }

    private static void registerBiome(BiomeGenBase biome, BiomeType[] types, int[] weights)
    {
        registerBiome(biome);

        for (int i = 0; i < types.length && i < weights.length; i++)
        {
            addBiome(types[i], new BiomeEntry(biome, weights[i]));
        }
    }

    public static void addBiome(BiomeType type, BiomeEntry entry)
    {
        List<BiomeEntry> list = biomes.get(type);

        if (list == null)
        {
            list = new ArrayList<BiomeEntry>();
            biomes.put(type, list);
        }

        list.add(entry);
    }

    public static ImmutableList<BiomeEntry> getBiomes(BiomeType type)
    {
        List<BiomeEntry> list = biomes.get(type);
        return list != null ? ImmutableList.copyOf(list) : null;
    }
}
