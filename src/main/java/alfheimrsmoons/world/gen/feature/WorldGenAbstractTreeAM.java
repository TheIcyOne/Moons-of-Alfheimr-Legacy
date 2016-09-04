package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.block.BlockLogAM;
import alfheimrsmoons.block.BlockSaplingAM;
import alfheimrsmoons.block.BlockGrassySoil;
import alfheimrsmoons.block.BlockSoil;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenAbstractTreeAM extends WorldGenAbstractTree
{
    public WorldGenAbstractTreeAM(boolean notify)
    {
        super(notify);
    }

    @Override
    protected boolean canGrowInto(Block block)
    {
        return super.canGrowInto(block) || block instanceof BlockGrassySoil || block instanceof BlockSoil || block instanceof BlockLogAM || block instanceof BlockSaplingAM;
    }

    @Override
    protected void setDirtAt(World world, BlockPos pos)
    {
        if (!(world.getBlockState(pos).getBlock() instanceof BlockSoil))
        {
            setBlockAndNotifyAdequately(world, pos, AMBlocks.SOIL.getDefaultState());
        }
    }
}
