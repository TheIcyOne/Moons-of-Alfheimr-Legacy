package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCosmicGlass extends BlockGlass {

    public BlockCosmicGlass() {
        super(Material.GLASS, false);
        setRegistryName("cosmic_glass");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "cosmic_glass");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);
    }
}