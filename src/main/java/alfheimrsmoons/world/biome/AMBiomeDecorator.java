package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.VariantHelper;
import alfheimrsmoons.world.gen.feature.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class AMBiomeDecorator extends BiomeDecorator
{
    // TODO configurable ore sizes, counts, and heights
    private final WorldGenAMSand sedimentGen = new WorldGenAMSand(AMBlocks.SEDIMENT.getDefaultState(), 7);
    private final WorldGenAMMinable soilGen = new WorldGenAMMinable(AMBlocks.SOIL.getDefaultState(), 33);
    private final WorldGenAMMinable nitroGen = new WorldGenAMMinable(EnumOreVariant.NITRO, 17);
    private final WorldGenAMMinable tektiteGen = new WorldGenAMMinable(EnumOreVariant.TEKTITE, 9);
    private final WorldGenAMMinable sylvaniteGen = new WorldGenAMMinable(EnumOreVariant.SYLVANITE, 8);
    private final WorldGenAMFlowers snapdragonGen = new WorldGenAMFlowers(VariantHelper.getDefaultStateWithVariant(AMBlocks.FLOWER, EnumFlowerVariant.SNAPDRAGON));
    private final WorldGenDeadPlant deadPlantGen = new WorldGenDeadPlant();
    private final WorldGenAMLiquids waterGen = new WorldGenAMLiquids(Blocks.FLOWING_WATER.getDefaultState());

    @Override
    public void decorate(World world, Random random, Biome biome, BlockPos pos)
    {
        if (decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            chunkPos = pos;
            genDecorations(biome, world, random);
            decorating = false;
        }
    }

    @Override
    protected void genDecorations(Biome biome, World world, Random random)
    {
        if (!(biome instanceof AMBiome))
        {
            super.genDecorations(biome, world, random);
            return;
        }

        AMBiome alfheimrBiome = (AMBiome) biome;

        generateOres(world, random);

        for (int i = 0; i < sandPerChunk2; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int yOffset = random.nextInt(16) + 8;
            sedimentGen.generate(world, random, world.getTopSolidOrLiquidBlock(chunkPos.add(xOffset, 0, yOffset)));
        }

        if (alfheimrBiome.hasTrees())
        {
            int trees = treesPerChunk;

            if (random.nextInt(10) == 0)
            {
                ++trees;
            }

            for (int i = 0; i < trees; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int yOffset = random.nextInt(16) + 8;
                WorldGenAbstractTree treeGen = alfheimrBiome.genBigTreeChance(random);
                treeGen.setDecorationDefaults();
                BlockPos pos = world.getHeight(chunkPos.add(xOffset, 0, yOffset));

                if (treeGen.generate(world, random, pos))
                {
                    treeGen.generateSaplings(world, random, pos);
                }
            }
        }

        if (alfheimrBiome.hasFlowers())
        {
            for (int i = 0; i < flowersPerChunk; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int height = world.getHeight(chunkPos.add(xOffset, 0, zOffset)).getY() + 32;

                if (height > 0)
                {
                    int yOffset = random.nextInt(height);
                    BlockPos pos = chunkPos.add(xOffset, yOffset, zOffset);
                    new WorldGenAMFlowers(alfheimrBiome.getRandomFlower(random)).generate(world, random, pos);
                }
            }
        }

        for (int i = 0; i < flowersPerChunk; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int zOffset = random.nextInt(16) + 8;
            int yOffset = random.nextInt(256);
            BlockPos pos = chunkPos.add(xOffset, yOffset, zOffset);
            snapdragonGen.generate(world, random, pos);
        }

        if (alfheimrBiome.hasGrass())
        {
            for (int i = 0; i < grassPerChunk; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int height = world.getHeight(chunkPos.add(xOffset, 0, zOffset)).getY() * 2;

                if (height > 0)
                {
                    int yOffset = random.nextInt(height);
                    alfheimrBiome.getRandomWorldGenForGrass(random).generate(world, random, chunkPos.add(xOffset, yOffset, zOffset));
                }
            }
        }

        for (int i = 0; i < deadBushPerChunk; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int zOffset = random.nextInt(16) + 8;
            int height = world.getHeight(chunkPos.add(xOffset, 0, zOffset)).getY() * 2;

            if (height > 0)
            {
                int yOffset = random.nextInt(height);
                deadPlantGen.generate(world, random, chunkPos.add(xOffset, yOffset, zOffset));
            }
        }

        if (generateLakes)
        {
            for (int i = 0; i < 50; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int height = random.nextInt(248) + 8;

                if (height > 0)
                {
                    int yOffset = random.nextInt(height);
                    BlockPos pos = chunkPos.add(xOffset, yOffset, zOffset);
                    waterGen.generate(world, random, pos);
                }
            }
        }
    }

    @Override
    protected void generateOres(World world, Random random)
    {
        genStandardOre1(world, random, 10, soilGen, 0, 256);
        genStandardOre1(world, random, 20, nitroGen, 0, 128);
        genStandardOre1(world, random, 20, tektiteGen, 0, 64);
        genStandardOre1(world, random, 1, sylvaniteGen, 0, 16);
    }
}
