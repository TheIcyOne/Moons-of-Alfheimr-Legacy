package alfheimrsmoons.world.biome;

import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.world.gen.feature.WorldGenFloodedTree;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeFloodedForest extends BiomeAM
{
    private static final WorldGenFloodedTree FLOODED_TREE_GEN = new WorldGenFloodedTree();

    public BiomeFloodedForest(BiomeProperties properties)
    {
        super(properties);
       decorator.treesPerChunk = 2;
       decorator.flowersPerChunk = 1;
       decorator.deadBushPerChunk = 0;
       decorator.mushroomsPerChunk = 8;
       decorator.reedsPerChunk = 10;
       decorator.clayPerChunk = 1;
       decorator.waterlilyPerChunk = 4;
       decorator.gravelPatchesPerChunk = 0;
       decorator.sandPatchesPerChunk = 0;
       decorator.grassPerChunk = 5;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return FLOODED_TREE_GEN;
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariant(VariantFlower.SKULLCAP, 20);
        addFlowerVariant(VariantFlower.PHILODENDRON, 10);
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
