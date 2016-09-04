package alfheimrsmoons.world.biome;

public class BiomeOceanAM extends BiomeAM
{
    public BiomeOceanAM(BiomeProperties properties)
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
