package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantDeadPlant implements IMetadata<VariantDeadPlant>
{
    FORGET_ME_NOT("forget_me_not"),
    ELM_SAPLING("elm_sapling");

    private final String name;

    VariantDeadPlant(String name)
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
