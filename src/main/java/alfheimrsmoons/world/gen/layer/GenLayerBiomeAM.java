package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.world.biome.BiomeProviderAM;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.List;

public class GenLayerBiomeAM extends GenLayer
{
    public GenLayerBiomeAM(long baseSeed, GenLayer parentLayer)
    {
        super(baseSeed);
        parent = parentLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] inputBiomeIDs = parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] outputBiomeIDs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int biomeID = inputBiomeIDs[j + i * areaWidth];
                int l = (biomeID & 3840) >> 8;
                biomeID &= -3841;

                if (isBiomeOceanic(biomeID))
                {
                    outputBiomeIDs[j + i * areaWidth] = biomeID;
                }
                else
                {
                    BiomeType biomeType = BiomeProviderAM.getBiomeTypeByID(biomeID);

                    if (biomeType != null)
                    {
                        outputBiomeIDs[j + i * areaWidth] = Biome.getIdForBiome(getWeightedBiomeEntry(biomeType).biome);
                    }
                }
            }
        }

        return outputBiomeIDs;
    }

    private BiomeEntry getWeightedBiomeEntry(BiomeType type)
    {
        List<BiomeEntry> biomeList = BiomeProviderAM.getBiomes(type);
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = nextInt(totalWeight);
        return WeightedRandom.getRandomItem(biomeList, weight);
    }
}
