package alfheimrsmoons.world.biome;

import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.combo.VariantTree;

import java.util.Random;

public class BiomeWoods extends BiomeAM
{
    public static final VariantFlower[] FLOWER_VARIANTS =
            {
                    VariantFlower.PHILODENDRON,
                    VariantFlower.CARNATION,
                    VariantFlower.PURPLE_PACIFIST,
                    VariantFlower.PINK_PRESTIGE,
                    VariantFlower.STARSINGER
            };

    public static final VariantFlower[] TALL_FLOWER_VARIANTS =
            {
                    VariantFlower.DINANTHUS,
                    VariantFlower.COLOMBINE
            };

    public BiomeWoods(BiomeProperties properties)
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
    protected VariantTree getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(3) == 0 ? VariantTree.BEECH : VariantTree.ELM;
    }
}
