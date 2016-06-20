package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumDeadPlantVariant;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

import java.util.Random;

public class WorldGenDeadPlant extends WorldGenDeadBush
{
    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        do
        {
            IBlockState state = world.getBlockState(position);
            Block block = state.getBlock();

            if (!block.isAir(state, world, position) && !block.isLeaves(state, world, position))
                break;

            position = position.down();
        }
        while (position.getY() > 0);

        EnumDeadPlantVariant variant = VariantHelper.getRandomVariant(AMBlocks.DEAD_PLANT, rand);
        IBlockState state = VariantHelper.getDefaultStateWithVariant(AMBlocks.DEAD_PLANT, variant);

        for (int i = 0; i < 4; ++i)
        {
            BlockPos saplingPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(saplingPos) && state.getBlock().canPlaceBlockAt(world, saplingPos))
            {
                world.setBlockState(saplingPos, state, 2);
            }
        }

        return true;
    }
}
