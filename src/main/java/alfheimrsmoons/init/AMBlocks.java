package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.*;
import alfheimrsmoons.item.ItemAMLeaves;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

/*
    Adding a block:
    - Registration (you are here)
    - Block state JSON (assets/alfheimrsmoons/blockstates)
    - Model JSON's (assets/alfheimrsmoons/models)
    - Texture(s) (assets/alfheimrsmoons/textures/blocks)
    - Localization (assets/alfheimrsmoons/lang)
    - OPTIONAL: State mapper (alfheimrsmoons.client.ProxyClient)
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
        AlfheimrsMoons.proxy.registerBlockWithItem(soil);
        AlfheimrsMoons.proxy.registerBlockWithItem(grassy_soil);
        AlfheimrsMoons.proxy.registerBlockWithItem(sediment);
        AlfheimrsMoons.proxy.registerBlockWithItem(shale);
        AlfheimrsMoons.proxy.registerBlockWithVariants(ore, BlockAMOre.EnumType.values, "ore");
        AlfheimrsMoons.proxy.registerBlockWithVariants(log, log.variants, "log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(log2, log2.variants, "log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(leaves, new ItemAMLeaves(leaves), leaves.variants, "leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(leaves2, new ItemAMLeaves(leaves2), leaves.variants, "leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(sapling, BlockAMPlanks.EnumType.values, "sapling");
        AlfheimrsMoons.proxy.registerBlockWithVariants(planks, BlockAMPlanks.EnumType.values, "planks");
        AlfheimrsMoons.proxy.registerBlockWithItem(rune_bookshelf);

        OreDictionary.registerOre("logWood", log);
        OreDictionary.registerOre("logWood", log2);
        OreDictionary.registerOre("plankWood", planks);
    }
}
