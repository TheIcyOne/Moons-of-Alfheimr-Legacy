package alfheimrsmoons.world.gen;

import alfheimrsmoons.block.BlockSediment;
import alfheimrsmoons.block.BlockShale;
import alfheimrsmoons.block.BlockSoil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenCavesAM extends MapGenCaves
{
    // TODO check for Alfheimr equivalents: Blocks.hardened_clay, Blocks.stained_hardened_clay, Blocks.sandstone, Blocks.red_sandstone, Blocks.mycelium
    @Override
    protected boolean canReplaceBlock(IBlockState state, IBlockState up)
    {
        Block block = state.getBlock();
        return block instanceof BlockShale
                || block instanceof BlockSoil
                || block instanceof BlockGrass
                || block instanceof BlockSnow
                || block instanceof BlockSediment && up.getMaterial() != Material.WATER
                || super.canReplaceBlock(state, up);
    }

    @Override
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        return data.getBlockState(x, y, z).getMaterial() == Material.WATER;
    }
}
