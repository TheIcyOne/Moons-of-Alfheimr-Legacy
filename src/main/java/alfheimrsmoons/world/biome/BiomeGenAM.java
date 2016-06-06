package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.gen.feature.WorldGenAMBigTree;
import alfheimrsmoons.world.gen.feature.WorldGenAMFlowers;
import alfheimrsmoons.world.gen.feature.WorldGenAMTrees;
import alfheimrsmoons.world.gen.feature.WorldGenSedge;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class BiomeGenAM extends BiomeGenBase
{
    protected static final IBlockState STONE = AMBlocks.shale.getDefaultState();
    protected static final WorldGenAMTrees worldGeneratorTrees = new WorldGenAMTrees(false, EnumWoodVariant.BEECH);
    protected static final WorldGenAMBigTree worldGeneratorBigTree = new WorldGenAMBigTree(false, EnumWoodVariant.BEECH);

    private EnumFlowerVariant[] flowerVariants;

    public BiomeGenAM(BiomeProperties properties)
    {
        super(properties);
        topBlock = AMBlocks.grassy_soil.getDefaultState();
        fillerBlock = AMBlocks.soil.getDefaultState();
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
    }

    @Override
    public AMBiomeDecorator createBiomeDecorator()
    {
        return new AMBiomeDecorator();
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        EnumWoodVariant variant = getRandomTreeVariant(rand);
        return rand.nextInt(10) == 0 ? new WorldGenAMBigTree(false, variant) : new WorldGenAMTrees(false, variant);
    }

    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return EnumWoodVariant.BEECH;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return getGrassWorldGen();
    }

    public WorldGenerator getGrassWorldGen()
    {
        return new WorldGenSedge();
    }

    public WorldGenerator getFlowerWorldGen()
    {
        return flowerVariants != null ? new WorldGenAMFlowers(flowerVariants) : null;
    }

    public EnumFlowerVariant getRandomFlowerVariant(Random rand, BlockPos pos)
    {
        return flowerVariants != null ? flowerVariants[rand.nextInt(flowerVariants.length)] : null;
    }

    public BiomeGenAM setFlowerVariants(EnumFlowerVariant... flowerVariants)
    {
        this.flowerVariants = flowerVariants;
        return this;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double noiseVal)
    {
        int seaLevel = world.getSeaLevel();
        IBlockState top = topBlock;
        IBlockState filler = fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkZ = x & 15;
        int chunkX = z & 15;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int chunkY = 255; chunkY >= 0; --chunkY)
        {
            if (chunkY <= rand.nextInt(5))
            {
                chunkPrimer.setBlockState(chunkX, chunkY, chunkZ, BEDROCK);
            }
            else
            {
                IBlockState state = chunkPrimer.getBlockState(chunkX, chunkY, chunkZ);

                if (state.getMaterial() == Material.air)
                {
                    j = -1;
                }
                else if (state.getBlock() == STONE.getBlock())
                {
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            top = AIR;
                            filler = STONE;
                        }
                        else if (chunkY >= seaLevel - 4 && chunkY <= seaLevel + 1)
                        {
                            top = topBlock;
                            filler = fillerBlock;
                        }

                        if (chunkY < seaLevel && (top == null || top.getMaterial() == Material.air))
                        {
                            if (getFloatTemperature(pos.set(x, chunkY, z)) < 0.15F)
                            {
                                top = ICE;
                            }
                            else
                            {
                                top = WATER;
                            }
                        }

                        j = k;

                        if (chunkY >= seaLevel - 1)
                        {
                            chunkPrimer.setBlockState(chunkX, chunkY, chunkZ, top);
                        }
                        else if (chunkY < seaLevel - 7 - k)
                        {
                            top = AIR;
                            filler = STONE;
                        }
                        else
                        {
                            chunkPrimer.setBlockState(chunkX, chunkY, chunkZ, filler);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        chunkPrimer.setBlockState(chunkX, chunkY, chunkZ, filler);
                    }
                }
            }
        }
    }
}
