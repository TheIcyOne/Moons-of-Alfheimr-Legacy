package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockLeavesAM;
import alfheimrsmoons.block.BlockLogAM;
import alfheimrsmoons.block.BlockPlanksAM;
import alfheimrsmoons.block.BlockSaplingAM;
import net.minecraft.block.BlockLeaves;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;

import java.util.Arrays;
import java.util.List;

public class ComboTrees extends VariantsOfTypesCombo<VariantTree>
{
    public static final ObjectType<VariantTree, BlockLogAM, ItemBlockMulti<VariantTree>> LOG =
            ObjectType.createBlock(VariantTree.class, "log", BlockLogAM.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockLeavesAM, ItemBlockMulti<VariantTree>> LEAVES =
            ObjectType.createBlock(VariantTree.class, "leaves", BlockLeavesAM.class)
                    .setIgnoredProperties(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockSaplingAM, ItemBlockMulti<VariantTree>> SAPLING =
            ObjectType.createBlock(VariantTree.class, "sapling", BlockSaplingAM.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantTree, BlockPlanksAM, ItemBlockMulti<VariantTree>> PLANKS =
            ObjectType.createBlock(VariantTree.class, "planks", "wood", BlockPlanksAM.class)
                    .setUseSeparateVariantJsons(false)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final List<? extends ObjectType<VariantTree, ?, ?>> TYPES = Arrays.asList(LOG, LEAVES, SAPLING, PLANKS);

    public ComboTrees()
    {
        super("trees", TYPES, VariantTree.class, Arrays.asList(VariantTree.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }
}
