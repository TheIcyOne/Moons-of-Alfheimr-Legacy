package alfheimrsmoons.common.item.tools;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemAMPickaxe extends ItemPickaxe {
    private final String name;

    public ItemAMPickaxe(String name, ToolMaterial material) {
        super(material);
        this.name = name;
        registerItem(this, name);
        canRepair = true;
    }

    public static void registerItem(ItemPickaxe item, String name) {
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
