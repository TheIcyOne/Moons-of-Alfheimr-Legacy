package alfheimrsmoons.block;

import java.util.Random;

import javax.annotation.Nullable;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantSedge;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrassySoil extends Block implements IGrowable
{
    public BlockGrassySoil()
    {
        super(Material.GRASS);
        setRegistryName("grassy_soil");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "grassy_soil");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState());
        setTickRandomly(true);
        setHardness(0.6F);
        setSoundType(SoundType.PLANT);
        setHarvestLevel("shovel", 0);
        EntityEnderman.setCarriable(this, true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getLightOpacity(world, pos.up()) > 2)
            {
                world.setBlockState(pos, AMBlocks.SOIL.getDefaultState());
            }
            else
            {
                if (world.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos spreadPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (spreadPos.getY() >= 0 && spreadPos.getY() < 256 && !world.isBlockLoaded(spreadPos))
                        {
                            return;
                        }

                        IBlockState iblockstate = world.getBlockState(spreadPos.up());
                        IBlockState iblockstate1 = world.getBlockState(spreadPos);

                        if (iblockstate1.getBlock() instanceof BlockSoil && world.getLightFromNeighbors(spreadPos.up()) >= 4 && iblockstate.getLightOpacity(world, pos.up()) <= 2)
                        {
                            world.setBlockState(spreadPos, AMBlocks.GRASSY_SOIL.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return AMBlocks.SOIL.getItemDropped(AMBlocks.SOIL.getDefaultState(), rand, fortune);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos upPos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos plantPos = upPos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (world.isAirBlock(plantPos))
                    {
                        if (rand.nextInt(8) == 0)
                        {
                            Biome biome = world.getBiome(plantPos);
                            biome.plantFlower(world, rand, pos);
                        }
                        else
                        {
                            VariantSedge sedgeVariant = rand.nextBoolean() ? VariantSedge.SHORT : VariantSedge.NORMAL;
                            BlockSedge sedgeBlock = AMBlocks.SEDGES.getBlock(sedgeVariant);
                            IBlockState sedgeState = sedgeBlock.getDefaultState().withProperty(AMBlocks.SEDGES.getVariantProperty(sedgeBlock), sedgeVariant);

                            if (sedgeBlock.canBlockStay(world, plantPos, sedgeState))
                            {
                                world.setBlockState(plantPos, sedgeState, 3);
                            }
                        }
                    }

                    break;
                }

                plantPos = plantPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(plantPos.down()).getBlock() instanceof BlockGrassySoil && !world.getBlockState(plantPos).isNormalCube())
                {
                    ++j;
                    continue;
                }

                break;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @SuppressWarnings("incomplete-switch")
	@Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Plains:
                return true;
            case Beach:
                for (EnumFacing horizontal : EnumFacing.HORIZONTALS)
                    if (world.getBlockState(pos.offset(horizontal)).getMaterial() == Material.WATER)
                        return true;
        }

        return false;
    }

    @Override
    public void onPlantGrow(IBlockState state, World world, BlockPos pos, BlockPos source)
    {
        world.setBlockState(pos, AMBlocks.SOIL.getDefaultState(), 2);
    }
}
