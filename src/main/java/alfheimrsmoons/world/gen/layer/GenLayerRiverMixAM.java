package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverMixAM extends GenLayer
{
    private final GenLayer biomePatternGeneratorChain;
    private final GenLayer riverPatternGeneratorChain;

    public GenLayerRiverMixAM(long seed, GenLayer biomePattern, GenLayer riverPattern)
    {
        super(seed);
        biomePatternGeneratorChain = biomePattern;
        riverPatternGeneratorChain = riverPattern;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] inputBiomeIDs = biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] inputRiverIDs = riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] outputBiomeIDs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (!isBiomeOceanic(inputBiomeIDs[i]))
            {
                if (inputRiverIDs[i] == Biome.getIdForBiome(AMBiomes.RIVER))
                {
                    outputBiomeIDs[i] = inputRiverIDs[i] & 255;
                }
                else
                {
                    outputBiomeIDs[i] = inputBiomeIDs[i];
                }
            }
            else
            {
                outputBiomeIDs[i] = inputBiomeIDs[i];
            }
        }

        return outputBiomeIDs;
    }
}
