package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantDeadPlant;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.SoundType;
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

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockDeadPlant extends BlockDeadBush
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public final VariantsOfTypesCombo<VariantDeadPlant> owner;
    public final ObjectType<VariantDeadPlant, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantDeadPlant>> type;

    public final List<VariantDeadPlant> variants;
    public final PropertyIMetadata<VariantDeadPlant> variantProperty;

    public BlockDeadPlant(VariantsOfTypesCombo<VariantDeadPlant> owner,
                          ObjectType<VariantDeadPlant, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantDeadPlant>> type,
                          List<VariantDeadPlant> variants, Class<VariantDeadPlant> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty);
        setDefaultState(blockState.getBaseState());

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
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

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() instanceof BlockGrassySoil || state.getBlock() instanceof BlockSoil || super.canSustainBush(state);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return state.getValue(variantProperty) == VariantDeadPlant.ELM_SAPLING ? random.nextInt(3) : 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(variantProperty) == VariantDeadPlant.ELM_SAPLING ? AMItems.BRANCH : null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Collections.singletonList(owner.getStack(type, world.getBlockState(pos).getValue(variantProperty)));
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
