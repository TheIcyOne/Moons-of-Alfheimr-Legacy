package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAMShore extends GenLayerShore
{
    public GenLayerAMShore(long seed, GenLayer parent)
    {
        super(seed, parent);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int biomeID = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                Biome biome = Biome.getBiome(biomeID);

                if (!isBiomeOceanic(biomeID) && biome != AMBiomes.RIVER && biome != AMBiomes.FLOODED_FOREST)
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (!isBiomeOceanic(l1) && !isBiomeOceanic(k2) && !isBiomeOceanic(j3) && !isBiomeOceanic(i4))
                    {
                        aint1[j + i * areaWidth] = biomeID;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = Biome.getIdForBiome(AMBiomes.BEACH);
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = biomeID;
                }
            }
        }

        return aint1;
    }
}
