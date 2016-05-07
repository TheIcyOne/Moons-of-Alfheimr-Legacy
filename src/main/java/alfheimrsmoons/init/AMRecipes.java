package alfheimrsmoons.init;

import alfheimrsmoons.block.BlockAMLog;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMRecipes {
    public static void registerRecipes() {
        registerLogRecipes(AMBlocks.log);
        registerLogRecipes(AMBlocks.log2);
    }

    private static void registerLogRecipes(BlockAMLog log) {
        for (int meta = 0; meta < log.types.length; meta++) {
            GameRegistry.addShapelessRecipe(new ItemStack(AMBlocks.planks, 4, log.types[meta].getMetadata()), new ItemStack(log, 1, meta));
        }
    }
}
