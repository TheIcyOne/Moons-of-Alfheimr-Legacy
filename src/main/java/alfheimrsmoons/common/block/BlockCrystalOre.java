package alfheimrsmoons.common.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystalOre extends Block {

    // add properties (note VARIANT is imported statically from the BlockCrystal
    // class)
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
    private String name;

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    public BlockCrystalOre() {
        super(Material.rock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.KASOLITE));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.name = "crystal_ore";
        this.setUnlocalizedName(this.name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, this.name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockCrystalOre.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });

        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypePiston);

        for (EnumType type : EnumType.values()) {
            this.setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockCrystalOre.EnumType blockamcrystalore$enumtype : BlockCrystalOre.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamcrystalore$enumtype.getMetadata()));
        }
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).getMetadata();
    }

    // The 3 functions below, getItemDropped, quantityDroppedWithBonus and
    // damageDropped work together to determine the drops
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (BlockCrystalOre.EnumType.byMetadata(getMetaFromState(state)).dropsAsOre()) {
            return Item.getItemFromBlock(this);
        }
        return ModItems.crystal;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        if (BlockCrystalOre.EnumType.byMetadata(getMetaFromState(state)).dropsAsOre()) {
            return 1;
        }
        return quantityDroppedWithBonus(fortune, random);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        // returns a number between 1 and (fortune+1) - functionally identical
        // to procedure for vanilla diamond drops, but simplified
        if (fortune > 0) {
            return Math.max(0, random.nextInt(fortune + 2) - 1) + 1;
        } else {
            return 1;
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    // Drop some experience when crystals are mined
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            return MathHelper.getRandomIntegerInRange(rand, 3, 7);
        }
        return 0;
    }

    public static enum EnumType implements IStringSerializable {
        KASOLITE(0, "kasolite", false, 3), NITRO(1, "nitro", false, 2), CORRODIUM(2, "corrodium", true, 2);

        private static final BlockCrystalOre.EnumType[] META_LOOKUP = new BlockCrystalOre.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final boolean dropsAsOre;
        private final int harvestLevel;

        private EnumType(int meta, String name, boolean dropAsOre, int harvestLevel) {
            this(meta, name, name, dropAsOre, harvestLevel);
        }

        private EnumType(int meta, String name, String unlocalizedName, boolean dropsAsOre, int harvestLevel) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.dropsAsOre = dropsAsOre;
            this.harvestLevel = harvestLevel;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockCrystalOre.EnumType byMetadata(int meta) {
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

        public boolean dropsAsOre() {
            return this.dropsAsOre;
        }

        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        static {
            for (BlockCrystalOre.EnumType blockamcrystalore$enumtype : values()) {
                META_LOOKUP[blockamcrystalore$enumtype.getMetadata()] = blockamcrystalore$enumtype;
            }
        }
    }

}
