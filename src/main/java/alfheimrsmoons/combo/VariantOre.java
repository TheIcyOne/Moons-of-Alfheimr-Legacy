package alfheimrsmoons.combo;

import alfheimrsmoons.util.IntRange;
import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

import javax.annotation.Nullable;

public enum VariantOre implements IMetadata<VariantOre>
{
    LOREIUM("loreium", MapColor.DIAMOND, 1, 0.1F),
    NITRO("nitro", MapColor.PURPLE, 1, 0.1F, IntRange.create(2), IntRange.create(0, 2)),
    TEKTITE("tektite", MapColor.PURPLE, 2, 0.7F),
    SYLVANITE("sylvanite", MapColor.EMERALD, 3, 1.0F, IntRange.create(1), IntRange.create(3, 7));

    private final String name;
    private final MapColor mapColor;
    private final int harvestLevel;
    private final float smeltingXP;
    private final IntRange dropSize;
    private final IntRange dropXP;

    VariantOre(String name, MapColor mapColor, int harvestLevel, float smeltingXP)
    {
        this(name, mapColor, harvestLevel, smeltingXP, null, null);
    }

    VariantOre(String name, MapColor mapColor, int harvestLevel, float smeltingXP, @Nullable IntRange dropSize, @Nullable IntRange dropXP)
    {
        this.name = name;
        this.mapColor = mapColor;
        this.harvestLevel = harvestLevel;
        this.smeltingXP = smeltingXP;
        this.dropSize = dropSize;
        this.dropXP = dropXP;
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

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public float getSmeltingXP()
    {
        return smeltingXP;
    }

    @Nullable
    public IntRange getDropSize()
    {
        return dropSize;
    }

    @Nullable
    public IntRange getDropXP()
    {
        return dropXP;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
