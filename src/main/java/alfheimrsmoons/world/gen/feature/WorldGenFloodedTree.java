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

public class WorldGenFloodedTree extends WorldGenAbstractTreeAM
{
    private static final IBlockState LOG = AMBlocks.TREES.getBlockState(ComboTrees.LOG, VariantTree.ELM);
    private static final IBlockState LEAVES = AMBlocks.TREES.getBlockState(ComboTrees.LEAVES, VariantTree.ELM).withProperty(BlockLeaves.CHECK_DECAY, false);
    private static final IPlantable SAPLING = AMBlocks.TREES.getBlock(ComboTrees.SAPLING, VariantTree.ELM);

    public WorldGenFloodedTree()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        while (world.getBlockState(position.down()).getMaterial() == Material.WATER)
        {
            position = position.down();
        }

        int height = rand.nextInt(4) + 5;
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
                radius = 2;//radius = 3 with vines
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

                    BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
                    IBlockState blockState = world.getBlockState(blockPos);
                    Block block = blockState.getBlock();

                    if (block.isAir(blockState, world, blockPos) || block.isLeaves(blockState, world, blockPos))
                    {
                        continue;
                    }

                    if (blockState.getMaterial() != Material.WATER)
                    {
                        return false;
                    }

                    if (blockY > y)
                    {
                        return false;
                    }
                }
            }
        }

        BlockPos down = position.down();
        IBlockState downState = world.getBlockState(down);
        Block downBlock = downState.getBlock();

        if (!downBlock.canSustainPlant(downState, world, down, EnumFacing.UP, SAPLING) || y >= world.getHeight() - height - 1)
        {
            return false;
        }

        downBlock.onPlantGrow(downState, world, down, position);

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
                int xDistance = Math.abs(blockX - x);

                for (int blockZ = minZ; blockZ <= maxZ; ++blockZ)
                {
                    int zDistance = Math.abs(blockZ - z);

                    if (xDistance != radius || zDistance != radius || rand.nextInt(2) != 0 && yDistance != 0)
                    {
                        BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
                        IBlockState blockState = world.getBlockState(blockPos);

                        if (blockState.getBlock().canBeReplacedByLeaves(blockState, world, blockPos))
                        {
                            setBlockAndNotifyAdequately(world, blockPos, LEAVES);
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

            if (upBlock.isAir(upState, world, up) || upBlock.isLeaves(upState, world, up) || upState.getMaterial() == Material.WATER)
            {
                setBlockAndNotifyAdequately(world, up, LOG);
            }
        }

        return true;
    }
}
