package alfheimrsmoons.network;

import net.minecraftforge.fml.relauncher.Side;

public class ProxyServer extends Proxy
{
    @Override
    public Side getSide()
    {
        return Side.SERVER;
    }
}
