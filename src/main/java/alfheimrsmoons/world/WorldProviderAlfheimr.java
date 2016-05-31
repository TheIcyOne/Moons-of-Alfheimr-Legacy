package alfheimrsmoons.world;

import alfheimrsmoons.init.AMDimensions;
import alfheimrsmoons.world.biome.AMBiomeProvider;
import alfheimrsmoons.world.gen.ChunkGeneratorAlfheimr;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderAlfheimr extends WorldProvider
{
    @Override
    public DimensionType getDimensionType()
    {
        return AMDimensions.alfheimr;
    }

    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new AMBiomeProvider(worldObj.getWorldInfo());
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorAlfheimr(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled(), worldObj.getWorldInfo().getGeneratorOptions());
    }
}