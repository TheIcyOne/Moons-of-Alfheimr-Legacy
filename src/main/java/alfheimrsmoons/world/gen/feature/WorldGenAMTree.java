package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.combo.ComboTrees;
import alfheimrsmoons.combo.VariantTree;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WorldGenAMTree extends WorldGenAMAbstractTree
{
    public static final int DEFAULT_HEIGHT = 4;

    /** The minimum height of a generated tree. */
    private final int minHeight;
    /** The block state of the wood to use in tree generation. */
    private final IBlockState woodState;
    /** The block state of the leaves to use in tree generation. */
    private final IBlockState leavesState;
    /** The sapling used to check if a tree can generate. */
    private final IPlantable sapling;

    public WorldGenAMTree(boolean notify, VariantTree variant)
    {
        this(notify, DEFAULT_HEIGHT, variant);
    }

    public WorldGenAMTree(boolean notify, int minHeight, VariantTree variant)
    {
        super(notify);
        this.minHeight = minHeight;
        this.woodState = AMBlocks.TREES.getBlockState(ComboTrees.LOG, variant);
        this.leavesState = AMBlocks.TREES.getBlockState(ComboTrees.LEAVES, variant).withProperty(BlockLeaves.CHECK_DECAY, false);
        this.sapling = AMBlocks.TREES.getBlock(ComboTrees.SAPLING, variant);
    }

    public WorldGenAMTree(boolean notify, int minHeight, IBlockState woodState, IBlockState leavesState, IPlantable sapling)
    {
        super(notify);
        this.minHeight = minHeight;
        this.woodState = woodState;
        this.leavesState = leavesState;
        this.sapling = sapling;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        int height = rand.nextInt(3) + minHeight;
        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();
        int maxY = y + height;

        if (y < 1 || maxY + 1 > world.getHeight())
        {
            return false;
        }

        for (int blockY = y; blockY <= maxY + 1; ++blockY)
        {
            int radius = 1;

            if (blockY == y)
            {
                radius = 0;
            }

            if (blockY >= maxY + 1 - 2)
            {
                radius = 2;
            }

            int minX = x - radius;
            int maxX = x + radius;
            int minZ = z - radius;
            int maxZ = z + radius;

            for (int blockX = minX; blockX <= maxX; ++blockX)
            {
                for (int blockZ = minZ; blockZ <= maxZ; ++blockZ)
                {
                    if (blockY < 0 || blockY >= world.getHeight())
                    {
                        return false;
                    }

                    if (!isReplaceable(world, new BlockPos(blockX, blockY, blockZ)))
                    {
                        return false;
                    }
                }
            }
        }

        BlockPos down = position.down();
        IBlockState downState = world.getBlockState(down);

        if (!downState.getBlock().canSustainPlant(downState, world, down, EnumFacing.UP, sapling) || y >= world.getHeight() - height - 1)
        {
            return false;
        }

        setDirtAt(world, down);

        for (int blockY = maxY - 3; blockY <= maxY; ++blockY)
        {
            int yDistance = blockY - maxY;

            int radius = 1 - yDistance / 2;
            int minX = x - radius;
            int maxX = x + radius;
            int minZ = z - radius;
            int maxZ = z + radius;

            for (int blockX = minX; blockX <= maxX; ++blockX)
            {
                int xDistance = Math.abs(blockX - x);

                for (int blockZ = minZ; blockZ <= maxZ; ++blockZ)
                {
                    int zDistance = Math.abs(blockZ - z);

                    if (xDistance != radius || zDistance != radius || rand.nextInt(2) != 0 && yDistance != 0)
                    {
                        BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
                        IBlockState blockState = world.getBlockState(blockPos);
                        Block block = blockState.getBlock();

                        if (block.isAir(blockState, world, blockPos) || block.isLeaves(blockState, world, blockPos) || blockState.getMaterial() == Material.VINE)
                        {
                            setBlockAndNotifyAdequately(world, blockPos, leavesState);
                        }
                    }
                }
            }
        }

        for (int yOffset = 0; yOffset < height; ++yOffset)
        {
            BlockPos up = position.up(yOffset);
            IBlockState upState = world.getBlockState(up);
            Block upBlock = upState.getBlock();

            if (upBlock.isAir(upState, world, up) || upBlock.isLeaves(upState, world, up) || upState.getMaterial() == Material.VINE)
            {
                setBlockAndNotifyAdequately(world, up, woodState);
            }
        }

        return true;
    }
}
