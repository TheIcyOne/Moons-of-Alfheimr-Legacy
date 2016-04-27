package alfheimrsmoons.block;

import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockShale extends BlockStone {
    public BlockShale() {
        super();
        blockState = new BlockStateContainer(this);
        setDefaultState(blockState.getBaseState());
        setHardness(1.5F);
        setResistance(10.0F);
        setStepSound(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public String getLocalizedName() {
        return DefaultBlockHelper.getLocalizedName(this);
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.stoneColor;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return DefaultBlockHelper.getItemDropped(this, state, rand, fortune);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return DefaultBlockHelper.damageDropped(this, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        DefaultBlockHelper.getSubBlocks(this, item, tab, list);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return DefaultBlockHelper.getStateFromMeta(this, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return DefaultBlockHelper.getMetaFromState(this, state);
    }
}
