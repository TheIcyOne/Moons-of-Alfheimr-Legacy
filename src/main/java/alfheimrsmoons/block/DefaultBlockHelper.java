package alfheimrsmoons.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class DefaultBlockHelper {
    public static ItemStack getItem(Block block, World world, BlockPos pos, IBlockState state) {
        Item item = Item.getItemFromBlock(block);
        return item == null ? null : new ItemStack(item, 1, block.damageDropped(state));
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
}
