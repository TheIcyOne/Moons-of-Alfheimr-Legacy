package alfheimrsmoons.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Instances of AMShapedOreRecipe are sorted before {@link ShapedOreRecipe}.
 */
public class ShapedOreRecipeAM extends ShapedOreRecipe
{
    public ShapedOreRecipeAM(Block result, Object... recipe)
    {
        super(result, recipe);
    }

    public ShapedOreRecipeAM(Item result, Object... recipe)
    {
        super(result, recipe);
    }

    public ShapedOreRecipeAM(ItemStack result, Object... recipe)
    {
        super(result, recipe);
    }
}
