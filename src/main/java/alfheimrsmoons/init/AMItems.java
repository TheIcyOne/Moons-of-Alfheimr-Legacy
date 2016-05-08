package alfheimrsmoons.init;

import alfheimrsmoons.item.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*
    Adding an item:
    - Registration (you are here)
    - Model registration (alfheimrsmoons.client.ItemModels)
    - Model JSON (assets/alfheimrsmoons/models/item)
    - Texture(s) (assets/alfheimrsmoons/textures/items)
    - Localization (assets/alfheimrsmoons/lang)
*/

public class AMItems {
    public static final Item branch = new Item().setUnlocalizedName("alfheimr.branch").setRegistryName("branch").setCreativeTab(CreativeTabs.tabMaterials).setFull3D();
    public static final Item ore_drop = new ItemOreDrop().setUnlocalizedName("alfheimr.ore_drop").setRegistryName("ore_drop");
    public static final Item branch_bow = new ItemBranchBow().setUnlocalizedName("alfheimr.branch_bow").setRegistryName("branch_bow");
    public static final Item rock_arrow = new ItemAMArrow().setUnlocalizedName("alfheimr.rock_arrow").setRegistryName("rock_arrow");

    public static void registerItems() {
        GameRegistry.register(branch);
        GameRegistry.register(ore_drop);
        GameRegistry.register(branch_bow);
        GameRegistry.register(rock_arrow);
    }
}
