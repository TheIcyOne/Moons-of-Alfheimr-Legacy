package alfheimrsmoons.combo;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public enum EnumHalf implements IStringSerializable
{
    BOTTOM("bottom"),
    TOP("top");

    public static final PropertyEnum<EnumHalf> PROPERTY = PropertyEnum.create("half", EnumHalf.class);

    private final String name;

    EnumHalf(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public boolean isBottom()
    {
        return this == BOTTOM;
    }

    public boolean isTop()
    {
        return this == TOP;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
