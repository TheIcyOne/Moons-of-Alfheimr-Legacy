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
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.deadBushPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 0;
        theBiomeDecorator.cactiPerChunk = 0;
    }
}
