package alfheimrsmoons.entity;

import com.google.common.base.Function;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAMArrow extends EntityArrow {
    private Function<EntityAMArrow, ItemStack> arrowStackGetter;

    public EntityAMArrow(World world) {
        super(world);
    }

    public EntityAMArrow(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityAMArrow(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setTag("arrowStack", getArrowStack().writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        final ItemStack arrowStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("arrowStack"));
        arrowStackGetter = new Function<EntityAMArrow, ItemStack>() {
            @Override
            public ItemStack apply(EntityAMArrow input) {
                return arrowStack;
            }
        };
    }

    @Override
    public ItemStack getArrowStack() {
        return getArrowStackGetter().apply(this);
    }

    public Function<EntityAMArrow, ItemStack> getArrowStackGetter() {
        return arrowStackGetter;
    }

    public void setArrowStackGetter(Function<EntityAMArrow, ItemStack> arrowStackGetter) {
        this.arrowStackGetter = arrowStackGetter;
    }
}
