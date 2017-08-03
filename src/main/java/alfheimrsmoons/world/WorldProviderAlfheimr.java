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
    protected void init()
    {
    	this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderAM(world.getWorldInfo());
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorAlfheimr(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return super.canCoordinateBeSpawn(x, z) || world.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getBlock() instanceof BlockGrassySoil;
    }
}
