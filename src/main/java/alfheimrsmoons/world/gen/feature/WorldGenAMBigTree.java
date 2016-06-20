package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumWoodVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenAMBigTree extends WorldGenBigTree
{
    private final IBlockState woodState;
    private final IBlockState leavesState;

    public WorldGenAMBigTree(boolean notify, EnumWoodVariant variant)
    {
        this(notify, variant.getLogState(), variant.getLeavesState());
    }

    public WorldGenAMBigTree(boolean notify, IBlockState woodState, IBlockState leavesState)
    {
        super(notify);
        this.woodState = woodState;
        this.leavesState = leavesState;
    }

    @Override
    public void generateLeafNode(BlockPos pos)
    {
        for (int i = 0; i < leafDistanceLimit; ++i)
        {
            crosSection(pos.up(i), leafSize(i), leavesState.withProperty(BlockLeaves.CHECK_DECAY, false));
        }
    }

    public void limb(BlockPos pos1, BlockPos pos2, IBlockState woodState)
    {
        BlockPos blockpos = pos2.subtract(pos1);
        int i = getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        for (int j = 0; j <= i; ++j)
        {
            BlockPos blockpos1 = pos1.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));
            BlockLog.EnumAxis blocklog$enumaxis = getLogAxis(pos1, blockpos1);
            setBlockAndNotifyAdequately(world, blockpos1, woodState.withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
        }
    }

    @Override
    public void generateTrunk()
    {
        BlockPos pos = basePos;
        BlockPos topPos = basePos.up(height);
        IBlockState state = woodState;
        limb(pos, topPos, state);

        if (trunkSize == 2)
        {
            limb(pos.east(), topPos.east(), state);
            limb(pos.east().south(), topPos.east().south(), state);
            limb(pos.south(), topPos.south(), state);
        }
    }

    @Override
    public void generateLeafNodeBases()
    {
        for (WorldGenBigTree.FoliageCoordinates coords : foliageCoords)
        {
            int i = coords.getBranchBase();
            BlockPos pos = new BlockPos(basePos.getX(), i, basePos.getZ());

            if (!pos.equals(coords) && leafNodeNeedsBase(i - basePos.getY()))
            {
                limb(pos, coords, woodState);
            }
        }
    }

    @Override
    public boolean validTreeLocation()
    {
        BlockPos down = basePos.down();
        IBlockState state = world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, AMBlocks.SAPLING);

        if (!isSoil)
        {
            return false;
        }
        else
        {
            int i = checkBlockLine(basePos, basePos.up(heightLimit - 1));

            if (i == -1)
            {
                return true;
            }
            else if (i < 6)
            {
                return false;
            }
            else
            {
                heightLimit = i;
                return true;
            }
        }
    }

    @Override
    protected void setDirtAt(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() != AMBlocks.SOIL)
        {
            setBlockAndNotifyAdequately(world, pos, AMBlocks.SOIL.getDefaultState());
        }
    }

    @Override
    protected boolean canGrowInto(Block block)
    {
        return super.canGrowInto(block) || WorldGenAMTrees.isReplaceable(block);
    }
}
