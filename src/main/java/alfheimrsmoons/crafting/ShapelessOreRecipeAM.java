package alfheimrsmoons.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Instances of AMShapedOreRecipe are sorted before {@link ShapelessOreRecipe}.
 */
public class ShapelessOreRecipeAM extends ShapelessOreRecipe
{
    public ShapelessOreRecipeAM(Block result, Object... recipe)
    {
        super(result, recipe);
    }

    public ShapelessOreRecipeAM(Item result, Object... recipe)
    {
        super(result, recipe);
    }

    public ShapelessOreRecipeAM(ItemStack result, Object... recipe)
    {
        super(result, recipe);
    }
}
