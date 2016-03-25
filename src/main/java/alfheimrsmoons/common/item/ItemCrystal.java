package alfheimrsmoons.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.BlockCrystalOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        super();
        String name = "crystal";

        setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        setUnlocalizedName(name);

        this.setHasSubtypes(true);
        this.setMaxDamage(0);

        GameRegistry.registerItem(this, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (BlockCrystalOre.EnumType crystalType : BlockCrystalOre.EnumType.values()) {
            subItems.add(new ItemStack(itemIn, 1, crystalType.ordinal()));
        }
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String crystalName;
        int meta = stack.getMetadata();
        try {
            crystalName = BlockCrystalOre.EnumType.byMetadata(meta).getName();
        } catch (Exception e) {
            // if lookup fails for whatever reason, just use the meta number
            crystalName = Integer.toString(meta);
        }
        return super.getUnlocalizedName() + "." + crystalName;
    }
}
