package alfheimrsmoons.lib;

import alfheimrsmoons.common.block.BlockCrystalOre;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class AMFuelHandler implements IFuelHandler {
    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == ModItems.crystal && fuel.getMetadata() == BlockCrystalOre.EnumType.NITRO.ordinal()) {
            return 2400;
        }
        return 0;
    }
}
