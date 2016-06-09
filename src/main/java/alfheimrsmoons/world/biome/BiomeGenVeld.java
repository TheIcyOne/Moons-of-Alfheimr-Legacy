package alfheimrsmoons.world.biome;

import net.minecraft.util.math.BlockPos;

public class BiomeGenVeld extends BiomeGenAM
{
    public BiomeGenVeld(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.deadBushPerChunk = 2;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x9E8729;
    }
}
