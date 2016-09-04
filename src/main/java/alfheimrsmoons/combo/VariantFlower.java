package alfheimrsmoons.combo;

import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

public enum VariantFlower implements IMetadata<VariantFlower>
{
    // TODO map colors
    SNAPDRAGON("snapdragon", MapColor.FOLIAGE, true, false),
    CARNATION("carnation", MapColor.FOLIAGE, true, false),
    BURSTBLOOM("burstbloom", MapColor.FOLIAGE, true, false),
    WARM_VIOLET("warm_violet", MapColor.FOLIAGE, true, false),
    STARSINGER("starsinger", MapColor.FOLIAGE, true, false),
    PHILODENDRON("philodendron", MapColor.FOLIAGE, true, false),
    KNOTLEAF("knotleaf", MapColor.FOLIAGE, true, false),
    COLOMBINE("colombine", MapColor.FOLIAGE, false, true),
    FURCREA("furcrea", MapColor.FOLIAGE, true, true),
    BLUEBELL("bluebell", MapColor.FOLIAGE, true, true),
    DINANTHUS("dinanthus", MapColor.FOLIAGE, false, true),
    PURPLE_PACIFIST("purple_pacifist", MapColor.FOLIAGE, true, false),
    SKULLCAP("skullcap", MapColor.FOLIAGE, true, false),
    PINK_PRESTIGE("pink_prestige", MapColor.FOLIAGE, true, false),
    ASTER("aster", MapColor.FOLIAGE, true, false),
    PROMISES_BURDEN("promises_burden", MapColor.FOLIAGE, true, false),
    FORGET_ME_NOT("forget_me_not", MapColor.FOLIAGE, true, false);

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
