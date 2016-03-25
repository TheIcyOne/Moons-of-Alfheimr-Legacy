package alfheimrsmoons.common.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.item.tools.ItemAMAxe;
import alfheimrsmoons.common.item.tools.ItemAMHoe;
import alfheimrsmoons.common.item.tools.ItemAMPickaxe;
import alfheimrsmoons.common.item.tools.ItemAMSpade;
import alfheimrsmoons.common.item.weapons.ItemAMSword;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBucket;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
    public static ToolMaterial TOOL_KASOLITE = EnumHelper.addToolMaterial("KASOLITE", 2, 1200, 7.0F, 3.0F, 15);
    public static ToolMaterial TOOL_CORRODIUM = EnumHelper.addToolMaterial("CORRODIUM", 2, 9001, 7.0F, 3.0F, 15);
    public static ArmorMaterial ARMOR_KASOLITE = EnumHelper.addArmorMaterial("KASOLITE", AlfheimrsMoons.instance.MODID + ":kasolite", 42, new int[] { 3, 8, 6, 3 }, 20);

    public static ItemBranch branch;
    public static ItemNitroPowder nitroPowder;
    public static ItemCrystal crystal;
    public static ItemAMArmor kasoliteHelmet;
    public static ItemAMArmor kasoliteChestplate;
    public static ItemAMArmor kasoliteLeggings;
    public static ItemAMArmor kasoliteBoots;
    public static ItemAMPickaxe kasolitePickaxe;
    public static ItemAMAxe kasoliteAxe;
    public static ItemAMSpade kasoliteSpade;
    public static ItemAMHoe kasoliteHoe;
    public static ItemAMSword kasoliteSword;
    public static ItemAMPickaxe corrodiumPickaxe;
    public static ItemAMAxe corrodiumAxe;
    public static ItemAMSpade corrodiumSpade;
    public static ItemAMHoe corrodiumHoe;
    public static ItemAMSword corrodiumSword;
    public static ItemAMFood foodOnion;
    public static ItemBucket bucketSomething;

    public static void load(FMLPreInitializationEvent event) {
        branch = new ItemBranch();
        nitroPowder = new ItemNitroPowder();
        crystal = new ItemCrystal();

        kasoliteHelmet = new ItemAMArmor("kasolite_helmet", ARMOR_KASOLITE, 1, 0);
        kasoliteChestplate = new ItemAMArmor("kasolite_chestplate", ARMOR_KASOLITE, 1, 1);
        kasoliteLeggings = new ItemAMArmor("kasolite_leggings", ARMOR_KASOLITE, 2, 2);
        kasoliteBoots = new ItemAMArmor("kasolite_boots", ARMOR_KASOLITE, 1, 3);

        kasolitePickaxe = new ItemAMPickaxe("kasolite_pickaxe", TOOL_KASOLITE);
        kasoliteAxe = new ItemAMAxe("kasolite_axe", TOOL_KASOLITE);
        kasoliteSpade = new ItemAMSpade("kasolite_spade", TOOL_KASOLITE);
        kasoliteHoe = new ItemAMHoe("kasolite_hoe", TOOL_KASOLITE);
        kasoliteSword = new ItemAMSword("kasolite_sword", TOOL_KASOLITE);

        corrodiumPickaxe = new ItemAMPickaxe("corrodium_pickaxe", TOOL_CORRODIUM);
        corrodiumAxe = new ItemAMAxe("corrodium_axe", TOOL_CORRODIUM);
        corrodiumSpade = new ItemAMSpade("corrodium_spade", TOOL_CORRODIUM);
        corrodiumHoe = new ItemAMHoe("corrodium_hoe", TOOL_CORRODIUM);
        corrodiumSword = new ItemAMSword("corrodium_sword", TOOL_CORRODIUM);

        foodOnion = new ItemAMFood("food_onion", 1, 0.1F, false, true, new PotionEffect(Potion.confusion.id, 80, 1));

        // TODO create bucket class for setting values and registering item
        bucketSomething = new ItemBucket(ModBlocks.fluidTest);
        bucketSomething.setContainerItem(Items.bucket);
        bucketSomething.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        bucketSomething.setUnlocalizedName("bucket_something");
        GameRegistry.registerItem(bucketSomething, "bucket_something");
    }
}
