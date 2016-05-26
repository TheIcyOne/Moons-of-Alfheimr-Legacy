package alfheimrsmoons.init;

import alfheimrsmoons.AMFuelHandler;
import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.block.VariantHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMRecipes {
    public static void addRecipes() {
        addCraftingRecipes();
        addSmeltingRecipes();
        setFuelBurnTimes();
    }

    private static void addCraftingRecipes() {
        AMItems.tektite_tools.addRecipes(new ItemStack(AMItems.ore_drop, 1, BlockAMOre.EnumType.TEKTITE.getMetadata()));
        AMItems.sylvanite_tools.addRecipes(new ItemStack(AMItems.ore_drop, 1, BlockAMOre.EnumType.SYLVANITE.getMetadata()));

        addShapedRecipe(AMBlocks.rune_bookshelf, "###", "XXX", "###", '#', new ItemStack(AMBlocks.log, 1, VariantHelper.getMetaFromVariant(AMBlocks.log.variants, BlockAMPlanks.EnumType.RUNE)), 'X', Items.book);

        for (int meta = 0; meta < BlockAMOre.EnumType.values.length; meta++) {
            addShapedRecipe(AMBlocks.ore_block, "###", "###", "###", '#', AMItems.ore_drop);
        }

        BlockAMLog[] logs = {AMBlocks.log, AMBlocks.log2};
        for (BlockAMLog log : logs) {
            for (int meta = 0; meta < log.variants.length; meta++) {
                addShapelessRecipe(new ItemStack(AMBlocks.planks, 4, log.variants[meta].getMetadata()), new ItemStack(log, 1, meta));
            }
        }
    }

    private static void addSmeltingRecipes() {
        addSmelting(AMBlocks.sediment, AMBlocks.sediment_glass, 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.LOREIUM.getMetadata(), 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.NITRO.getMetadata(), 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.TEKTITE.getMetadata(), 0.7F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.SYLVANITE.getMetadata(), 1.0F);
    }

    private static void setFuelBurnTimes() {
        AMFuelHandler fuelHandler = new AMFuelHandler();
        fuelHandler.setBurnTime(AMItems.branch, 100);
        fuelHandler.setBurnTime(AMBlocks.sapling, 100);
        GameRegistry.registerFuelHandler(fuelHandler);
    }

    public static void addRecipe(IRecipe recipe) {
        GameRegistry.addRecipe(recipe);
    }

    public static void addShapelessRecipe(Block output, Object... params) {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    public static void addShapelessRecipe(Item output, Object... params) {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        GameRegistry.addShapelessRecipe(output, params);
    }

    public static void addShapedRecipe(Block output, Object... params) {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    public static void addShapedRecipe(Item output, Object... params) {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    public static void addShapedRecipe(ItemStack output, Object... params) {
        GameRegistry.addRecipe(output, params);
    }

    public static void addOreSmelting(Block ore, Item item, int meta, float experience) {
        addOreSmelting(ore, meta, item, meta, experience);
    }

    public static void addOreSmelting(Block ore, int oreMeta, Item item, int itemMeta, float experience) {
        GameRegistry.addSmelting(new ItemStack(ore, 1, oreMeta), new ItemStack(item, 1, itemMeta), experience);
    }

    public static void addSmelting(Block input, Block output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Block input, Item output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Block input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(Item input, Block output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Item input, Item output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Item input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(ItemStack input, Block output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(ItemStack input, Item output, float xp) {
        GameRegistry.addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(ItemStack input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }
}
