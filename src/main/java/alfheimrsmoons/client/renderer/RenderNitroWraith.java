package alfheimrsmoons.client.renderer;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.client.model.ModelNitroWraith;
import alfheimrsmoons.entity.EntityNitroWraith;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNitroWraith extends RenderLiving<EntityNitroWraith>
{
    private static final ResourceLocation NITRO_WRAITH_TEXTURES = new ResourceLocation(AlfheimrsMoons.MOD_ID, "textures/entity/nitro_wraith.png");

    public RenderNitroWraith(RenderManager renderManager)
    {
        super(renderManager, new ModelNitroWraith(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNitroWraith entity)
    {
        return NITRO_WRAITH_TEXTURES;
    }
}
