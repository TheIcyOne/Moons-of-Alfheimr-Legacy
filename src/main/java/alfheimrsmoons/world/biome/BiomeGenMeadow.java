package alfheimrsmoons.world.biome;

public class BiomeGenMeadow extends BiomeGenAM
{
    public BiomeGenMeadow(BiomeProperties properties)
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
        addFlowerVariants(BiomeGenWoods.FLOWER_VARIANTS);
        addTallFlowerVariants(BiomeGenWoods.TALL_FLOWER_VARIANTS);
    }
}
