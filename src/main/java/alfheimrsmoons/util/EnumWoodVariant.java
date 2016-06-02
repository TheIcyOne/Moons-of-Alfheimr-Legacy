package alfheimrsmoons.util;

import net.minecraft.block.material.MapColor;

public enum EnumWoodVariant implements IVariant<EnumWoodVariant>
{
    RUNE("rune", MapColor.lightBlueColor),
    BEECH("beech", MapColor.woodColor),
    ELM("elm", MapColor.obsidianColor),
    REDBUD("redbud", MapColor.sandColor),
    LARCH("larch", MapColor.dirtColor);

    public static final EnumWoodVariant[] values = values();
    private final String name;
    private final MapColor mapColor;

    EnumWoodVariant(String name, MapColor mapColor)
    {
        this.name = name;
        this.mapColor = mapColor;
    }

    public MapColor getMapColor()
    {
        return mapColor;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
