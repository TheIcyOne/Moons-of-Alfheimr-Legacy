package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.EntityArrowAM;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowAM extends ItemArrow
{
    public ItemArrowAM()
    {
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public EntityArrowAM createArrow(World world, ItemStack stack, EntityLivingBase shooter)
    {
        EntityArrowAM arrow = new EntityArrowAM(world, shooter);
        arrow.setArrowStack(stack);
        return arrow;
    }
}
