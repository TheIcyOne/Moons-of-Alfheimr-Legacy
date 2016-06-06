package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAMFlower extends BlockFlower implements IVariantBlock<EnumFlowerVariant>
{
    public static final PropertyEnum<EnumFlowerVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumFlowerVariant.class);

    public BlockAMFlower()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    public PropertyEnum<EnumFlowerVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumFlowerVariant[] getVariants()
    {
        return EnumFlowerVariant.values;
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
    public int getMetaFromState(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    public EnumFlowerColor getBlockType()
    {
        return EnumFlowerColor.RED;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState soil = world.getBlockState(pos.down());

        if (state.getBlock() == this)
        {
            return canSustainFlower(state, soil);
        }
        else
        {
            return func_185514_i(soil);
        }
    }

    protected boolean canSustainFlower(IBlockState flower, IBlockState soil)
    {
        if (flower.getValue(VARIANT_PROPERTY) == EnumFlowerVariant.SNAPDRAGON)
        {
            return soil.getBlock() instanceof BlockShale;
        }
        else
        {
            return func_185514_i(soil);
        }
    }

    @Override
    protected boolean func_185514_i(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof BlockSoil || block instanceof BlockGrassySoil || super.func_185514_i(state);
    }
}
