package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantBioluminescence;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

import java.util.List;

public class BlockBioluminescenceLamp extends Block
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public final VariantsOfTypesCombo<VariantBioluminescence> owner;
    public final ObjectType<VariantBioluminescence, ? extends BlockBioluminescenceTorch, ? extends ItemBlockMulti<VariantBioluminescence>> type;

    public final List<VariantBioluminescence> variants;
    public final PropertyIMetadata<VariantBioluminescence> variantProperty;

    public BlockBioluminescenceLamp(VariantsOfTypesCombo<VariantBioluminescence> owner,
                                    ObjectType<VariantBioluminescence, ? extends BlockBioluminescenceTorch, ? extends ItemBlockMulti<VariantBioluminescence>> type,
                                    List<VariantBioluminescence> variants, Class<VariantBioluminescence> variantClass)
    {
        super(Material.REDSTONE_LIGHT);

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty);
        setDefaultState(blockState.getBaseState());

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setLightLevel(1.0F);
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return owner.getItemMetadata(type, state.getValue(variantProperty));
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
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty);
    }
}
