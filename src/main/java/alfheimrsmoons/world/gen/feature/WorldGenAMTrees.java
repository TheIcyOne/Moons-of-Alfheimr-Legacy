package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class WorldGenAMTrees extends WorldGenTrees
{
    public WorldGenAMTrees(boolean notify, IBlockState metaWood, IBlockState metaLeaves)
    {
        this(notify, 4, metaWood, metaLeaves, false);
    }

    public WorldGenAMTrees(boolean notify, int minTreeHeight, IBlockState metaWood, IBlockState metaLeaves, boolean vinesGrow)
    {
        super(notify, minTreeHeight, metaWood, metaLeaves, vinesGrow);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        int i = rand.nextInt(3) + minTreeHeight;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getHeight())
        {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < world.getHeight())
                        {
                            if (!isReplaceable(world, blockpos$mutableblockpos.set(l, j, i1)))
                            {
                                flag = false;
                            }
                        } else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            } else
            {
                IBlockState state = world.getBlockState(position.down());

                if (state.getBlock().canSustainPlant(state, world, position.down(), EnumFacing.UP, AMBlocks.sapling) && position.getY() < world.getHeight() - i - 1)
                {
                    setDirtAt(world, position.down());
                    int k2 = 3;
                    int l2 = 0;

                    for (int i3 = position.getY() - k2 + i; i3 <= position.getY() + i; ++i3)
                    {
                        int i4 = i3 - (position.getY() + i);
                        int j1 = l2 + 1 - i4 / 2;

                        for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1)
                        {
                            int l1 = k1 - position.getX();

                            for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - position.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                {
                                    BlockPos blockpos = new BlockPos(k1, i3, i2);
                                    state = world.getBlockState(blockpos);

                                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos) || state.getMaterial() == Material.vine)
                                    {
                                        setBlockAndNotifyAdequately(world, blockpos, metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        BlockPos upN = position.up(j3);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN) || state.getMaterial() == Material.vine)
                        {
                            setBlockAndNotifyAdequately(world, position.up(j3), metaWood);

                            if (vinesGrow && j3 > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(position.add(-1, j3, 0)))
                                {
                                    func_181651_a(world, position.add(-1, j3, 0), BlockVine.EAST);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(position.add(1, j3, 0)))
                                {
                                    func_181651_a(world, position.add(1, j3, 0), BlockVine.WEST);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(position.add(0, j3, -1)))
                                {
                                    func_181651_a(world, position.add(0, j3, -1), BlockVine.SOUTH);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(position.add(0, j3, 1)))
                                {
                                    func_181651_a(world, position.add(0, j3, 1), BlockVine.NORTH);
                                }
                            }
                        }
                    }

                    if (vinesGrow)
                    {
                        for (int k3 = position.getY() - 3 + i; k3 <= position.getY() + i; ++k3)
                        {
                            int j4 = k3 - (position.getY() + i);
                            int k4 = 2 - j4 / 2;
                            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                            for (int l4 = position.getX() - k4; l4 <= position.getX() + k4; ++l4)
                            {
                                for (int i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5)
                                {
                                    blockpos$mutableblockpos1.set(l4, k3, i5);

                                    state = world.getBlockState(blockpos$mutableblockpos1);
                                    if (state.getBlock().isLeaves(state, world, blockpos$mutableblockpos1))
                                    {
                                        BlockPos blockpos2 = blockpos$mutableblockpos1.west();
                                        BlockPos blockpos3 = blockpos$mutableblockpos1.east();
                                        BlockPos blockpos4 = blockpos$mutableblockpos1.north();
                                        BlockPos blockpos1 = blockpos$mutableblockpos1.south();

                                        if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos2))
                                        {
                                            func_181650_b(world, blockpos2, BlockVine.EAST);
                                        }

                                        if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos3))
                                        {
                                            func_181650_b(world, blockpos3, BlockVine.WEST);
                                        }

                                        if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos4))
                                        {
                                            func_181650_b(world, blockpos4, BlockVine.SOUTH);
                                        }

                                        if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos1))
                                        {
                                            func_181650_b(world, blockpos1, BlockVine.NORTH);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && i > 5)
                        {
                            for (int l3 = 0; l3 < 2; ++l3)
                            {
                                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                                {
                                    if (rand.nextInt(4 - l3) == 0)
                                    {
                                        EnumFacing enumfacing1 = enumfacing.getOpposite();
                                        func_181652_a(world, rand.nextInt(3), position.add(enumfacing1.getFrontOffsetX(), i - 5 + l3, enumfacing1.getFrontOffsetZ()), enumfacing);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                } else
                {
                    return false;
                }
            }
        } else
        {
            return false;
        }
    }
}
