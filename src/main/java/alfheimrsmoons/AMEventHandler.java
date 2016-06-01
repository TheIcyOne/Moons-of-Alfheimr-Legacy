package alfheimrsmoons;

import alfheimrsmoons.item.ItemBranchBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AMEventHandler
{
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event)
    {
        EntityPlayer player = event.getEntity();
        if (player.isHandActive() && player.getActiveItemStack() != null && player.getActiveItemStack().getItem() instanceof ItemBranchBow)
        {
            int i = player.getItemInUseMaxCount();
            float f = (float) i / 20.0F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            else
            {
                f = f * f;
            }

            event.setNewfov(event.getFov() * (1.0F - f * 0.15F));
        }
    }

    // TODO delete placeholder
    @SubscribeEvent
    public void dimensionPlaceholder(net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock e) {
        if (e.getWorld().getBlockState(e.getPos()).getBlock() == alfheimrsmoons.init.AMBlocks.soil && e.getItemStack() == null) {
            e.getEntityPlayer().changeDimension(alfheimrsmoons.init.AMDimensions.alfheimr.getId());
        }
    }
}
