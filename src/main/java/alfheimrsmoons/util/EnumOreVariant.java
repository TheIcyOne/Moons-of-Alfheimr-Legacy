package alfheimrsmoons.util;

import net.minecraft.block.material.MapColor;

public enum EnumOreVariant implements IVariant<EnumOreVariant>
{
    LOREIUM("loreium", MapColor.DIAMOND, 0, 0.1F),
    NITRO("nitro", MapColor.PURPLE, 0, 0.1F),
    TEKTITE("tektite", MapColor.PURPLE, 2, 0.7F),
    SYLVANITE("sylvanite", MapColor.EMERALD, 3, 1.0F);

    public static final EnumOreVariant[] VARIANTS = values();
    private final String name;
    private final MapColor blockColor;
    private final int harvestLevel;
    private final float smeltingXP;

    EnumOreVariant(String name, MapColor blockColor, int harvestLevel, float smeltingXP)
    {
        this.name = name;
        this.blockColor = blockColor;
        this.harvestLevel = harvestLevel;
        this.smeltingXP = smeltingXP;
    }

    public MapColor getBlockColor()
    {
        return blockColor;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public float getSmeltingXP()
    {
        return smeltingXP;
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
