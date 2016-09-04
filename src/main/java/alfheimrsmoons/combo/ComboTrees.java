package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockAMLeaves;
import alfheimrsmoons.block.BlockAMLog;
import alfheimrsmoons.block.BlockAMPlanks;
import alfheimrsmoons.block.BlockAMSapling;
import net.minecraft.block.BlockLeaves;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;

import java.util.Arrays;
import java.util.List;

public class ComboTrees extends VariantsOfTypesCombo<VariantTree>
{
    public static final ObjectType<VariantTree, BlockAMLog, ItemBlockMulti<VariantTree>> LOG =
            ObjectType.createBlock(VariantTree.class, "log", BlockAMLog.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockAMLeaves, ItemBlockMulti<VariantTree>> LEAVES =
            ObjectType.createBlock(VariantTree.class, "leaves", BlockAMLeaves.class)
                    .setIgnoredProperties(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockAMSapling, ItemBlockMulti<VariantTree>> SAPLING =
            ObjectType.createBlock(VariantTree.class, "sapling", BlockAMSapling.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockAMPlanks, ItemBlockMulti<VariantTree>> PLANKS =
            ObjectType.createBlock(VariantTree.class, "planks", "wood", BlockAMPlanks.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final List<? extends ObjectType<VariantTree, ?, ?>> TYPES = Arrays.asList(LOG, LEAVES, SAPLING, PLANKS);

    public ComboTrees()
    {
        super("trees", TYPES, VariantTree.class, Arrays.asList(VariantTree.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }
}
