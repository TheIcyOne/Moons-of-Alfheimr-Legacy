package alfheimrsmoons.world.biome;

import net.minecraft.util.math.BlockPos;

public class BiomeVeld extends BiomeAM
{
    public BiomeVeld(BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.clear();
       decorator.treesPerChunk = -999;
       decorator.deadBushPerChunk = 2;
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
