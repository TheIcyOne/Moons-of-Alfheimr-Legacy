package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumTallFlowerVariant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTallFlower extends WorldGenerator
{
    private EnumTallFlowerVariant variant;

    public WorldGenTallFlower(EnumTallFlowerVariant variant)
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

            if (world.isAirBlock(flowerPos) && (!world.provider.getHasNoSky() || flowerPos.getY() < 254) && AMBlocks.tall_flower.canPlaceBlockAt(world, flowerPos))
            {
                AMBlocks.tall_flower.placeAt(world, flowerPos, variant, 2);
                generated = true;
            }
        }

        return generated;
    }
}
