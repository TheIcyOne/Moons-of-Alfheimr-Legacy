package alfheimrsmoons.client.renderer;

import alfheimrsmoons.entity.EntityAMArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.util.ResourceLocation;

public class RenderAMArrow extends RenderArrow<EntityAMArrow>
{
    public RenderAMArrow(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAMArrow entity)
    {
        //TODO return new ResourceLocation(AlfheimrsMoons.MOD_ID, "textures/entity/projectiles/" + entity.getArrowStack().getItem().getRegistryName() + ".png");
        return RenderTippedArrow.RES_ARROW;
    }
}
