package alfheimrsmoons.init;

import alfheimrsmoons.AMFuelHandler;
import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.crafting.AMShapedOreRecipe;
import alfheimrsmoons.crafting.AMShapelessOreRecipe;
import alfheimrsmoons.util.EnumOreVariant;
import alfheimrsmoons.util.EnumShaleVariant;
import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.util.VariantHelper;
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

        AMItems.TIMBER_TOOLS.addRecipes(new ItemStack(AMBlocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        AMItems.SHALE_TOOLS.addRecipes(VariantHelper.createStack(AMBlocks.SHALE, EnumShaleVariant.NORMAL));
        AMItems.TEKTITE_TOOLS.addRecipes(VariantHelper.createStack(AMItems.ORE_DROP, EnumOreVariant.TEKTITE));
        AMItems.SYLVANITE_TOOLS.addRecipes(VariantHelper.createStack(AMItems.ORE_DROP, EnumOreVariant.SYLVANITE));

        addShapedRecipe(AMBlocks.RUNE_BOOKSHELF, "###", "XXX", "###", '#', VariantHelper.createStack(EnumWoodVariant.RUNE.getLogBlock(), EnumWoodVariant.RUNE), 'X', Items.BOOK);
        addShapedRecipe(new ItemStack(AMBlocks.NITRO_TORCH, 4), "X", "#", 'X', VariantHelper.createStack(AMItems.ORE_DROP, EnumOreVariant.NITRO), '#', AMItems.BRANCH);

        for (int meta = 0; meta < EnumOreVariant.VARIANTS.length; meta++)
        {
            addShapedRecipe(new ItemStack(AMBlocks.ORE_BLOCK, 1, meta), "###", "###", "###", '#', new ItemStack(AMItems.ORE_DROP, 1, meta));
        }

        for (EnumWoodVariant variant : EnumWoodVariant.VARIANTS)
        {
            addShapelessRecipe(VariantHelper.createStack(AMBlocks.PLANKS, 4, variant), VariantHelper.createStack(variant.getLogBlock(), variant));
        }
    }

    private static void addSmeltingRecipes()
    {
        addSmelting(AMBlocks.SEDIMENT, AMBlocks.SEDIMENT_GLASS, 0.1F);

        for (EnumOreVariant variant : EnumOreVariant.VARIANTS)
        {
            GameRegistry.addSmelting(VariantHelper.createStack(AMBlocks.ORE, variant), VariantHelper.createStack(AMItems.ORE_DROP, variant), variant.getSmeltingXP());
        }
    }

    private static void setFuelBurnTimes()
    {
        AMFuelHandler fuelHandler = new AMFuelHandler();
        fuelHandler.setBurnTime(AMItems.BRANCH, 100);
        fuelHandler.setBurnTime(AMBlocks.SAPLING, 100);
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
