package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumTallFlowerVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockTallFlower extends BlockDoublePlant implements IVariantBlock<EnumTallFlowerVariant>
{
    public static final PropertyEnum<EnumTallFlowerVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumTallFlowerVariant.class);

    public BlockTallFlower()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY, HALF);
        setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.LOWER));
    }

    private EnumTallFlowerVariant getVariant(IBlockAccess world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this)
        {
            return state.getActualState(world, pos).getValue(VARIANT_PROPERTY);
        }
        else
        {
            return EnumTallFlowerVariant.EELGRASS;
        }
    }

    @Override
    public PropertyEnum<EnumTallFlowerVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumTallFlowerVariant[] getVariants()
    {
        return EnumTallFlowerVariant.values;
    }

    @Override
    protected boolean func_185514_i(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof BlockSoil || block instanceof BlockGrassySoil || super.func_185514_i(state);
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() != this)
        {
            return true;
        }
        else
        {
            EnumTallFlowerVariant variant = state.getActualState(world, pos).getValue(VARIANT_PROPERTY);
            return variant == EnumTallFlowerVariant.EELGRASS;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            return null;
        }
        else
        {
            EnumTallFlowerVariant variant = state.getValue(VARIANT_PROPERTY);
            return variant == EnumTallFlowerVariant.EELGRASS ? null : Item.getItemFromBlock(this);
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(HALF) != EnumBlockHalf.UPPER && state.getValue(VARIANT_PROPERTY) != EnumTallFlowerVariant.EELGRASS ? VariantHelper.getMetaFromState(this, state) : 0;
    }

    public void placeAt(World world, BlockPos lowerPos, EnumTallFlowerVariant variant, int flags)
    {
        world.setBlockState(lowerPos, getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER).withProperty(VARIANT_PROPERTY, variant), flags);
        world.setBlockState(lowerPos.up(), getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), flags);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            BlockPos lowerPos = pos.down();

            if (world.getBlockState(lowerPos).getBlock() == this)
            {
                boolean destroyBlock = false;

                if (!player.capabilities.isCreativeMode)
                {
                    IBlockState lowerState = world.getBlockState(lowerPos);
                    EnumTallFlowerVariant variant = lowerState.getValue(VARIANT_PROPERTY);

                    if (variant != EnumTallFlowerVariant.EELGRASS)
                    {
                        destroyBlock = true;
                    }
                    else if (!world.isRemote)
                    {
                        if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemShears)
                        {
                            onHarvest(world, pos, lowerState, player);
                        }
                        else
                        {
                            destroyBlock = true;
                        }
                    }
                }

                if (destroyBlock)
                {
                    world.destroyBlock(lowerPos, true);
                }
                else
                {
                    world.setBlockToAir(lowerPos);
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockState(pos.up(), Blocks.air.getDefaultState(), 2);
        }

        super.onBlockHarvested(world, pos, state, player);
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        EnumTallFlowerVariant variant = state.getValue(VARIANT_PROPERTY);

        if (variant != EnumTallFlowerVariant.EELGRASS)
        {
            return false;
        }
        else
        {
            player.addStat(StatList.func_188055_a(this));
            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, VariantHelper.getMetaFromVariant(this, getVariant(world, pos, state)));
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return getVariant(world, pos, state) != EnumTallFlowerVariant.EELGRASS;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        spawnAsEntity(world, pos, getItem(world, pos, state));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        if ((meta & 8) > 0)
        {
            return getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER);
        }
        else
        {
            return VariantHelper.getDefaultStateWithMeta(this, meta & 7).withProperty(HALF, EnumBlockHalf.LOWER);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            IBlockState lowerState = world.getBlockState(pos.down());

            if (lowerState.getBlock() == this)
            {
                state = state.withProperty(VARIANT_PROPERTY, lowerState.getValue(VARIANT_PROPERTY));
            }
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            return 8;
        }
        else
        {
            return VariantHelper.getMetaFromState(this, state);
        }
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        EnumTallFlowerVariant variant = state.getValue(VARIANT_PROPERTY);
        return state.getValue(HALF) == EnumBlockHalf.LOWER && variant == EnumTallFlowerVariant.EELGRASS;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> stacks = new ArrayList<ItemStack>();
        EnumTallFlowerVariant variant = world.getBlockState(pos).getValue(VARIANT_PROPERTY);

        if (variant == EnumTallFlowerVariant.EELGRASS)
        {
            stacks.add(VariantHelper.createStack(this, variant));
        }

        return stacks;
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
