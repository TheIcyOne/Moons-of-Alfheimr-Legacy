package alfheimrsmoons.block.fluid;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidSacredWater extends Fluid{
	
	public static final String name = "sacred_water";
	public static final FluidSacredWater instance = new FluidSacredWater();
	
	public FluidSacredWater(){
		super(name, new ResourceLocation(AlfheimrsMoons.MOD_ID, "blocks/sacred_water_still"), new ResourceLocation(AlfheimrsMoons.MOD_ID, "blocks/sacred_water_flowing"));
	}
}
