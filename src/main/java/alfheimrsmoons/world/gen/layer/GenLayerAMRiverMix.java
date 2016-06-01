package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAMRiverMix extends GenLayerRiverMix
{
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;

    public GenLayerAMRiverMix(long seed, GenLayer biomePattern, GenLayer riverPattern)
    {
        super(seed, biomePattern, riverPattern);
        biomePatternGeneratorChain = biomePattern;
        riverPatternGeneratorChain = riverPattern;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (!isBiomeOceanic(aint[i]))
            {
                if (aint1[i] == BiomeGenBase.getIdForBiome(AMBiomes.river))
                {
                    aint2[i] = aint1[i] & 255;
                }
                else
                {
                    aint2[i] = aint[i];
                }
            }
            else
            {
                aint2[i] = aint[i];
            }
        }

        return aint2;
    }
}
