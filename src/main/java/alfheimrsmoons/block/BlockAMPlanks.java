package alfheimrsmoons.block;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAMPlanks extends BlockPlanks {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockAMPlanks() {
        blockState = new BlockStateContainer(this, VARIANT);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.RUNE));
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(SoundType.WOOD);
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
        return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return state.getValue(VARIANT).getMapColor();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    public enum EnumType implements IStringSerializable {
        RUNE("rune", MapColor.lightBlueColor);

        public static final EnumType[] values = values();
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;

        EnumType(String name, MapColor mapColor) {
            this(name, name, mapColor);
        }

        EnumType(String name, String unlocalizedName, MapColor mapColor) {
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.mapColor = mapColor;
        }

        public int getMetadata() {
            return ordinal();
        }

        public MapColor getMapColor() {
            return mapColor;
        }

        @Override
        public String toString() {
            return name;
        }

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= values.length) {
                meta = 0;
            }

            return values[MathHelper.clamp_int(meta, 0, values.length - 1)];
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
