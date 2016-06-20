package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSand;

import java.util.Random;

public class WorldGenAMSand extends WorldGenSand
{
    private IBlockState state;
    private int radius;

    public WorldGenAMSand(IBlockState state, int radius)
    {
        super(state.getBlock(), radius);
        this.state = state;
        this.radius = radius;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        if (world.getBlockState(position).getMaterial() != Material.WATER)
        {
            return false;
        }
        else
        {
            int i = rand.nextInt(radius - 2) + 2;
            int j = 2;

            for (int k = position.getX() - i; k <= position.getX() + i; ++k)
            {
                for (int l = position.getZ() - i; l <= position.getZ() + i; ++l)
                {
                    int i1 = k - position.getX();
                    int j1 = l - position.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i)
                    {
                        for (int k1 = position.getY() - j; k1 <= position.getY() + j; ++k1)
                        {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = world.getBlockState(blockpos).getBlock();

                            if (block == AMBlocks.SOIL || block == AMBlocks.GRASSY_SOIL)
                            {
                                world.setBlockState(blockpos, state, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
