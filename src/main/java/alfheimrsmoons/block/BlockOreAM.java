package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.ComboOres;
import alfheimrsmoons.combo.VariantOre;
import alfheimrsmoons.util.AMUtils;
import alfheimrsmoons.util.IntRange;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class BlockOreAM extends BlockOre
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public final VariantsOfTypesCombo<VariantOre> owner;
    public final ObjectType<VariantOre, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantOre>> type;

    public final List<VariantOre> variants;
    public final PropertyIMetadata<VariantOre> variantProperty;

    public BlockOreAM(VariantsOfTypesCombo<VariantOre> owner,
                      ObjectType<VariantOre, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantOre>> type,
                      List<VariantOre> variants, Class<VariantOre> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty);
        setDefaultState(blockState.getBaseState());

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(variantProperty).getMapColor();
    }

    @Override
    public int getHarvestLevel(IBlockState state)
    {
        return state.getValue(variantProperty).getHarvestLevel();
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        VariantOre variant = state.getValue(variantProperty);

        if (!variant.hasDrop())
        {
            return Collections.singletonList(owner.getStack(type, variant));
        }

        IntRange range = variant.getDropSize();
        Random rand = AMUtils.getWorldRandom(world, RANDOM);
        int size = range.get(rand);

        if (fortune > 0)
        {
            int i = rand.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            size *= (i + 1);
        }

        return Collections.singletonList(owner.getStack(ComboOres.DROP, variant, size));
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        VariantOre variant = state.getValue(variantProperty);
        return variant.hasDrop() ? variant.getDropXP().get(AMUtils.getWorldRandom(world, RANDOM)) : 0;
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return owner.getStack(type, state.getValue(variantProperty));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty);
    }
}
