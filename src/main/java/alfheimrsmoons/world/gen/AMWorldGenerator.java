package alfheimrsmoons.world.gen;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class AMWorldGenerator implements IWorldGenerator
{
    private static final int OVERWORLD_ID = 0;
    private static final int NETHER_ID = -1;
    private static final int THE_END_ID = 1;

    private static final int CHUNK_SIZE = 16;

    private static final int MANNA_VEIN_SIZE = 9;
    private static final int MANNA_GEN_COUNT = 20;
    private static final int MANNA_MIN_HEIGHT = 10;
    private static final int MANNA_MAX_HEIGHT = 118;
    private static final WorldGenerator MANNA_GEN = new WorldGenMinable(AMBlocks.MANNA_ORE.getDefaultState(), MANNA_VEIN_SIZE, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        BlockPos chunkPos = new BlockPos(chunkX * CHUNK_SIZE, 0, chunkZ * CHUNK_SIZE);

        switch (world.provider.getDimension())
        {
            case OVERWORLD_ID:
                genOverworld(world, random, chunkPos);
                break;
            case NETHER_ID:
                genNether(world, random, chunkPos);
                break;
            case THE_END_ID:
                genTheEnd(world, random, chunkPos);
                break;
        }
    }

    private void genOverworld(World world, Random random, BlockPos chunkPos) {}

    private void genNether(World world, Random random, BlockPos chunkPos)
    {
        genStandardOre(world, random, chunkPos, MANNA_GEN_COUNT, MANNA_GEN, MANNA_MAX_HEIGHT, MANNA_MIN_HEIGHT);
    }

    private void genTheEnd(World world, Random random, BlockPos chunkPos) {}

    /**
     * Generates ore with the standard method of generating ore in a chunk at a
     * random position between a minimum height (inclusive) and a maximum
     * height (exclusive).
     *
     * @param world The world
     * @param random The RNG
     * @param chunkPos The chunk position
     * @param genCount The number of times to generate
     * @param generator The ore generator
     * @param minY The minimum Y position (inclusive)
     * @param maxY The maximum Y position (exclusive)
     */
    private void genStandardOre(World world, Random random, BlockPos chunkPos, int genCount, WorldGenerator generator, int minY, int maxY)
    {
        if (maxY < minY)
        {
            int i = minY;
            minY = maxY;
            maxY = i;
        }
        else if (maxY == minY)
        {
            if (minY >= world.getHeight())
            {
                --minY;
            }
            else
            {
                ++maxY;
            }
        }

        for (int i = 0; i < genCount; ++i)
        {
            BlockPos pos = chunkPos.add(random.nextInt(CHUNK_SIZE), random.nextInt(maxY - minY) + minY, random.nextInt(CHUNK_SIZE));
            generator.generate(world, random, pos);
        }
    }
}
