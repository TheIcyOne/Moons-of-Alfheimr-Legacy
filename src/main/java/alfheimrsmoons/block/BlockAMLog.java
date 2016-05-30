package alfheimrsmoons.block;

import alfheimrsmoons.block.BlockAMPlanks.EnumType;
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
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class BlockAMLog extends BlockLog
{
    public final EnumType[] variants;
    public final PropertyEnum<EnumType> variant;

    public BlockAMLog(int startMeta, int endMeta)
    {
        variants = VariantHelper.getMetaVariants(EnumType.values, startMeta, endMeta);
        variant = PropertyEnum.create("variant", EnumType.class, variants);
        blockState = new BlockStateContainer(this, variant, LOG_AXIS);
        setDefaultState(blockState.getBaseState().withProperty(variant, VariantHelper.getDefaultVariant(variants)).withProperty(LOG_AXIS, EnumAxis.Y));
        setHarvestLevel("axe", 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (int meta = 0; meta < variants.length; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = getDefaultState().withProperty(variant, variants[meta & 3]);

        switch (meta & 12)
        {
            case 0:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;
        meta = meta | VariantHelper.getMetaFromVariant(variants, state, variant);

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
        return new ItemStack(this, 1, VariantHelper.getMetaFromVariant(variants, state, variant));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return VariantHelper.getMetaFromVariant(variants, state, variant);
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
