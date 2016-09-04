package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantShale;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import zaggy1024.block.BlockMulti;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;

import java.util.List;

public class BlockShale extends BlockMulti<VariantShale>
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public BlockShale(VariantsOfTypesCombo<VariantShale> owner,
                      ObjectType<VariantShale, ? extends BlockMulti<VariantShale>, ? extends Item> type,
                      List<VariantShale> variants, Class<VariantShale> variantClass)
    {
        super(owner, type, variants, variantClass, Material.ROCK, SoundType.STONE);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(1.5F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 1);
    }
}
