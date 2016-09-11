package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerHillsAM extends GenLayer
{
    private final GenLayer riverLayer;

    public GenLayerHillsAM(long seed, GenLayer parent, GenLayer riverLayer)
    {
        super(seed);
        this.parent = parent;
        this.riverLayer = riverLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] inputBiomeIDs = parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = riverLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] outputBiomeIDs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int biomeID = inputBiomeIDs[j + 1 + (i + 1) * (areaWidth + 2)];
                int l = aint1[j + 1 + (i + 1) * (areaWidth + 2)];
                boolean flag = (l - 2) % 29 == 0;

                Biome biome = Biome.getBiomeForId(biomeID);
                boolean isMutation = biome != null && biome.isMutation();

                if (biomeID != 0 && l >= 2 && (l - 2) % 29 == 1 && !isMutation)
                {
                    Biome mutation = Biome.getMutationForBiome(biome);
                    outputBiomeIDs[j + i * areaWidth] = mutation == null ? biomeID : Biome.getIdForBiome(mutation);
                }
                else if (nextInt(3) != 0 && !flag)
                {
                    outputBiomeIDs[j + i * areaWidth] = biomeID;
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
                    else if (biome == AMBiomes.DEEP_OCEAN && nextInt(3) == 0)
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

                    if (flag && hillsBiomeID != biomeID)
                    {
                        Biome hillsMutation = Biome.getMutationForBiome(hillsBiome);
                        hillsBiomeID = hillsMutation == null ? biomeID : Biome.getIdForBiome(hillsMutation);
                    }

                    if (hillsBiomeID == biomeID)
                    {
                        outputBiomeIDs[j + i * areaWidth] = biomeID;
                    }
                    else
                    {
                        int biomeID1 = inputBiomeIDs[j + 1 + (i + 0) * (areaWidth + 2)];
                        int biomeID2 = inputBiomeIDs[j + 2 + (i + 1) * (areaWidth + 2)];
                        int biomeID3 = inputBiomeIDs[j + 0 + (i + 1) * (areaWidth + 2)];
                        int biomeID4 = inputBiomeIDs[j + 1 + (i + 2) * (areaWidth + 2)];
                        int i2 = 0;

                        if (biomesEqualOrMesaPlateau(biomeID1, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(biomeID2, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(biomeID3, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(biomeID4, biomeID))
                        {
                            ++i2;
                        }

                        if (i2 >= 3)
                        {
                            outputBiomeIDs[j + i * areaWidth] = hillsBiomeID;
                        }
                        else
                        {
                            outputBiomeIDs[j + i * areaWidth] = biomeID;
                        }
                    }
                }
            }
        }

        return outputBiomeIDs;
    }
}
