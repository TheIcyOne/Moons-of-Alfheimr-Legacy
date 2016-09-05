package alfheimrsmoons.combo;

import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

public enum VariantBioluminescence implements IMetadata<VariantBioluminescence>
{
    PINK("pink", MapColor.PINK),
    RED("red", MapColor.RED),
    ORANGE("orange", MapColor.ADOBE),
    YELLOW("yellow", MapColor.YELLOW),
    GREEN("green", MapColor.GREEN),
    BLUE("blue", MapColor.BLUE),
    PURPLE("purple", MapColor.PURPLE);

    private final String name;
    private final MapColor mapColor;

    VariantBioluminescence(String name, MapColor mapColor)
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
