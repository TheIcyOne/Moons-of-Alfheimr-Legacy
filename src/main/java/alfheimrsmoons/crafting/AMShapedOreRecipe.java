package alfheimrsmoons.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Instances of AMShapedOreRecipe are sorted before {@link ShapedOreRecipe}.
 */
public class AMShapedOreRecipe extends ShapedOreRecipe {
    public AMShapedOreRecipe(Block result, Object... recipe) {
        super(result, recipe);
    }

    public AMShapedOreRecipe(Item result, Object... recipe) {
        super(result, recipe);
    }

    public AMShapedOreRecipe(ItemStack result, Object... recipe) {
        super(result, recipe);
    }
}
