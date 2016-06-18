package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockLog;
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

public class BlockAMLog extends BlockLog implements IVariantBlock
{
    private final EnumWoodVariant[] variants;
    private final PropertyEnum<EnumWoodVariant> variantProp;

    public BlockAMLog(int startMeta, int endMeta)
    {
        variants = VariantHelper.getVariantsInRange(EnumWoodVariant.values, startMeta, endMeta);
        variantProp = PropertyEnum.create("variant", EnumWoodVariant.class, variants);
        blockState = new BlockStateContainer(this, variantProp, LOG_AXIS);
        setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
        setHarvestLevel("axe", 0);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public EnumWoodVariant[] getVariants()
    {
        return variants;
    }

    @Override
    public PropertyEnum<EnumWoodVariant> getVariantProperty()
    {
        return variantProp;
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
        IBlockState state = VariantHelper.getDefaultStateWithMeta(this, meta & 3);

        switch (meta & 12)
        {
            case 0:
                return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
            case 4:
                return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
            case 8:
                return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
            default:
                return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;
        meta = meta | VariantHelper.getMetaFromState(this, state);

        switch (state.getValue(LOG_AXIS))
        {
            case X:
                meta |= 4;
                break;
            case Z:
                meta |= 8;
                break;
            case NONE:
                meta |= 12;
        }

        return meta;
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return VariantHelper.createStack(this, state);
    }

    @Override
    public int damageDropped(IBlockState state)
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
        return 5;
    }
}
