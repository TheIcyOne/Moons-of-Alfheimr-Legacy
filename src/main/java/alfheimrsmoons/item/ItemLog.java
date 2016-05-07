package alfheimrsmoons.item;

import alfheimrsmoons.block.BlockAMLog;
import com.google.common.base.Function;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class ItemLog extends ItemMultiTexture {
    public ItemLog(final BlockAMLog log) {
        super(log, log, new Function<ItemStack, String>() {
            public String apply(ItemStack stack) {
                return log.types[stack.getMetadata()].getUnlocalizedName();
            }
        });
    }
}
