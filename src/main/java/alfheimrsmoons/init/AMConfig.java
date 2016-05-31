package alfheimrsmoons.init;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class AMConfig
{
    public static final String CATEGORY_DIMENSIONS = "dimensions";

    public static int alfheimrDimID;

    public static void load(File configFile)
    {
        Configuration config = new Configuration(configFile);
        config.load();
        alfheimrDimID = config.getInt("alfheimrDimID", CATEGORY_DIMENSIONS, 304, 2, 1023, "The ID of the Alfheimr dimension");
        config.save();
    }
}
