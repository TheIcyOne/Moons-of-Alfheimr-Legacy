package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;

import java.util.Random;

public class BiomeGenBluebellForest extends BiomeGenWoods
{
    public BiomeGenBluebellForest(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 100;
        theBiomeDecorator.grassPerChunk = 1;
        setFlowerVariants(EnumFlowerVariant.BLUEBELL);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? EnumWoodVariant.ELM : EnumWoodVariant.BEECH;
    }
}
