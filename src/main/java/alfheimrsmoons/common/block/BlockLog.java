package alfheimrsmoons.common.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLog extends net.minecraft.block.BlockLog {
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType> create("variant", BlockPlanks.EnumType.class,
            new Predicate<BlockPlanks.EnumType>() {
                public boolean apply(BlockPlanks.EnumType type) {
                    return type.getMetadata() < 4;
                }
            });
    private String name;

    public BlockLog() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.BEECH).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.name = "log";
        this.setUnlocalizedName(this.name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, this.name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockPlanks.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });
    }

    public String getName() {
        return this.name;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state) {
        BlockPlanks.EnumType blockamplanks$enumtype = (BlockPlanks.EnumType) state.getValue(VARIANT);

        switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
        case X:
        case Z:
        case NONE:
        default:

            switch (blockamplanks$enumtype) {
            case BEECH:
            default:
                return BlockPlanks.EnumType.BEECH.func_181070_c();
            case ELM:
                return BlockPlanks.EnumType.ELM.func_181070_c();
            case RED_BUD:
                return BlockPlanks.EnumType.RED_BUD.func_181070_c();
            case LARCH:
                return BlockPlanks.EnumType.LARCH.func_181070_c();
            }

        case Y:
            return blockamplanks$enumtype.func_181070_c();
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.BEECH.getMetadata()));
        list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.ELM.getMetadata()));
        list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.RED_BUD.getMetadata()));
        list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.LARCH.getMetadata()));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12) {
        case 0:
            iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
            break;
        case 4:
            iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
            break;
        case 8:
            iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
            break;
        default:
            iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata();

        switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
        case X:
            i |= 4;
            break;
        case Z:
            i |= 8;
            break;
        case NONE:
            i |= 12;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT, LOG_AXIS });
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata());
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
}
