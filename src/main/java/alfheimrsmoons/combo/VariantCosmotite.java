package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantCosmotite implements IMetadata<VariantCosmotite>
{
    NORMAL("normal", "cosmotite"),
    BRICKS("bricks", "cosmotite_bricks"),
    CHISELED("chiseled", "chiseled_cosmotite"),
    SMOOTH("smooth", "smooth_cosmotite");

    private final String name;
    private final String resourceName;

    VariantCosmotite(String name, String resourceName)
    {
        this.name = name;
        this.resourceName = resourceName;
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

    public String getResourceName()
    {
        return resourceName;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
