package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdgeAM extends GenLayerAM
{
    public GenLayerBiomeEdgeAM(long seed, GenLayer parent)
    {
        super(seed, parent);
    }

    @Override
    public int[] getInts(int offsetX, int offsetY, int width, int height)
    {
        int inputOffsetX = offsetX - 1;
        int inputOffsetY = offsetY - 1;
        int inputWidth = width + 2;
        int inputHeight = height + 2;
        int[] inputBiomeIDs = parent.getInts(inputOffsetX, inputOffsetY, inputWidth, inputHeight);
        int[] outputBiomeIDs = IntCache.getIntCache(width * height);

        for (int y = 0; y < height; ++y)
        {
            int inputY = y + 1;
            
            for (int x = 0; x < width; ++x)
            {
                initChunkSeed((long) (x + offsetX), (long) (y + offsetY));
                
                int inputX = x + 1;
                int biomeID = inputBiomeIDs[inputX + inputY * inputWidth];

                if (biomeID == Biome.getIdForBiome(AMBiomes.FLOODED_FOREST))
                {
                    int biomeID1 = inputBiomeIDs[inputX + 0 + (inputY - 1) * inputWidth];
                    int biomeID2 = inputBiomeIDs[inputX + 1 + (inputY + 0) * inputWidth];
                    int biomeID3 = inputBiomeIDs[inputX - 1 + (inputY + 0) * inputWidth];
                    int biomeID4 = inputBiomeIDs[inputX + 0 + (inputY + 1) * inputWidth];
                    int desertID = Biome.getIdForBiome(AMBiomes.VELD);

                    if (biomeID1 != desertID && biomeID2 != desertID && biomeID3 != desertID && biomeID4 != desertID)
                    {
                        outputBiomeIDs[x + y * width] = biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[x + y * width] = Biome.getIdForBiome(AMBiomes.MEADOW);
                    }
                }
                else
                {
                    outputBiomeIDs[x + y * width] = biomeID;
                }
            }
        }

        return outputBiomeIDs;
    }
}
