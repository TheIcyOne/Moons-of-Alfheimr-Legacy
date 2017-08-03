package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;

public class BlockCosmicDust extends BlockFalling {

    public BlockCosmicDust() {
        super();
        setRegistryName("cosmic_dust");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "cosmic_dust");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.3F);
        setSoundType(SoundType.SAND);
    }
}
