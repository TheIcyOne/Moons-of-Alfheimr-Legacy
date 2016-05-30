package alfheimrsmoons.client.renderer;

import alfheimrsmoons.client.ProxyClient;
import alfheimrsmoons.entity.EntitySeedPouch;
import alfheimrsmoons.init.AMItems;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderSeedPouch extends RenderSnowball<EntitySeedPouch>
{
    public RenderSeedPouch(RenderManager renderManager)
    {
        super(renderManager, AMItems.seed_pouch, ProxyClient.getRenderItem());
    }
}
