package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.world.biome.BiomeGenBeach;

public class BiomeGenAMBeach extends BiomeGenAM
{
    public BiomeGenAMBeach(BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.clear();
        topBlock = AMBlocks.sediment.getDefaultState();
        fillerBlock = AMBlocks.sediment.getDefaultState();
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.deadBushPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 0;
        theBiomeDecorator.cactiPerChunk = 0;
    }
}
