package alfheimrsmoons.world.gen;

import alfheimrsmoons.combo.VariantShale;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.biome.BiomeVeld;
import alfheimrsmoons.world.gen.feature.WorldGenLakesAM;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorAlfheimr implements IChunkGenerator
{
    private IBlockState waterBlock = Blocks.WATER.getDefaultState();
    private IBlockState iceBlock = Blocks.ICE.getDefaultState();
    private IBlockState snowLayerBlock = Blocks.SNOW_LAYER.getDefaultState();
    private WorldGenerator waterLakeGen = new WorldGenLakesAM(waterBlock);

    protected static final IBlockState STONE = AMBlocks.SHALE.getBlockState(VariantShale.NORMAL);
    private final Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;
    private final World world;
    private final boolean mapFeaturesEnabled;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private ChunkProviderSettings settings;
    private IBlockState oceanBlock = waterBlock;
    private double[] depthBuffer = new double[256];
    private MapGenBase caveGenerator = new MapGenCavesAM();
    private MapGenBase ravineGenerator = new MapGenRavineAM();
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    public ChunkGeneratorAlfheimr(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions)
    {
        this.world = world;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.terrainType = world.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(rand, 8);
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((i * i + j * j) + 0.2F);
                biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }

        if (generatorOptions != null)
        {
            settings = ChunkProviderSettings.Factory.jsonToFactory(generatorOptions).build();
            oceanBlock = waterBlock;
            world.setSeaLevel(settings.seaLevel);
        }
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = heightMap[i1 + i2];
                    double d2 = heightMap[j1 + i2];
                    double d3 = heightMap[k1 + i2];
                    double d4 = heightMap[l1 + i2];
                    double d5 = (heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (heightMap[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
                                }
                                else if (i2 * 8 + j2 < settings.seaLevel)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, oceanBlock);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomes)
    {
        double d0 = 0.03125D;
        depthBuffer = surfaceNoise.getRegion(depthBuffer, (double) (x * 16), (double) (z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomes[j + i * 16];
                biome.genTerrainBlocks(world, rand, primer, x * 16 + i, z * 16 + j, depthBuffer[j + i * 16]);
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z)
    {
    	rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer primer = new ChunkPrimer();
        setBlocksInChunk(x, z, primer);
        biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x * 16, z * 16, 16, 16);
        replaceBiomeBlocks(x, z, primer, biomesForGeneration);

        if (settings.useCaves)
        {
            caveGenerator.generate(world, x, z, primer);
        }

        if (settings.useRavines)
        {
            ravineGenerator.generate(world, x, z, primer);
        }

        if (mapFeaturesEnabled)
        {
            // Generate map features
        }

        Chunk chunk = new Chunk(world, primer, x, z);
        byte[] chunkBiomeArray = chunk.getBiomeArray();

        for (int i = 0; i < chunkBiomeArray.length; ++i)
        {
            chunkBiomeArray[i] = (byte) Biome.getIdForBiome(biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateHeightmap(int xOffset, int yOffset, int zOffset)
    {
        depthRegion = depthNoise.generateNoiseOctaves(depthRegion, xOffset, zOffset, 5, 5, (double) settings.depthNoiseScaleX, (double) settings.depthNoiseScaleZ, (double) settings.depthNoiseScaleExponent);
        float coordinateScale = settings.coordinateScale;
        float heightScale = settings.heightScale;
        mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, xOffset, yOffset, zOffset, 5, 33, 5, (double) (coordinateScale / settings.mainNoiseScaleX), (double) (heightScale / settings.mainNoiseScaleY), (double) (coordinateScale / settings.mainNoiseScaleZ));
        minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, xOffset, yOffset, zOffset, 5, 33, 5, (double) coordinateScale, (double) heightScale, (double) coordinateScale);
        maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, xOffset, yOffset, zOffset, 5, 33, 5, (double) coordinateScale, (double) heightScale, (double) coordinateScale);
        zOffset = 0;
        xOffset = 0;
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biomegenbase = biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -i1; j1 <= i1; ++j1)
                {
                    for (int k1 = -i1; k1 <= i1; ++k1)
                    {
                        Biome biomegenbase1 = biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = settings.biomeDepthOffSet + biomegenbase1.getBaseHeight() * settings.biomeDepthWeight;
                        float f6 = settings.biomeScaleOffset + biomegenbase1.getHeightVariation() * settings.biomeScaleWeight;

                        if (terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double) f3;
                double d9 = (double) f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double) settings.baseSize / 8.0D;
                double d0 = (double) settings.baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double) l1 - d0) * (double) settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = minLimitRegion[i] / (double) settings.lowerLimitScale;
                    double d3 = maxLimitRegion[i] / (double) settings.upperLimitScale;
                    double d4 = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clamp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double) ((float) (l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    @Override
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int blockX = x * 16;
        int blockZ = z * 16;
        BlockPos pos = new BlockPos(blockX, 0, blockZ);
        Biome biome = world.getBiome(pos.add(16, 0, 16));
        rand.setSeed(world.getSeed());
        long k = rand.nextLong() / 2L * 2L + 1L;
        long l = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed((long) x * k + (long) z * l ^ world.getSeed());
        boolean hasVillageGenerated = false;
        ChunkPos chunkPos = new ChunkPos(x, z);

        if (mapFeaturesEnabled)
        {
            // Generate map features
        }

        if (!(biome instanceof BiomeVeld) && settings.useWaterLakes && !hasVillageGenerated && rand.nextInt(settings.waterLakeChance) == 0)
        {
            int xOffset = rand.nextInt(16) + 8;
            int yOffset = rand.nextInt(256);
            int zOffset = rand.nextInt(16) + 8;
            waterLakeGen.generate(world, rand, pos.add(xOffset, yOffset, zOffset));
        }

//        if (settings.useDungeons)
//        {
//            for (int i = 0; i < settings.dungeonChance; ++i)
//            {
//                int xOffset = rand.nextInt(16) + 8;
//                int yOffset = rand.nextInt(256);
//                int zOffset = rand.nextInt(16) + 8;
//                (new WorldGenDungeons()).generate(world, rand, pos.add(xOffset, yOffset, zOffset));
//            }
//        }

        biome.decorate(world, rand, new BlockPos(blockX, 0, blockZ));

        WorldEntitySpawner.performWorldGenSpawning(world, biome, blockX + 8, blockZ + 8, 16, 16, rand);

        pos = pos.add(8, 0, 8);

        for (int xOffset = 0; xOffset < 16; ++xOffset)
        {
            for (int zOffset = 0; zOffset < 16; ++zOffset)
            {
                BlockPos precipitationHeight = world.getPrecipitationHeight(pos.add(xOffset, 0, zOffset));
                BlockPos surfaceHeight = precipitationHeight.down();

                if (world.canBlockFreezeWater(surfaceHeight))
                {
                    world.setBlockState(surfaceHeight, iceBlock, 2);
                }

                if (world.canSnowAt(precipitationHeight, true))
                {
                    world.setBlockState(precipitationHeight, snowLayerBlock, 2);
                }
            }
        }

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunk, int x, int z)
    {
        return false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = world.getBiome(pos);

        if (mapFeaturesEnabled)
        {
            // Return spawn list of map feature if at correct position
        }

        return biome.getSpawnableList(creatureType);
    }

    
    //TODO: Structures 'n stuff
    
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z)
    {
        if (mapFeaturesEnabled)
        {
            // Generate map features
        }
    }
}
