package alfheimrsmoons.world.biome;

import alfheimrsmoons.init.AMBiomes;
import alfheimrsmoons.world.gen.layer.*;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
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

    public BiomeProviderAM(long seed)
    {
        super();
        GenLayer[] genLayers = initializeAllBiomeGenerators(seed);
        genBiomes = genLayers[0];
        biomeIndexLayer = genLayers[1];
    }

    public BiomeProviderAM(WorldInfo worldInfo)
    {
        this(worldInfo.getSeed());
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

    // GenLayer.initializeAllBiomeGenerators
    private static GenLayer[] initializeAllBiomeGenerators(long seed)
    {
        GenLayer genlayer = new GenLayerIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        GenLayerAddIsland genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
        genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        GenLayerAddIsland genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        GenLayerZoom genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayerAddIsland genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland3);
        GenLayerDeepOceanAM genlayerdeepocean = new GenLayerDeepOceanAM(4L, genlayeraddmushroomisland);
        GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        int i = 4;
        int j = i;

        GenLayer lvt_8_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, lvt_8_1_);
        GenLayer lvt_10_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer ret = new GenLayerBiomeAM(200L, genlayer4);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        GenLayer genlayerbiomeedge = new GenLayerBiomeEdgeAM(1000L, ret);
        GenLayer genlayerhills = new GenLayerHillsAM(1000L, genlayerbiomeedge, lvt_10_1_);
        GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, j);
        GenLayerRiverAM genlayerriver = new GenLayerRiverAM(1L, genlayer5);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

        for (int k = 0; k < i; ++k)
        {
            genlayerhills = new GenLayerZoom((long) (1000 + k), genlayerhills);

            if (k == 0)
            {
                genlayerhills = new GenLayerAddIsland(3L, genlayerhills);
            }

            if (k == 1 || i == 1)
            {
                genlayerhills = new GenLayerShoreAM(1000L, genlayerhills);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
        GenLayerRiverMixAM genlayerrivermix = new GenLayerRiverMixAM(100L, genlayersmooth1, genlayersmooth);
        GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayer3.initWorldGenSeed(seed);
        return new GenLayer[]{genlayerrivermix, genlayer3, genlayerrivermix};
    }
}
