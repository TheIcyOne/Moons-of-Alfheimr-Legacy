package alfheimrsmoons.block;

import java.util.List;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantBioluminescence;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

public class BlockBioluminescenceTorch extends BlockTorch
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = {FACING};

    public final VariantsOfTypesCombo<VariantBioluminescence> owner;
    public final ObjectType<VariantBioluminescence, ? extends BlockBioluminescenceTorch, ? extends ItemBlockMulti<VariantBioluminescence>> type;

    public final List<VariantBioluminescence> variants;
    public final PropertyIMetadata<VariantBioluminescence> variantProperty;

    public BlockBioluminescenceTorch(VariantsOfTypesCombo<VariantBioluminescence> owner,
                                     ObjectType<VariantBioluminescence, ? extends BlockBioluminescenceTorch, ? extends ItemBlockMulti<VariantBioluminescence>> type,
                                     List<VariantBioluminescence> variants, Class<VariantBioluminescence> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty, FACING);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.0F);
        setLightLevel(0.9375F);
        setSoundType(SoundType.WOOD);
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
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty, FACING);
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
    		float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
    	IBlockState state = getStateFromMeta(meta);

        if (canPlaceAt(world, pos, facing))
        {
            return state.withProperty(FACING, facing);
        }
        else
        {
            for (EnumFacing face : EnumFacing.Plane.HORIZONTAL)
            {
                if (world.isSideSolid(pos.offset(face.getOpposite()), face, true))
                {
                    return state.withProperty(FACING, face);
                }
            }

            return state;
        }
    }
    
    /** ======================================== BlockTorch START ===================================== **/

    private boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing)
    {
        BlockPos offset = pos.offset(facing.getOpposite());
        return facing.getAxis().isHorizontal() && world.isSideSolid(offset, facing, true) || facing.equals(EnumFacing.UP) && canPlaceOn(world, offset);
    }

    private boolean canPlaceOn(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.isSideSolid(world, pos, EnumFacing.UP) || state.getBlock().canPlaceTorchOnTop(state, world, pos);
    }

    /** ========================================= BlockTorch END ====================================== **/
}
