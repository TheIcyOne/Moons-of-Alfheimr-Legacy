package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.combo.ComboTrees;
import alfheimrsmoons.combo.VariantTree;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenAMBigTree extends WorldGenAMAbstractTree
{
    private static final double HEIGHT_ATTENUATION = 0.618D;
    private static final double BRANCH_SLOPE = 0.381D;
    private static final double SCALE_WIDTH = 1.0D;
    private static final double LEAF_DENSITY = 1.0D;
    private static final int TRUNK_SIZE = 1;
    private static final int HEIGHT_LIMIT_LIMIT = 12;

    /** The block state of the wood to use in tree generation. */
    private final IBlockState woodState;
    /** The block state of the leaves to use in tree generation. */
    private final IBlockState leavesState;
    /** The sapling used to check if a tree can generate. */
    private final IPlantable sapling;

    private int heightLimit;
    private int height;
    /**
     * Sets the distance limit for how far away the generator will
     * populate leaves from the base leaf node.
     */
    private int leafDistanceLimit = 4;

    public WorldGenAMBigTree(boolean notify, VariantTree variant)
    {
        super(notify);
        this.woodState = AMBlocks.TREES.getBlockState(ComboTrees.LOG, variant);
        this.leavesState = AMBlocks.TREES.getBlockState(ComboTrees.LEAVES, variant).withProperty(BlockLeaves.CHECK_DECAY, false);
        this.sapling = AMBlocks.TREES.getBlock(ComboTrees.SAPLING, variant);
    }

    public WorldGenAMBigTree(boolean notify, IBlockState woodState, IBlockState leavesState, IPlantable sapling)
    {
        super(notify);
        this.woodState = woodState;
        this.leavesState = leavesState;
        this.sapling = sapling;
    }

    /**
     * Generates a list of leaf nodes for the tree, to be populated by
     * generateLeaves.
     */
    private void generateLeafNodeList(World world, Random rand, BlockPos basePos, List<FoliageCoordinates> foliageCoordList)
    {
        height = (int) ((double) heightLimit * HEIGHT_ATTENUATION);

        if (height >= heightLimit)
        {
            height = heightLimit - 1;
        }

        int i = (int) (1.382D + Math.pow(LEAF_DENSITY * (double) heightLimit / 13.0D, 2.0D));

        if (i < 1)
        {
            i = 1;
        }

        int j = basePos.getY() + height;
        int k = heightLimit - leafDistanceLimit;

        foliageCoordList.add(new FoliageCoordinates(basePos.up(k), j));

        for (; k >= 0; --k)
        {
            float f = layerSize(k);

            if (f >= 0.0F)
            {
                for (int l = 0; l < i; ++l)
                {
                    double d0 = SCALE_WIDTH * (double) f * ((double) rand.nextFloat() + 0.328D);
                    double d1 = (double) (rand.nextFloat() * 2.0F) * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = basePos.add(d2, (double) (k - 1), d3);
                    BlockPos blockpos1 = blockpos.up(leafDistanceLimit);

                    if (checkBlockLine(world, blockpos, blockpos1) == -1)
                    {
                        int i1 = basePos.getX() - blockpos.getX();
                        int j1 = basePos.getZ() - blockpos.getZ();
                        double d4 = (double) blockpos.getY() - Math.sqrt((double) (i1 * i1 + j1 * j1)) * BRANCH_SLOPE;
                        int k1 = d4 > (double) j ? j : (int) d4;
                        BlockPos blockpos2 = new BlockPos(basePos.getX(), k1, basePos.getZ());

                        if (checkBlockLine(world, blockpos2, blockpos) == -1)
                        {
                            foliageCoordList.add(new FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    private void crosSection(World world, BlockPos pos, float p_181631_2_, IBlockState p_181631_3_)
    {
        int i = (int) ((double) p_181631_2_ + 0.618D);

        for (int j = -i; j <= i; ++j)
        {
            for (int k = -i; k <= i; ++k)
            {
                if (Math.pow((double) Math.abs(j) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k) + 0.5D, 2.0D) <= (double) (p_181631_2_ * p_181631_2_))
                {
                    BlockPos blockpos = pos.add(j, 0, k);
                    IBlockState state = world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos))
                    {
                        setBlockAndNotifyAdequately(world, blockpos, p_181631_3_);
                    }
                }
            }
        }
    }

    /**
     * Gets the rough size of a layer of the tree.
     */
    private float layerSize(int y)
    {
        if ((float) y < (float) heightLimit * 0.3F)
        {
            return -1.0F;
        }
        else
        {
            float f = (float) heightLimit / 2.0F;
            float f1 = f - (float) y;
            float f2 = MathHelper.sqrt_float(f * f - f1 * f1);

            if (f1 == 0.0F)
            {
                f2 = f;
            }
            else if (Math.abs(f1) >= f)
            {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    private float leafSize(int y)
    {
        return y >= 0 && y < leafDistanceLimit ? (y != 0 && y != leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    /**
     * Generates the leaves surrounding an individual entry in the
     * leafNodes list.
     */
    private void generateLeafNode(World world, BlockPos pos)
    {
        for (int i = 0; i < leafDistanceLimit; ++i)
        {
            crosSection(world, pos.up(i), leafSize(i), leavesState);
        }
    }

    private void limb(World world, BlockPos p_175937_1_, BlockPos p_175937_2_)
    {
        BlockPos blockpos = p_175937_2_.add(-p_175937_1_.getX(), -p_175937_1_.getY(), -p_175937_1_.getZ());
        int i = getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        for (int j = 0; j <= i; ++j)
        {
            BlockPos blockpos1 = p_175937_1_.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));
            EnumAxis blocklog$enumaxis = getLogAxis(p_175937_1_, blockpos1);
            setBlockAndNotifyAdequately(world, blockpos1, woodState.withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
        }
    }

    /**
     * Returns the absolute greatest distance in the BlockPos object.
     */
    private int getGreatestDistance(BlockPos posIn)
    {
        int i = MathHelper.abs_int(posIn.getX());
        int j = MathHelper.abs_int(posIn.getY());
        int k = MathHelper.abs_int(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private EnumAxis getLogAxis(BlockPos p_175938_1_, BlockPos p_175938_2_)
    {
        EnumAxis blocklog$enumaxis = EnumAxis.Y;
        int i = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
        int j = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
        int k = Math.max(i, j);

        if (k > 0)
        {
            if (i == k)
            {
                blocklog$enumaxis = EnumAxis.X;
            }
            else if (j == k)
            {
                blocklog$enumaxis = EnumAxis.Z;
            }
        }

        return blocklog$enumaxis;
    }

    /**
     * Generates the leaf portion of the tree as specified by the
     * leafNodes list.
     */
    private void generateLeaves(World world, List<FoliageCoordinates> foliageCoordList)
    {
        for (FoliageCoordinates foliageCoords : foliageCoordList)
        {
            generateLeafNode(world, foliageCoords);
        }
    }

    /**
     * Indicates whether or not a leaf node requires additional wood to
     * be added to preserve integrity.
     */
    private boolean leafNodeNeedsBase(int p_76493_1_)
    {
        return (double) p_76493_1_ >= (double) heightLimit * 0.2D;
    }

    /**
     * Places the trunk for the big tree that is being generated. Able
     * to generate double-sized trunks by changing a field that is
     * always 1 to 2.
     */
    private void generateTrunk(World world, BlockPos basePos)
    {
        BlockPos blockpos = basePos;
        BlockPos blockpos1 = basePos.up(height);
        limb(world, blockpos, blockpos1);

        if (TRUNK_SIZE == 2)
        {
            limb(world, blockpos.east(), blockpos1.east());
            limb(world, blockpos.east().south(), blockpos1.east().south());
            limb(world, blockpos.south(), blockpos1.south());
        }
    }

    /**
     * Generates additional wood blocks to fill out the bases of
     * different leaf nodes that would otherwise degrade.
     */
    private void generateLeafNodeBases(World world, BlockPos basePos, List<FoliageCoordinates> foliageCoordList)
    {
        for (FoliageCoordinates foliageCoords : foliageCoordList)
        {
            int i = foliageCoords.branchBase;
            BlockPos blockpos = new BlockPos(basePos.getX(), i, basePos.getZ());

            if (!blockpos.equals(foliageCoords) && leafNodeNeedsBase(i - basePos.getY()))
            {
                limb(world, blockpos, foliageCoords);
            }
        }
    }

    /**
     * Checks a line of blocks in the world from the first coordinate to
     * triplet to the second, returning the distance (in blocks) before
     * a non-air, non-leaf block is encountered and/or the end is
     * encountered.
     */
    private int checkBlockLine(World world, BlockPos posOne, BlockPos posTwo)
    {
        BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
        int i = getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        if (i == 0)
        {
            return -1;
        }
        else
        {
            for (int j = 0; j <= i; ++j)
            {
                BlockPos blockpos1 = posOne.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));

                if (!isReplaceable(world, blockpos1))
                {
                    return j;
                }
            }

            return -1;
        }
    }

    @Override
    public void setDecorationDefaults()
    {
        leafDistanceLimit = 5;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos basePos)
    {
        rand = new Random(rand.nextLong());

        if (heightLimit == 0)
        {
            heightLimit = 5 + rand.nextInt(HEIGHT_LIMIT_LIMIT);
        }

        if (!validTreeLocation(world, basePos))
        {
            return false;
        }
        else
        {
            List<FoliageCoordinates> foliageCoordList = new ArrayList<>();
            generateLeafNodeList(world, rand, basePos, foliageCoordList);
            generateLeaves(world, foliageCoordList);
            generateTrunk(world, basePos);
            generateLeafNodeBases(world, basePos, foliageCoordList);
            return true;
        }
    }

    /**
     * Returns a boolean indicating whether or not the current location
     * for the tree, spanning basePos to to the height limit, is valid.
     */
    private boolean validTreeLocation(World world, BlockPos basePos)
    {
        BlockPos down = basePos.down();
        IBlockState state = world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, sapling);

        if (!isSoil)
        {
            return false;
        }
        else
        {
            int i = checkBlockLine(world, basePos, basePos.up(heightLimit - 1));

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

    private static class FoliageCoordinates extends BlockPos
    {
        private final int branchBase;

        private FoliageCoordinates(BlockPos pos, int p_i45635_2_)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            branchBase = p_i45635_2_;
        }
    }
}
