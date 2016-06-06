package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.VariantHelper;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenAMMinable extends WorldGenMinable
{
    public WorldGenAMMinable(EnumOreVariant oreVariant, int blockCount)
    {
        this(VariantHelper.getDefaultStateWithVariant(AMBlocks.ore, oreVariant), blockCount);
    }

    public WorldGenAMMinable(IBlockState state, int blockCount)
    {
        this(state, blockCount, BlockMatcher.forBlock(AMBlocks.shale));
    }

    public WorldGenAMMinable(IBlockState state, int blockCount, Predicate<IBlockState> predicate)
    {
        super(state, blockCount, predicate);
    }
}
