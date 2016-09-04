package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.ItemVariantCount;

@ItemVariantCount(1)
public class ItemHoeAM extends ItemHoe
{
    public final VariantsOfTypesCombo<VariantToolMaterial> owner;
    public final ObjectType<VariantToolMaterial, Block, ? extends ItemHoeAM> type;
    public final VariantToolMaterial variant;

    public ItemHoeAM(VariantsOfTypesCombo<VariantToolMaterial> owner,
                     ObjectType<VariantToolMaterial, Block, ? extends ItemHoeAM> type,
                     VariantToolMaterial variant, Class<VariantToolMaterial> variantClass)
    {
        super(variant.getMaterial());

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
