package alfheimrsmoons.util;

import net.minecraft.util.IStringSerializable;

public interface IVariant<T> extends IStringSerializable, Comparable<T>
{
    String toString();
}
