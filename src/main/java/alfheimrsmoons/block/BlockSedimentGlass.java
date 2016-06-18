package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSedimentGlass extends BlockGlass
{
    public BlockSedimentGlass()
    {
        super(Material.glass, false);
        setHardness(0.3F);
        setStepSound(SoundType.GLASS);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }
}
