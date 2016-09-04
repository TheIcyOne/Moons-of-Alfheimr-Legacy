package alfheimrsmoons.item;

import alfheimrsmoons.combo.VariantToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.ItemVariantCount;

@ItemVariantCount(1)
public class ItemAMSword extends ItemSword
{
    public final VariantsOfTypesCombo<VariantToolMaterial> owner;
    public final ObjectType<VariantToolMaterial, Block, ? extends ItemAMSword> type;
    public final VariantToolMaterial variant;

    public ItemAMSword(VariantsOfTypesCombo<VariantToolMaterial> owner,
                       ObjectType<VariantToolMaterial, Block, ? extends ItemAMSword> type,
                       VariantToolMaterial variant, Class<VariantToolMaterial> variantClass)
    {
        super(variant.getMaterial());

        this.owner = owner;
        this.type = type;
        this.variant = variant;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return owner.getUnlocalizedName(stack, super.getUnlocalizedName(stack));
    }
}
