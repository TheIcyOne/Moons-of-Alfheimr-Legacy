package alfheimrsmoons.world.gen;

import alfheimrsmoons.block.BlockShale;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenRavineAM extends MapGenRavine
{
    @Override
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        return data.getBlockState(x, y, z).getMaterial() == Material.WATER;
    }

    @Override
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop)
    {
        Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (state.getBlock() instanceof BlockShale || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
            if (y - 1 < 10)
            {
                data.setBlockState(x, y, z, FLOWING_LAVA);
            }
            else
            {
                data.setBlockState(x, y, z, AIR);

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
}
