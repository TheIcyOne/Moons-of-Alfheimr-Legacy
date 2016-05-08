package alfheimrsmoons.block;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAMOre extends BlockOre {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockAMOre() {
        blockState = new BlockStateContainer(this, VARIANT);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.KASOLITE));
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(SoundType.STONE);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < EnumType.values.length; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    public enum EnumType implements IStringSerializable {
        KASOLITE("kasolite"),
        NITRO("nitro"),
        LOREIUM("loreium");

        public static final EnumType[] values = values();
        private final String name;
        private final String unlocalizedName;

        EnumType(String name) {
            this(name, name);
        }

        EnumType(String name, String unlocalizedName) {
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return ordinal();
        }

        @Override
        public String toString() {
            return name;
        }

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= values.length) {
                meta = 0;
            }

            return values[meta];
        }

        @Override
        public String getName() {
            return name;
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }
    }
}
