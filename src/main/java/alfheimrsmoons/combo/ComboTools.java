package alfheimrsmoons.combo;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMRecipes;
import alfheimrsmoons.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;

import java.util.Arrays;
import java.util.List;

public class ComboTools extends VariantsOfTypesCombo<VariantToolMaterial>
{
    public static final ObjectType<VariantToolMaterial, Block, ItemSwordAM> SWORD =
            ObjectType.createItem(VariantToolMaterial.class, "sword", ItemSwordAM.class)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantToolMaterial, Block, ItemShovelAM> SHOVEL =
            ObjectType.createItem(VariantToolMaterial.class, "shovel", ItemShovelAM.class)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantToolMaterial, Block, ItemPickaxeAM> PICKAXE =
            ObjectType.createItem(VariantToolMaterial.class, "pickaxe", ItemPickaxeAM.class)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantToolMaterial, Block, ItemAxeAM> AXE =
            ObjectType.createItem(VariantToolMaterial.class, "axe", ItemAxeAM.class)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final ObjectType<VariantToolMaterial, Block, ItemHoeAM> HOE =
            ObjectType.createItem(VariantToolMaterial.class, "hoe", ItemHoeAM.class)
                    .setTypeNamePosition(TypeNamePosition.POSTFIX);

    public static final List<? extends ObjectType<VariantToolMaterial, ?, ?>> TYPES = Arrays.asList(SWORD, SHOVEL, PICKAXE, AXE, HOE);

    public ComboTools()
    {
        super("tools", TYPES, VariantToolMaterial.class, Arrays.asList(VariantToolMaterial.values()));
        setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);
    }

    public void addRecipes(VariantToolMaterial variant, ItemStack material)
    {
        AMRecipes.addShapedRecipe(getStack(SWORD, variant), "X", "X", "#", '#', "stickWood", 'X', material);
        AMRecipes.addShapedRecipe(getStack(SHOVEL, variant), "X", "#", "#", '#', "stickWood", 'X', material);
        AMRecipes.addShapedRecipe(getStack(PICKAXE, variant), "XXX", " # ", " # ", '#', "stickWood", 'X', material);
        AMRecipes.addShapedRecipe(getStack(AXE, variant), "XX", "X#", " #", '#', "stickWood", 'X', material);
        AMRecipes.addShapedRecipe(getStack(HOE, variant), "XX", " #", " #", '#', "stickWood", 'X', material);
    }
}
