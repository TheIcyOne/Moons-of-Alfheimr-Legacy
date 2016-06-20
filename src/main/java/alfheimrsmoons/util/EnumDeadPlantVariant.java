package alfheimrsmoons.util;

public enum EnumDeadPlantVariant implements IVariant<EnumDeadPlantVariant>
{
    FORGET_ME_NOT("forget_me_not"),
    ELM_SAPLING("elm_sapling");

    public static final EnumDeadPlantVariant[] VARIANTS = values();
    private final String name;

    EnumDeadPlantVariant(String name)
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
