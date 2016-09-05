package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.BlockBioluminescenceTorch;
import net.minecraft.block.Block;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.item.ItemMulti;

import java.util.Arrays;
import java.util.List;

public class ComboBioluminescence extends VariantsOfTypesCombo<VariantBioluminescence>
{
//    public static final ObjectType<VariantBioluminescence, BlockBioluminescenceLamp, ItemBlockMulti<VariantBioluminescence>> LAMP =
//            ObjectType.createBlock(VariantBioluminescence.class, "bioluminescence_lamp", BlockBioluminescenceLamp.class)
//                    .setUseSeparateVariantJsons(false);

    public static final ObjectType<VariantBioluminescence, BlockBioluminescenceTorch, ItemBlockMulti<VariantBioluminescence>> TORCH =
            ObjectType.createBlock(VariantBioluminescence.class, "bioluminescence_torch", BlockBioluminescenceTorch.class)
                    .setUseSeparateVariantJsons(false);
    
    public static final ObjectType<VariantBioluminescence, Block, ItemMulti<VariantBioluminescence>> BIOLUMINESCENCE =
            ObjectType.createItem(VariantBioluminescence.class, "bioluminescence");

    public static final List<? extends ObjectType<VariantBioluminescence, ?, ?>> TYPES = Arrays.asList(/*LAMP,*/ TORCH, BIOLUMINESCENCE);
    
    public ComboBioluminescence()
    {
        super("bioluminescence", TYPES, VariantBioluminescence.class, Arrays.asList(VariantBioluminescence.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }
}
