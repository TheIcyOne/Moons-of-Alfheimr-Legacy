package alfheimrsmoons.world.biome;

public class BiomeAMOcean extends AMBiome
{
    public BiomeAMOcean(BiomeProperties properties)
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
