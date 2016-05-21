package alfheimrsmoons.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import org.apache.commons.lang3.ArrayUtils;

public class VariantHelper {
    public static <T> T[] getMetaVariants(T[] variants, int startMeta, int endMeta) {
        return ArrayUtils.subarray(variants, startMeta, endMeta + 1);
    }

    public static <T> T getDefaultVariant(T[] variants) {
        for (T variant : variants) {
            if (variant != null) {
                return variant;
            }
        }
        return null;
    }

    public static <T extends Comparable<T>> int getMetaFromVariant(T[] variants, IBlockState state, IProperty<T> variantProperty) {
        return getMetaFromVariant(variants, state.getValue(variantProperty));
    }

    public static <T> int getMetaFromVariant(T[] variants, T variant) {
        return ArrayUtils.indexOf(variants, variant);
    }

    public static <T> T getVariantFromMeta(T[] variants, int meta) {
        if (meta < 0 || meta >= variants.length) {
            meta = 0;
        }

        return variants[meta];
    }
}
