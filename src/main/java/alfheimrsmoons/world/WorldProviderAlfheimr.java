package alfheimrsmoons.world;

import alfheimrsmoons.block.BlockGrassySoil;
import alfheimrsmoons.init.AMDimensions;
import alfheimrsmoons.world.biome.BiomeProviderAM;
import alfheimrsmoons.world.gen.ChunkGeneratorAlfheimr;
import net.minecraft.util.math.BlockPos;
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
    protected void createBiomeProvider()
    {
        biomeProvider = new BiomeProviderAM(worldObj.getWorldInfo());
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorAlfheimr(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled(), worldObj.getWorldInfo().getGeneratorOptions());
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return super.canCoordinateBeSpawn(x, z) || worldObj.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getBlock() instanceof BlockGrassySoil;
    }
}
