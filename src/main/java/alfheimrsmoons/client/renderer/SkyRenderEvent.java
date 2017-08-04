package alfheimrsmoons.client.renderer;

import alfheimrsmoons.world.WorldProviderAlfheimr;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyRenderEvent {
	@SubscribeEvent
	public static void rendering(RenderWorldLastEvent e){
		WorldProvider p = Minecraft.getMinecraft().world.provider;
		if (p instanceof WorldProviderAlfheimr)
			if (!(p.getSkyRenderer() instanceof AMSkyRenderer))
			p.setSkyRenderer(new AMSkyRenderer());			
	}
}