package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockOreBlock extends Block implements IVariantBlock<EnumOreVariant>
{
    public static final PropertyEnum<EnumOreVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumOreVariant.class);

    public BlockOreBlock()
    {
        super(Material.iron);
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(SoundType.METAL);
        setCreativeTab(CreativeTabs.tabBlock);
        for (int meta = 0; meta < getVariants().length; meta++)
        {
            setHarvestLevel("pickaxe", getVariants()[meta].getHarvestLevel());
        }
    }

    @Override
    public PropertyEnum<EnumOreVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumOreVariant[] getVariants()
    {
        return EnumOreVariant.values;
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
        return VariantHelper.getDefaultStateWithMeta(this, meta);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(VARIANT_PROPERTY).getBlockColor();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }
}
