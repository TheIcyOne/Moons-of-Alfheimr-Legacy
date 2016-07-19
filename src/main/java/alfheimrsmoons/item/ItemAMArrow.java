package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.EntityAMArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAMArrow extends ItemArrow
{
    public ItemAMArrow()
    {
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public EntityAMArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter)
    {
        EntityAMArrow arrow = new EntityAMArrow(world, shooter);
        arrow.setArrowStack(stack);
        return arrow;
    }
}
