package alfheimrsmoons.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAMArrow extends EntityArrow
{
    private ItemStack arrowStack;

    public EntityAMArrow(World world)
    {
        super(world);
    }

    public EntityAMArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityAMArrow(World world, EntityLivingBase shooter)
    {
        super(world, shooter);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setTag("arrow", getArrowStack().writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        arrowStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("arrow"));
    }

    @Override
    public ItemStack getArrowStack() {
        return arrowStack;
    }

    public void setArrowStack(ItemStack stack) {
        arrowStack = stack;
    }
}
