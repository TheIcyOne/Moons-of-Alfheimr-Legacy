package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverAM extends GenLayerAM
{
    public GenLayerRiverAM(long seed, GenLayer parent)
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
        int[] input = parent.getInts(inputOffsetX, inputOffsetY, inputWidth, inputHeight);
        int[] output = IntCache.getIntCache(width * height);

        for (int y = 0; y < height; ++y)
        {
            int inputY = y + 1;

            for (int x = 0; x < width; ++x)
            {
                int inputX = x + 1;
                int biomeID1 = riverFilter(input[inputX - 1 + (inputY + 0) * inputWidth]);
                int biomeID2 = riverFilter(input[inputX + 1 + (inputY + 0) * inputWidth]);
                int biomeID3 = riverFilter(input[inputX + 0 + (inputY - 1) * inputWidth]);
                int biomeID4 = riverFilter(input[inputX + 0 + (inputY + 1) * inputWidth]);
                int biomeID = riverFilter(input[inputX + 0 + (inputY + 0) * inputWidth]);

                if (biomeID == biomeID1 && biomeID == biomeID3 && biomeID == biomeID2 && biomeID == biomeID4)
                {
                    output[x + y * width] = -1;
                }
                else
                {
                    output[x + y * width] = Biome.getIdForBiome(AMBiomes.RIVER);
                }
            }
        }

        return output;
    }

    private int riverFilter(int i)
    {
        return i >= 2 ? 2 + (i & 1) : i;
    }
}
