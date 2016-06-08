package alfheimrsmoons.world.biome;

import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.gen.feature.WorldGenAMSand;
import alfheimrsmoons.world.gen.feature.WorldGenAMLiquids;
import alfheimrsmoons.world.gen.feature.WorldGenAMMinable;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class AMBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator sedimentGen = new WorldGenAMSand(AMBlocks.sediment.getDefaultState(), 7);
    public WorldGenerator soilGen;
    public WorldGenerator nitroGen;
    public WorldGenerator tektiteGen;
    public WorldGenerator sylvaniteGen;
    public WorldGenerator waterLakeGen;

    @Override
    public void decorate(World world, Random random, BiomeGenBase biome, BlockPos pos)
    {
        if (field_185425_a)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            field_180294_c = pos;
            // TODO configurable ore sizes, counts, and heights
            soilGen = new WorldGenAMMinable(AMBlocks.soil.getDefaultState(), 33);
            nitroGen = new WorldGenAMMinable(EnumOreVariant.NITRO, 17);
            tektiteGen = new WorldGenAMMinable(EnumOreVariant.TEKTITE, 9);
            sylvaniteGen = new WorldGenAMMinable(EnumOreVariant.SYLVANITE, 8);
            waterLakeGen = new WorldGenAMLiquids(Blocks.flowing_water.getDefaultState());
            genDecorations(biome, world, random);
            field_185425_a = false;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biome, World world, Random random)
    {
        if (!(biome instanceof BiomeGenAM))
        {
            super.genDecorations(biome, world, random);
            return;
        }

        BiomeGenAM alfheimrBiome = (BiomeGenAM) biome;;

        generateOres(world, random);

        for (int i = 0; i < sandPerChunk2; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int yOffset = random.nextInt(16) + 8;
            sedimentGen.generate(world, random, world.getTopSolidOrLiquidBlock(field_180294_c.add(xOffset, 0, yOffset)));
        }

        int trees = treesPerChunk;

        if (random.nextInt(10) == 0)
        {
            ++trees;
        }

        for (int i = 0; i < trees; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int yOffset = random.nextInt(16) + 8;
            WorldGenAbstractTree treeGen = biome.genBigTreeChance(random);
            treeGen.func_175904_e();
            BlockPos pos = world.getHeight(field_180294_c.add(xOffset, 0, yOffset));

            if (treeGen.generate(world, random, pos))
            {
                treeGen.func_180711_a(world, random, pos);
            }
        }

        WorldGenerator flowerGen = alfheimrBiome.getFlowerWorldGen();

        if (flowerGen != null)
        {
            for (int i = 0; i < flowersPerChunk; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int height = world.getHeight(field_180294_c.add(xOffset, 0, zOffset)).getY() + 32;

                if (height > 0)
                {
                    int yOffset = random.nextInt(height);
                    BlockPos pos = field_180294_c.add(xOffset, yOffset, zOffset);
                    flowerGen.generate(world, random, pos);
                }
            }
        }

        for (int i = 0; i < grassPerChunk; ++i)
        {
            int xOffset = random.nextInt(16) + 8;
            int zOffset = random.nextInt(16) + 8;
            int height = world.getHeight(field_180294_c.add(xOffset, 0, zOffset)).getY() * 2;

            if (height > 0)
            {
                int yOffset = random.nextInt(height);
                alfheimrBiome.getGrassWorldGen().generate(world, random, field_180294_c.add(xOffset, yOffset, zOffset));
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
                    BlockPos pos = field_180294_c.add(xOffset, yOffset, zOffset);
                    waterLakeGen.generate(world, random, pos);
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
