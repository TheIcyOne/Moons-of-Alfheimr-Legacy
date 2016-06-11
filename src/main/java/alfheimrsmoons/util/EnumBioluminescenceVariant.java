package alfheimrsmoons.util;

public enum EnumBioluminescenceVariant implements IVariant<EnumBioluminescenceVariant>
{
    PINK("pink"),
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    PURPLE("purple");

    public static final EnumBioluminescenceVariant[] values = values();
    private final String name;

    EnumBioluminescenceVariant(String name)
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
