package alfheimrsmoons.world.biome;

public class BiomeMeadow extends BiomeAM
{
    public BiomeMeadow(BiomeProperties properties)
    {
        super(properties);
       decorator.treesPerChunk = -999;
       decorator.flowersPerChunk = 4;
       decorator.grassPerChunk = 10;
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
