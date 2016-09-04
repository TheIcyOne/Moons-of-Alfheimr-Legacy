package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.combo.ComboFlowers;
import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFlowersAM extends WorldGenerator
{
    private final IBlockState state;

    public WorldGenFlowersAM(VariantFlower variant)
    {
        this(AMBlocks.FLOWERS.getBlockState(ComboFlowers.FLOWER, variant));
    }

    public WorldGenFlowersAM(IBlockState state)
    {
        this.state = state;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos flowerPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(flowerPos) && (!world.provider.getHasNoSky() || flowerPos.getY() < 255))
            {
                Block block = state.getBlock();
                boolean canPlace;

                if (block instanceof BlockBush)
                {
                    canPlace = ((BlockBush) block).canBlockStay(world, flowerPos, state);
                }
                else
                {
                    canPlace = block.canPlaceBlockAt(world, flowerPos);
                }

                if (canPlace)
                {
                    world.setBlockState(flowerPos, state, 2);
                }
            }
        }

        return true;
    }
}
