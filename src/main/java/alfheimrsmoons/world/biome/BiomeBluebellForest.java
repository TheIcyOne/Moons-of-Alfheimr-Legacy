package alfheimrsmoons.world.biome;

import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.combo.VariantTree;

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
        addFlowerVariants(VariantFlower.BLUEBELL);
        addTallFlowerVariants(VariantFlower.BLUEBELL);
    }

    @Override
    protected VariantTree getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? VariantTree.ELM : VariantTree.BEECH;
    }
}
