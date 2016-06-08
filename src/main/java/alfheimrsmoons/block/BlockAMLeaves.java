package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockAMLeaves extends BlockLeaves implements IVariantBlock
{
    private final EnumWoodVariant[] variants;
    private final PropertyEnum<EnumWoodVariant> variantProp;

    public BlockAMLeaves(int startMeta, int endMeta)
    {
        variants = VariantHelper.getVariantsInRange(EnumWoodVariant.values, startMeta, endMeta);
        variantProp = PropertyEnum.create("variant", EnumWoodVariant.class, variants);
        blockState = new BlockStateContainer(this, variantProp, CHECK_DECAY, DECAYABLE);
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }

    @Override
    public EnumWoodVariant[] getVariants()
    {
        return variants;
    }

    @Override
    public PropertyEnum<EnumWoodVariant> getVariantProperty()
    {
        return variantProp;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(AMBlocks.sapling);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return VariantHelper.createStack(this, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return VariantHelper.getDefaultStateWithMeta(this, meta).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | VariantHelper.getMetaFromState(this, state);

        if (!state.getValue(DECAYABLE))
        {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY))
        {
            i |= 8;
        }

        return i;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return BlockPlanks.EnumType.OAK;
    }

    public EnumWoodVariant getAMWoodType(int meta)
    {
        return VariantHelper.getVariantFromMeta(variants, meta & 3);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        if (!world.isRemote && stack != null && stack.getItem() instanceof ItemShears)
        {
            player.addStat(StatList.func_188055_a(this));
        }
        else
        {
            super.harvestBlock(world, player, pos, state, te, stack);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Arrays.asList(VariantHelper.createStack(this, world.getBlockState(pos)));
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 30;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }
}
