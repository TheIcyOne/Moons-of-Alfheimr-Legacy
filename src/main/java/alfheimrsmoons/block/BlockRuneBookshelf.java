package alfheimrsmoons.block;

import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRuneBookshelf extends BlockBookshelf {
    public BlockRuneBookshelf() {
        setHardness(1.5F);
        setStepSound(SoundType.WOOD);
        setHarvestLevel("axe", 0);
        Blocks.fire.setFireInfo(this, 30, 20);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 1;
    }
}
