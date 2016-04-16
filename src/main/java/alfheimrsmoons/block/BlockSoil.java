package alfheimrsmoons.block;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockSoil extends BlockDirt {
    public BlockSoil() {
        super();
        blockState = new BlockStateContainer(this);
        setDefaultState(blockState.getBaseState());
        setHardness(0.5F);
        setStepSound(SoundType.GROUND);
        setHarvestLevel("shovel", 0);
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.dirtColor;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(item));
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return DefaultBlockHelper.getItem(this, world, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return DefaultBlockHelper.getMetaFromState(this, state);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }
}
