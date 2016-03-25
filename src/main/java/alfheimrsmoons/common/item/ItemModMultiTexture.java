package alfheimrsmoons.common.item;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemModMultiTexture extends ItemBlock {
    protected Function<ItemStack, String> nameFunction;

    public ItemModMultiTexture(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    public Function<ItemStack, String> getNameFunction() {
        return nameFunction;
    }

    public void setNameFunction(Function<ItemStack, String> nameFunction) {
        this.nameFunction = nameFunction;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + this.nameFunction.apply(stack);
    }
}