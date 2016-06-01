package alfheimrsmoons.world.biome;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenWoods extends BiomeGenAM
{
    public BiomeGenWoods(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 10;
        theBiomeDecorator.grassPerChunk = 2;
    }
}
