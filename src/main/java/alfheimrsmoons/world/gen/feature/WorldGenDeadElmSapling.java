package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

import java.util.Random;

public class WorldGenDeadElmSapling extends WorldGenDeadBush
{
    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        do
        {
            IBlockState state = world.getBlockState(position);
            Block block = state.getBlock();
            
            if (!block.isAir(state, world, position) && !block.isLeaves(state, world, position))
                break;
            
            position = position.down();
        }
        while (position.getY() > 0);

        for (int i = 0; i < 4; ++i)
        {
            BlockPos saplingPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(saplingPos) && AMBlocks.dead_elm_sapling.canPlaceBlockAt(world, saplingPos))
            {
                world.setBlockState(saplingPos, AMBlocks.dead_elm_sapling.getDefaultState(), 2);
            }
        }

        return true;
    }
}
