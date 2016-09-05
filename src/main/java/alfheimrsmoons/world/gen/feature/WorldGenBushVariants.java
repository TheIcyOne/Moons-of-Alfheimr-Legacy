package alfheimrsmoons.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zaggy1024.combo.VariantsCombo;
import zaggy1024.combo.variant.IMetadata;

import java.util.List;
import java.util.Random;

public class WorldGenBushVariants<V extends IMetadata<V>> extends WorldGenerator
{
    private final VariantsCombo<V, ? extends BlockBush, ?> combo;
    private final List<V> variants;

    private final int iterations;

    public WorldGenBushVariants(VariantsCombo<V, ? extends BlockBush, ?> combo, int iterations)
    {
        this.combo = combo;
        this.variants = combo.getVariants();
        this.iterations = iterations;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        do
        {
            IBlockState state = world.getBlockState(position);
            Block block = state.getBlock();

            if (block.isAir(state, world, position) || block.isLeaves(state, world, position))
            {
                position = position.down();
                continue;
            }

            break;
        }
        while (position.getY() > 0);

        for (int i = 0; i < iterations; ++i)
        {
            BlockPos blockPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockPos))
            {
                V variant = variants.get(rand.nextInt(variants.size()));
                BlockBush block = combo.getBlock(variant);
                IBlockState state = block.getDefaultState().withProperty(combo.getVariantProperty(block), variant);

                if (block.canBlockStay(world, blockPos, state))
                {
                    world.setBlockState(blockPos, state, 2);
                }
            }
        }

        return true;
    }
}
