package alfheimrsmoons.init;

import alfheimrsmoons.AMFuelHandler;
import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.block.VariantHelper;
import alfheimrsmoons.crafting.AMShapedOreRecipe;
import alfheimrsmoons.crafting.AMShapelessOreRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class AMRecipes
{
    public static void addRecipes()
    {
        addCraftingRecipes();
        addSmeltingRecipes();
        setFuelBurnTimes();
    }

    private static void addCraftingRecipes()
    {
        RecipeSorter.register(AlfheimrsMoons.MOD_ID + ":shapedore", AMShapedOreRecipe.class, Category.SHAPED, "after:minecraft:shaped before:forge:shapedore");
        RecipeSorter.register(AlfheimrsMoons.MOD_ID + ":shapelessore", AMShapelessOreRecipe.class, Category.SHAPELESS, "after:minecraft:shapeless before:forge:shapelessore");

        AMItems.timber_tools.addRecipes(new ItemStack(AMBlocks.planks, 1, OreDictionary.WILDCARD_VALUE));
        AMItems.shale_tools.addRecipes(new ItemStack(AMBlocks.shale));
        AMItems.tektite_tools.addRecipes(VariantHelper.createStack(AMItems.ore_drop, EnumOreVariant.TEKTITE));
        AMItems.sylvanite_tools.addRecipes(VariantHelper.createStack(AMItems.ore_drop, EnumOreVariant.SYLVANITE));

        addShapedRecipe(AMBlocks.rune_bookshelf, "###", "XXX", "###", '#', VariantHelper.createStack(AMBlocks.log, EnumWoodVariant.RUNE), 'X', Items.book);
        addShapedRecipe(new ItemStack(AMBlocks.nitro_torch, 4), "X", "#", 'X', VariantHelper.createStack(AMItems.ore_drop, EnumOreVariant.NITRO), '#', AMItems.branch);

        for (int meta = 0; meta < EnumOreVariant.values.length; meta++)
        {
            addShapedRecipe(AMBlocks.ore_block, "###", "###", "###", '#', AMItems.ore_drop);
        }

        BlockAMLog[] logs = {AMBlocks.log, AMBlocks.log2};
        for (BlockAMLog log : logs)
        {
            for (EnumWoodVariant variant : log.getVariants())
            {
                addShapelessRecipe(VariantHelper.createStack(AMBlocks.planks, 4, variant), VariantHelper.createStack(log, variant));
            }
        }
    }

    private static void addSmeltingRecipes()
    {
        addSmelting(AMBlocks.sediment, AMBlocks.sediment_glass, 0.1F);

        for (EnumOreVariant variant : EnumOreVariant.values)
        {
            GameRegistry.addSmelting(VariantHelper.createStack(AMBlocks.ore, variant), VariantHelper.createStack(AMItems.ore_drop, variant), variant.getSmeltingXP());
        }
    }

    private static void setFuelBurnTimes()
    {
        AMFuelHandler fuelHandler = new AMFuelHandler();
        fuelHandler.setBurnTime(AMItems.branch, 100);
        fuelHandler.setBurnTime(AMBlocks.sapling, 100);
        GameRegistry.registerFuelHandler(fuelHandler);
    }

    public static void addRecipe(IRecipe recipe)
    {
        GameRegistry.addRecipe(recipe);
    }

    public static void addShapelessRecipe(Block output, Object... params)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    public static void addShapelessRecipe(Item output, Object... params)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addShapelessRecipe(output, params);
    }

    public static void addShapedRecipe(Block output, Object... params)
    {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    public static void addShapedRecipe(Item output, Object... params)
    {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    public static void addShapedRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addRecipe(output, params);
    }

    public static void addSmelting(Block input, Block output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Block input, Item output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Block input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(Item input, Block output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Item input, Item output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Item input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(ItemStack input, Block output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(ItemStack input, Item output, float xp)
    {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }
}
