package alfheimrsmoons.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBranchBow extends ItemBow {
    public ItemBranchBow() {
        super();
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World world, EntityLivingBase entity) {
                if (entity == null) {
                    return 0.0F;
                } else {
                    ItemStack activeStack = entity.getActiveItemStack();
                    return activeStack != null && activeStack.getItem() instanceof ItemBranchBow ? (float) (stack.getMaxItemUseDuration() - entity.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
    }
}
