package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBiomes;
import alfheimrsmoons.world.gen.layer.GenLayerAM;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeProviderAM extends BiomeProvider
{
    private static final Map<BiomeType, List<BiomeEntry>> BIOMES = setupBiomes();

    public BiomeProviderAM(long seed, WorldType worldType)
    {
        super();
        GenLayer[] genLayers = GenLayerAM.initializeAllBiomeGenerators(seed, worldType);
        genBiomes = genLayers[0];
        biomeIndexLayer = genLayers[1];
    }

    public BiomeProviderAM(WorldInfo worldInfo)
    {
        this(worldInfo.getSeed(), worldInfo.getTerrainType());
    }

    public static List<BiomeEntry> getBiomes(BiomeType type)
    {
        return BIOMES.get(type);
    }

    public static BiomeType getBiomeTypeByID(int id)
    {
        for (BiomeType type : BIOMES.keySet())
        {
            if (type.ordinal() == id)
            {
                return type;
            }
        }
        return null;
    }

    // BiomeManager.setupBiomes
    private static HashMap<BiomeType, List<BiomeEntry>> setupBiomes()
    {
        HashMap<BiomeType, List<BiomeEntry>> biomes = new HashMap<>();

        List<BiomeEntry> list = new ArrayList<>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.WOODS, 10));
        list.add(new BiomeEntry(AMBiomes.FLOODED_FOREST, 10));
        list.add(new BiomeEntry(AMBiomes.RUNEWOOD_FOREST, 10));//TODO generate only at spawn
        biomes.put(BiomeType.WARM, list);

        list = new ArrayList<>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.WOODS, 10));
        biomes.put(BiomeType.COOL, list);

        list = new ArrayList<>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.VELD, 30));
        biomes.put(BiomeType.DESERT, list);

        return biomes;
    }
}
