package alfheimrsmoons.lib;

import alfheimrsmoons.common.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AMCreativeTab extends CreativeTabs {
    public AMCreativeTab() {
        super("tabcreativeTab.name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ModItems.branch;
    }

}