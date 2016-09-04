package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdgeAM extends GenLayerBiomeEdge
{
    public GenLayerBiomeEdgeAM(long seed, GenLayer parent)
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
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];

                if (k == Biome.getIdForBiome(AMBiomes.FLOODED_FOREST))
                {
                    int l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (l != Biome.getIdForBiome(AMBiomes.VELD) && i1 != Biome.getIdForBiome(AMBiomes.VELD) && j1 != Biome.getIdForBiome(AMBiomes.VELD) && k1 != Biome.getIdForBiome(AMBiomes.VELD))
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = Biome.getIdForBiome(AMBiomes.MEADOW);
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = k;
                }
            }
        }

        return aint1;
    }
}
