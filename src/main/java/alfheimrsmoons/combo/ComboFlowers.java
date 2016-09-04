package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockFlowerAM;
import alfheimrsmoons.block.BlockTallFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;

import java.util.Arrays;
import java.util.List;

public class ComboFlowers extends VariantsOfTypesCombo<VariantFlower>
{
    public static final ObjectType<VariantFlower, BlockFlowerAM, ItemBlockMulti<VariantFlower>> FLOWER =
            ObjectType.createBlock(VariantFlower.class, "flower", BlockFlowerAM.class)
                    .setVariantFilter(VariantFlower::hasNormalFlower)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.NONE);

    public static final ObjectType<VariantFlower, BlockTallFlower, ItemBlockMulti<VariantFlower>> TALL_FLOWER =
            ObjectType.createBlock(VariantFlower.class, "tall_flower", BlockTallFlower.class)
                    .setVariantFilter(VariantFlower::hasTallFlower)
                    .setUseSeparateVariantJsons(false)
                    .setVariantNameFunction((v, n) -> "tall_" + v);

    public static final List<? extends ObjectType<VariantFlower, ?, ?>> TYPES = Arrays.asList(FLOWER, TALL_FLOWER);

    public ComboFlowers()
    {
        super("flowers", TYPES, VariantFlower.class, Arrays.asList(VariantFlower.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }

    public boolean placeTallFlower(World world, BlockPos pos, VariantFlower variant)
    {
        IBlockState state = getBlockState(TALL_FLOWER, variant);
        if (state.getBlock().canPlaceBlockAt(world, pos))
        {
            world.setBlockState(pos, state.withProperty(EnumHalf.PROPERTY, EnumHalf.BOTTOM), 2);
            world.setBlockState(pos.up(), state.withProperty(EnumHalf.PROPERTY, EnumHalf.TOP), 2);
            return true;
        }
        return false;
    }
}
