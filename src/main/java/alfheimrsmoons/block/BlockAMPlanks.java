package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAMPlanks extends BlockPlanks implements IVariantBlock<EnumWoodVariant>
{
    public static final PropertyEnum<EnumWoodVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumWoodVariant.class);

    public BlockAMPlanks()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(SoundType.WOOD);
        setHarvestLevel("axe", 0);
    }

    @Override
    public PropertyEnum<EnumWoodVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumWoodVariant[] getVariants()
    {
        return EnumWoodVariant.values;
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
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(VARIANT_PROPERTY, VariantHelper.getVariantFromMeta(EnumWoodVariant.values, meta));
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(VARIANT_PROPERTY).getMapColor();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 5;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 20;
    }

}
