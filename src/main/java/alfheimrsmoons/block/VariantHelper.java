package alfheimrsmoons.block;

import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class VariantHelper
{
    // ----------------- METADATA - VARIANT - BLOCKSTATE ----------------- //

    public static <V extends IVariant<V>> int getMetaFromState(IVariantBlock<V> variantObject, IBlockState state)
    {
        return getMetaFromState(variantObject.getVariants(), variantObject.getVariantProperty(), state);
    }

    public static <V extends Comparable<V>> int getMetaFromState(V[] variants, IProperty<V> variantProperty, IBlockState state)
    {
        return getMetaFromVariant(variants, state.getValue(variantProperty));
    }

    public static <V extends IVariant<V>> int getMetaFromVariant(IVariantObject<V> variantObject, V variant)
    {
        return ArrayUtils.indexOf(variantObject.getVariants(), variant);
    }

    public static <V> int getMetaFromVariant(V[] variants, V variant)
    {
        return ArrayUtils.indexOf(variants, variant);
    }

    public static <V extends IVariant<V>> V getVariantFromMeta(IVariantObject<V> variantObject, int meta)
    {
        return getVariantFromMeta(variantObject.getVariants(), meta);
    }

    public static <V> V getVariantFromMeta(V[] variants, int meta)
    {
        if (meta < 0 || meta >= variants.length)
        {
            meta = 0;
        }

        return variants[meta];
    }

    // ----------------- getVariantStack ----------------- //

    public static <V extends IVariant<V>, I extends Item & IVariantObject<V>> ItemStack createStack(I item, V variant)
    {
        return createStack(item, 1, variant);
    }

    public static <V extends IVariant<V>, I extends Item & IVariantObject<V>> ItemStack createStack(I item, int amount, V variant)
    {
        return new ItemStack(item, amount, getMetaFromVariant(item, variant));
    }

    public static <V extends IVariant<V>, B extends Block & IVariantBlock<V>> ItemStack createStack(B block, IBlockState state)
    {
        return createStack(block, state.getValue(block.getVariantProperty()));
    }

    public static <V extends IVariant<V>, B extends Block & IVariantObject<V>> ItemStack createStack(B block, V variant)
    {
        return createStack(block, 1, variant);
    }

    public static <V extends IVariant<V>, B extends Block & IVariantObject<V>> ItemStack createStack(B block, int amount, V variant)
    {
        return new ItemStack(block, amount, getMetaFromVariant(block, variant));
    }

    // ----------------- addSubItems ----------------- //

    public static <V extends IVariant<V>> void addSubItems(IVariantObject<V> variantObject, Item item, List<ItemStack> list)
    {
        addSubItems(variantObject.getVariants(), item, list);
    }

    public static <V> void addSubItems(V[] variants, Item item, List<ItemStack> list)
    {
        addSubItems(variants.length, item, list);
    }

    public static void addSubItems(int amount, Item item, List<ItemStack> list)
    {
        for (int meta = 0; meta < amount; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    // ----------------- MISCELLANEOUS ----------------- //

    public static <V> V[] getVariantsInRange(V[] variants, int startMeta, int endMeta)
    {
        return ArrayUtils.subarray(variants, startMeta, endMeta + 1);
    }

    public static <V extends IVariant<V>> String getUnlocalizedName(IVariantObject<V> variantObject, String base, ItemStack stack)
    {
        return getUnlocalizedName(variantObject.getVariants(), base, stack);
    }

    public static <V extends IVariant<V>> String getUnlocalizedName(V[] variants, String base, ItemStack stack)
    {
        return base + "." + getVariantFromMeta(variants, stack.getMetadata()).getName();
    }

    public static <V extends IVariant<V>, B extends Block & IVariantBlock<V>> IBlockState getDefaultStateWithVariant(B block, V variant)
    {
        return block.getDefaultState().withProperty(block.getVariantProperty(), variant);
    }
}
