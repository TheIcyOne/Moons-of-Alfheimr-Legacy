package alfheimrsmoons.util;

public enum EnumFlowerVariant implements IVariant<EnumFlowerVariant>
{
    SNAPDRAGON("snapdragon"),
    CARNATION("carnation"),
    BURSTBLOOM("burstbloom"),
    WARM_VIOLET("warm_violet"),
    STARSINGER("starsinger"),
    PHILODENDRON("philodendron"),
    KNOTLEAF("knotleaf"),
    FURCREA("furcrea", EnumTallFlowerVariant.FURCREA),
    BLUEBELL("bluebell", EnumTallFlowerVariant.BLUEBELL),
    PURPLE_PACIFIST("purple_pacifist"),
    SKULLCAP("skullcap"),
    PINK_PRESTIGE("pink_prestige"),
    ASTER("aster"),
    PROMISES_BURDEN("promises_burden"),
    FORGET_ME_NOT("forget_me_not");

    public static final EnumFlowerVariant[] VARIANTS = values();
    private final String name;
    private final EnumTallFlowerVariant tallVariant;

    EnumFlowerVariant(String name)
    {
        this(name, null);
    }

    EnumFlowerVariant(String name, EnumTallFlowerVariant tallVariant)
    {
        this.name = name;
        this.tallVariant = tallVariant;
    }

    public EnumTallFlowerVariant getTallVariant()
    {
        return tallVariant;
    }

    public boolean hasTallVariant()
    {
        return tallVariant != null;
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
