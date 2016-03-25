package alfheimrsmoons.common.item.weapons;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemAMSword extends ItemSword {
    private final String name;

    public ItemAMSword(String name, ToolMaterial material) {
        super(material);
        this.name = name;
        registerItem(this, name);
        canRepair = true;
    }

    public static void registerItem(ItemSword item, String name) {
        item.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        item.setUnlocalizedName(name);

        GameRegistry.registerItem(item, name);
    }

    public String getName() {
        return name;
    }

    public boolean getIsRepairable(ItemStack tool, ItemStack repairMaterial) {
        return false;
        // ModItems.crystal.getRegistryName().equals(repairMaterial.getItem().getRegistryName())
        // ? true
        // : super.getIsRepairable(tool, repairMaterial);
    }
}
