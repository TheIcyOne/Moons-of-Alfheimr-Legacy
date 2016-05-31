package alfheimrsmoons.world.biome;

import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockAMOre.EnumType;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.gen.feature.layer.WorldGenAMLiquids;
import alfheimrsmoons.world.gen.feature.layer.WorldGenAMMinable;
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
    public WorldGenerator soilGen;
    public WorldGenerator nitroGen;
    public WorldGenerator tektiteGen;
    public WorldGenerator sylvaniteGen;
    public WorldGenerator waterLakeGen;
    public WorldGenerator lavaLakeGen;

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
            nitroGen = new WorldGenAMMinable(AMBlocks.ore.getDefaultState().withProperty(BlockAMOre.VARIANT, BlockAMOre.EnumType.NITRO), 17);
            tektiteGen = new WorldGenAMMinable(AMBlocks.ore.getDefaultState().withProperty(BlockAMOre.VARIANT, BlockAMOre.EnumType.TEKTITE), 9);
            sylvaniteGen = new WorldGenAMMinable(AMBlocks.ore.getDefaultState().withProperty(BlockAMOre.VARIANT, EnumType.SYLVANITE), 8);
            waterLakeGen = new WorldGenAMLiquids(Blocks.flowing_water.getDefaultState());
            lavaLakeGen = new WorldGenAMLiquids(Blocks.flowing_lava.getDefaultState());
            genDecorations(biome, world, random);
            field_185425_a = false;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biome, World world, Random random)
    {
        generateOres(world, random);

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

        if (generateLakes)
        {
            for (int i = 0; i < 50; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int yOffset = random.nextInt(random.nextInt(248) + 8);
                BlockPos pos = field_180294_c.add(xOffset, yOffset, zOffset);
                waterLakeGen.generate(world, random, pos);
            }

            for (int i = 0; i < 20; ++i)
            {
                int xOffset = random.nextInt(16) + 8;
                int zOffset = random.nextInt(16) + 8;
                int yOffset = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
                BlockPos pos = field_180294_c.add(xOffset, yOffset, zOffset);
                lavaLakeGen.generate(world, random, pos);
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
