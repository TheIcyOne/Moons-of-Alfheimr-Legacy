package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantCosmotite;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import zaggy1024.block.BlockMulti;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;

import java.util.List;

public class BlockCosmotite extends BlockMulti<VariantCosmotite>
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = new IProperty[0];

    public BlockCosmotite(VariantsOfTypesCombo<VariantCosmotite> owner,
                          ObjectType<VariantCosmotite, ? extends BlockMulti<VariantCosmotite>, ? extends Item> type,
                          List<VariantCosmotite> variants, Class<VariantCosmotite> variantClass)
    {
        super(owner, type, variants, variantClass, Material.ROCK, MapColor.OBSIDIAN, SoundType.STONE);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(1.5F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 1);
    }
}
