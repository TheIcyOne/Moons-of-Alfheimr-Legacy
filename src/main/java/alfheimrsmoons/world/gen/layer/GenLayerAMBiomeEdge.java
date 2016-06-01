package alfheimrsmoons.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAMBiomeEdge extends GenLayerBiomeEdge
{
    public GenLayerAMBiomeEdge(long seed, GenLayer parent)
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
                aint1[j + i * areaWidth] = k;
            }
        }

        return aint1;
    }
}
