package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.client.models.ItemModels;
import alfheimrsmoons.common.CommonProxy;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerModels() {
        ItemModels.register();
        // BlockModels.register();
    }

    @Override
    public void registerRenderers() {
    }

    @Override
    public void preInit() {
        super.preInit();

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amGrass), new ResourceLocation(AlfheimrsMoons.MODID + ":grass_normal"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":grass_niflheimr"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amSoil), new ResourceLocation(AlfheimrsMoons.MODID + ":soil_normal"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":soil_niflheimr"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amSapling), new ResourceLocation(AlfheimrsMoons.MODID + ":sapling_beech"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":sapling_elm"), new ResourceLocation(AlfheimrsMoons.MODID + ":sapling_red_bud"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":sapling_larch"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amLog), new ResourceLocation(AlfheimrsMoons.MODID + ":log_beech"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":log_elm"), new ResourceLocation(AlfheimrsMoons.MODID + ":log_red_bud"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":log_larch"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amPlanks), new ResourceLocation(AlfheimrsMoons.MODID + ":planks_beech"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":planks_elm"), new ResourceLocation(AlfheimrsMoons.MODID + ":planks_red_bud"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":planks_larch"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.amLeaves), new ResourceLocation(AlfheimrsMoons.MODID + ":leaves_beech"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":leaves_elm"), new ResourceLocation(AlfheimrsMoons.MODID + ":leaves_red_bud"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":leaves_larch"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.crystalOre), new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_ore_kasolite"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_ore_nitro"), new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_ore_corrodium"));

        ModelBakery.registerItemVariants(ModItems.crystal, new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_kasolite"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_nitro"), new ResourceLocation(AlfheimrsMoons.MODID + ":crystal_corrodium"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.storage), new ResourceLocation(AlfheimrsMoons.MODID + ":storage_kasolite"),
                new ResourceLocation(AlfheimrsMoons.MODID + ":storage_nitro"), new ResourceLocation(AlfheimrsMoons.MODID + ":storage_corrodium"));

        ModelLoader.setCustomStateMapper(ModBlocks.fluidTest, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(AlfheimrsMoons.MODID + ":fluids", ModBlocks.fluidTest.getName());
            }
        });
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

}