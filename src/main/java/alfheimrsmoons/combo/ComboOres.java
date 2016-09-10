package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockOreAM;
import alfheimrsmoons.block.BlockOreBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.item.ItemMulti;

import java.util.Arrays;
import java.util.List;

public class ComboOres extends VariantsOfTypesCombo<VariantOre>
{
    public static final ObjectType<VariantOre, BlockOreAM, ItemBlockMulti<VariantOre>> ORE =
            ObjectType.createBlock(VariantOre.class, "ore", BlockOreAM.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantOre, BlockOreBlock, ItemBlockMulti<VariantOre>> BLOCK =
            ObjectType.createBlock(VariantOre.class, "ore_block", BlockOreBlock.class)
                    .setUseSeparateVariantJsons(false)
                    .setVariantNameFunction((v, n) -> v.getName() + "_block");

    public static final ObjectType<VariantOre, Block, ItemMulti<VariantOre>> DROP =
            ObjectType.createItem(VariantOre.class, "ore_drop")
                    .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
                    .setVariantFilter(VariantOre::hasDrop)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantOre, Block, ItemMulti<VariantOre>> INGOT =
            ObjectType.createItem(VariantOre.class, "ingot")
                    .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
                    .setVariantFilter(VariantOre::hasIngot)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final List<? extends ObjectType<VariantOre, ?, ?>> TYPES = Arrays.asList(ORE, BLOCK, DROP, INGOT);

    public ComboOres()
    {
        super("ores", TYPES, VariantOre.class, Arrays.asList(VariantOre.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }

    public static ObjectType<VariantOre, Block, ItemMulti<VariantOre>> getSmeltingOutputType(VariantOre variant)
    {
        return variant.hasIngot() ? INGOT : DROP;
    }

    public ItemStack getSmeltingOutput(VariantOre variant, int stackSize)
    {
        return getStack(getSmeltingOutputType(variant), variant, stackSize);
    }

    public ItemStack getSmeltingOutput(VariantOre variant)
    {
        return getSmeltingOutput(variant, 1);
    }
}
