package alfheimrsmoons.item;

import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class ItemVariantBlock extends ItemMultiTexture {
    public <T extends Comparable<T> & IStringSerializable> ItemVariantBlock(Block block, final T[] variants) {
        super(block, block, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                int meta = stack.getMetadata();
                if (meta < 0 || meta >= variants.length) {
                    meta = 0;
                }
                return variants[meta].getName();
            }
        });
    }
}
