package alfheimrsmoons.client;

import alfheimrsmoons.network.Proxy;

public class ProxyClient extends Proxy {
    @Override
    public void preInit() {
        ItemModels.registerModels();
        RenderFactory.registerRenders();
    }
}
