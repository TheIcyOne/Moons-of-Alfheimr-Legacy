package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.ComboTrees;
import alfheimrsmoons.combo.VariantTree;
import alfheimrsmoons.init.AMItems;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
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

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockLeavesAM extends BlockLeaves
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = {CHECK_DECAY, DECAYABLE};

    public final VariantsOfTypesCombo<VariantTree> owner;
    public final ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type;

    public final List<VariantTree> variants;
    public final PropertyIMetadata<VariantTree> variantProperty;

    public BlockLeavesAM(VariantsOfTypesCombo<VariantTree> owner,
                         ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type,
                         List<VariantTree> variants, Class<VariantTree> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty, CHECK_DECAY, DECAYABLE);
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    protected void dropApple(World world, BlockPos pos, IBlockState state, int chance)
    {
        if (state.getValue(variantProperty) == VariantTree.RUNE && world.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(world, pos, new ItemStack(AMItems.KNOWLEDGE_FRUIT));
        }
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(variantProperty).getMapColor();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return owner.getItem(ComboTrees.SAPLING, state.getValue(variantProperty));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    /*//Never actually used this myself, not sure what it was replaced by or what it was used by, I'm sure the compiler will complain about it at me.
    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return owner.getStack(type, state.getValue(variantProperty));
    }*/

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty, DECAYABLE, CHECK_DECAY);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty, DECAYABLE, CHECK_DECAY);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return BlockPlanks.EnumType.OAK;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return owner.getItemMetadata(ComboTrees.SAPLING, state.getValue(variantProperty));
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        if (!world.isRemote && stack != null && stack.getItem() instanceof ItemShears)
        {
            player.addStat(StatList.getBlockStats(this));
        }
        else
        {
            super.harvestBlock(world, player, pos, state, te, stack);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Collections.singletonList(owner.getStack(type, world.getBlockState(pos).getValue(variantProperty)));
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
