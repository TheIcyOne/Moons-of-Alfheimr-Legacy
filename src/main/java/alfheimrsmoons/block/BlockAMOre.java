package alfheimrsmoons.block;

import alfheimrsmoons.init.AMItems;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAMOre extends BlockOre {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockAMOre() {
        blockState = new BlockStateContainer(this, VARIANT);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.NITRO));
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(SoundType.STONE);
        //TODO: harvest levels
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        switch(state.getValue(VARIANT)) {
            default:
                return Item.getItemFromBlock(this);
            case NITRO:
            case SYLVANITE:
                return AMItems.ore_drop;
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        if (Item.getItemFromBlock(this) != getItemDropped(state, random, fortune)) {
            int quantity;

            switch(state.getValue(VARIANT)) {
                default:
                    quantity = 1;
                    break;
                case NITRO:
                    quantity = 2;
                    break;
            }

            if (fortune > 0) {
                int i = random.nextInt(fortune + 2) - 1;

                if (i < 0) {
                    i = 0;
                }

                quantity *= (i + 1);
            }

            return quantity;
        } else {
            return 1;
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        switch (state.getValue(VARIANT)) {
            //TODO: XP drops
            default:
                return 0;
        }
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return DefaultBlockHelper.getItem(this, world, pos, state);
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
        return getDefaultState().withProperty(VARIANT, VariantHelper.getVariantFromMeta(EnumType.values, meta));
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
        NITRO("nitro", MapColor.purpleColor),
        SYLVANITE("sylvanite", MapColor.emeraldColor),
        LOREIUM("loreium", MapColor.diamondColor),
        TEKTITE("tektite", MapColor.purpleColor);

        public static final EnumType[] values = values();
        private final String name;
        private final MapColor blockColor;

        EnumType(String name, MapColor blockColor) {
            this.name = name;
            this.blockColor = blockColor;
        }

        public MapColor getBlockColor() {
            return blockColor;
        }

        public int getMetadata() {
            return ordinal();
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
