package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantShale implements IMetadata<VariantShale>
{
    NORMAL("normal"),
    MOSSY("mossy"),
    SMOOTH("smooth");

    private final String name;

    VariantShale(String name)
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
