package alfheimrsmoons.init;

import alfheimrsmoons.block.*;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMBlocks {
    public static final Block soil = new BlockSoil().setUnlocalizedName("alfheimr.soil").setRegistryName("soil");
    public static final Block shale = new BlockShale().setUnlocalizedName("alfheimr.shale").setRegistryName("shale");
    public static final Block rune_log = new BlockAMLog().setUnlocalizedName("alfheimr.log.rune").setRegistryName("rune_log");
    public static final Block planks = new BlockAMPlanks().setUnlocalizedName("alfheimr.wood").setRegistryName("planks");

    public static void registerBlocks() {
        registerItemBlock(soil);
        registerItemBlock(shale);
        registerItemBlock(rune_log);
        registerItemBlock(planks, new ItemMultiTexture(planks, planks, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                return BlockAMPlanks.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
            }
        }).setUnlocalizedName("alfheimr.wood"));
    }

    private static void registerItemBlock(Block block) {
        registerItemBlock(block, new ItemBlock(block));
    }

    private static void registerItemBlock(Block block, Item item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }
}
