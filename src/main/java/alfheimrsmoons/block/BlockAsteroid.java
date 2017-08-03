package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAsteroid extends Block
{
    public BlockAsteroid()
    {
        super(Material.ROCK, MapColor.OBSIDIAN);
        setRegistryName("asteroid");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "asteroid");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP;
    }
}
