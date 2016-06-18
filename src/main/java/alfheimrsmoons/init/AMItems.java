package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/*
    Adding an item:
    - Registration (you are here)
    - Model JSON (assets/alfheimrsmoons/models/item)
    - Texture(s) (assets/alfheimrsmoons/textures/items)
    - Localization (assets/alfheimrsmoons/lang)
*/

public class AMItems
{
    public static final Item branch = new AMItem().setUnlocalizedName("alfheimrsmoons.branch").setRegistryName("branch").setFull3D();
    public static final ItemOreDrop ore_drop = (ItemOreDrop) new ItemOreDrop().setUnlocalizedName("alfheimrsmoons.ore_drop").setRegistryName("ore_drop");
    public static final ItemBioluminescence bioluminescence = (ItemBioluminescence) new ItemBioluminescence().setUnlocalizedName("alfheimrsmoons.bioluminescence").setRegistryName("bioluminescence");
    public static final Item stardust = new AMItem().setUnlocalizedName("alfheimrsmoons.stardust").setRegistryName("stardust");
    public static final Item seed_pouch = new ItemSeedPouch().setUnlocalizedName("alfheimrsmoons.seed_pouch").setRegistryName("seed_pouch");
    public static final Tools timber_tools = new Tools("timber", 0, 59, 2.0F, 0.0F, 15).setAxeAttack(6.0F, 3.2F);
    public static final Tools shale_tools = new Tools("shale", 1, 131, 4.0F, 1.0F, 5).setAxeAttack(8.0F, 3.2F);
    public static final Tools tektite_tools = new Tools("tektite", 2, 250, 6.0F, 2.0F, 14).setAxeAttack(8.0F, 3.1F);
    public static final Tools sylvanite_tools = new Tools("sylvanite", 3, 1561, 8.0F, 3.0F, 10).setAxeAttack(8.0F, 3.0F);
    public static final Item branch_bow = new ItemBranchBow().setUnlocalizedName("alfheimrsmoons.branch_bow").setRegistryName("branch_bow");
    public static final Item rock_arrow = new ItemAMArrow().setUnlocalizedName("alfheimrsmoons.rock_arrow").setRegistryName("rock_arrow");

    public static void registerItems()
    {
        AlfheimrsMoons.proxy.registerItem(branch);
        AlfheimrsMoons.proxy.registerItemWithVariants(ore_drop, "_ore_drop");
        AlfheimrsMoons.proxy.registerItemWithVariants(bioluminescence, "bioluminescence_");
        AlfheimrsMoons.proxy.registerItem(stardust);
        AlfheimrsMoons.proxy.registerItem(seed_pouch);
        timber_tools.registerItems();
        shale_tools.registerItems();
        tektite_tools.registerItems();
        sylvanite_tools.registerItems();
        AlfheimrsMoons.proxy.registerItem(branch_bow);
        AlfheimrsMoons.proxy.registerItem(rock_arrow);

        OreDictionary.registerOre("stickWood", branch);
    }
}
