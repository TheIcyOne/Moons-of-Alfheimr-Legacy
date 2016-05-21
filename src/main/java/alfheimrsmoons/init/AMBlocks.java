package alfheimrsmoons.init;

import alfheimrsmoons.block.*;
import alfheimrsmoons.item.ItemAMLeaves;
import alfheimrsmoons.item.ItemLog;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
    public static final Block grassy_soil = new BlockGrassySoil().setUnlocalizedName("alfheimr.grassy_soil").setRegistryName("grassy_soil");
    public static final Block sediment = new BlockSediment().setUnlocalizedName("alfheimr.sediment").setRegistryName("sediment");
    public static final Block shale = new BlockShale().setUnlocalizedName("alfheimr.shale").setRegistryName("shale");
    public static final Block ore = new BlockAMOre().setUnlocalizedName("alfheimr.ore").setRegistryName("ore");
    public static final BlockAMLog log = (BlockAMLog) new BlockAMLog(0, 3).setUnlocalizedName("alfheimr.log").setRegistryName("log");
    public static final BlockAMLog log2 = (BlockAMLog) new BlockAMLog(4, 4).setUnlocalizedName("alfheimr.log").setRegistryName("log2");
    public static final BlockAMLeaves leaves = (BlockAMLeaves) new BlockAMLeaves(0, 3).setUnlocalizedName("alfheimr.leaves").setRegistryName("leaves");
    public static final BlockAMLeaves leaves2 = (BlockAMLeaves) new BlockAMLeaves(4, 4).setUnlocalizedName("alfheimr.leaves").setRegistryName("leaves2");
    public static final BlockAMSapling sapling = (BlockAMSapling) new BlockAMSapling().setUnlocalizedName("alfheimr.sapling").setRegistryName("sapling");
    public static final Block planks = new BlockAMPlanks().setUnlocalizedName("alfheimr.wood").setRegistryName("planks");
    public static final Block rune_bookshelf = new BlockRuneBookshelf().setUnlocalizedName("alfheimr.rune_bookshelf").setRegistryName("rune_bookshelf");

    public static void registerBlocks() {
        registerItemBlock(soil);
        registerItemBlock(grassy_soil);
        registerItemBlock(sediment);
        registerItemBlock(shale);
        registerItemBlock(ore, new ItemMultiTexture(ore, ore, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                return BlockAMOre.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
            }
        }));
        registerItemBlock(log, new ItemLog(log));
        registerItemBlock(log2, new ItemLog(log2));
        registerItemBlock(leaves, new ItemAMLeaves(leaves));
        registerItemBlock(leaves2, new ItemAMLeaves(leaves2));
        registerItemBlock(sapling, new ItemMultiTexture(sapling, sapling, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                return BlockAMPlanks.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
            }
        }));
        registerItemBlock(planks, new ItemMultiTexture(planks, planks, new Function<ItemStack, String>() {
            @Override
            public String apply(ItemStack stack) {
                return BlockAMPlanks.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
            }
        }));
        registerItemBlock(rune_bookshelf);

        OreDictionary.registerOre("logWood", log);
        OreDictionary.registerOre("logWood", log2);
        OreDictionary.registerOre("plankWood", planks);
    }

    private static void registerItemBlock(Block block) {
        registerItemBlock(block, new ItemBlock(block));
    }

    private static void registerItemBlock(Block block, Item item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }
}
