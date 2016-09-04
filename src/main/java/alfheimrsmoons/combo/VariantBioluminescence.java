package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantBioluminescence implements IMetadata<VariantBioluminescence>
{
    PINK("pink"),
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    PURPLE("purple");

    private final String name;

    VariantBioluminescence(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getUnlocalizedName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
