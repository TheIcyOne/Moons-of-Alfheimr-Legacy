package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumSedgeVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSedge extends BlockTallGrass implements IVariantBlock<EnumSedgeVariant>
{
    public static final PropertyEnum<EnumSedgeVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumSedgeVariant.class);
    protected static final AxisAlignedBB SHORT_SEDGE_AABB = TALL_GRASS_AABB.setMaxY(TALL_GRASS_AABB.maxY / 2);

    public BlockSedge()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    public PropertyEnum<EnumSedgeVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumSedgeVariant[] getVariants()
    {
        return EnumSedgeVariant.values;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return state.getValue(VARIANT_PROPERTY) == EnumSedgeVariant.SHORT ? SHORT_SEDGE_AABB : TALL_GRASS_AABB;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(VARIANT_PROPERTY) == EnumSedgeVariant.SHORT;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(VARIANT_PROPERTY) == EnumSedgeVariant.SHORT)
        {
            world.setBlockState(pos, VariantHelper.getDefaultStateWithVariant(this, EnumSedgeVariant.NORMAL), 2);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return VariantHelper.getDefaultStateWithMeta(this, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(VariantHelper.createStack(this, world.getBlockState(pos)));
        return ret;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 100;
    }

}
