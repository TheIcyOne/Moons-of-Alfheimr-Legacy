package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShoreAM extends GenLayer
{
    public GenLayerShoreAM(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] inputBiomeIDs = parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] outputBiomeIDs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int biomeID = inputBiomeIDs[j + 1 + (i + 1) * (areaWidth + 2)];
                Biome biome = Biome.getBiome(biomeID);

                if (!isBiomeOceanic(biomeID) && biome != AMBiomes.RIVER && biome != AMBiomes.FLOODED_FOREST)
                {
                    int biomeID1 = inputBiomeIDs[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int biomeID2 = inputBiomeIDs[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int biomeID3 = inputBiomeIDs[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int biomeID4 = inputBiomeIDs[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (!isBiomeOceanic(biomeID1) && !isBiomeOceanic(biomeID2) && !isBiomeOceanic(biomeID3) && !isBiomeOceanic(biomeID4))
                    {
                        outputBiomeIDs[j + i * areaWidth] = biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j + i * areaWidth] = Biome.getIdForBiome(AMBiomes.BEACH);
                    }
                }
                else
                {
                    outputBiomeIDs[j + i * areaWidth] = biomeID;
                }
            }
        }

        return outputBiomeIDs;
    }
}
