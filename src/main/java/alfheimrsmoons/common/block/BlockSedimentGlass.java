package alfheimrsmoons.common.block;

import java.util.Random;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSedimentGlass extends BlockGlass {
    private String name;

    public BlockSedimentGlass() {
        super(Material.glass, false);
        setHardness(0.3F);
        setStepSound(soundTypeGlass);

        this.name = "sediment_glass";
        setUnlocalizedName(name);
        setCreativeTab(AlfheimrsMoons.instance.creativeTab);

        GameRegistry.registerBlock(this, name);
    }

    public String getName() {
        return this.name;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
}
