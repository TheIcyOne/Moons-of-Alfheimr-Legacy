package alfheimrsmoons.world.biome;

import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.combo.VariantTree;
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
        addFlowerVariants(VariantFlower.FORGET_ME_NOT);
    }

    @Override
    protected VariantTree getRandomTreeVariant(Random rand)
    {
        return rand.nextInt(10) == 0 ? VariantTree.BEECH : VariantTree.RUNE;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x135574;
    }
}
