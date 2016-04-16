package alfheimrsmoons.init;

import alfheimrsmoons.item.ItemBranchBow;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMItems {
    public static final Item branch_bow = new ItemBranchBow().setUnlocalizedName("bowBranch").setRegistryName("branch_bow");

    public static void register() {
        GameRegistry.register(branch_bow);
    }
}
