package alfheimrsmoons.world.gen.feature.layer;

import alfheimrsmoons.init.AMBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
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
            ImmutableList<BiomeEntry> list = AMBiomes.getBiomes(type);

            if (list == null)
            {
                list = ImmutableList.of();
            }

            biomes.put(type, list);
        }
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] ints = parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] intCache = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int k = ints[j + i * areaWidth];
                int l = (k & 3840) >> 8;
                k = k & -3841;

                if (isBiomeOceanic(k))
                {
                    intCache[j + i * areaWidth] = k;
                }
                else
                {
                    BiomeType type = null;

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
                        case 4:
                            type = BiomeType.ICY;
                            break;
                    }

                    if (type != null)
                    {
                        intCache[j + i * areaWidth] = BiomeGenBase.getIdForBiome(getWeightedBiomeEntry(type).biome);
                    }
                }
            }
        }

        return intCache;
    }

    protected BiomeEntry getWeightedBiomeEntry(BiomeType type)
    {
        List<BiomeEntry> biomeList = biomes.get(type);
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = BiomeManager.isTypeListModded(type) ? nextInt(totalWeight) : nextInt(totalWeight / 10) * 10;
        return WeightedRandom.getRandomItem(biomeList, weight);
    }
}
