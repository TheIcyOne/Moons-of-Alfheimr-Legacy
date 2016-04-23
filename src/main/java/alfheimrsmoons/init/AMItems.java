package alfheimrsmoons.init;

import alfheimrsmoons.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMItems {
    public static final Item branch_bow = new ItemBranchBow().setUnlocalizedName("branch_bow").setRegistryName("branch_bow");
    public static final Item rock_arrow = new ItemAMArrow().setUnlocalizedName("rock_arrow").setRegistryName("rock_arrow");

    public static void registerItems() {
        GameRegistry.register(branch_bow);
        GameRegistry.register(rock_arrow);
    }
}
