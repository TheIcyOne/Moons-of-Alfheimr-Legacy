package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.block.BlockShale;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLiquids;

import java.util.Random;

public class WorldGenLiquidsAM extends WorldGenLiquids
{
    private final IBlockState state;

    public WorldGenLiquidsAM(IBlockState state)
    {
        super(state.getBlock());
        this.state = state;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        if (!(world.getBlockState(position.up()).getBlock() instanceof BlockShale))
        {
            return false;
        }

        if (!(world.getBlockState(position.down()).getBlock() instanceof BlockShale))
        {
            return false;
        }

        if (!world.isAirBlock(position) && !(world.getBlockState(position).getBlock() instanceof BlockShale))
        {
            return false;
        }

        int stoneCount = 0;

        if (world.getBlockState(position.west()).getBlock() instanceof BlockShale)
        {
            ++stoneCount;
        }

        if (world.getBlockState(position.east()).getBlock() instanceof BlockShale)
        {
            ++stoneCount;
        }

        if (world.getBlockState(position.north()).getBlock() instanceof BlockShale)
        {
            ++stoneCount;
        }

        if (world.getBlockState(position.south()).getBlock() instanceof BlockShale)
        {
            ++stoneCount;
        }

        int airCount = 0;

        if (world.isAirBlock(position.west()))
        {
            ++airCount;
        }

        if (world.isAirBlock(position.east()))
        {
            ++airCount;
        }

        if (world.isAirBlock(position.north()))
        {
            ++airCount;
        }

        if (world.isAirBlock(position.south()))
        {
            ++airCount;
        }

        if (stoneCount == 3 && airCount == 1)
        {
            world.setBlockState(position, state, 2);
            world.immediateBlockTick(position, state, rand);
        }

        return true;
    }
}
