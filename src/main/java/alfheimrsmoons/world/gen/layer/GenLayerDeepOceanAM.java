package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDeepOceanAM extends GenLayerAM
{
    public GenLayerDeepOceanAM(long seed, GenLayer parent)
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
                int i1 = input[inputX + 0 + (inputY - 1) * inputWidth];
                int i2 = input[inputX + 1 + (inputY + 0) * inputWidth];
                int i3 = input[inputX - 1 + (inputY + 0) * inputWidth];
                int i4 = input[inputX + 0 + (inputY + 1) * inputWidth];
                int i = input[inputX + inputY * inputWidth];
                int numOceans = 0;

                if (i1 == 0)
                {
                    ++numOceans;
                }

                if (i2 == 0)
                {
                    ++numOceans;
                }

                if (i3 == 0)
                {
                    ++numOceans;
                }

                if (i4 == 0)
                {
                    ++numOceans;
                }

                if (i == 0 && numOceans > 3)
                {
                    output[x + y * width] = Biome.getIdForBiome(AMBiomes.DEEP_LAKE);
                }
                else
                {
                    output[x + y * width] = i;
                }
            }
        }

        return output;
    }
}
