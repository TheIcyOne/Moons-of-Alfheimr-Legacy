package alfheimrsmoons.block;

import alfheimrsmoons.init.AMItems;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAMOre extends BlockOre implements IVariantBlock<EnumOreVariant>
{
    public static final PropertyEnum<EnumOreVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumOreVariant.class);

    public BlockAMOre()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY);
        setDefaultState(blockState.getBaseState());
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(SoundType.STONE);
        for (int meta = 0; meta < getVariants().length; meta++)
        {
            setHarvestLevel("pickaxe", getVariants()[meta].getHarvestLevel());
        }
    }

    @Override
    public PropertyEnum<EnumOreVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumOreVariant[] getVariants()
    {
        return EnumOreVariant.values;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        switch (state.getValue(VARIANT_PROPERTY))
        {
            case NITRO:
            case SYLVANITE:
                return AMItems.ore_drop;
            default:
                return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        if (Item.getItemFromBlock(this) != getItemDropped(state, random, fortune))
        {
            int quantity;

            switch (state.getValue(VARIANT_PROPERTY))
            {
                case NITRO:
                    quantity = 2;
                    break;
                default:
                    quantity = 1;
                    break;
            }

            if (fortune > 0)
            {
                int i = random.nextInt(fortune + 2) - 1;

                if (i < 0)
                {
                    i = 0;
                }

                quantity *= (i + 1);
            }

            return quantity;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        switch (state.getValue(VARIANT_PROPERTY))
        {
            case NITRO:
                return MathHelper.getRandomIntegerInRange(rand, 0, 2);
            case SYLVANITE:
                return MathHelper.getRandomIntegerInRange(rand, 3, 7);
            default:
                return 0;
        }
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return VariantHelper.createStack(this, state);
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
        return getDefaultState().withProperty(VARIANT_PROPERTY, VariantHelper.getVariantFromMeta(EnumOreVariant.values, meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }
}
