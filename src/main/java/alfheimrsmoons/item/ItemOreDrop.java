package alfheimrsmoons.item;

import alfheimrsmoons.util.EnumOreVariant;
import net.minecraft.creativetab.CreativeTabs;

public class ItemOreDrop extends ItemVariant<EnumOreVariant>
{
    public ItemOreDrop()
    {
        super(CreativeTabs.tabMaterials, EnumOreVariant.values);
    }
}
