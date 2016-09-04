package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSediment extends BlockFalling
{
    public BlockSediment()
    {
        super(Material.SAND);
        setRegistryName("sediment");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "sediment");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.5F);
        setSoundType(SoundType.SAND);
        setHarvestLevel("shovel", 0);
        EntityEnderman.setCarriable(this, true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int func_189876_x(IBlockState state) // getDustColor
    {
        return BlockSand.EnumType.SAND.func_189865_a();
    }
}
