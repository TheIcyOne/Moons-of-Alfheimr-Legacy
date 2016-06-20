package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.init.AMItems;
import alfheimrsmoons.util.EnumDeadPlantVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockDeadPlant extends BlockDeadBush implements IVariantBlock<EnumDeadPlantVariant>
{
    public static final PropertyEnum<EnumDeadPlantVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumDeadPlantVariant.class);

    public BlockDeadPlant()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public PropertyEnum<EnumDeadPlantVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumDeadPlantVariant[] getVariants()
    {
        return EnumDeadPlantVariant.VARIANTS;
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
    protected boolean canSustainBush(IBlockState state)
    {
        Block block = state.getBlock();
        return block == AMBlocks.GRASSY_SOIL || block == AMBlocks.SOIL || super.canSustainBush(state);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return state.getValue(VARIANT_PROPERTY) == EnumDeadPlantVariant.ELM_SAPLING ? random.nextInt(3) : 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(VARIANT_PROPERTY) == EnumDeadPlantVariant.ELM_SAPLING ? AMItems.BRANCH : null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Collections.singletonList(new ItemStack(this));
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
