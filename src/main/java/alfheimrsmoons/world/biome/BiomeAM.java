package alfheimrsmoons.world.biome;

import alfheimrsmoons.combo.ComboFlowers;
import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.combo.VariantShale;
import alfheimrsmoons.combo.VariantTree;
import alfheimrsmoons.entity.EntityNitroWraith;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.gen.feature.WorldGenBigTreeAM;
import alfheimrsmoons.world.gen.feature.WorldGenTreeAM;
import alfheimrsmoons.world.gen.feature.WorldGenSedge;
import alfheimrsmoons.world.gen.feature.WorldGenTallFlower;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class BiomeAM extends Biome
{
    protected static final IBlockState BEDROCK = AMBlocks.YGGDRASIL_LEAVES.getDefaultState();
    protected static final IBlockState STONE = AMBlocks.SHALE.getBlockState(VariantShale.NORMAL);

    protected final List<VariantFlower> tallFlowerVariants;

    public BiomeAM(BiomeProperties properties)
    {
        super(properties);

        topBlock = AMBlocks.GRASSY_SOIL.getDefaultState();
        fillerBlock = AMBlocks.SOIL.getDefaultState();

        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();

        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityNitroWraith.class, 100, 4, 4));

        tallFlowerVariants = new ArrayList<>();
        addFlowerVariants();
    }

    @Override
    public BiomeDecoratorAM createBiomeDecorator()
    {
        return new BiomeDecoratorAM();
    }

    /**
     * Used for tree generation
     *
     * @return true if the biome generates trees
     */
    public boolean hasTrees()
    {
        return true;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        VariantTree variant = getRandomTreeVariant(rand);
        return rand.nextInt(10) == 0 ? new WorldGenBigTreeAM(false, variant) : new WorldGenTreeAM(false, variant);
    }

    /**
     * Gets a random {@link VariantTree} for tree generation
     *
     * @param rand Random number generator
     * @return A random tree variant
     */
    protected VariantTree getRandomTreeVariant(Random rand)
    {
        return VariantTree.BEECH;
    }

    /**
     * Used for grass generation
     *
     * @return true if the biome generates grass (AKA sedges)
     */
    public boolean hasGrass()
    {
        return true;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return new WorldGenSedge();
    }

    /**
     * @deprecated Use {@link #addFlowerVariants()}
     */
    @Override
    @Deprecated
    public final void addDefaultFlowers() {}

    /**
     * Init method for adding flower variants
     *
     * @see #addFlowerVariants(VariantFlower...)
     * @see #addTallFlowerVariants(VariantFlower...)
     */
    protected void addFlowerVariants() {}

    /**
     * Checks if {@link #flowers} is not empty.
     * Used for flower generation.
     *
     * @return true if flowers list is not empty
     */
    public boolean hasFlowers()
    {
        return !flowers.isEmpty();
    }

    /**
     * Gets a random {@link IBlockState} for flower generation
     *
     * @param rand Random number generator
     * @return A random flower state
     */
    public IBlockState getRandomFlower(Random rand)
    {
        return hasFlowers() ? WeightedRandom.getRandomItem(rand, flowers).state : null;
    }

    /**
     * Adds flower variants for flower generation with a weight of 20
     *
     * @param variants flower variants
     * @return this biome instance
     */
    public BiomeAM addFlowerVariants(VariantFlower... variants)
    {
        for (VariantFlower variant : variants)
        {
            addFlowerVariant(variant, 20);
        }

        return this;
    }

    /**
     * Adds a flower variant for flower generation
     *
     * @param variant flower variant
     * @param weight  flower weight (higher number is higher chance (lower is lower))
     * @return this biome instance
     */
    public BiomeAM addFlowerVariant(VariantFlower variant, int weight)
    {
        addFlower(AMBlocks.FLOWERS.getBlockState(ComboFlowers.FLOWER, variant), weight);
        return this;
    }

    @Override
    public void plantFlower(World world, Random rand, BlockPos pos)
    {
        IBlockState state = getRandomFlower(rand);

        if (state == null)
        {
            return;
        }

        if (state.getBlock() instanceof BlockBush)
        {
            BlockBush bush = (BlockBush) state.getBlock();

            if (!bush.canBlockStay(world, pos, state))
            {
                return;
            }
        }

        world.setBlockState(pos, state, 3);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        addTallFlowerDecorations(world, rand, pos);
        super.decorate(world, rand, pos);
    }

    /**
     * Adds tall flower variants for tall flower generation
     *
     * @param variants tall flower variants
     * @return this biome instance
     */
    public BiomeAM addTallFlowerVariants(VariantFlower... variants)
    {
        Collections.addAll(tallFlowerVariants, variants);
        return this;
    }

    /**
     * Decorates the world with tall flowers
     *
     * @param world World
     * @param rand  Random number generator
     * @param pos   Position to generate
     */
    protected void addTallFlowerDecorations(World world, Random rand, BlockPos pos)
    {
        if (!tallFlowerVariants.isEmpty())
        {
            int flowersPerChunk = rand.nextInt(5) - 3;

            for (int i = 0; i < flowersPerChunk; ++i)
            {
                VariantFlower variant = tallFlowerVariants.get(rand.nextInt(tallFlowerVariants.size()));
                WorldGenTallFlower gen = new WorldGenTallFlower(variant);

                for (int j = 0; j < 5; ++j)
                {
                    int xOffset = rand.nextInt(16) + 8;
                    int zOffset = rand.nextInt(16) + 8;
                    BlockPos offsetPos = pos.add(xOffset, 0, zOffset);
                    int y = rand.nextInt(world.getHeight(offsetPos).getY() + 32);

                    if (gen.generate(world, rand, new BlockPos(offsetPos.getX(), y, offsetPos.getZ())))
                    {
                        break;
                    }
                }
            }
        }
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

                if (state.getMaterial() == Material.AIR)
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

                        if (chunkY < seaLevel && (top == null || top.getMaterial() == Material.AIR))
                        {
                            if (getFloatTemperature(pos.setPos(x, chunkY, z)) < 0.15F)
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
