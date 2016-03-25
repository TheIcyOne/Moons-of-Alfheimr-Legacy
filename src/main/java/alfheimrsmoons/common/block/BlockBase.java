package alfheimrsmoons.common.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBase extends Block {
    private final String name;

    public BlockBase(String name, Material material) {
        super(material);
        this.name = name;
        registerBlock(this, name);
    }

    public static void registerBlock(BlockBase block, String name) {
        block.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        block.setUnlocalizedName(name);

        GameRegistry.registerBlock(block, name);
    }

    public String getName() {
        return name;
    }
}
