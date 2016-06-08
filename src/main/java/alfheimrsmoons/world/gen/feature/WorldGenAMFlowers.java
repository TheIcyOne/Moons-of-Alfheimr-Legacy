package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.block.BlockAMFlower;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenAMFlowers extends WorldGenerator
{
    private IBlockState state;

    public WorldGenAMFlowers(IBlockState state)
    {
        this.state = state;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos flowerPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(flowerPos) && (!world.provider.getHasNoSky() || flowerPos.getY() < 255) && state.getBlock().canPlaceBlockAt(world, flowerPos))
            {
                world.setBlockState(flowerPos, state, 2);
            }
        }

        return true;
    }
}
