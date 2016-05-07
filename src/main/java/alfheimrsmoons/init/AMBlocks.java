package alfheimrsmoons.init;

import alfheimrsmoons.block.*;
import alfheimrsmoons.item.ItemLog;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*
    Adding a block:
    - Registration (you are here)
    - Model registration (alfheimrsmoons.client.ItemModels)
    - Block state JSON (assets/alfheimrsmoons/blockstates)
    - Model JSON's (assets/alfheimrsmoons/models)
    - Texture(s) (assets/alfheimrsmoons/textures/blocks)
    - Localization (assets/alfheimrsmoons/lang)
*/

public class AMBlocks {
    public static final Block soil = new BlockSoil().setUnlocalizedName("alfheimr.soil").setRegistryName("soil");
    public static final Block shale = new BlockShale().setUnlocalizedName("alfheimr.shale").setRegistryName("shale");
    public static final BlockAMLog log = (BlockAMLog) new BlockAMLog(0, 3).setUnlocalizedName("alfheimr.log").setRegistryName("log");
    public static final BlockAMLog log2 = (BlockAMLog) new BlockAMLog(4).setUnlocalizedName("alfheimr.log").setRegistryName("log2");
    public static final Block planks = new BlockAMPlanks().setUnlocalizedName("alfheimr.wood").setRegistryName("planks");
    public static final Block sediment = new BlockSediment().setUnlocalizedName("alfheimr.sediment").setRegistryName("sediment");

    public static void registerBlocks() {
        registerItemBlock(soil);
        registerItemBlock(shale);
        registerItemBlock(log, new ItemLog(log).setUnlocalizedName("alfheimr.log"));
        registerItemBlock(log2, new ItemLog(log2).setUnlocalizedName("alfheimr.log"));
        registerItemBlock(planks, new ItemMultiTexture(planks, planks, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                return BlockAMPlanks.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
            }
        }).setUnlocalizedName("alfheimr.wood"));
        registerItemBlock(sediment);
    }

    private static void registerItemBlock(Block block) {
        registerItemBlock(block, new ItemBlock(block));
    }

    private static void registerItemBlock(Block block, Item item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }
}
