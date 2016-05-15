package alfheimrsmoons;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class AMFuelHandler implements IFuelHandler {
    private final List<Fuel> fuels = new ArrayList<Fuel>();

    @Override
    public int getBurnTime(ItemStack stack) {
        for (Fuel fuel : fuels) {
            if (fuel.item == stack.getItem()) {
                if (fuel.meta == stack.getMetadata() || fuel.meta == OreDictionary.WILDCARD_VALUE) {
                    if (fuel.stack != null) {
                        if (ItemStack.areItemStackTagsEqual(fuel.stack, stack)) {
                            return fuel.burnTime;
                        }
                    } else {
                        return fuel.burnTime;
                    }
                }
            }
        }
        return 0;
    }

    public void setBurnTime(ItemStack stack, int time) {
        fuels.add(new Fuel(stack, time));
    }

    public void setBurnTime(Block block, int time) {
        setBurnTime(Item.getItemFromBlock(block), time);
    }

    public void setBurnTime(Item item, int time) {
        fuels.add(new Fuel(item, time));
    }

    private class Fuel {
        private ItemStack stack;
        private Item item;
        private int meta;
        private int burnTime;

        private Fuel(ItemStack stack, int burnTime) {
            this.stack = stack;
            this.item = stack.getItem();
            this.meta = stack.getMetadata();
            this.burnTime = burnTime;
        }

        private Fuel(Item item, int burnTime) {
            this(item, OreDictionary.WILDCARD_VALUE, burnTime);
        }

        private Fuel(Item item, int meta, int burnTime) {
            this.item = item;
            this.meta = meta;
            this.burnTime = burnTime;
        }
    }
}
