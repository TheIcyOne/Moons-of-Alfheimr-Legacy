package alfheimrsmoons.item;

import alfheimrsmoons.util.VariantHelper;
import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.IVariantObject;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class ItemVariantBlock<V extends IVariant<V>, B extends Block & IVariantBlock<V>> extends ItemMultiTexture implements IVariantObject
{
    private final B block;

    public ItemVariantBlock(final B block)
    {
        super(block, block, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack stack)
            {
                return VariantHelper.getVariantFromMeta(block, stack.getMetadata()).getName();
            }
        });
        this.block = block;
    }

    @Override
    public V[] getVariants()
    {
        return block.getVariants();
    }
}
