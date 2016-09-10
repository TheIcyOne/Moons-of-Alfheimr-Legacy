package alfheimrsmoons.combo;

import alfheimrsmoons.util.IntRange;
import net.minecraft.block.material.MapColor;
import zaggy1024.combo.variant.IMetadata;

public enum VariantOre implements IMetadata<VariantOre>
{
    LOREIUM("loreium", MapColor.DIAMOND, 1, 0.1F, ItemType.INGOT),
    TEKTITE("tektite", MapColor.PURPLE, 2, 0.7F, ItemType.INGOT),
    SYLVANITE("sylvanite", MapColor.EMERALD, 3, 1.0F, ItemType.DROP, IntRange.create(1), IntRange.create(3, 7)),
    MOONSTONE("moonstone", MapColor.PURPLE, 4, 1.2F, ItemType.BOTH, IntRange.create(1), IntRange.create(4, 9)),
    SUNSTONE("sunstone", MapColor.ADOBE, 4, 1.2F, ItemType.BOTH, IntRange.create(1), IntRange.create(4, 9));

    public enum ItemType
    {
        DROP, INGOT, BOTH
    }

    private final String name;
    private final MapColor mapColor;
    private final int harvestLevel;
    private final float smeltingXP;
    private final IntRange dropSize;
    private final IntRange dropXP;
    private final ItemType itemType;

    VariantOre(String name, MapColor mapColor, int harvestLevel, float smeltingXP, ItemType itemType)
    {
        this(name, mapColor, harvestLevel, smeltingXP, itemType, null, null);
    }

    VariantOre(String name, MapColor mapColor, int harvestLevel, float smeltingXP, ItemType itemType, IntRange dropSize, IntRange dropXP)
    {
        this.name = name;
        this.mapColor = mapColor;
        this.harvestLevel = harvestLevel;
        this.smeltingXP = smeltingXP;
        this.itemType = itemType;
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

    public boolean hasIngot()
    {
        return itemType != ItemType.DROP;
    }

    public boolean hasDrop()
    {
        return itemType != ItemType.INGOT;
    }

    public IntRange getDropSize()
    {
        return dropSize;
    }

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
