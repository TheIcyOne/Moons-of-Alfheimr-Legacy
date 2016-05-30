package alfheimrsmoons.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Instances of AMShapedOreRecipe are sorted before {@link ShapelessOreRecipe}.
 */
public class AMShapelessOreRecipe extends ShapelessOreRecipe
{
    public AMShapelessOreRecipe(Block result, Object... recipe)
    {
        super(result, recipe);
    }

    public AMShapelessOreRecipe(Item result, Object... recipe)
    {
        super(result, recipe);
    }

    public AMShapelessOreRecipe(ItemStack result, Object... recipe)
    {
        super(result, recipe);
    }
}
