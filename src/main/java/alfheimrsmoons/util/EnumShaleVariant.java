package alfheimrsmoons.util;

public enum EnumShaleVariant implements IVariant<EnumShaleVariant>
{
    NORMAL("normal"),
    MOSSY("mossy"),
    SMOOTH("smooth");

    public static final EnumShaleVariant[] values = values();
    private final String name;

    EnumShaleVariant(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
