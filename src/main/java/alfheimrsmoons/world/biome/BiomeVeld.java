package alfheimrsmoons.world.biome;

import net.minecraft.util.math.BlockPos;

public class BiomeVeld extends AMBiome
{
    public BiomeVeld(BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.clear();
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.deadBushPerChunk = 2;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x9E8729;
    }

    @Override
    public boolean hasTrees()
    {
        return false;
    }
}
