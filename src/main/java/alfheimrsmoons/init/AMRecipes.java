package alfheimrsmoons.init;

import alfheimrsmoons.AMFuelHandler;
import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.*;
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

        for (VariantTree variant : AMBlocks.TREES.getValidVariants(ComboTrees.PLANKS))
        {
            AMItems.TOOLS.addRecipes(VariantToolMaterial.TIMBER, AMBlocks.TREES.getStack(ComboTrees.PLANKS, variant));
        }
        AMItems.TOOLS.addRecipes(VariantToolMaterial.SHALE, AMBlocks.SHALE.getStack(VariantShale.NORMAL));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.TEKTITE, AMBlocks.ORES.getStack(ComboOres.DROP, VariantOre.TEKTITE));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.SYLVANITE, AMBlocks.ORES.getStack(ComboOres.DROP, VariantOre.SYLVANITE));

        addShapedRecipe(AMBlocks.RUNE_BOOKSHELF, "###", "XXX", "###", '#', AMBlocks.TREES.getStack(ComboTrees.PLANKS, VariantTree.RUNE), 'X', Items.BOOK);
        addShapedRecipe(new ItemStack(AMBlocks.NITRO_TORCH, 4), "X", "#", 'X', AMBlocks.ORES.getStack(ComboOres.DROP, VariantOre.NITRO), '#', AMItems.BRANCH);

        for(VariantOre variant : AMBlocks.ORES.getVariants())
        {
            ItemStack block = AMBlocks.ORES.getStack(ComboOres.BLOCK, variant);
            ItemStack drop = AMBlocks.ORES.getStack(ComboOres.DROP, variant);
            addShapedRecipe(block, "###", "###", "###", '#', drop);
        }

        for (VariantTree variant : AMBlocks.TREES.getVariants())
        {
            ItemStack planks = AMBlocks.TREES.getStack(ComboTrees.PLANKS, variant);
            ItemStack log = AMBlocks.TREES.getStack(ComboTrees.LOG, variant);
            addShapelessRecipe(planks, log);
        }
    }

    private static void addSmeltingRecipes()
    {
        addSmelting(AMBlocks.SEDIMENT, new ItemStack(AMBlocks.SEDIMENT_GLASS), 0.1F);

        for (VariantOre variant : AMBlocks.ORES.getVariants())
        {
            ItemStack ore = AMBlocks.ORES.getStack(ComboOres.ORE, variant);
            ItemStack drop = AMBlocks.ORES.getStack(ComboOres.DROP, variant);
            addSmelting(ore, drop, variant.getSmeltingXP());
        }
    }

    private static void setFuelBurnTimes()
    {
        AMFuelHandler fuelHandler = new AMFuelHandler();
        fuelHandler.setBurnTime(AMItems.BRANCH, 100);

        for (Block block : AMBlocks.TREES.getBlocks(ComboTrees.SAPLING))
        {
            fuelHandler.setBurnTime(block, 100);
        }

        GameRegistry.registerFuelHandler(fuelHandler);
    }

    public static void addRecipe(IRecipe recipe)
    {
        GameRegistry.addRecipe(recipe);
    }

    public static void addShapelessRecipe(Block output, Object... params)
    {
        addRecipe(new AMShapelessOreRecipe(output, params));
    }

    public static void addShapelessRecipe(Item output, Object... params)
    {
        addRecipe(new AMShapelessOreRecipe(output, params));
    }

    public static void addShapelessRecipe(ItemStack output, Object... params)
    {
        addRecipe(new AMShapelessOreRecipe(output, params));
    }

    public static void addShapedRecipe(Block output, Object... params)
    {
        addRecipe(new AMShapedOreRecipe(output, params));
    }

    public static void addShapedRecipe(Item output, Object... params)
    {
        addRecipe(new AMShapedOreRecipe(output, params));
    }

    public static void addShapedRecipe(ItemStack output, Object... params)
    {
        addRecipe(new AMShapedOreRecipe(output, params));
    }

    public static void addSmelting(Block input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(Item input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }
}
