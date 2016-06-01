package alfheimrsmoons.block;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSedge extends BlockTallGrass
{
    public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);
    protected static final AxisAlignedBB SHORT_SEDGE_AABB = TALL_GRASS_AABB.setMaxY(TALL_GRASS_AABB.maxY / 2);

    public BlockSedge()
    {
        blockState = new BlockStateContainer(this, TYPE);
        setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumType.NORMAL));
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return state.getValue(TYPE) == EnumType.SHORT ? SHORT_SEDGE_AABB : TALL_GRASS_AABB;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(TYPE).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (EnumType type : EnumType.values)
        {
            list.add(new ItemStack(item, 1, type.getMetadata()));
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(TYPE) == EnumType.SHORT;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(TYPE) == EnumType.SHORT)
        {
            world.setBlockState(pos, getDefaultState().withProperty(TYPE, EnumType.NORMAL), 2);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(TYPE, VariantHelper.getVariantFromMeta(EnumType.values, meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(TYPE).getMetadata();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockState(pos).getValue(TYPE).getMetadata()));
        return ret;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
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

    public enum EnumType implements IStringSerializable
    {
        NORMAL("normal"),
        SHORT("short");

        public static final EnumType[] values = values();
        private final String name;

        EnumType(String name)
        {
            this.name = name;
        }

        public int getMetadata()
        {
            return ordinal();
        }

        @Override
        public String toString()
        {
            return name;
        }

        @Override
        public String getName()
        {
            return name;
        }
    }
}
