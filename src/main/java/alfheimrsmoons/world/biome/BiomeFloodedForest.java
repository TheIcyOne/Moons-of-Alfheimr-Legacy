package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.world.gen.feature.WorldGenFloodedTree;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeFloodedForest extends AMBiome
{
    private static final WorldGenFloodedTree FLOODED_TREE_GEN = new WorldGenFloodedTree();

    public BiomeFloodedForest(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 2;
        theBiomeDecorator.flowersPerChunk = 1;
        theBiomeDecorator.deadBushPerChunk = 0;
        theBiomeDecorator.mushroomsPerChunk = 8;
        theBiomeDecorator.reedsPerChunk = 10;
        theBiomeDecorator.clayPerChunk = 1;
        theBiomeDecorator.waterlilyPerChunk = 4;
        theBiomeDecorator.sandPerChunk2 = 0;
        theBiomeDecorator.sandPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 5;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return FLOODED_TREE_GEN;
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariant(EnumFlowerVariant.SKULLCAP, 20);
        addFlowerVariant(EnumFlowerVariant.PHILODENDRON, 10);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double noiseVal)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double) x * 0.25D, (double) z * 0.25D);

        if (d0 > 0.0D)
        {
            int chunkZ = x & 15;
            int chunkX = z & 15;

            for (int chunkY = 255; chunkY >= 0; --chunkY)
            {
                if (chunkPrimer.getBlockState(chunkX, chunkY, chunkZ).getMaterial() != Material.AIR)
                {
                    if (chunkY == 62 && chunkPrimer.getBlockState(chunkX, chunkY, chunkZ).getBlock() != Blocks.WATER)
                    {
                        chunkPrimer.setBlockState(chunkX, chunkY, chunkZ, WATER);
                    }

                    break;
                }
            }
        }

        super.genTerrainBlocks(world, rand, chunkPrimer, x, z, noiseVal);
    }
}
