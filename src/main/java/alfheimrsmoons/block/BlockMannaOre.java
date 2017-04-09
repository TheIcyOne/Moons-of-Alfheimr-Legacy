package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMItems;
import alfheimrsmoons.util.AMUtils;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockMannaOre extends BlockOre
{
    public BlockMannaOre()
    {
        super(MapColor.NETHERRACK);
        setRegistryName("manna_ore");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "manna_ore");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return AMItems.MANNA_CRYSTAL;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return MathHelper.getRandomIntegerInRange(AMUtils.getWorldRandom(world, RANDOM), 2, 5);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
}
