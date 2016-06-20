package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumSedgeVariant;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSedge extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        do
        {
            IBlockState state = world.getBlockState(position);
            if (!state.getBlock().isAir(state, world, position) && !state.getBlock().isLeaves(state, world, position))
                break;
            position = position.down();
        }
        while (position.getY() > 0);

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            EnumSedgeVariant sedgeVariant = rand.nextBoolean() ? EnumSedgeVariant.SHORT : EnumSedgeVariant.NORMAL;
            IBlockState sedgeState = VariantHelper.getDefaultStateWithVariant(AMBlocks.SEDGE, sedgeVariant);

            if (world.isAirBlock(blockPos) && AMBlocks.SEDGE.canBlockStay(world, blockPos, sedgeState))
            {
                world.setBlockState(blockPos, sedgeState, 2);
            }
        }

        return true;
    }
}
