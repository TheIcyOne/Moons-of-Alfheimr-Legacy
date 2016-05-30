package alfheimrsmoons.block;

import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockSediment extends BlockSand
{
    public BlockSediment()
    {
        super();
        blockState = new BlockStateContainer(this);
        setDefaultState(blockState.getBaseState());
        setHardness(0.5F);
        setStepSound(SoundType.SAND);
        setHarvestLevel("shovel", 0);
        EntityEnderman.setCarriable(this, true);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return DefaultBlockHelper.damageDropped(this, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        DefaultBlockHelper.getSubBlocks(this, item, tab, list);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.sandColor;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return DefaultBlockHelper.getStateFromMeta(this, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return DefaultBlockHelper.getMetaFromState(this, state);
    }
}
