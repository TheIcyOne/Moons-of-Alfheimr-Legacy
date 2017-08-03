package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBlocks;

public class BiomeBeachAM extends BiomeAM
{
    public BiomeBeachAM(BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.clear();
        topBlock = AMBlocks.SEDIMENT.getDefaultState();
        fillerBlock = AMBlocks.SEDIMENT.getDefaultState();
        decorator.treesPerChunk = -999;
        decorator.deadBushPerChunk = 0;
        decorator.reedsPerChunk = 0;
        decorator.cactiPerChunk = 0;
    }
}
