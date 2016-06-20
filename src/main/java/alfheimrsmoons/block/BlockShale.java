package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.util.EnumShaleVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockShale extends BlockStone implements IVariantBlock<EnumShaleVariant>
{
    public static final PropertyEnum<EnumShaleVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumShaleVariant.class);
    
    public BlockShale()
    {
        super();
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT_PROPERTY, EnumShaleVariant.NORMAL));
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));

        if (plantable instanceof BlockAMFlower && ((BlockAMFlower) plantable).canSustainFlower(plant, state))
        {
            return true;
        }

        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public PropertyEnum<EnumShaleVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumShaleVariant[] getVariants()
    {
        return EnumShaleVariant.VARIANTS;
    }

    @Override
    public String getLocalizedName()
    {
        return I18n.translateToLocal(getUnlocalizedName() + "." + getDefaultState().getValue(VARIANT_PROPERTY).getName() + ".name");
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.STONE;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
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
}
