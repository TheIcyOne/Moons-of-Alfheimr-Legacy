package alfheimrsmoons.block;

import java.util.List;
import java.util.Random;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

public class BlockFlowerAM extends BlockBush implements IGrowable
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public final VariantsOfTypesCombo<VariantFlower> owner;
    public final ObjectType<VariantFlower, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantFlower>> type;

    public final List<VariantFlower> variants;
    public final PropertyIMetadata<VariantFlower> variantProperty;

    public BlockFlowerAM(VariantsOfTypesCombo<VariantFlower> owner,
                         ObjectType<VariantFlower, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantFlower>> type,
                         List<VariantFlower> variants, Class<VariantFlower> variantClass)
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
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(variantProperty).getMapColor();
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

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }
    
/* FIXME canReplace
    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, @Nullable ItemStack stack)
    {
        if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
        {
            IBlockState state = owner.getBlockState(type, owner.getVariant(stack));
            IBlockState soil = world.getBlockState(pos.offset(side.getOpposite()));
            return canSustainFlower(state, soil);
        }
        else
        {
            return super.canReplace(world, pos, side, stack);
        }
    }
*/
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState soil = world.getBlockState(pos.down());
        return state.getBlock() == this ? canSustainFlower(state, soil) : canSustainBush(soil);
    }

    protected boolean canSustainFlower(IBlockState state, IBlockState soil)
    {
        switch (state.getValue(variantProperty))
        {
            case SNAPDRAGON:
                return AMBlocks.SHALE.containsState(soil);
            default:
                return canSustainBush(soil);
        }
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() instanceof BlockSoil || state.getBlock() instanceof BlockGrassySoil || super.canSustainBush(state);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(variantProperty).hasTallFlower();
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        AMBlocks.FLOWERS.placeTallFlower(world, pos, state.getValue(variantProperty));
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
