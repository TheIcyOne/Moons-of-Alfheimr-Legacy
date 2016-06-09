package alfheimrsmoons.block;

import alfheimrsmoons.init.AMItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockDeadElmSapling extends BlockDeadBush
{
    public BlockDeadElmSapling()
    {
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    protected boolean func_185514_i(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof BlockGrassySoil || block instanceof BlockSoil || super.func_185514_i(state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return AMItems.branch;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 100;
    }
}
