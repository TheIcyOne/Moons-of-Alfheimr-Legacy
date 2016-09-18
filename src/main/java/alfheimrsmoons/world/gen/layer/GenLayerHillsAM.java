package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerHillsAM extends GenLayerAM
{
    private GenLayer riverLayer;

    public GenLayerHillsAM(long seed, GenLayer parent, GenLayer riverLayer)
    {
        super(seed, parent);
        this.riverLayer = riverLayer;
    }

    @Override
    public int[] getInts(int offsetX, int offsetY, int width, int height)
    {
        int inputOffsetX = offsetX - 1;
        int inputOffsetY = offsetY - 1;
        int inputWidth = width + 2;
        int inputHeight = height + 2;
        int[] inputBiomeIDs = parent.getInts(inputOffsetX, inputOffsetY, inputWidth, inputHeight);
        int[] inputRiverIDs = riverLayer.getInts(inputOffsetX, inputOffsetY, inputWidth, inputHeight);
        int[] outputBiomeIDs = IntCache.getIntCache(width * height);

        for (int y = 0; y < height; ++y)
        {
            int inputY = y + 1;
            
            for (int x = 0; x < width; ++x)
            {
                initChunkSeed((long) (x + offsetX), (long) (y + offsetY));
                
                int inputX = x + 1;

                int biomeID = inputBiomeIDs[inputX + inputY * inputWidth];
                Biome biome = Biome.getBiomeForId(biomeID);
                boolean isMutation = biome != null && biome.isMutation();

                int riverID = inputRiverIDs[inputX + inputY * inputWidth];
                int i = (riverID - 2) % 29;
                boolean flag1 = riverID >= 2 && i == 1;
                boolean flag2 = i == 0;

                if (biomeID != 0 && flag1 && !isMutation)
                {
                    Biome mutation = Biome.getMutationForBiome(biome);
                    outputBiomeIDs[x + y * width] = mutation != null ? Biome.getIdForBiome(mutation) : biomeID;
                }
                else if (nextInt(3) != 0 && !flag2)
                {
                    outputBiomeIDs[x + y * width] = biomeID;
                }
                else
                {
                    Biome hillsBiome;

                    if (biome == AMBiomes.MEADOW)
                    {
                        if (nextInt(3) == 0)
                        {
                            hillsBiome = AMBiomes.WOODS_HILLS;
                        }
                        else
                        {
                            hillsBiome = AMBiomes.WOODS;
                        }
                    }
                    else if (biome == AMBiomes.DEEP_LAKE && nextInt(3) == 0)
                    {
                        if (nextInt(2) == 0)
                        {
                            hillsBiome = AMBiomes.MEADOW;
                        }
                        else
                        {
                            hillsBiome = AMBiomes.WOODS;
                        }
                    }
                    else
                    {
                        hillsBiome = AMBiomes.getHills(biome);
                    }

                    int hillsBiomeID = Biome.getIdForBiome(hillsBiome);

                    if (flag2 && hillsBiomeID != biomeID)
                    {
                        Biome hillsMutation = Biome.getMutationForBiome(hillsBiome);
                        hillsBiomeID = hillsMutation != null ? Biome.getIdForBiome(hillsMutation) : biomeID;
                    }

                    if (hillsBiomeID == biomeID)
                    {
                        outputBiomeIDs[x + y * width] = biomeID;
                    }
                    else
                    {
                        int biomeID1 = inputBiomeIDs[inputX + 0 + (inputY - 1) * inputWidth];
                        int biomeID2 = inputBiomeIDs[inputX + 1 + (inputY + 0) * inputWidth];
                        int biomeID3 = inputBiomeIDs[inputX - 1 + (inputY + 0) * inputWidth];
                        int biomeID4 = inputBiomeIDs[inputX + 0 + (inputY + 1) * inputWidth];
                        int numEqualBiomes = 0;

                        if (biomeID1 == biomeID)
                        {
                            ++numEqualBiomes;
                        }

                        if (biomeID2 == biomeID)
                        {
                            ++numEqualBiomes;
                        }

                        if (biomeID3 == biomeID)
                        {
                            ++numEqualBiomes;
                        }

                        if (biomeID4 == biomeID)
                        {
                            ++numEqualBiomes;
                        }

                        if (numEqualBiomes >= 3)
                        {
                            outputBiomeIDs[x + y * width] = hillsBiomeID;
                        }
                        else
                        {
                            outputBiomeIDs[x + y * width] = biomeID;
                        }
                    }
                }
            }
        }

        return outputBiomeIDs;
    }
}
