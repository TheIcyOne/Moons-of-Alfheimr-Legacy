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
    public static final Block YGGDRASIL_LEAVES = new BlockYggdrasilLeaves().setUnlocalizedName("alfheimrsmoons.yggdrasil_leaves").setRegistryName("yggdrasil_leaves");
    public static final Block SOIL = new BlockSoil().setUnlocalizedName("alfheimrsmoons.soil").setRegistryName("soil");
    public static final Block GRASSY_SOIL = new BlockGrassySoil().setUnlocalizedName("alfheimrsmoons.grassy_soil").setRegistryName("grassy_soil");
    public static final BlockSedge SEDGE = (BlockSedge) new BlockSedge().setUnlocalizedName("alfheimrsmoons.sedge").setRegistryName("sedge");
    public static final BlockDeadPlant DEAD_PLANT = (BlockDeadPlant) new BlockDeadPlant().setUnlocalizedName("alfheimrsmoons.dead_plant").setRegistryName("dead_plant");
    public static final BlockAMFlower FLOWER = (BlockAMFlower) new BlockAMFlower().setUnlocalizedName("alfheimrsmoons.flower").setRegistryName("flower");
    public static final BlockTallFlower TALL_FLOWER = (BlockTallFlower) new BlockTallFlower().setUnlocalizedName("alfheimrsmoons.tall_flower").setRegistryName("tall_flower");
    public static final Block SEDIMENT = new BlockSediment().setUnlocalizedName("alfheimrsmoons.sediment").setRegistryName("sediment");
    public static final Block SEDIMENT_GLASS = new BlockSedimentGlass().setUnlocalizedName("alfheimrsmoons.sediment_glass").setRegistryName("sediment_glass");
    public static final BlockShale SHALE = (BlockShale) new BlockShale().setUnlocalizedName("alfheimrsmoons.shale").setRegistryName("shale");
    public static final BlockAMOre ORE = (BlockAMOre) new BlockAMOre().setUnlocalizedName("alfheimrsmoons.ore").setRegistryName("ore");
    public static final BlockOreBlock ORE_BLOCK = (BlockOreBlock) new BlockOreBlock().setUnlocalizedName("alfheimrsmoons.ore_block").setRegistryName("ore_block");
    public static final Block METEORITE = new BlockMeteorite().setUnlocalizedName("alfheimrsmoons.meteorite").setRegistryName("meteorite");
    public static final Block COSMOTITE = new BlockCosmotite().setUnlocalizedName("alfheimrsmoons.cosmotite").setRegistryName("cosmotite");
    public static final BlockAMLog LOG = (BlockAMLog) new BlockAMLog(0, 3).setUnlocalizedName("alfheimrsmoons.log").setRegistryName("log");
    public static final BlockAMLog LOG2 = (BlockAMLog) new BlockAMLog(4, 4).setUnlocalizedName("alfheimrsmoons.log").setRegistryName("log2");
    public static final BlockAMLeaves LEAVES = (BlockAMLeaves) new BlockAMLeaves(0, 3).setUnlocalizedName("alfheimrsmoons.leaves").setRegistryName("leaves");
    public static final BlockAMLeaves LEAVES2 = (BlockAMLeaves) new BlockAMLeaves(4, 4).setUnlocalizedName("alfheimrsmoons.leaves").setRegistryName("leaves2");
    public static final BlockAMSapling SAPLING = (BlockAMSapling) new BlockAMSapling().setUnlocalizedName("alfheimrsmoons.sapling").setRegistryName("sapling");
    public static final BlockAMPlanks PLANKS = (BlockAMPlanks) new BlockAMPlanks().setUnlocalizedName("alfheimrsmoons.wood").setRegistryName("planks");
    public static final Block RUNE_BOOKSHELF = new BlockRuneBookshelf().setUnlocalizedName("alfheimrsmoons.rune_bookshelf").setRegistryName("rune_bookshelf");
    public static final Block NITRO_TORCH = new BlockNitroTorch().setUnlocalizedName("alfheimrsmoons.nitro_torch").setRegistryName("nitro_torch");

    public static void registerBlocks()
    {
        AlfheimrsMoons.proxy.registerBlockWithItem(YGGDRASIL_LEAVES);
        AlfheimrsMoons.proxy.registerBlockWithItem(SOIL);
        AlfheimrsMoons.proxy.registerBlockWithItem(GRASSY_SOIL);
        AlfheimrsMoons.proxy.registerBlockWithVariants(SEDGE, "_sedge");
        AlfheimrsMoons.proxy.registerBlockWithVariants(DEAD_PLANT, "_dead");
        AlfheimrsMoons.proxy.registerBlockWithVariants(FLOWER);
        AlfheimrsMoons.proxy.registerBlockWithVariants(TALL_FLOWER, "tall_");
        AlfheimrsMoons.proxy.registerBlockWithItem(SEDIMENT);
        AlfheimrsMoons.proxy.registerBlockWithItem(SEDIMENT_GLASS);
        AlfheimrsMoons.proxy.registerBlockWithVariants(SHALE, "_shale");
        AlfheimrsMoons.proxy.registerBlockWithVariants(ORE, "_ore");
        AlfheimrsMoons.proxy.registerBlockWithVariants(ORE_BLOCK, "_ore_block");
        AlfheimrsMoons.proxy.registerBlockWithItem(METEORITE);
        AlfheimrsMoons.proxy.registerBlockWithItem(COSMOTITE);
        AlfheimrsMoons.proxy.registerBlockWithVariants(LOG, "_log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(LOG2, "_log");
        AlfheimrsMoons.proxy.registerBlockWithVariants(LEAVES, new ItemAMLeaves(LEAVES), "_leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(LEAVES2, new ItemAMLeaves(LEAVES2), "_leaves");
        AlfheimrsMoons.proxy.registerBlockWithVariants(SAPLING, "_sapling");
        AlfheimrsMoons.proxy.registerBlockWithVariants(PLANKS, "_planks");
        AlfheimrsMoons.proxy.registerBlockWithItem(RUNE_BOOKSHELF);
        AlfheimrsMoons.proxy.registerBlockWithItem(NITRO_TORCH);

        OreDictionary.registerOre("logWood", new ItemStack(LOG, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("logWood", new ItemStack(LOG2, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("plankWood", new ItemStack(PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeSapling", new ItemStack(SAPLING, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(LEAVES, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(LEAVES2, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("torch", NITRO_TORCH);
    }
}
