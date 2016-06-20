package alfheimrsmoons.world.biome;

import alfheimrsmoons.util.EnumFlowerVariant;
import alfheimrsmoons.util.EnumTallFlowerVariant;
import alfheimrsmoons.util.EnumWoodVariant;

import java.util.Random;

public class BiomeGenWoods extends BiomeGenAM
{
    public static final EnumFlowerVariant[] FLOWER_VARIANTS =
            {
                    EnumFlowerVariant.PHILODENDRON,
                    EnumFlowerVariant.CARNATION,
                    EnumFlowerVariant.PURPLE_PACIFIST,
                    EnumFlowerVariant.PINK_PRESTIGE,
                    EnumFlowerVariant.STARSINGER
            };

    public static final EnumTallFlowerVariant[] TALL_FLOWER_VARIANTS =
            {
                    EnumTallFlowerVariant.DINANTHUS,
                    EnumTallFlowerVariant.COLOMBINE
            };

    public BiomeGenWoods(BiomeProperties properties)
    {
        super(properties);
        theBiomeDecorator.treesPerChunk = 10;
        theBiomeDecorator.grassPerChunk = 2;
    }

    @Override
    protected void addFlowerVariants()
    {
        addFlowerVariants(FLOWER_VARIANTS);
        addTallFlowerVariants(TALL_FLOWER_VARIANTS);
    }

    @Override
    protected EnumWoodVariant getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(3) == 0 ? EnumWoodVariant.BEECH : EnumWoodVariant.ELM;
    }
}
