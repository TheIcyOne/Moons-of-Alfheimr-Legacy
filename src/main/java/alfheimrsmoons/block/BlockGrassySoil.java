package alfheimrsmoons.block;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.DefaultBlockHelper;
import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumSedgeVariant;
import alfheimrsmoons.util.VariantHelper;
import alfheimrsmoons.world.biome.BiomeGenAM;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockGrassySoil extends BlockGrass
{
    public BlockGrassySoil()
    {
        blockState = new BlockStateContainer(this);
        setDefaultState(blockState.getBaseState());
        setHardness(0.6F);
        setStepSound(SoundType.PLANT);
        setHarvestLevel("shovel", 0);
        EntityEnderman.setCarriable(this, true);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return DefaultBlockHelper.getActualState(this, state, world, pos);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            BlockPos upPos = pos.up();

            if (world.getLightFromNeighbors(upPos) < 4 && world.getBlockState(upPos).getLightOpacity(world, upPos) > 2)
            {
                world.setBlockState(pos, AMBlocks.soil.getDefaultState());
            }
            else
            {
                if (world.getLightFromNeighbors(upPos) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos spreadPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (spreadPos.getY() >= 0 && spreadPos.getY() < 256 && !world.isBlockLoaded(spreadPos))
                        {
                            return;
                        }

                        BlockPos upSpreadPos = spreadPos.up();
                        IBlockState upSpreadState = world.getBlockState(upSpreadPos);
                        IBlockState spreadState = world.getBlockState(spreadPos);

                        if (spreadState.getBlock() == AMBlocks.soil && world.getLightFromNeighbors(upSpreadPos) >= 4 && upSpreadState.getLightOpacity(world, upPos) <= 2)
                        {
                            world.setBlockState(spreadPos, AMBlocks.grassy_soil.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return AMBlocks.soil.getItemDropped(AMBlocks.soil.getDefaultState(), rand, fortune);
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
                            BiomeGenBase biome = world.getBiomeGenForCoords(plantPos);
                            biome.plantFlower(world, rand, pos);
                        }
                        else
                        {
                            EnumSedgeVariant tallgrassVariant = rand.nextBoolean() ? EnumSedgeVariant.SHORT : EnumSedgeVariant.NORMAL;
                            IBlockState tallgrassState = VariantHelper.getDefaultStateWithVariant(AMBlocks.sedge, tallgrassVariant);

                            if (AMBlocks.sedge.canBlockStay(world, plantPos, tallgrassState))
                            {
                                world.setBlockState(plantPos, tallgrassState, 3);
                            }
                        }
                    }

                    break;
                }

                plantPos = plantPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(plantPos.down()).getBlock() != AMBlocks.grassy_soil || world.getBlockState(plantPos).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Plains:
                return true;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.water ||
                        world.getBlockState(pos.west()).getMaterial() == Material.water ||
                        world.getBlockState(pos.north()).getMaterial() == Material.water ||
                        world.getBlockState(pos.south()).getMaterial() == Material.water);
                return hasWater;
        }

        return false;
    }

    @Override
    public void onPlantGrow(IBlockState state, World world, BlockPos pos, BlockPos source)
    {
        world.setBlockState(pos, AMBlocks.soil.getDefaultState(), 2);
    }
}
