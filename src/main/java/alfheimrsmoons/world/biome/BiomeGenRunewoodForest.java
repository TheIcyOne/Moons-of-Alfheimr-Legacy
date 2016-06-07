package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;

import java.util.Random;

public class BiomeGenRunewoodForest extends BiomeGenWoods
{
    public BiomeGenRunewoodForest(BiomeProperties properties)
    {
        super(properties);
        setFlowerVariants(EnumFlowerVariant.FORGET_ME_NOT);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? EnumWoodVariant.BEECH : EnumWoodVariant.RUNE;
    }
}
