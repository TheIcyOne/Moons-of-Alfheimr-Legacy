package alfheimrsmoons.item;

import alfheimrsmoons.util.IVariant;
import alfheimrsmoons.util.IVariantObject;
import alfheimrsmoons.util.VariantHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemVariant<V extends IVariant<V>> extends Item implements IVariantObject<V>
{
    private final V[] variants;

    public ItemVariant(V... variants)
    {
        this(null, variants);
    }
    
    public ItemVariant(CreativeTabs creativeTab, V... variants)
    {
        this.variants = variants;
        setHasSubtypes(true);
        setCreativeTab(creativeTab);
    }

    @Override
    public V[] getVariants()
    {
        return variants;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return VariantHelper.getUnlocalizedName(this, super.getUnlocalizedName(stack), stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }
}
