package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockCosmotite extends Block
{
    public BlockCosmotite()
    {
        super(Material.ROCK, MapColor.OBSIDIAN);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
    }
}
