package alfheimrsmoons.common.item;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBase extends Item {
    private final String name;

    public ItemBase(String name) {
        this.name = name;
        registerItem(this, name);
    }

    public static void registerItem(Item item, String name) {
        item.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        item.setUnlocalizedName(name);

        GameRegistry.registerItem(item, name);
    }

    public String getName() {
        return name;
    }
}
