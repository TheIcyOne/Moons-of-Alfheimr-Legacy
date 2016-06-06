package alfheimrsmoons.util;

import alfheimrsmoons.block.BlockAMLeaves;
import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;

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

    public BlockAMLog getLogBlock()
    {
        switch (this)
        {
            default:
            case RUNE:
            case BEECH:
            case ELM:
            case REDBUD:
                return AMBlocks.log;
            case LARCH:
                return AMBlocks.log2;
        }
    }

    public IBlockState getLogState()
    {
        return VariantHelper.getDefaultStateWithVariant(getLogBlock(), this);
    }

    public BlockAMLeaves getLeavesBlock()
    {
        switch (this)
        {
            default:
            case RUNE:
            case BEECH:
            case ELM:
            case REDBUD:
                return AMBlocks.leaves;
            case LARCH:
                return AMBlocks.leaves2;
        }
    }

    public IBlockState getLeavesState()
    {
        return VariantHelper.getDefaultStateWithVariant(getLeavesBlock(), this);
    }
}
