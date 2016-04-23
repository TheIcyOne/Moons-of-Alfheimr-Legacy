package alfheimrsmoons.init;

import alfheimrsmoons.block.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMBlocks {
    public static final Block soil = new BlockSoil().setUnlocalizedName("soil").setRegistryName("soil");
    public static final Block shale = new BlockShale().setUnlocalizedName("shale").setRegistryName("shale");

    public static void registerBlocks() {
        registerItemBlock(soil);
        registerItemBlock(shale);
    }

    private static void registerItemBlock(Block block) {
        registerItemBlock(block, new ItemBlock(block));
    }

    private static void registerItemBlock(Block block, Item item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }
}
