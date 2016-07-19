package alfheimrsmoons.world.biome;

public class BiomeMeadow extends AMBiome
{
    public BiomeMeadow(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.flowersPerChunk = 4;
        theBiomeDecorator.grassPerChunk = 10;
    }

    @Override
    public boolean hasTrees()
    {
        return false;
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariants(BiomeWoods.FLOWER_VARIANTS);
        addTallFlowerVariants(BiomeWoods.TALL_FLOWER_VARIANTS);
    }
}
