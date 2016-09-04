package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantTree;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

import java.util.List;

public class BlockPlanksAM extends Block
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public final VariantsOfTypesCombo<VariantTree> owner;
    public final ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type;

    public final List<VariantTree> variants;
    public final PropertyIMetadata<VariantTree> variantProperty;

    public BlockPlanksAM(VariantsOfTypesCombo<VariantTree> owner,
                         ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type,
                         List<VariantTree> variants, Class<VariantTree> variantClass)
    {
        super(Material.WOOD);

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty);
        setDefaultState(blockState.getBaseState());

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return owner.getItemMetadata(type, state.getValue(variantProperty));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(variantProperty).getMapColor();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty);
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
