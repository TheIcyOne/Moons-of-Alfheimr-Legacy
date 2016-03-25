package alfheimrsmoons.common.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidTest extends BlockFluidClassic {

    private String name;

    public BlockFluidTest(Fluid fluid, Material material) {
        super(fluid, material);
        // setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        setCreativeTab(null);

        this.name = "something";
        setUnlocalizedName(name);

        GameRegistry.registerBlock(this, this.name);
    }

    public String getName() {
        return this.name;
    }
}