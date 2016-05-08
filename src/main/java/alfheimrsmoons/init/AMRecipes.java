package alfheimrsmoons.init;

import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockAMPlanks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMRecipes {
    public static void addRecipes() {
        addLogRecipes(AMBlocks.log);
        addLogRecipes(AMBlocks.log2);
        GameRegistry.addRecipe(new ItemStack(AMBlocks.rune_bookshelf), "###", "XXX", "###", '#', new ItemStack(AMBlocks.log, 1, AMBlocks.log.getMetaFromVariant(BlockAMPlanks.EnumType.RUNE)), 'X', Items.book);

        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.NITRO.getMetadata(), 0.1F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.KASOLITE.getMetadata(), 0.7F);
        addOreSmelting(AMBlocks.ore, AMItems.ore_drop, BlockAMOre.EnumType.LOREIUM.getMetadata(), 1.0F);
    }

    private static void addLogRecipes(BlockAMLog log) {
        for (int meta = 0; meta < log.types.length; meta++) {
            GameRegistry.addShapelessRecipe(new ItemStack(AMBlocks.planks, 4, log.types[meta].getMetadata()), new ItemStack(log, 1, meta));
        }
    }

    private static void addOreSmelting(Block ore, Item item, int meta, float experience) {
        addOreSmelting(ore, meta, item, meta, experience);
    }

    private static void addOreSmelting(Block ore, int oreMeta, Item item, int itemMeta, float experience) {
        GameRegistry.addSmelting(new ItemStack(ore, 1, oreMeta), new ItemStack(item, 1, itemMeta), experience);
    }
}