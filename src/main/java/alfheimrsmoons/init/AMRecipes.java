package alfheimrsmoons.init;

import alfheimrsmoons.AMFuelHandler;
import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.*;
import alfheimrsmoons.crafting.ShapedOreRecipeAM;
import alfheimrsmoons.crafting.ShapelessOreRecipeAM;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
        RecipeSorter.register(AlfheimrsMoons.MOD_ID + ":shapedore", ShapedOreRecipeAM.class, Category.SHAPED, "after:minecraft:shaped before:forge:shapedore");
        RecipeSorter.register(AlfheimrsMoons.MOD_ID + ":shapelessore", ShapelessOreRecipeAM.class, Category.SHAPELESS, "after:minecraft:shapeless before:forge:shapelessore");

        for (VariantTree variant : AMBlocks.TREES.getVariants())
        {
            AMItems.TOOLS.addRecipes(VariantToolMaterial.TIMBER, AMBlocks.TREES.getStack(ComboTrees.PLANKS, variant));
        }
        AMItems.TOOLS.addRecipes(VariantToolMaterial.SHALE, AMBlocks.SHALE.getStack(VariantShale.NORMAL));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.TEKTITE, AMBlocks.ORES.getStack(ComboOres.INGOT, VariantOre.TEKTITE));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.SYLVANITE, AMBlocks.ORES.getStack(ComboOres.DROP, VariantOre.SYLVANITE));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.MOONSTEEL, AMBlocks.ORES.getStack(ComboOres.INGOT, VariantOre.MOONSTONE));
        AMItems.TOOLS.addRecipes(VariantToolMaterial.SUNSTEEL, AMBlocks.ORES.getStack(ComboOres.INGOT, VariantOre.SUNSTONE));

        addShapedRecipe(AMBlocks.RUNE_BOOKSHELF, "###", "XXX", "###", '#', AMBlocks.TREES.getStack(ComboTrees.PLANKS, VariantTree.RUNE), 'X', Items.BOOK);

        for (VariantBioluminescence variant : AMBlocks.BIOLUMINESCENCE.getVariants())
        {
            ItemStack torches = AMBlocks.BIOLUMINESCENCE.getStack(ComboBioluminescence.TORCH, variant, 4);
            ItemStack bioluminescence = AMBlocks.BIOLUMINESCENCE.getStack(ComboBioluminescence.BIOLUMINESCENCE, variant);
            addShapedRecipe(torches, "X", "#", 'X', bioluminescence, '#', "stickWood");
        }

        for(VariantOre variant : AMBlocks.ORES.getVariants())
        {
            ItemStack block = AMBlocks.ORES.getStack(ComboOres.BLOCK, variant);
            ItemStack ingot = AMBlocks.ORES.getSmeltingOutput(variant);
            addShapedRecipe(block, "###", "###", "###", '#', ingot);
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
            ItemStack ingot = AMBlocks.ORES.getSmeltingOutput(variant);
            addSmelting(ore, ingot, variant.getSmeltingXP());

            if (variant.hasDrop() && variant.hasIngot())
            {
                ItemStack drop = AMBlocks.ORES.getStack(ComboOres.DROP, variant);
                addSmelting(drop, ingot, variant.getSmeltingXP());
            }
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
        addRecipe(new ShapelessOreRecipeAM(output, params));
    }

    public static void addShapelessRecipe(Item output, Object... params)
    {
        addRecipe(new ShapelessOreRecipeAM(output, params));
    }

    public static void addShapelessRecipe(ItemStack output, Object... params)
    {
        addRecipe(new ShapelessOreRecipeAM(output, params));
    }

    public static void addShapedRecipe(Block output, Object... params)
    {
        addRecipe(new ShapedOreRecipeAM(output, params));
    }

    public static void addShapedRecipe(Item output, Object... params)
    {
        addRecipe(new ShapedOreRecipeAM(output, params));
    }

    public static void addShapedRecipe(ItemStack output, Object... params)
    {
        addRecipe(new ShapedOreRecipeAM(output, params));
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
