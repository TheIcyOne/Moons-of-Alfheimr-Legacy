package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BiomeRunewoodForest extends BiomeWoods
{
    public BiomeRunewoodForest(BiomeProperties properties)
    {
        super(properties);
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariants(EnumFlowerVariant.FORGET_ME_NOT);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? EnumWoodVariant.BEECH : EnumWoodVariant.RUNE;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x135574;
    }
}
