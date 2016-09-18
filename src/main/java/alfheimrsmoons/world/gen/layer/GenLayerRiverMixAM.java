package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverMixAM extends GenLayerAM
{
    private static final int MAX_BIOME_ID = 255;
    private final GenLayer biomeLayer;
    private final GenLayer riverLayer;

    public GenLayerRiverMixAM(long seed, GenLayer biomeLayer, GenLayer riverLayer)
    {
        super(seed);
        this.biomeLayer = biomeLayer;
        this.riverLayer = riverLayer;
    }

    @Override
    public void initWorldGenSeed(long seed)
    {
        biomeLayer.initWorldGenSeed(seed);
        riverLayer.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int offsetX, int offsetY, int width, int height)
    {
        int[] inputBiomeIDs = biomeLayer.getInts(offsetX, offsetY, width, height);
        int[] inputRiverIDs = riverLayer.getInts(offsetX, offsetY, width, height);
        int[] outputBiomeIDs = IntCache.getIntCache(width * height);

        for (int i = 0; i < width * height; ++i)
        {
            if (!isBiomeOceanic(inputBiomeIDs[i]))
            {
                if (inputRiverIDs[i] == Biome.getIdForBiome(AMBiomes.STREAM))
                {
                    outputBiomeIDs[i] = inputRiverIDs[i] & MAX_BIOME_ID;
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
