package alfheimrsmoons.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class DefaultBlockHelper {
    public static ItemStack getItem(Block block, World world, BlockPos pos, IBlockState state) {
        Item item = Item.getItemFromBlock(block);
        return item == null ? null : new ItemStack(item, 1, block.damageDropped(state));
    }

    public static IBlockState getStateFromMeta(Block block, int meta) {
        return block.getDefaultState();
    }

    public static int getMetaFromState(Block block, IBlockState state) {
        if (state != null && !state.getPropertyNames().isEmpty()) {
            throw new IllegalArgumentException("Don\'t know how to convert " + state + " back into data...");
        } else {
            return 0;
        }
    }

    public static String getLocalizedName(Block block) {
        return I18n.translateToLocal(block.getUnlocalizedName() + ".name");
    }

    public static IBlockState getActualState(Block block, IBlockState state, IBlockAccess world, BlockPos pos) {
        return state;
    }

    @SideOnly(Side.CLIENT)
    public static void getSubBlocks(Block block, Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(item));
    }

    public static Item getItemDropped(Block block, IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(block);
    }

    public static int damageDropped(Block block, IBlockState state) {
        return 0;
    }
}
