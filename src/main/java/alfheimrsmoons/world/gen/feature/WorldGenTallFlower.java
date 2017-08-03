package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTallFlower extends WorldGenerator
{
    private final VariantFlower variant;

    public WorldGenTallFlower(VariantFlower variant)
    {
        this.variant = variant;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        boolean generated = false;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos flowerPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(flowerPos) && (world.provider.hasSkyLight() || flowerPos.getY() < 254))
            {
                if (AMBlocks.FLOWERS.placeTallFlower(world, flowerPos, variant))
                {
                    generated = true;
                }
            }
        }

        return generated;
    }
}
