package alfheimrsmoons.common.fluid;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidTest extends Fluid {
    public static final String name = "something";
    public static final FluidTest instance = new FluidTest();

    public FluidTest() {
        super(name, new ResourceLocation(AlfheimrsMoons.MODID + ":blocks/something_still"), new ResourceLocation(AlfheimrsMoons.MODID + ":blocks/something_flowing"));

        setLuminosity(15);
        setDensity(1000);
        setViscosity(3000);

        setBlock(ModBlocks.fluidTest);
        FluidRegistry.registerFluid(this);
    }

}
