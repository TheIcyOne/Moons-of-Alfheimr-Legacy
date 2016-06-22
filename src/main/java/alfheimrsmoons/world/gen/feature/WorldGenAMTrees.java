package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumWoodVariant;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class WorldGenAMTrees extends WorldGenTrees
{
    private static final ImmutableSet<Block> REPLACEABLE_BLOCKS = ImmutableSet.of(AMBlocks.GRASSY_SOIL, AMBlocks.SOIL, AMBlocks.LOG, AMBlocks.LOG2, AMBlocks.SAPLING);

    public WorldGenAMTrees(boolean notify, EnumWoodVariant variant)
    {
        this(notify, variant, false);
    }

    public WorldGenAMTrees(boolean notify, IBlockState metaWood, IBlockState metaLeaves)
    {
        this(notify, 4, metaWood, metaLeaves, false);
    }

    public WorldGenAMTrees(boolean notify, EnumWoodVariant variant, boolean vinesGrow)
    {
        this(notify, 4, variant.getLogState(), variant.getLeavesState(), vinesGrow);
    }

    public WorldGenAMTrees(boolean notify, int minTreeHeight, IBlockState metaWood, IBlockState metaLeaves, boolean vinesGrow)
    {
        super(notify, minTreeHeight, metaWood, metaLeaves, vinesGrow);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        int height = rand.nextInt(3) + minTreeHeight;
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

        if (!downState.getBlock().canSustainPlant(downState, world, down, EnumFacing.UP, AMBlocks.SAPLING) || y >= world.getHeight() - height - 1)
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
                            setBlockAndNotifyAdequately(world, blockPos, metaLeaves);
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
                setBlockAndNotifyAdequately(world, up, metaWood);

                if (vinesGrow && yOffset > 0)
                {
                    BlockPos west = position.add(-1, yOffset, 0);
                    BlockPos east = position.add(1, yOffset, 0);
                    BlockPos north = position.add(0, yOffset, -1);
                    BlockPos south = position.add(0, yOffset, 1);

                    if (rand.nextInt(3) > 0 && world.isAirBlock(west))
                    {
                        addVine(world, west, BlockVine.EAST);
                    }

                    if (rand.nextInt(3) > 0 && world.isAirBlock(east))
                    {
                        addVine(world, east, BlockVine.WEST);
                    }

                    if (rand.nextInt(3) > 0 && world.isAirBlock(north))
                    {
                        addVine(world, north, BlockVine.SOUTH);
                    }

                    if (rand.nextInt(3) > 0 && world.isAirBlock(south))
                    {
                        addVine(world, south, BlockVine.NORTH);
                    }
                }
            }
        }

        if (vinesGrow)
        {
            for (int blockY = maxY - 3; blockY <= maxY; ++blockY)
            {
                int yDistance = blockY - maxY;

                int radius = 2 - yDistance / 2;
                int minX = x - radius;
                int maxX = x + radius;
                int minZ = z - radius;
                int maxZ = z + radius;

                for (int blockX = minX; blockX <= maxX; ++blockX)
                {
                    for (int blockZ = minZ; blockZ <= maxZ; ++blockZ)
                    {
                        BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
                        IBlockState blockState = world.getBlockState(blockPos);

                        if (blockState.getBlock().isLeaves(blockState, world, blockPos))
                        {
                            BlockPos west = blockPos.west();
                            BlockPos east = blockPos.east();
                            BlockPos north = blockPos.north();
                            BlockPos south = blockPos.south();

                            if (rand.nextInt(4) == 0 && world.isAirBlock(west))
                            {
                                addHangingVine(world, west, BlockVine.EAST);
                            }

                            if (rand.nextInt(4) == 0 && world.isAirBlock(east))
                            {
                                addHangingVine(world, east, BlockVine.WEST);
                            }

                            if (rand.nextInt(4) == 0 && world.isAirBlock(north))
                            {
                                addHangingVine(world, north, BlockVine.SOUTH);
                            }

                            if (rand.nextInt(4) == 0 && world.isAirBlock(south))
                            {
                                addHangingVine(world, south, BlockVine.NORTH);
                            }
                        }
                    }
                }
            }
        }

        return true;
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
        return super.canGrowInto(block) || isReplaceable(block);
    }

    public static boolean isReplaceable(Block block)
    {
        return REPLACEABLE_BLOCKS.contains(block);
    }
}
