package alfheimrsmoons.util;

public enum EnumFlowerVariant implements IVariant<EnumFlowerVariant>
{
    SNAPDRAGON("snapdragon"),
    CARNATION("carnation"),
    BURSTBLOOM("burstbloom"),
    WARM_VIOLET("warm_violet"),
    STARSINGER("starsinger"),
    PHILODENDRON("philodendron"),
    FURCREA("furcrea"),
    BLUEBELL("bluebell"),
    PURPLE_PACIFIST("purple_pacifist"),
    SKULLCAP("skullcap"),
    PINK_PRESTIGE("pink_prestige"),
    ASTER("aster"),
    PROMISES_BURDEN("promises_burden"),
    FORGET_ME_NOT("forget_me_not");

    public static final EnumFlowerVariant[] values = values();
    private final String name;

    EnumFlowerVariant(String name)
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
