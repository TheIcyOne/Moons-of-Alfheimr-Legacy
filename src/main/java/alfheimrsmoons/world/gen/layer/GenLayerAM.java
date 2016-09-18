package alfheimrsmoons.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public abstract class GenLayerAM extends GenLayer
{
    public GenLayerAM(long seed)
    {
        super(seed);
    }

    public GenLayerAM(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    // GenLayer.initializeAllBiomeGenerators
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType)
    {
        GenLayer baseLayer = new GenLayerIsland(1L);
        baseLayer = new GenLayerFuzzyZoom(2000L, baseLayer);
        baseLayer = new GenLayerAddIsland(1L, baseLayer);
        baseLayer = new GenLayerZoom(2001L, baseLayer);
        baseLayer = new GenLayerAddIsland(2L, baseLayer);
        baseLayer = new GenLayerAddIsland(50L, baseLayer);
        baseLayer = new GenLayerAddIsland(70L, baseLayer);
        baseLayer = new GenLayerRemoveTooMuchOcean(2L, baseLayer);
        baseLayer = new GenLayerAddSnow(2L, baseLayer);
        baseLayer = new GenLayerAddIsland(3L, baseLayer);
        baseLayer = new GenLayerEdge(2L, baseLayer, GenLayerEdge.Mode.COOL_WARM);
        baseLayer = new GenLayerEdge(2L, baseLayer, GenLayerEdge.Mode.HEAT_ICE);
        baseLayer = new GenLayerEdge(3L, baseLayer, GenLayerEdge.Mode.SPECIAL);
        baseLayer = new GenLayerZoom(2002L, baseLayer);
        baseLayer = new GenLayerZoom(2003L, baseLayer);
        baseLayer = new GenLayerAddIsland(4L, baseLayer);
//        baseLayer = new GenLayerAddMushroomIsland(5L, baseLayer);
        baseLayer = new GenLayerDeepOceanAM(4L, baseLayer);
        baseLayer = GenLayerZoom.magnify(1000L, baseLayer, 0);

        int biomeSize = 4;
        int riverSize = biomeSize;

        if (worldType == WorldType.LARGE_BIOMES)
        {
            biomeSize = 6;
        }

        GenLayer baseRiverLayer = GenLayerZoom.magnify(1000L, baseLayer, 0);
        baseRiverLayer = new GenLayerRiverInit(100L, baseRiverLayer);

        GenLayer biomeRiverLayer = GenLayerZoom.magnify(1000L, baseRiverLayer, 2);

        GenLayer biomeLayer = new GenLayerBiomeAM(200L, baseLayer);
        biomeLayer = GenLayerZoom.magnify(1000L, biomeLayer, 2);
        biomeLayer = new GenLayerBiomeEdgeAM(1000L, biomeLayer);
        biomeLayer = new GenLayerHillsAM(1000L, biomeLayer, biomeRiverLayer);

        GenLayer riverLayer = GenLayerZoom.magnify(1000L, baseRiverLayer, 2);
        riverLayer = GenLayerZoom.magnify(1000L, riverLayer, riverSize);
        riverLayer = new GenLayerRiverAM(1L, riverLayer);
        riverLayer = new GenLayerSmooth(1000L, riverLayer);

        biomeLayer = new GenLayerRareBiome(1001L, biomeLayer);

        for (int i = 0; i < biomeSize; ++i)
        {
            biomeLayer = new GenLayerZoom((long) (1000 + i), biomeLayer);

            if (i == 0)
            {
                biomeLayer = new GenLayerAddIsland(3L, biomeLayer);
            }

            if (i == 1 || biomeSize == 1)
            {
                biomeLayer = new GenLayerShoreAM(1000L, biomeLayer);
            }
        }

        biomeLayer = new GenLayerSmooth(1000L, biomeLayer);

        GenLayerRiverMixAM genBiomes = new GenLayerRiverMixAM(100L, biomeLayer, riverLayer);
        GenLayer biomeIndexLayer = new GenLayerVoronoiZoom(10L, genBiomes);
        genBiomes.initWorldGenSeed(seed);
        biomeIndexLayer.initWorldGenSeed(seed);
        return new GenLayer[]{genBiomes, biomeIndexLayer, genBiomes};
    }

    @Override
    public abstract int[] getInts(int offsetX, int offsetY, int width, int height);
}
