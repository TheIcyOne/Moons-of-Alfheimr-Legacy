package alfheimrsmoons.common.recipes;

import alfheimrsmoons.common.block.BlockCrystalOre;
import alfheimrsmoons.common.block.BlockPlanks;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {
    public static void register() {
        final ItemStack branch = new ItemStack(ModItems.branch, 1);
        final ItemStack crystalKasolite = new ItemStack(ModItems.crystal, 1, BlockCrystalOre.EnumType.KASOLITE.ordinal());
        final ItemStack crystalNitro = new ItemStack(ModItems.crystal, 1, BlockCrystalOre.EnumType.NITRO.ordinal());

        GameRegistry
                .addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.crystal, 1, BlockCrystalOre.EnumType.NITRO.getMetadata()), "NNN", "NNN", "NNN", 'N', ModItems.nitroPowder));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nitroPowder, 9), new ItemStack(ModItems.crystal, 1, BlockCrystalOre.EnumType.NITRO.getMetadata()));

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.nitroTorch, 4), "N", "B", 'N', crystalNitro, 'B', new ItemStack(ModItems.branch, 1));

        // TODO create methods for tools+armor recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteHelmet, "KKK", "K K", 'K', crystalKasolite));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteChestplate, "K K", "KKK", "KKK", 'K', crystalKasolite));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteLeggings, "KKK", "K K", "K K", 'K', crystalKasolite));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteBoots, "K K", "K K", 'K', crystalKasolite));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteSword, "K", "K", "B", 'K', crystalKasolite, 'B', branch));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasolitePickaxe, "KKK", " B ", " B ", 'K', crystalKasolite, 'B', branch));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteAxe, "KK", "KB", " B", 'K', crystalKasolite, 'B', branch));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteSpade, "K", "B", "B", 'K', crystalKasolite, 'B', branch));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.kasoliteHoe, "KK", " B", " B", 'K', crystalKasolite, 'B', branch));

        GameRegistry.addSmelting(new ItemStack(ModBlocks.crystalOre, 1, BlockCrystalOre.EnumType.CORRODIUM.ordinal()),
                new ItemStack(ModItems.crystal, 1, BlockCrystalOre.EnumType.CORRODIUM.ordinal()), 2F);

        for (BlockCrystalOre.EnumType t : BlockCrystalOre.EnumType.values()) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(new ItemStack(ModBlocks.storage, 1, t.getMetadata()), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.crystal, 1, t.getMetadata())));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.crystal, 9, t.getMetadata()), "S", 'S', new ItemStack(ModBlocks.storage, 1, t.getMetadata())));
        }

        for (BlockPlanks.EnumType t : BlockPlanks.EnumType.values()) {
            GameRegistry.addShapelessRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.amPlanks), 4, t.getMetadata()),
                    new ItemStack(Item.getItemFromBlock(ModBlocks.amLog), 1, t.getMetadata()));

            GameRegistry.addShapedRecipe(new ItemStack(ModItems.branch, 4), "P", "P", 'P', new ItemStack(ModBlocks.amPlanks, 1, t.getMetadata()));
        }
    }
}
