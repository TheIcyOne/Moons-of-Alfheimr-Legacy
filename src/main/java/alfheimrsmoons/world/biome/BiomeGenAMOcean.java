package alfheimrsmoons.world.biome;

public class BiomeGenAMOcean extends BiomeGenAM
{
    public BiomeGenAMOcean(BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.clear();
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}
