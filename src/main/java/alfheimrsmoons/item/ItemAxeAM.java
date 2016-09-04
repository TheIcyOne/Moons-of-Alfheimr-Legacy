package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.ItemVariantCount;

@ItemVariantCount(1)
public class ItemAxeAM extends ItemAxe
{
    public final VariantsOfTypesCombo<VariantToolMaterial> owner;
    public final ObjectType<VariantToolMaterial, Block, ? extends ItemAxeAM> type;
    public final VariantToolMaterial variant;

    public ItemAxeAM(VariantsOfTypesCombo<VariantToolMaterial> owner,
                     ObjectType<VariantToolMaterial, Block, ? extends ItemAxeAM> type,
                     VariantToolMaterial variant, Class<VariantToolMaterial> variantClass)
    {
        super(variant.getMaterial(), variant.getAttackDamage(), variant.getAxeSpeed());

        this.owner = owner;
        this.type = type;
        this.variant = variant;

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return owner.getUnlocalizedName(stack, super.getUnlocalizedName(stack));
    }
}
