package alfheimrsmoons.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import org.apache.commons.lang3.ArrayUtils;

public class VariantHelper {
    public static <T extends Comparable<T>> int getMetaFromVariant(T[] variants, IBlockState state, IProperty<T> variantProperty) {
        return getMetaFromVariant(variants, state.getValue(variantProperty));
    }

    public static <T> int getMetaFromVariant(T[] variants, T variant) {
        return ArrayUtils.indexOf(variants, variant);
    }
}
