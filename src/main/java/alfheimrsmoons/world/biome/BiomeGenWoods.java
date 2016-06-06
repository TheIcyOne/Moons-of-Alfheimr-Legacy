package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;

import java.util.Random;

public class BiomeGenWoods extends BiomeGenAM
{
    private static final EnumFlowerVariant[] FLOWER_VARIANTS = {
            EnumFlowerVariant.PHILODENDRON,
            EnumFlowerVariant.CARNATION,
            EnumFlowerVariant.PURPLE_PACIFIST,
            EnumFlowerVariant.PINK_PRESTIGE,
            EnumFlowerVariant.STARSINGER
    };

    public BiomeGenWoods(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 10;
        theBiomeDecorator.grassPerChunk = 2;
        setFlowerVariants(FLOWER_VARIANTS);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(3) == 0 ? EnumWoodVariant.BEECH : EnumWoodVariant.ELM;
    }
}
