package alfheimrsmoons.item;

import alfheimrsmoons.util.VariantHelper;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.IVariantObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemOreDrop extends Item implements IVariantObject<EnumOreVariant>
{
    public ItemOreDrop()
    {
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public EnumOreVariant[] getVariants()
    {
        return EnumOreVariant.values;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return VariantHelper.getUnlocalizedName(this, super.getUnlocalizedName(stack), stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }
}
