package alfheimrsmoons.common.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlanks extends Block {

    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType> create("variant", BlockPlanks.EnumType.class,
            new Predicate<BlockPlanks.EnumType>() {
                public boolean apply(BlockPlanks.EnumType type) {
                    return type.getMetadata() < 4;
                }
            });

    public BlockPlanks() {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.BEECH));
        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
        String name = "planks";
        this.setUnlocalizedName(name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockPlanks.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called
     * when the block gets destroyed. It returns the metadata of the dropped
     * item based on the old metadata of the block.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockPlanks.EnumType blockamplanks$enumtype : BlockPlanks.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamplanks$enumtype.getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state) {
        return ((BlockPlanks.EnumType) state.getValue(VARIANT)).func_181070_c();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    public static enum EnumType implements IStringSerializable {
        BEECH(0, "beech", MapColor.woodColor), ELM(1, "elm", MapColor.obsidianColor), RED_BUD(2, "red_bud", MapColor.sandColor), LARCH(3, "larch", MapColor.dirtColor);

        private static final BlockPlanks.EnumType[] META_LOOKUP = new BlockPlanks.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final MapColor field_181071_k;

        private EnumType(int meta, String name, MapColor p_i46388_5_) {
            this(meta, name, name, p_i46388_5_);
        }

        private EnumType(int meta, String name, String unlocalizedName, MapColor p_i46389_6_) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.field_181071_k = p_i46389_6_;
        }

        public int getMetadata() {
            return this.meta;
        }

        public MapColor func_181070_c() {
            return this.field_181071_k;
        }

        public String toString() {
            return this.name;
        }

        public static BlockPlanks.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockPlanks.EnumType blockamplanks$enumtype : values()) {
                META_LOOKUP[blockamplanks$enumtype.getMetadata()] = blockamplanks$enumtype;
            }
        }
    }
}
