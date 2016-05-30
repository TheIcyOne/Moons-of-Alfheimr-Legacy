package alfheimrsmoons.block;

import alfheimrsmoons.block.BlockAMPlanks.EnumType;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockAMLeaves extends BlockLeaves
{
    public final EnumType[] variants;
    public final PropertyEnum<EnumType> variant;

    public BlockAMLeaves(int startMeta, int endMeta)
    {
        variants = VariantHelper.getMetaVariants(EnumType.values, startMeta, endMeta);
        variant = PropertyEnum.create("variant", EnumType.class, variants);
        blockState = new BlockStateContainer(this, variant, CHECK_DECAY, DECAYABLE);
        setDefaultState(blockState.getBaseState().withProperty(variant, VariantHelper.getDefaultVariant(variants)).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
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
        for (int meta = 0; meta < variants.length; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(this, 1, VariantHelper.getMetaFromVariant(variants, state, variant));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(variant, getAMWoodType(meta)).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | VariantHelper.getMetaFromVariant(variants, state, variant);

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

    public EnumType getAMWoodType(int meta)
    {
        return variants[meta & 3];
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return VariantHelper.getMetaFromVariant(variants, state, variant);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack != null && stack.getItem() == Items.shears)
        {
            player.addStat(StatList.func_188055_a(this));
        } else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Arrays.asList(new ItemStack(this, 1, VariantHelper.getMetaFromVariant(variants, world.getBlockState(pos), variant)));
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
