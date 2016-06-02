package alfheimrsmoons.util;

public interface IVariantObject<V extends IVariant<V>>
{
    V[] getVariants();
}
