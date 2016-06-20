package alfheimrsmoons.world.gen;

import alfheimrsmoons.init.AMBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenAMCaves extends MapGenCaves
{
    // TODO add Alfheimr equivalents: Blocks.hardened_clay, Blocks.stained_hardened_clay, Blocks.sandstone, Blocks.red_sandstone, Blocks.mycelium
    private static final ImmutableSet<Block> REPLACEABLE_BLOCKS = ImmutableSet.of(AMBlocks.SHALE, AMBlocks.SOIL, AMBlocks.GRASSY_SOIL, Blocks.SNOW_LAYER);

    @Override
    protected boolean canReplaceBlock(IBlockState state, IBlockState up)
    {
        Block block = state.getBlock();
        return REPLACEABLE_BLOCKS.contains(block) || ((block instanceof BlockSand || block instanceof BlockGravel) && up.getMaterial() != Material.WATER);
    }

    @Override
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        return ChunkGeneratorAlfheimr.OCEAN_BLOCKS.contains(data.getBlockState(x, y, z).getBlock());
    }
}
