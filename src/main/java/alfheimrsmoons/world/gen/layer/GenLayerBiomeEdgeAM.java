package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdgeAM extends GenLayer
{
    public GenLayerBiomeEdgeAM(long seed, GenLayer parent)
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

                if (biomeID == Biome.getIdForBiome(AMBiomes.FLOODED_FOREST))
                {
                    int biomeID1 = inputBiomeIDs[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int biomeID2 = inputBiomeIDs[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int biomeID3 = inputBiomeIDs[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int biomeID4 = inputBiomeIDs[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                    int desertID = Biome.getIdForBiome(AMBiomes.VELD);

                    if (biomeID1 != desertID && biomeID2 != desertID && biomeID3 != desertID && biomeID4 != desertID)
                    {
                        outputBiomeIDs[j + i * areaWidth] = biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j + i * areaWidth] = Biome.getIdForBiome(AMBiomes.MEADOW);
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
