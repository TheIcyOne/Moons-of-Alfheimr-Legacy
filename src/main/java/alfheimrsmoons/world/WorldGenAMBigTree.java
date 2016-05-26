package alfheimrsmoons.world;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenAMBigTree extends WorldGenBigTree {
    private final IBlockState woodState;
    private final IBlockState leavesState;
    private final BlockSapling sapling;

    public WorldGenAMBigTree(boolean notify, IBlockState woodState, IBlockState leavesState, BlockSapling sapling) {
        super(notify);
        this.woodState = woodState;
        this.leavesState = leavesState;
        this.sapling = sapling;
    }

    @Override
    public void generateLeafNode(BlockPos pos) {
        for (int i = 0; i < leafDistanceLimit; ++i) {
            func_181631_a(pos.up(i), leafSize(i), leavesState.withProperty(BlockLeaves.CHECK_DECAY, false));
        }
    }

    public void generateBranch(BlockPos pos1, BlockPos pos2, IBlockState woodState) {
        BlockPos blockpos = pos2.subtract(pos1);
        int i = getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        for (int j = 0; j <= i; ++j) {
            BlockPos blockpos1 = pos1.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));
            BlockLog.EnumAxis blocklog$enumaxis = func_175938_b(pos1, blockpos1);
            setBlockAndNotifyAdequately(world, blockpos1, woodState.withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
        }
    }

    @Override
    public void generateTrunk() {
        BlockPos pos = basePos;
        BlockPos topPos = basePos.up(height);
        IBlockState state = woodState;
        generateBranch(pos, topPos, state);

        if (trunkSize == 2) {
            generateBranch(pos.east(), topPos.east(), state);
            generateBranch(pos.east().south(), topPos.east().south(), state);
            generateBranch(pos.south(), topPos.south(), state);
        }
    }

    @Override
    public void generateLeafNodeBases() {
        for (WorldGenBigTree.FoliageCoordinates coords : field_175948_j) {
            int i = coords.func_177999_q();
            BlockPos pos = new BlockPos(basePos.getX(), i, basePos.getZ());

            if (!pos.equals(coords) && leafNodeNeedsBase(i - basePos.getY())) {
                generateBranch(pos, coords, woodState);
            }
        }
    }

    @Override
    public boolean validTreeLocation() {
        BlockPos down = basePos.down();
        IBlockState state = world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, sapling);

        if (!isSoil) {
            return false;
        } else {
            int i = checkBlockLine(basePos, basePos.up(heightLimit - 1));

            if (i == -1) {
                return true;
            } else if (i < 6) {
                return false;
            } else {
                heightLimit = i;
                return true;
            }
        }
    }
}
