package alfheimrsmoons.world.gen.feature.layer;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLiquids;

import java.util.Random;

public class WorldGenAMLiquids extends WorldGenLiquids
{
    private IBlockState state;

    public WorldGenAMLiquids(IBlockState state)
    {
        super(state.getBlock());
        this.state = state;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        if (world.getBlockState(position.up()).getBlock() != AMBlocks.shale)
        {
            return false;
        }
        else if (world.getBlockState(position.down()).getBlock() != AMBlocks.shale)
        {
            return false;
        }
        else if (!world.isAirBlock(position) && world.getBlockState(position).getBlock() != AMBlocks.shale)
        {
            return false;
        }
        else
        {
            int i = 0;

            if (world.getBlockState(position.west()).getBlock() == AMBlocks.shale)
            {
                ++i;
            }

            if (world.getBlockState(position.east()).getBlock() == AMBlocks.shale)
            {
                ++i;
            }

            if (world.getBlockState(position.north()).getBlock() == AMBlocks.shale)
            {
                ++i;
            }

            if (world.getBlockState(position.south()).getBlock() == AMBlocks.shale)
            {
                ++i;
            }

            int j = 0;

            if (world.isAirBlock(position.west()))
            {
                ++j;
            }

            if (world.isAirBlock(position.east()))
            {
                ++j;
            }

            if (world.isAirBlock(position.north()))
            {
                ++j;
            }

            if (world.isAirBlock(position.south()))
            {
                ++j;
            }

            if (i == 3 && j == 1)
            {
                world.setBlockState(position, state, 2);
                world.forceBlockUpdateTick(state.getBlock(), position, rand);
            }

            return true;
        }
    }
}
