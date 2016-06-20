package alfheimrsmoons.util;

import alfheimrsmoons.block.BlockAMLeaves;
import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;

public enum EnumWoodVariant implements IVariant<EnumWoodVariant>
{
    RUNE("rune", MapColor.LIGHT_BLUE),
    BEECH("beech", MapColor.WOOD),
    ELM("elm", MapColor.OBSIDIAN),
    REDBUD("redbud", MapColor.SAND),
    LARCH("larch", MapColor.DIRT);

    public static final EnumWoodVariant[] VARIANTS = values();
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
                return AMBlocks.LOG;
            case LARCH:
                return AMBlocks.LOG2;
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
                return AMBlocks.LEAVES;
            case LARCH:
                return AMBlocks.LEAVES2;
        }
    }

    public IBlockState getLeavesState()
    {
        return VariantHelper.getDefaultStateWithVariant(getLeavesBlock(), this);
    }
}
