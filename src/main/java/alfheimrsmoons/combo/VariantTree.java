package alfheimrsmoons.combo;

import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

public enum VariantTree implements IMetadata<VariantTree>
{
    BEECH("beech", MapColor.WOOD),
    ELM("elm", MapColor.OBSIDIAN),
    REDBUD("redbud", MapColor.SAND),
    LARCH("larch", MapColor.DIRT),
    RUNE("rune", MapColor.LIGHT_BLUE);

    private final String name;
    private final MapColor mapColor;

    VariantTree(String name, MapColor mapColor)
    {
        this.name = name;
        this.mapColor = mapColor;
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

    @Override
    public String toString()
    {
        return name;
    }
}