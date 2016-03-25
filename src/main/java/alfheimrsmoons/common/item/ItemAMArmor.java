package alfheimrsmoons.common.item;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemAMArmor extends ItemArmor {
    private String name;

    public ItemAMArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
        this.name = unlocalizedName;

        this.setUnlocalizedName(name);
        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        GameRegistry.registerItem(this, name);
    }

    public String getName() {
        return this.name;
    }
}