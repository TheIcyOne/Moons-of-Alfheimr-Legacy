package alfheimrsmoons.item;

import alfheimrsmoons.entity.EntityAMArrow;
import com.google.common.base.Function;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAMArrow extends ItemArrow {
    @Override
    public EntityAMArrow makeTippedArrow(World world, ItemStack stack, EntityLivingBase shooter) {
        EntityAMArrow arrow = new EntityAMArrow(world, shooter);
        arrow.setArrowStackGetter(new Function<EntityAMArrow, ItemStack>() {
            @Override
            public ItemStack apply(EntityAMArrow input) {
                return new ItemStack(ItemAMArrow.this);
            }
        });
        return arrow;
    }
}
