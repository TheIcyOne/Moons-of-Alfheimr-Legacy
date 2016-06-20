package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumTallFlowerVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAMFlower extends BlockFlower implements IVariantBlock<EnumFlowerVariant>, IGrowable
{
    public static final PropertyEnum<EnumFlowerVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumFlowerVariant.class);

    public BlockAMFlower()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
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
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack stack)
    {
        if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
        {
            IBlockState flower = getStateFromMeta(stack.getMetadata());
            IBlockState soil = world.getBlockState(pos.offset(side.getOpposite()));
            return canSustainFlower(flower, soil);
        }
        else
        {
            return super.canReplace(world, pos, side, stack);
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState soil = world.getBlockState(pos.down());
        return state.getBlock() == this ? canSustainFlower(state, soil) : func_185514_i(soil);
    }

    protected boolean canSustainFlower(IBlockState flower, IBlockState soil)
    {
        if (flower.getValue(VARIANT_PROPERTY) == EnumFlowerVariant.SNAPDRAGON)
        {
            return soil.getBlock() == AMBlocks.shale;
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
        return block == AMBlocks.soil || block == AMBlocks.grassy_soil || super.func_185514_i(state);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(VARIANT_PROPERTY).hasTallVariant();
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (AMBlocks.tall_flower.canPlaceBlockAt(world, pos))
        {
            EnumTallFlowerVariant variant = state.getValue(VARIANT_PROPERTY).getTallVariant();
            AMBlocks.tall_flower.placeAt(world, pos, variant, 2);
        }
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
