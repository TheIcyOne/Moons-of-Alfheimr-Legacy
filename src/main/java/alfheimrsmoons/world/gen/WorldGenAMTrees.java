package alfheimrsmoons.world.gen;

import java.util.Random;

import alfheimrsmoons.common.block.BlockLog;
import alfheimrsmoons.common.block.BlockPlanks;
import alfheimrsmoons.common.block.BlockSapling;
import alfheimrsmoons.common.block.BlockLeaves;
import alfheimrsmoons.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenAMTrees extends WorldGenAbstractTree {// implements
                                                           // IWorldGenerator {

    // public WorldGenAMTrees(boolean doBlockNotify) {
    // super(doBlockNotify);
    // }

    private final int minTreeHeight;
    private final boolean vinesGrow;
    private final IBlockState wood;
    private final IBlockState leaves;

    public WorldGenAMTrees(Block wood, Block leaves, int metaWood, int metaLeaves) {
        this(wood, leaves, metaWood, metaLeaves, false, 4, false);
    }

    public WorldGenAMTrees(Block wood, Block leaves, int metaWood, int metaLeaves, boolean doBlockNotify, int minTreeHeight, boolean vinesGrow) {

        super(doBlockNotify);
        this.wood = wood.getDefaultState().withProperty(BlockLog.VARIANT, BlockPlanks.EnumType.byMetadata(metaWood));
        this.leaves = leaves.getDefaultState().withProperty(BlockLeaves.VARIANT, BlockPlanks.EnumType.byMetadata(metaLeaves)).withProperty(BlockLeaves.CHECK_DECAY,
                Boolean.valueOf(false));
        this.minTreeHeight = minTreeHeight;
        this.vinesGrow = vinesGrow;
    }

    public boolean generate(World world, Random random, BlockPos pos) {
        int i = random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
            byte b0;
            int l;

            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {
                b0 = 1;

                if (j == pos.getY()) {
                    b0 = 0;
                }
                if (j >= pos.getY() + 1 + i - 2) {
                    b0 = 2;
                }
                for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k) {
                    for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l) {
                        if (j >= 0 && j < 256) {
                            if (!this.isReplaceable(world, new BlockPos(k, j, l))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) {
                return false;
            } else {
                BlockPos down = pos.down();
                Block block1 = world.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(world, down, net.minecraft.util.EnumFacing.UP, (BlockSapling) ModBlocks.amSapling);

                if (isSoil && pos.getY() < 256 - i - 1) {
                    block1.onPlantGrow(world, down, pos);
                    b0 = 3; // the higher the # the farther down and out the
                    // tree leaves will generate, default = 3
                    byte b1 = 0; // the higher the # the farther up and out the
                                 // tree leaves will generate, default = 0
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    BlockPos blockpos1;
                    int topHeight = 11; // default is Math.min(6,
                                        // nextIntBetween(random, height / 5,
                                        // height / 3))
                    // generateColumn(world, random, pos, topHeight);
                    int maxLeavesRadius = 3; // default is 2 + topHeight / 4

                    for (l = pos.getY() - b0 + i; l <= pos.getY() + i; ++l) {
                        i1 = l - (pos.getY() + i);
                        j1 = b1 + 1 - i1 / 2; // b1 + 1 - i1 / 2 removing the "/
                                              // 2" gives the tree a more
                                              // pyramidal shape, the number
                                              // after "b1" does same but less
                                              // so

                        for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
                            l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2) {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || random.nextInt(2) != 0 && i1 != 0) {
                                    blockpos1 = new BlockPos(k1, l, i2);
                                    Block block = world.getBlockState(blockpos1).getBlock();

                                    if (block.canBeReplacedByLeaves(world, blockpos1)) {
                                        this.setBlockAndNotifyAdequately(world, blockpos1, this.leaves);

                                    }
                                }
                            }
                        }
                    }
                    for (int y = 0; y < i; y++) { // default is (int y = 0; y <
                                                  // i; y++)
                        int radius = Math.min(3, 2 + (i - y) / 4); // default =
                        // Math.min(3,
                        // 2 + (i -
                        // y)
                        for (int x = -radius; x <= radius; x++) {
                            for (int z = -radius; z <= radius; z++) {
                                int dist = Math.abs(x) + Math.abs(z);
                                if ((dist < 4) || ((dist == 4) && (random.nextInt(2) == 0))) {
                                    blockpos1 = new BlockPos(x, y, z);
                                    Block block = world.getBlockState(blockpos1).getBlock();
                                    if (block.isAir(world, blockpos1) || block.isLeaves(world, blockpos1) || block.getMaterial() == Material.vine) {
                                        this.setBlockAndNotifyAdequately(world, blockpos1, this.leaves);
                                    }
                                }
                            }
                        }
                    }
                    for (l = 0; l < i; ++l) {
                        BlockPos upN = pos.up(l);
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world, upN) || block2.isLeaves(world, upN) || block2.getMaterial() == Material.vine) {
                            this.setBlockAndNotifyAdequately(world, pos.up(l), this.wood);

                            if (this.vinesGrow) {
                                for (l = pos.getY() - 3 + i; l <= pos.getY() + i; ++l) {
                                    i1 = l - (pos.getY() + i);
                                    j1 = 2 - i1 / 2;

                                    for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
                                        for (l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1; ++l1) {
                                            BlockPos blockpos3 = new BlockPos(k1, l, l1);

                                            if (world.getBlockState(blockpos3).getBlock().isLeaves(world, blockpos3)) {
                                                BlockPos blockpos4 = blockpos3.west();
                                                blockpos1 = blockpos3.east();
                                                BlockPos blockpos5 = blockpos3.north();
                                                BlockPos blockpos2 = blockpos3.south();

                                                if (random.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world, blockpos4)) {
                                                    this.generateVines(world, blockpos4, 0);
                                                }
                                                if (random.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world, blockpos1)) {
                                                    this.generateVines(world, blockpos1, 0);
                                                }

                                                if (random.nextInt(4) == 0 && world.getBlockState(blockpos5).getBlock().isAir(world, blockpos5)) {
                                                    this.generateVines(world, blockpos5, 0);
                                                }

                                                if (random.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world, blockpos2)) {
                                                    this.generateVines(world, blockpos2, 0);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * generates willow-leaves-colums
     */
    public void generateColumn(World worldIn, Random random, BlockPos pos, int topHeight) {
        for (int y = 0; y < topHeight; y++) {
            int radius = Math.min(3, 2 + (topHeight - y) / 4); // default is
                                                               // Math.min(3, 2
                                                               // + (topHeight
                                                               // - y) / 4);
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    int dist = Math.abs(x) + Math.abs(z);
                    if ((dist < 4) || ((dist == 4) && (random.nextInt(1) == 0))) { // default
                                                                                   // is
                                                                                   // ((dist
                                                                                   // <
                                                                                   // 4)
                                                                                   // ||
                                                                                   // ((dist
                                                                                   // ==
                                                                                   // 4)
                                                                                   // &&
                                                                                   // (random.nextInt(2)
                                                                                   // ==
                                                                                   // 0)))
                        setLeaves(worldIn, pos.add(x, y, z));
                    }
                    if ((dist <= 3)) {
                        setAir(worldIn, pos.add(x, y, z));
                    }
                }
            }
        }
    }

    private void setLeaves(World world, BlockPos pos) {
        // this.func_175905_a(world, pos, ModBlocks.amLeaves, this.metaLeaves);
        if (world.getBlockState(pos).getBlock().isAir(world, pos)) {
            setBlockAndNotifyAdequately(world, pos, this.leaves);
        }
    }

    private void setWood(World world, BlockPos pos) {
        // this.func_175905_a(world, pos, ModBlocks.amLog, this.metaLeaves);
        setBlockAndNotifyAdequately(world, pos, this.wood);

    }

    private void setAir(World world, BlockPos pos) {
        // this.func_175905_a(world, pos, Blocks.air, 0);
        setBlockAndNotifyAdequately(world, pos, Blocks.air.getDefaultState());
    }

    private void generateVines(World worldIn, BlockPos pos, int meta) {
        setBlockAndNotifyAdequately(worldIn, pos, Blocks.vine.getDefaultState());
        int j = 4;

        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().isAir(worldIn, pos) && j > 0; --j) {
            // this.func_175905_a(worldIn, pos, ModBlocks.amLeaves, 2);
            setBlockAndNotifyAdequately(worldIn, pos, Blocks.vine.getDefaultState());
            pos = pos.down();
        }
    }

    // TODO add tree world gen
    // @Override
    // public void generate(Random random, int chunkX, int chunkZ, World world,
    // IChunkProvider chunkGenerator,
    // IChunkProvider chunkProvider) {
    // int xSpawn, ySpawn, zSpawn;
    // int xPos = chunkX * 16 + 8, zPos = chunkZ * 16 + 8;
    // BlockPos pos = new BlockPos(xPos, 64, zPos);
    // String biomeName = world.getBiomeGenForCoords(pos).biomeName;
    //
    // if (biomeName == null) {
    // return;
    // }
    //
    // if (biomeName == "Plains") {
    // // TODO add config options for tree generation and rarity
    // // if (AlfheimrsMoons.generateBeechwood &&
    // // random.nextInt(AlfheimrsMoons.beechwoodSpawnRarity) == 0)
    // if (random.nextInt(5) == 0) {
    // xSpawn = xPos + random.nextInt(16);
    // zSpawn = zPos + random.nextInt(16);
    // generate(world, random, pos);
    // }
    // }
    // }

}
