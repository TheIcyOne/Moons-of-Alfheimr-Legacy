package alfheimrsmoons.init;

import alfheimrsmoons.item.*;
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
    public static final Item branch_bow = new ItemBranchBow().setUnlocalizedName("alfheimr.branch_bow").setRegistryName("branch_bow");
    public static final Item rock_arrow = new ItemAMArrow().setUnlocalizedName("alfheimr.rock_arrow").setRegistryName("rock_arrow");

    public static void registerItems() {
        GameRegistry.register(branch_bow);
        GameRegistry.register(rock_arrow);
    }
}
