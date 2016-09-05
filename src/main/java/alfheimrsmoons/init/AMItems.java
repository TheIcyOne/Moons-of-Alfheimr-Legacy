package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.ComboBioluminescence;
import alfheimrsmoons.network.Proxy;
import alfheimrsmoons.combo.ComboOres;
import alfheimrsmoons.combo.ComboTools;
import alfheimrsmoons.combo.VariantBioluminescence;
import alfheimrsmoons.item.ItemArrowAM;
import alfheimrsmoons.item.ItemBranchBow;
import alfheimrsmoons.item.ItemSeedPouch;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsCombo;
import zaggy1024.item.ItemMulti;

public class AMItems
{
    public static final Item BRANCH = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "branch")
            .setRegistryName("branch")
            .setFull3D();

    public static final Item TWINE = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "twine")
            .setRegistryName("twine");

    public static final Item STARDUST = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "stardust")
            .setRegistryName("stardust");

    public static final ItemSeedPouch SEED_POUCH = new ItemSeedPouch();

    public static final ComboTools TOOLS = new ComboTools();

    public static final ItemBranchBow BRANCH_BOW = new ItemBranchBow();

    public static final Item ROCK_ARROW = new ItemArrowAM()
            .setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "rock_arrow")
            .setRegistryName("rock_arrow");

    public static void registerItems()
    {
        Proxy proxy = AlfheimrsMoons.proxy;
        proxy.registerItem(BRANCH);
        proxy.registerItem(TWINE);
        AMBlocks.ORES.registerVariants(proxy, ComboOres.DROP);
        AMBlocks.BIOLUMINESCENCE.registerVariants(proxy, ComboBioluminescence.BIOLUMINESCENCE);
        proxy.registerItem(STARDUST);
        proxy.registerItem(SEED_POUCH);
        TOOLS.registerAll(proxy);
        proxy.registerItem(BRANCH_BOW);
        proxy.registerItem(ROCK_ARROW);

        OreDictionary.registerOre("stickWood", BRANCH);
    }
}
