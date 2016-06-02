package alfheimrsmoons.item;

import alfheimrsmoons.block.BlockAMLeaves;
import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemAMLeaves extends ItemLeaves implements IVariantObject<EnumWoodVariant>
{
    private final BlockAMLeaves leaves;

    public ItemAMLeaves(BlockAMLeaves leaves)
    {
        super(leaves);
        this.leaves = leaves;
    }

    @Override
    public EnumWoodVariant[] getVariants()
    {
        return leaves.getVariants();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return leaves.getUnlocalizedName() + "." + leaves.getAMWoodType(stack.getMetadata()).getName();
    }
}
