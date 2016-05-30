package alfheimrsmoons.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMeteorite extends Block
{
    public BlockMeteorite()
    {
        super(Material.rock, MapColor.obsidianColor);
        setHardness(1.5F);
        setResistance(10.0F);
        setStepSound(SoundType.STONE);
        setCreativeTab(CreativeTabs.tabBlock);
    }
}
