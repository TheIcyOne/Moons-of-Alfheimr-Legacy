package alfheimrsmoons.block.fluid;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidEitr extends Fluid{
	
	public static final String name = "eitr";
	public static final FluidEitr instance = new FluidEitr();
	
	public FluidEitr(){
		super(name, new ResourceLocation(AlfheimrsMoons.MOD_ID, "blocks/eitr_still"), new ResourceLocation(AlfheimrsMoons.MOD_ID, "blocks/eitr_flowing"));
	}
}
