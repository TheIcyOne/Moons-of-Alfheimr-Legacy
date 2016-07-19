package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumTallFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;

import java.util.Random;

public class BiomeBluebellForest extends BiomeWoods
{
    public BiomeBluebellForest(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 100;
        theBiomeDecorator.grassPerChunk = 1;
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariants(EnumFlowerVariant.BLUEBELL);
        addTallFlowerVariants(EnumTallFlowerVariant.BLUEBELL);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? EnumWoodVariant.ELM : EnumWoodVariant.BEECH;
    }
}
