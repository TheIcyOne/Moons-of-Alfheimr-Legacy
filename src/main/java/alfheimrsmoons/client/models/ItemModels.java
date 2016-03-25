package alfheimrsmoons.client.models;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.BlockCrystalOre;
import alfheimrsmoons.common.block.BlockPlanks;
import alfheimrsmoons.common.block.BlockSoil;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemModels {
    public static void register() {
        registerItem(ModItems.foodOnion, ModItems.foodOnion.getName());
        // FIXME replace hardcoded name with BucketClass.getName
        registerItem(ModItems.bucketSomething, "bucket_something");

        registerItem(ModItems.branch, ModItems.branch.getName());
        registerItem(ModItems.nitroPowder, ModItems.nitroPowder.getName());
        registerItem(ModItems.kasoliteHelmet, ModItems.kasoliteHelmet.getName());
        registerItem(ModItems.kasoliteChestplate, ModItems.kasoliteChestplate.getName());
        registerItem(ModItems.kasoliteLeggings, ModItems.kasoliteLeggings.getName());
        registerItem(ModItems.kasoliteBoots, ModItems.kasoliteBoots.getName());

        registerItem(ModItems.kasoliteSword, ModItems.kasoliteSword.getName());
        registerItem(ModItems.kasolitePickaxe, ModItems.kasolitePickaxe.getName());
        registerItem(ModItems.kasoliteAxe, ModItems.kasoliteAxe.getName());
        registerItem(ModItems.kasoliteSpade, ModItems.kasoliteSpade.getName());
        registerItem(ModItems.kasoliteHoe, ModItems.kasoliteHoe.getName());

        registerItem(ModItems.corrodiumSword, ModItems.corrodiumSword.getName());
        registerItem(ModItems.corrodiumPickaxe, ModItems.corrodiumPickaxe.getName());
        registerItem(ModItems.corrodiumAxe, ModItems.corrodiumAxe.getName());
        registerItem(ModItems.corrodiumSpade, ModItems.corrodiumSpade.getName());
        registerItem(ModItems.corrodiumHoe, ModItems.corrodiumHoe.getName());

        registerBlock(ModBlocks.sedimentGlass, ModBlocks.sedimentGlass.getName());
        registerBlock(ModBlocks.nitroTorch, ModBlocks.nitroTorch.getName());

        for (BlockSoil.EnumType t : BlockSoil.EnumType.values()) {
            registerBlockVariant(ModBlocks.amSoil, t.getMetadata(), t.toString());
            registerBlockVariant(ModBlocks.amGrass, t.getMetadata(), t.toString());
        }

        for (BlockPlanks.EnumType t : BlockPlanks.EnumType.values()) {
            registerBlockVariant(ModBlocks.amSapling, t.getMetadata(), t.toString());
            registerBlockVariant(ModBlocks.amLog, t.getMetadata(), t.toString());
            registerBlockVariant(ModBlocks.amPlanks, t.getMetadata(), t.toString());
            registerBlockVariant(ModBlocks.amLeaves, t.getMetadata(), t.toString());
        }

        for (BlockCrystalOre.EnumType t : BlockCrystalOre.EnumType.values()) {
            registerBlockVariant(ModBlocks.crystalOre, t.getMetadata(), t.toString());
            registerBlockVariant(ModBlocks.storage, t.getMetadata(), t.toString());
            registerItemVariant(ModItems.crystal, t.getMetadata(), t.toString());
        }
    }

    private static void registerItem(Item i, String name) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(i, 0, new ModelResourceLocation(AlfheimrsMoons.MODID + ":" + name, "inventory"));
    }

    private static void registerItemVariant(Item item, int metadata, String name) {
        String itemName = item.getUnlocalizedName().substring(item.getUnlocalizedName().indexOf('.') + 1);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(item, metadata, new ModelResourceLocation(AlfheimrsMoons.MODID + ":" + itemName + "_" + name, "inventory"));
    }

    private static void registerBlock(Block block, String name) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(AlfheimrsMoons.MODID + ":" + name, "inventory"));
    }

    private static void registerBlockVariant(Block block, int metadata, String name) {
        String blockName = block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf('.') + 1);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), metadata,
                new ModelResourceLocation(AlfheimrsMoons.MODID + ":" + blockName + "_" + name, "inventory"));
    }
}
