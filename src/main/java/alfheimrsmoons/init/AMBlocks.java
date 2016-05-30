package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.*;
import alfheimrsmoons.item.ItemAMLeaves;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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

public class AMBlocks
{
    public static final Block soil = new BlockSoil().setUnlocalizedName("alfheimrsmoons.soil").setRegistryName("soil");
    public static final Block grassy_soil = new BlockGrassySoil().setUnlocalizedName("alfheimrsmoons.grassy_soil").setRegistryName("grassy_soil");
    public static final Block sediment = new BlockSediment().setUnlocalizedName("alfheimrsmoons.sediment").setRegistryName("sediment");
    public static final Block sediment_glass = new BlockSedimentGlass().setUnlocalizedName("alfheimrsmoons.sediment_glass").setRegistryName("sediment_glass");
    public static final Block shale = new BlockShale().setUnlocalizedName("alfheimrsmoons.shale").setRegistryName("shale");
    public static final Block ore = new BlockAMOre().setUnlocalizedName("alfheimrsmoons.ore").setRegistryName("ore");
    public static final Block ore_block = new BlockOreBlock().setUnlocalizedName("alfheimrsmoons.ore_block").setRegistryName("ore_block");
    public static final Block meteorite = new BlockMeteorite().setUnlocalizedName("alfheimrsmoons.meteorite").setRegistryName("meteorite");
    public static final BlockAMLog log = (BlockAMLog) new BlockAMLog(0, 3).setUnlocalizedName("alfheimrsmoons.log").setRegistryName("log");
    public static final BlockAMLog log2 = (BlockAMLog) new BlockAMLog(4, 4).setUnlocalizedName("alfheimrsmoons.log").setRegistryName("log2");
    public static final BlockAMLeaves leaves = (BlockAMLeaves) new BlockAMLeaves(0, 3).setUnlocalizedName("alfheimrsmoons.leaves").setRegistryName("leaves");
    public static final BlockAMLeaves leaves2 = (BlockAMLeaves) new BlockAMLeaves(4, 4).setUnlocalizedName("alfheimrsmoons.leaves").setRegistryName("leaves2");
    public static final BlockAMSapling sapling = (BlockAMSapling) new BlockAMSapling().setUnlocalizedName("alfheimrsmoons.sapling").setRegistryName("sapling");
    public static final Block planks = new BlockAMPlanks().setUnlocalizedName("alfheimrsmoons.wood").setRegistryName("planks");
    public static final Block rune_bookshelf = new BlockRuneBookshelf().setUnlocalizedName("alfheimrsmoons.rune_bookshelf").setRegistryName("rune_bookshelf");
    public static final Block nitro_torch = new BlockNitroTorch().setUnlocalizedName("alfheimrsmoons.nitro_torch").setRegistryName("nitro_torch");

    public static void registerBlocks()
    {
        AlfheimrsMoons.proxy.registerBlockWithItem(soil);
        AlfheimrsMoons.proxy.registerBlockWithItem(grassy_soil);
        AlfheimrsMoons.proxy.registerBlockWithItem(sediment);
        AlfheimrsMoons.proxy.registerBlockWithItem(sediment_glass);
        AlfheimrsMoons.proxy.registerBlockWithItem(shale);
        AlfheimrsMoons.proxy.registerBlockWithVariants(ore, BlockAMOre.EnumType.values, "ore");
        AlfheimrsMoons.proxy.registerBlockWithVariants(ore_block, BlockAMOre.EnumType.values, "ore_block");
        AlfheimrsMoons.proxy.registerBlockWithItem(meteorite);
        AlfheimrsMoons.proxy.registerBlockWithVariants(log, log.variants, "log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(log2, log2.variants, "log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(leaves, new ItemAMLeaves(leaves), leaves.variants, "leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(leaves2, new ItemAMLeaves(leaves2), leaves.variants, "leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(sapling, BlockAMPlanks.EnumType.values, "sapling");
        AlfheimrsMoons.proxy.registerBlockWithVariants(planks, BlockAMPlanks.EnumType.values, "planks");
        AlfheimrsMoons.proxy.registerBlockWithItem(rune_bookshelf);
        AlfheimrsMoons.proxy.registerBlockWithItem(nitro_torch);

        OreDictionary.registerOre("logWood", new ItemStack(log, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("logWood", new ItemStack(log2, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("plankWood", new ItemStack(planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeSapling", new ItemStack(sapling, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(leaves2, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("torch", nitro_torch);
    }
}
