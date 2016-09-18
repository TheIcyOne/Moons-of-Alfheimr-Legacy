package alfheimrsmoons.world.gen.layer;

import alfheimrsmoons.init.AMBiomes;
import alfheimrsmoons.world.biome.BiomeProviderAM;
import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.List;

public class GenLayerBiomeAM extends GenLayerAM
{
    public GenLayerBiomeAM(long seed, GenLayer parent)
    {
        super(seed, parent);
    }

    @Override
    public int[] getInts(int offsetX, int offsetY, int width, int height)
    {
        int[] input = parent.getInts(offsetX, offsetY, width, height);
        int[] outputBiomeIDs = IntCache.getIntCache(width * height);

        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                initChunkSeed((long) (x + offsetX), (long) (y + offsetY));
                int i = input[x + y * width];
                int l = (i & 3840) >> 8;
                i &= -3841;

                if (i == Biome.getIdForBiome(Biomes.OCEAN))
                {
                    outputBiomeIDs[x + y * width] = Biome.getIdForBiome(AMBiomes.LAKE);
                }
                else if (isBiomeOceanic(i))
                {
                    outputBiomeIDs[x + y * width] = i;
                }
                else
                {
                    BiomeType type = BiomeProviderAM.getBiomeTypeByID(i - 1);

                    if (type != null)
                    {
                        outputBiomeIDs[x + y * width] = Biome.getIdForBiome(getWeightedBiomeEntry(type).biome);
                    }
                }
            }
        }

        return outputBiomeIDs;
    }

    private BiomeEntry getWeightedBiomeEntry(BiomeType type)
    {
        List<BiomeEntry> biomeList = BiomeProviderAM.getBiomes(type);
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = nextInt(totalWeight);
        return WeightedRandom.getRandomItem(biomeList, weight);
    }
}
