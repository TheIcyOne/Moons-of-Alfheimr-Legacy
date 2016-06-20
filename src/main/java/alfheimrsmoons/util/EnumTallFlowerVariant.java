package alfheimrsmoons.util;

public enum EnumTallFlowerVariant implements IVariant<EnumTallFlowerVariant>
{
    COLOMBINE("colombine"),
    FURCREA("furcrea"),
    EELGRASS("eelgrass"),
    BLUEBELL("bluebell"),
    DINANTHUS("dinanthus");

    public static final EnumTallFlowerVariant[] VARIANTS = values();
    private final String name;

    EnumTallFlowerVariant(String name)
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
