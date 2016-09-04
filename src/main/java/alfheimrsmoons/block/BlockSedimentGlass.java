package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSedimentGlass extends BlockGlass
{
    public BlockSedimentGlass()
    {
        super(Material.GLASS, false);
        setRegistryName("sediment_glass");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "sediment_glass");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);
    }
}
