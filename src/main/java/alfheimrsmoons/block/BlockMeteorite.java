package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMeteorite extends Block
{
    public BlockMeteorite()
    {
        super(Material.rock, MapColor.obsidianColor);
        setHardness(1.5F);
        setResistance(10.0F);
        setStepSound(SoundType.STONE);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP;
    }
}
