package alfheimrsmoons.util;

import net.minecraft.block.properties.IProperty;

public interface IVariantBlock<V extends IVariant<V>> extends IVariantObject<V>
{
    IProperty<V> getVariantProperty();
}
