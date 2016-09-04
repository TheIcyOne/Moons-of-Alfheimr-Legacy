package alfheimrsmoons.combo;

import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

public enum VariantFlower implements IMetadata<VariantFlower>
{
    COLOMBINE("colombine", MapColor.RED, false, true),
    SNAPDRAGON("snapdragon", MapColor.RED, true, false),
    CARNATION("carnation", MapColor.RED, true, false),
    BURSTBLOOM("burstbloom", MapColor.ADOBE, true, false),
    WARM_VIOLET("warm_violet", MapColor.YELLOW, true, false),
    STARSINGER("starsinger", MapColor.YELLOW, true, false),
    PHILODENDRON("philodendron", MapColor.GREEN, true, false),
    KNOTLEAF("knotleaf", MapColor.GREEN, true, false),
    FURCREA("furcrea", MapColor.GREEN, true, true),
    BLUEBELL("bluebell", MapColor.BLUE, true, true),
    PURPLE_PACIFIST("purple_pacifist", MapColor.PURPLE, true, false),
    SKULLCAP("skullcap", MapColor.PURPLE, true, false),
    DINANTHUS("dinanthus", MapColor.MAGENTA, false, true),
    PINK_PRESTIGE("pink_prestige", MapColor.PINK, true, false),
    ASTER("aster", MapColor.PINK, true, false),
    FORGET_ME_NOT("forget_me_not", MapColor.PINK, true, false),
    PROMISES_BURDEN("promises_burden", MapColor.SNOW, true, false);

    private final String name;
    private final MapColor mapColor;
    private final boolean hasNormalFlower;
    private final boolean hasTallFlower;

    VariantFlower(String name, MapColor mapColor, boolean hasNormalFlower, boolean hasTallFlower)
    {
        this.name = name;
        this.mapColor = mapColor;
        this.hasNormalFlower = hasNormalFlower;
        this.hasTallFlower = hasTallFlower;
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

    public MapColor getMapColor()
    {
        return mapColor;
    }

    public boolean hasNormalFlower()
    {
        return hasNormalFlower;
    }

    public boolean hasTallFlower()
    {
        return hasTallFlower;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
