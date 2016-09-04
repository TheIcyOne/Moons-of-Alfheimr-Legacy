package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMOre;
import alfheimrsmoons.block.BlockOreBlock;
import net.minecraft.block.Block;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.item.ItemMulti;

import java.util.Arrays;
import java.util.List;

public class ComboOres extends VariantsOfTypesCombo<VariantOre>
{
    public static final ObjectType<VariantOre, BlockAMOre, ItemBlockMulti<VariantOre>> ORE =
            ObjectType.createBlock(VariantOre.class, "ore", BlockAMOre.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantOre, BlockOreBlock, ItemBlockMulti<VariantOre>> BLOCK =
            ObjectType.createBlock(VariantOre.class, "ore_block", BlockOreBlock.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantOre, Block, ItemMulti<VariantOre>> DROP =
            ObjectType.createItem(VariantOre.class, "ore_drop")
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final List<? extends ObjectType<VariantOre, ?, ?>> TYPES = Arrays.asList(ORE, BLOCK, DROP);

    public ComboOres()
    {
        super("ores", TYPES, VariantOre.class, Arrays.asList(VariantOre.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }
}
