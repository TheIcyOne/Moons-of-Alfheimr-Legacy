package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

import java.util.Random;

public class WorldGenAMBlockBlob extends WorldGenBlockBlob
{
    private final IBlockState state;
    private final int startRadius;

    public WorldGenAMBlockBlob(IBlockState state, int startRadius)
    {
        super(state.getBlock(), startRadius);
        this.state = state;
        this.startRadius = startRadius;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        for (; position.getY() > 3; position = position.down())
        {
            if (world.isAirBlock(position.down()))
            {
                continue;
            }

            Block block = world.getBlockState(position.down()).getBlock();

            if (block != AMBlocks.grassy_soil && block != AMBlocks.soil && block != AMBlocks.shale)
            {
                continue;
            }

            int radius = startRadius;

            for (int i = 0; radius >= 0 && i < 3; ++i)
            {
                int xOffset = radius + rand.nextInt(2);
                int yOffset = radius + rand.nextInt(2);
                int zOffset = radius + rand.nextInt(2);
                float f = (float)(xOffset + yOffset + zOffset) * 0.333F + 0.5F;

                for (BlockPos blockPos : BlockPos.getAllInBox(position.add(-xOffset, -yOffset, -zOffset), position.add(xOffset, yOffset, zOffset)))
                {
                    if (blockPos.distanceSq(position) <= (double)(f * f))
                    {
                        world.setBlockState(blockPos, state, 4);
                    }
                }

                position = position.add(-(radius + 1) + rand.nextInt(2 + radius * 2), 0 - rand.nextInt(2), -(radius + 1) + rand.nextInt(2 + radius * 2));
            }

            return true;
        }

        return false;
    }
}
