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
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMRecipes {
    public static void addRecipes() {
        addLogRecipes(AMBlocks.log);
        addLogRecipes(AMBlocks.log2);
        addRecipe(AMBlocks.rune_bookshelf, "###", "XXX", "###", '#', new ItemStack(AMBlocks.log, 1, VariantHelper.getMetaFromVariant(AMBlocks.log.variants, BlockAMPlanks.EnumType.RUNE)), 'X', Items.book);

        addSmelting(AMBlocks.sediment, AMBlocks.sediment_glass, 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.NITRO.getMetadata(), 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.KASOLITE.getMetadata(), 0.7F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.LOREIUM.getMetadata(), 1.0F);

        AMFuelHandler fuelHandler = new AMFuelHandler();
        fuelHandler.setBurnTime(AMItems.branch, 100);
        fuelHandler.setBurnTime(AMBlocks.sapling, 100);
        GameRegistry.registerFuelHandler(fuelHandler);
    }

    private static void addLogRecipes(BlockAMLog log) {
        for (int meta = 0; meta < log.variants.length; meta++) {
            GameRegistry.addShapelessRecipe(new ItemStack(AMBlocks.planks, 4, log.variants[meta].getMetadata()), new ItemStack(log, 1, meta));
        }
    }

    private static void addShapelessRecipe(Block output, Object... params) {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    private static void addShapelessRecipe(Item output, Object... params) {
        GameRegistry.addShapelessRecipe(new ItemStack(output), params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        GameRegistry.addShapelessRecipe(output, params);
    }

    private static void addRecipe(Block output, Object... params) {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    private static void addRecipe(Item output, Object... params) {
        GameRegistry.addRecipe(new ItemStack(output), params);
    }

    private static void addRecipe(ItemStack output, Object... params) {
        GameRegistry.addRecipe(output, params);
    }

    private static void addOreSmelting(Block ore, Item item, int meta, float experience) {
        addOreSmelting(ore, meta, item, meta, experience);
    }

    private static void addOreSmelting(Block ore, int oreMeta, Item item, int itemMeta, float experience) {
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
