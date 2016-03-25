package alfheimrsmoons.common.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.BlockSoil.EnumType;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSoil extends Block {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
    private String name;

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    public BlockSoil() {
        super(Material.ground);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.GRASSY));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.name = "soil";
        this.setUnlocalizedName(this.name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, this.name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockSoil.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });

        setHardness(0.5F);
        setHarvestLevel("shovel", 0);
        setStepSound(soundTypeGravel);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockSoil.EnumType blockamsoil$enumtype : BlockSoil.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamsoil$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType) {
        case Desert:
        case Plains:
            return true;
        case Cave:
            return isSideSolid(world, pos, EnumFacing.UP);
        case Beach:
            boolean hasWater = (world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water
                    || world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water || world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water
                    || world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water);
            return hasWater;
        default:
            return false;
        }
    }

    public static enum EnumType implements IStringSerializable {
        GRASSY(0, "normal"), NIFLHEIMR(1, "niflheimr");

        private static final BlockSoil.EnumType[] META_LOOKUP = new BlockSoil.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, String name) {
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockSoil.EnumType byMetadata(int meta) {
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
            for (BlockSoil.EnumType blockamsoil$enumtype : values()) {
                META_LOOKUP[blockamsoil$enumtype.getMetadata()] = blockamsoil$enumtype;
            }
        }
    }
}
