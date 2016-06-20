package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNitroTorch extends BlockTorch
{
    public BlockNitroTorch()
    {
        setHardness(0.0F);
        setLightLevel(0.9375F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        EnumFacing facing = state.getValue(FACING);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        if (facing.getAxis().isHorizontal())
        {
            EnumFacing opposite = facing.getOpposite();
            world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + d4 * (double) opposite.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) opposite.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.REDSTONE, d0 + d4 * (double) opposite.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) opposite.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
        else
        {
            world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
