package alfheimrsmoons.item;

import alfheimrsmoons.block.VariantHelper;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class ItemVariantBlock extends ItemMultiTexture
{
    public <T extends Comparable<T> & IStringSerializable> ItemVariantBlock(Block block, final T[] variants)
    {
        super(block, block, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack stack)
            {
                return VariantHelper.getVariantFromMeta(variants, stack.getMetadata()).getName();
            }
        });
    }
}
