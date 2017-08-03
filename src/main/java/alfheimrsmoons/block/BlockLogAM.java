package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantTree;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

import java.util.List;

public class BlockLogAM extends BlockLog
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = {LOG_AXIS};

    public final VariantsOfTypesCombo<VariantTree> owner;
    public final ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type;

    public final List<VariantTree> variants;
    public final PropertyIMetadata<VariantTree> variantProperty;

    public BlockLogAM(VariantsOfTypesCombo<VariantTree> owner,
                      ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type,
                      List<VariantTree> variants, Class<VariantTree> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty, LOG_AXIS);
        setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHarvestLevel("axe", 0);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(variantProperty).getMapColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty, LOG_AXIS);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty, LOG_AXIS);
    }

    /*
    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return owner.getStack(type, state.getValue(variantProperty));
    }*/

    @Override
    public int damageDropped(IBlockState state)
    {
        return owner.getItemMetadata(type, state.getValue(variantProperty));
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
