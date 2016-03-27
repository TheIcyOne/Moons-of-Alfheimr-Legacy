package alfheimrsmoons.world.gen;

import java.util.Random;

import alfheimrsmoons.common.block.BlockCrystalOre;
import alfheimrsmoons.common.block.ModBlocks;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenAMOres implements IWorldGenerator {

    public WorldGenAMOres() {
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
        case 0:
            // Generate Surface World
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        case -1:
            // Generate Nether World
            generateNether(world, random, chunkX * 16, chunkZ * 16);
        case 1:
            // Generate End World
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateSurface(World world, Random random, int x, int z) {
        this.addOreSpawn(ModBlocks.crystalOre.getStateFromMeta(BlockCrystalOre.EnumType.KASOLITE.ordinal()), world, random, x, z, 16, 16, 4 + random.nextInt(6), 15, 20, 40);
        this.addOreSpawn(ModBlocks.crystalOre.getStateFromMeta(BlockCrystalOre.EnumType.NITRO.ordinal()), world, random, x, z, 16, 16, 5 + random.nextInt(7), 15, 20, 60);
        this.addOreSpawn(ModBlocks.crystalOre.getStateFromMeta(BlockCrystalOre.EnumType.CORRODIUM.ordinal()), world, random, x, z, 16, 16, 5 + random.nextInt(7), 15, 20, 60);
    }

    private void generateNether(World world, Random random, int x, int z) {

    }

    private void generateEnd(World world, Random random, int x, int z) {

    }

    private void addOreSpawn(IBlockState iBlockState, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancetospawn, int minY,
            int maxY) {
        for (int i = 0; i < chancetospawn; i++) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(iBlockState, maxVeinSize)).generate(world, random, new BlockPos(posX, posY, posZ));
        }
    }
}