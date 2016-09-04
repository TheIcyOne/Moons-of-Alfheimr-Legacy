package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.block.*;
import alfheimrsmoons.combo.*;
import alfheimrsmoons.network.Proxy;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsCombo;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.TypeNamePosition;
import zaggy1024.combo.variant.IMetadata;
import zaggy1024.item.ItemBlockMulti;

public class AMBlocks
{
    public static final BlockYggdrasilLeaves YGGDRASIL_LEAVES = new BlockYggdrasilLeaves();

    public static final BlockSoil SOIL = new BlockSoil();

    public static final BlockGrassySoil GRASSY_SOIL = new BlockGrassySoil();

    public static final VariantsCombo<VariantSedge, BlockSedge, ItemBlockMulti<VariantSedge>> SEDGES =
            new VariantsCombo<>(
                    "sedges",
                    ObjectType.createBlock(VariantSedge.class, "sedge", BlockSedge.class)
                            .setUseSeparateVariantJsons(false)
                            .setVariantNameFunction((v, n) -> v == VariantSedge.NORMAL ? n : v.getName() + "_" + n),
                    VariantSedge.class,
                    VariantSedge.values()
            ).setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);

    public static final VariantsCombo<VariantDeadPlant, BlockDeadPlant, ItemBlockMulti<VariantDeadPlant>> DEAD_PLANTS =
            new VariantsCombo<>(
                    "dead_plants",
                    ObjectType.createBlock(VariantDeadPlant.class, "dead_plant", BlockDeadPlant.class)
                            .setUseSeparateVariantJsons(false)
                            .setVariantNameFunction((v, n) -> "dead_" + v.getName()),
                    VariantDeadPlant.class,
                    VariantDeadPlant.values()
            ).setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);

    public static final ComboFlowers FLOWERS = new ComboFlowers();

    public static final BlockSediment SEDIMENT = new BlockSediment();

    public static final BlockSedimentGlass SEDIMENT_GLASS = new BlockSedimentGlass();

    public static final VariantsCombo<VariantShale, BlockShale, ItemBlockMulti<VariantShale>> SHALE =
            new VariantsCombo<>(
                    "shale",
                    ObjectType.createBlock(VariantShale.class, "shale", BlockShale.class)
                            .setUseSeparateVariantJsons(false)
                            .setVariantNameFunction((v, n) -> v == VariantShale.NORMAL ? n : v.getName() + "_" + n),
                    VariantShale.class,
                    VariantShale.values()
            ).setNames(AlfheimrsMoons.MOD_ID, AlfheimrsMoons.UNLOCALIZED_PREFIX);

    public static final ComboOres ORES = new ComboOres();

    public static final BlockMeteorite METEORITE = new BlockMeteorite();

    public static final BlockCosmotite COSMOTITE = new BlockCosmotite();

    public static final ComboTrees TREES = new ComboTrees();

    public static final BlockRuneBookshelf RUNE_BOOKSHELF = new BlockRuneBookshelf();

    public static final BlockNitroTorch NITRO_TORCH = new BlockNitroTorch();

    public static void registerBlocks()
    {
        Proxy proxy = AlfheimrsMoons.proxy;
        proxy.registerBlock(YGGDRASIL_LEAVES);
        proxy.registerBlock(SOIL);
        proxy.registerBlock(GRASSY_SOIL);
        SEDGES.registerAll(proxy);
        DEAD_PLANTS.registerAll(proxy);
        FLOWERS.registerAll(proxy);
        proxy.registerBlock(SEDIMENT);
        proxy.registerBlock(SEDIMENT_GLASS);
        SHALE.registerAll(proxy);
        ORES.registerVariants(proxy, ComboOres.ORE);
        ORES.registerVariants(proxy, ComboOres.BLOCK);
        proxy.registerBlock(METEORITE);
        proxy.registerBlock(COSMOTITE);
        TREES.registerAll(proxy);
        proxy.registerBlock(RUNE_BOOKSHELF);
        proxy.registerBlock(NITRO_TORCH);

        registerVariantOres("logWood", TREES, ComboTrees.LOG);
        registerVariantOres("plankWood", TREES, ComboTrees.PLANKS);
        registerVariantOres("treeSapling", TREES, ComboTrees.SAPLING);
        registerVariantOres("treeLeaves", TREES, ComboTrees.LEAVES);
        OreDictionary.registerOre("torch", NITRO_TORCH);
    }

    private static <V extends IMetadata<V>> void registerVariantOres(String name, VariantsOfTypesCombo<V> combo, ObjectType<V, ?, ?> type)
    {
        for (Block block : combo.getBlocks(type))
        {
            OreDictionary.registerOre(name, new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
        }
    }
}
