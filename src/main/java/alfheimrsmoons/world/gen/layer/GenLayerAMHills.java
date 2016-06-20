package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAMHills extends GenLayerHills
{
    private GenLayer riverLayer;

    public GenLayerAMHills(long seed, GenLayer parent, GenLayer riverLayer)
    {
        super(seed, parent, riverLayer);
        this.riverLayer = riverLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = riverLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                initChunkSeed((long) (j + areaX), (long) (i + areaY));
                int biomeID = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                int l = aint1[j + 1 + (i + 1) * (areaWidth + 2)];
                boolean flag = (l - 2) % 29 == 0;

                BiomeGenBase biome = BiomeGenBase.getBiomeForId(biomeID);
                boolean isMutation = biome != null && biome.isMutation();

                if (biomeID != 0 && l >= 2 && (l - 2) % 29 == 1 && !isMutation)
                {
                    BiomeGenBase mutation = BiomeGenBase.getMutationForBiome(biome);
                    aint2[j + i * areaWidth] = mutation == null ? biomeID : BiomeGenBase.getIdForBiome(mutation);
                }
                else if (nextInt(3) != 0 && !flag)
                {
                    aint2[j + i * areaWidth] = biomeID;
                }
                else
                {
                    BiomeGenBase hillsBiome;

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

                        if (hillsBiome == null)
                        {
                            hillsBiome = biome;
                        }
                    }

                    int hillsBiomeID = BiomeGenBase.getIdForBiome(hillsBiome);

                    if (flag && hillsBiomeID != biomeID)
                    {
                        BiomeGenBase hillsMutation = BiomeGenBase.getMutationForBiome(hillsBiome);
                        hillsBiomeID = hillsMutation == null ? biomeID : BiomeGenBase.getIdForBiome(hillsMutation);
                    }

                    if (hillsBiomeID == biomeID)
                    {
                        aint2[j + i * areaWidth] = biomeID;
                    }
                    else
                    {
                        int k2 = aint[j + 1 + (i + 0) * (areaWidth + 2)];
                        int j1 = aint[j + 2 + (i + 1) * (areaWidth + 2)];
                        int k1 = aint[j + 0 + (i + 1) * (areaWidth + 2)];
                        int l1 = aint[j + 1 + (i + 2) * (areaWidth + 2)];
                        int i2 = 0;

                        if (biomesEqualOrMesaPlateau(k2, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(j1, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(k1, biomeID))
                        {
                            ++i2;
                        }

                        if (biomesEqualOrMesaPlateau(l1, biomeID))
                        {
                            ++i2;
                        }

                        if (i2 >= 3)
                        {
                            aint2[j + i * areaWidth] = hillsBiomeID;
                        }
                        else
                        {
                            aint2[j + i * areaWidth] = biomeID;
                        }
                    }
                }
            }
        }

        return aint2;
    }
}
