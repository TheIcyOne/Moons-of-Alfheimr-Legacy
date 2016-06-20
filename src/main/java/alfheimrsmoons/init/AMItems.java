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
    public static final Item BRANCH = new AMItem().setUnlocalizedName("alfheimrsmoons.branch").setRegistryName("branch").setFull3D();
    public static final Item TWINE = new AMItem().setUnlocalizedName("alfheimrsmoons.twine").setRegistryName("twine");
    public static final ItemOreDrop ORE_DROP = (ItemOreDrop) new ItemOreDrop().setUnlocalizedName("alfheimrsmoons.ore_drop").setRegistryName("ore_drop");
    public static final ItemBioluminescence BIOLUMINESCENCE = (ItemBioluminescence) new ItemBioluminescence().setUnlocalizedName("alfheimrsmoons.bioluminescence").setRegistryName("bioluminescence");
    public static final Item STARDUST = new AMItem().setUnlocalizedName("alfheimrsmoons.stardust").setRegistryName("stardust");
    public static final Item SEED_POUCH = new ItemSeedPouch().setUnlocalizedName("alfheimrsmoons.seed_pouch").setRegistryName("seed_pouch");
    public static final Tools TIMBER_TOOLS = new Tools("timber", 0, 59, 2.0F, 0.0F, 15).setAxeAttack(6.0F, 3.2F);
    public static final Tools SHALE_TOOLS = new Tools("shale", 1, 131, 4.0F, 1.0F, 5).setAxeAttack(8.0F, 3.2F);
    public static final Tools TEKTITE_TOOLS = new Tools("tektite", 2, 250, 6.0F, 2.0F, 14).setAxeAttack(8.0F, 3.1F);
    public static final Tools SYLVANITE_TOOLS = new Tools("sylvanite", 3, 1561, 8.0F, 3.0F, 10).setAxeAttack(8.0F, 3.0F);
    public static final Item BRANCH_BOW = new ItemBranchBow().setUnlocalizedName("alfheimrsmoons.branch_bow").setRegistryName("branch_bow");
    public static final Item ROCK_ARROW = new ItemAMArrow().setUnlocalizedName("alfheimrsmoons.rock_arrow").setRegistryName("rock_arrow");

    public static void registerItems()
    {
        AlfheimrsMoons.proxy.registerItem(BRANCH);
        AlfheimrsMoons.proxy.registerItem(TWINE);
        AlfheimrsMoons.proxy.registerItemWithVariants(ORE_DROP, "_ore_drop");
        AlfheimrsMoons.proxy.registerItemWithVariants(BIOLUMINESCENCE, "bioluminescence_");
        AlfheimrsMoons.proxy.registerItem(STARDUST);
        AlfheimrsMoons.proxy.registerItem(SEED_POUCH);
        TIMBER_TOOLS.registerItems();
        SHALE_TOOLS.registerItems();
        TEKTITE_TOOLS.registerItems();
        SYLVANITE_TOOLS.registerItems();
        AlfheimrsMoons.proxy.registerItem(BRANCH_BOW);
        AlfheimrsMoons.proxy.registerItem(ROCK_ARROW);

        OreDictionary.registerOre("stickWood", BRANCH);
    }
}
