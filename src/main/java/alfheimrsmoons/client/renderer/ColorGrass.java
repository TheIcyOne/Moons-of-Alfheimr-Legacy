package alfheimrsmoons.client.renderer;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

public class ColorGrass implements IBlockColor, IItemColor
{
    private final Predicate<IBlockState> predicate;

    public ColorGrass()
    {
        this(null);
    }

    public ColorGrass(Predicate<IBlockState> predicate)
    {
        this.predicate = predicate;
    }

    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
    {
        if (predicate == null || predicate.apply(state))
        {
            if (world != null && pos != null)
            {
                return BiomeColorHelper.getGrassColorAtPos(world, pos);
            }
            else
            {
                return ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }
        else
        {
            return -1;
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex)
    {
        IBlockState state = Block.getBlockFromItem(stack.getItem()).getStateFromMeta(stack.getMetadata()); //TODO deprecated
        return colorMultiplier(state, null, null, tintIndex);
    }
}
