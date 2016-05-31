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
    private static final ImmutableSet<Block> REPLACEABLE_BLOCKS = ImmutableSet.of(AMBlocks.shale, AMBlocks.soil, AMBlocks.grassy_soil, Blocks.snow_layer);

    @Override
    protected boolean func_175793_a(IBlockState state, IBlockState up)//canReplaceBlock(state, up)
    {
        Block block = state.getBlock();
        return REPLACEABLE_BLOCKS.contains(block) || ((block instanceof BlockSand || block instanceof BlockGravel) && up.getMaterial() != Material.water);
    }

    @Override
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        return ChunkGeneratorAlfheimr.OCEAN_BLOCKS.contains(data.getBlockState(x, y, z).getBlock());
    }
}
