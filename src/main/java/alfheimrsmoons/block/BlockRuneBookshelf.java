package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.SoundType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneBookshelf extends BlockBookshelf
{
    public BlockRuneBookshelf()
    {
        setRegistryName("rune_bookshelf");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "rune_bookshelf");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(1.5F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos)
    {
        return 1;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 30;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 20;
    }
}
