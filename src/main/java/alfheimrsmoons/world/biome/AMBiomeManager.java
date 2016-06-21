package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBiomes;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AMBiomeManager
{
    public static final Map<BiomeType, List<BiomeEntry>> BIOMES = setupBiomes();

    private static HashMap<BiomeType, List<BiomeEntry>> setupBiomes()
    {
        HashMap<BiomeType, List<BiomeEntry>> biomes = new HashMap<BiomeType, List<BiomeEntry>>();

        List<BiomeEntry> list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.WOODS, 10));
        list.add(new BiomeEntry(AMBiomes.RUNEWOOD_FOREST, 10));//TODO generate only at spawn
        biomes.put(BiomeType.WARM, list);

        list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.WOODS, 10));
        biomes.put(BiomeType.COOL, list);

        list = new ArrayList<BiomeEntry>();
        list.add(new BiomeEntry(AMBiomes.MEADOW, 10));
        list.add(new BiomeEntry(AMBiomes.VELD, 30));
        biomes.put(BiomeType.DESERT, list);

        return biomes;
    }

    public static List<BiomeEntry> getBiomes(BiomeType type)
    {
        return BIOMES.get(type);
    }
}
