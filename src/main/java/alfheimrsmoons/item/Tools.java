package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.crafting.AMShapedOreRecipe;
import alfheimrsmoons.init.AMRecipes;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Tools
{
    private final Item.ToolMaterial material;
    private final ItemSword sword;
    private final ItemSpade shovel;
    private final ItemPickaxe pickaxe;
    private final ItemAMAxe axe;
    private final ItemHoe hoe;

    public Tools(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability)
    {
        material = EnumHelper.addToolMaterial(name.toUpperCase(), harvestLevel, maxUses, efficiency, damage, enchantability);

        sword = new ItemSword(material);
        sword.setUnlocalizedName(AlfheimrsMoons.MOD_ID + "." + name + "_sword");
        sword.setRegistryName(name + "_sword");

        shovel = new ItemSpade(material);
        shovel.setUnlocalizedName(AlfheimrsMoons.MOD_ID + "." + name + "_shovel");
        shovel.setRegistryName(name + "_shovel");

        pickaxe = new ItemPickaxe(material) {};
        pickaxe.setUnlocalizedName(AlfheimrsMoons.MOD_ID + "." + name + "_pickaxe");
        pickaxe.setRegistryName(name + "_pickaxe");

        axe = new ItemAMAxe(material);
        axe.setUnlocalizedName(AlfheimrsMoons.MOD_ID + "." + name + "_axe");
        axe.setRegistryName(name + "_axe");

        hoe = new ItemHoe(material);
        hoe.setUnlocalizedName(AlfheimrsMoons.MOD_ID + "." + name + "_hoe");
        hoe.setRegistryName(name + "_hoe");
    }

    public ToolMaterial getMaterial()
    {
        return material;
    }

    public ItemSword getSword()
    {
        return sword;
    }

    public ItemSpade getShovel()
    {
        return shovel;
    }

    public ItemPickaxe getPickaxe()
    {
        return pickaxe;
    }

    public ItemAMAxe getAxe()
    {
        return axe;
    }

    public ItemHoe getHoe()
    {
        return hoe;
    }

    public Tools setAxeAttack(float damage, float speed)
    {
        axe.setAttackDamage(damage);
        axe.setAttackSpeed(speed);
        return this;
    }

    public void registerItems()
    {
        AlfheimrsMoons.proxy.registerItem(sword);
        AlfheimrsMoons.proxy.registerItem(shovel);
        AlfheimrsMoons.proxy.registerItem(pickaxe);
        AlfheimrsMoons.proxy.registerItem(axe);
        AlfheimrsMoons.proxy.registerItem(hoe);
    }

    public void addRecipes(ItemStack materialStack)
    {
        material.setRepairItem(materialStack);
        AMRecipes.addRecipe(new AMShapedOreRecipe(sword, "X", "X", "#", '#', "stickWood", 'X', materialStack));
        AMRecipes.addRecipe(new AMShapedOreRecipe(pickaxe, "XXX", " # ", " # ", '#', "stickWood", 'X', materialStack));
        AMRecipes.addRecipe(new AMShapedOreRecipe(shovel, "X", "#", "#", '#', "stickWood", 'X', materialStack));
        AMRecipes.addRecipe(new AMShapedOreRecipe(axe, "XX", "X#", " #", '#', "stickWood", 'X', materialStack));
        AMRecipes.addRecipe(new AMShapedOreRecipe(hoe, "XX", " #", " #", '#', "stickWood", 'X', materialStack));
    }
}
