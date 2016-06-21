package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.world.biome.AMBiomeManager;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenLayerAMBiome extends GenLayer
{
    private Map<BiomeType, List<BiomeEntry>> biomes = new HashMap<BiomeType, List<BiomeEntry>>();

    public GenLayerAMBiome(long baseSeed, GenLayer parentLayer)
    {
        super(baseSeed);
        parent = parentLayer;

        for (BiomeType type : BiomeType.values())
        {
            biomes.put(type, AMBiomeManager.getBiomes(type));
        }
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int k = aint[j + i * areaWidth];
                int l = (k & 3840) >> 8;
                k = k & -3841;

                if (isBiomeOceanic(k))
                {
                    aint1[j + i * areaWidth] = k;
                }
                else
                {
                    BiomeType type = null;
                    BiomeGenBase biome = null;

                    switch (k)
                    {
                        case 1:
                            type = BiomeType.DESERT;
                            break;
                        case 2:
                            type = BiomeType.WARM;
                            break;
                        case 3:
                            type = BiomeType.COOL;
                            break;
                    }

                    if (type != null)
                    {
                        if (biome == null)
                        {
                            biome = getWeightedBiomeEntry(type).biome;
                        }

                        aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(biome);
                    }
                }
            }
        }

        return aint1;
    }

    protected BiomeEntry getWeightedBiomeEntry(BiomeType type)
    {
        List<BiomeEntry> biomeList = biomes.get(type);
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = nextInt(totalWeight);
        return WeightedRandom.getRandomItem(biomeList, weight);
    }
}
