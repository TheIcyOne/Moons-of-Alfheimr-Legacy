package alfheimrsmoons.item;

import alfheimrsmoons.entity.EntityArrowAM;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowAM extends ItemArrow
{
    @Override
    public EntityArrowAM createArrow(World world, ItemStack stack, EntityLivingBase shooter)
    {
        EntityArrowAM arrow = new EntityArrowAM(world, shooter);
        arrow.setArrowStack(stack);
        return arrow;
    }
}
