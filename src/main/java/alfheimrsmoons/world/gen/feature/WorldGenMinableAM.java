package alfheimrsmoons.world.gen.feature;

import alfheimrsmoons.block.BlockShale;
import alfheimrsmoons.combo.ComboOres;
import alfheimrsmoons.combo.VariantOre;
import alfheimrsmoons.init.AMBlocks;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenMinableAM extends WorldGenMinable
{
    public WorldGenMinableAM(VariantOre variant, int blockCount)
    {
        this(AMBlocks.ORES.getBlockState(ComboOres.ORE, variant), blockCount);
    }

    public WorldGenMinableAM(IBlockState state, int blockCount)
    {
        this(state, blockCount, (s) -> s.getBlock() instanceof BlockShale);
    }

    public WorldGenMinableAM(IBlockState state, int blockCount, Predicate<IBlockState> predicate)
    {
        super(state, blockCount, predicate);
    }
}
