package alfheimrsmoons.network;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zaggy1024.proxy.IProxy;

public abstract class Proxy implements IProxy
{
    public void preInit() {}

    public void init() {}

    public void postInit() {}

    @Override
    public void registerItem(Item item, ResourceLocation name, boolean doModel)
    {
        registerItem(item.setRegistryName(name), doModel);
    }

    public void registerItem(Item item, boolean doModel)
    {
        GameRegistry.register(item);
    }

    public void registerItem(Item item)
    {
        registerItem(item, true);
    }

    @Override
    public void registerBlock(Block block, Item item, ResourceLocation name, boolean doModel)
    {
        registerBlock(block.setRegistryName(name), item, doModel);
    }

    public void registerBlock(Block block, Item item, boolean doModel)
    {
        GameRegistry.register(block);

        if (item != null)
        {
            registerItem(item.setRegistryName(block.getRegistryName()), doModel);
        }
    }

    public void registerBlock(Block block, Item item)
    {
        registerBlock(block, item, true);
    }

    public void registerBlock(Block block, boolean doModel)
    {
        registerBlock(block, new ItemBlock(block), doModel);
    }

    public void registerBlock(Block block)
    {
        registerBlock(block, true);
    }
    
    public Fluid registerFluid(Fluid fluid){
    	FluidRegistry.registerFluid(fluid);
    	FluidRegistry.addBucketForFluid(fluid);
    	return fluid;
    }
    
    public void registerFluidBlock(Fluid fluid, Block fluidBlock, String name){
    	ForgeRegistries.BLOCKS.register(fluidBlock);
    	fluid.setBlock(fluidBlock);
    	registerFluidRendering(fluidBlock, name);
    }

	public void registerFluidRendering(Block block, String name) {}
}
