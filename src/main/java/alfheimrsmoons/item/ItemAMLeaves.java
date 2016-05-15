package alfheimrsmoons.item;

import alfheimrsmoons.block.BlockAMLeaves;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemAMLeaves extends ItemLeaves {
    private final BlockAMLeaves leaves;

    public ItemAMLeaves(BlockAMLeaves leaves) {
        super(leaves);
        this.leaves = leaves;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return leaves.getUnlocalizedName() + "." + leaves.getAMWoodType(stack.getMetadata()).getUnlocalizedName();
    }
}
