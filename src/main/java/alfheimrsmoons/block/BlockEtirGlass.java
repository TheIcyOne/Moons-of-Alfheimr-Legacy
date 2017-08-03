package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockEtirGlass extends BlockGlass
{
    public BlockEtirGlass() {
        super(Material.GLASS, false);
        setRegistryName("etir_glass");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "etir_glass");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);
    }
}
