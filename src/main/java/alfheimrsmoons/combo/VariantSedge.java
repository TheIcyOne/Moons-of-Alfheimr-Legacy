package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantSedge implements IMetadata<VariantSedge>
{
    NORMAL("normal"),
    SHORT("short");

    private final String name;

    VariantSedge(String name)
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
