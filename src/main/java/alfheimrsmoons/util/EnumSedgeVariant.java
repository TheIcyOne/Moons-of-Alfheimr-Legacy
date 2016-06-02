package alfheimrsmoons.util;

public enum EnumSedgeVariant implements IVariant<EnumSedgeVariant>
{
    NORMAL("normal"),
    SHORT("short");

    public static final EnumSedgeVariant[] values = values();
    private final String name;

    EnumSedgeVariant(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
