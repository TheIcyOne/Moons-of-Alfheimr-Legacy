package alfheimrsmoons.block;

import alfheimrsmoons.block.BlockAMOre.EnumType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockOreBlock extends Block {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockOreBlock() {
        super(Material.iron);
        blockState = new BlockStateContainer(this, VARIANT);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.NITRO));
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(SoundType.METAL);
        setCreativeTab(CreativeTabs.tabBlock);
        for (int meta = 0; meta < EnumType.values.length; meta++) {
            setHarvestLevel("pickaxe", EnumType.values[meta].getHarvestLevel());
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (EnumType type : EnumType.values) {
            list.add(new ItemStack(item, 1, type.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, VariantHelper.getVariantFromMeta(EnumType.values, meta));
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return state.getValue(VARIANT).getBlockColor();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }
}
