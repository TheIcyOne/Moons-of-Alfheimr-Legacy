package alfheimrsmoons.item;

import alfheimrsmoons.block.BlockAMOre.EnumType;
import alfheimrsmoons.block.VariantHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemOreDrop extends Item {
    public ItemOreDrop() {
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + VariantHelper.getVariantFromMeta(EnumType.values, stack.getMetadata()).getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (int meta = 0; meta < EnumType.values.length; meta++) {
            subItems.add(new ItemStack(item, 1, meta));
        }
    }
}
