package alfheimrsmoons.item;

import alfheimrsmoons.util.EnumBioluminescenceVariant;
import net.minecraft.creativetab.CreativeTabs;

public class ItemBioluminescence extends ItemVariant<EnumBioluminescenceVariant>
{
    public ItemBioluminescence()
    {
        super(CreativeTabs.tabMaterials, EnumBioluminescenceVariant.values);
    }
}
