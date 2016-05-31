package alfheimrsmoons.init;

import alfheimrsmoons.world.WorldProviderAlfheimr;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class AMDimensions
{
    public static DimensionType alfheimr;

    public static void registerDimensions()
    {
        alfheimr = registerDimensionType("Alfheimr", "_alfheimr", AMConfig.alfheimrDimID, WorldProviderAlfheimr.class, false);
    }

    private static DimensionType registerDimensionType(String name, String suffix, int id, Class<? extends WorldProvider> provider, boolean keepLoaded)
    {
        DimensionType type = DimensionType.register(name, suffix, id, provider, keepLoaded);
        DimensionManager.registerDimension(type.getId(), type);
        return type;
    }
}
