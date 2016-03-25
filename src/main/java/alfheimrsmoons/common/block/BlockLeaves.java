package alfheimrsmoons.common.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeaves extends net.minecraft.block.BlockLeaves {

    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType> create("variant", BlockPlanks.EnumType.class,
            new Predicate<BlockPlanks.EnumType>() {
                public boolean apply(BlockPlanks.EnumType type) {
                    return type.getMetadata() < 4;
                }
            });

    public BlockLeaves() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.BEECH).withProperty(CHECK_DECAY, Boolean.valueOf(true))
                .withProperty(DECAYABLE, Boolean.valueOf(true)));
        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        String name = "leaves";
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
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state) {
        return ((BlockPlanks.EnumType) state.getValue(VARIANT)).func_181070_c();
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state) {
        if (state.getBlock() != this) {
            return super.getRenderColor(state);
        } else {
            BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType) state.getValue(VARIANT);
            return blockplanks$enumtype == BlockPlanks.EnumType.BEECH ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(state);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        // if (iblockstate.getBlock() == this) {
        // BlockAMPlanks.EnumType blockplanks$enumtype =
        // (BlockAMPlanks.EnumType) iblockstate.getValue(VARIANT);
        //
        // if (blockplanks$enumtype == BlockAMPlanks.EnumType.BEECH) {
        // return ColorizerFoliage.getFoliageColorBirch();
        // }
        //
        // }

        return super.colorMultiplier(worldIn, pos, renderPass);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.amSapling);
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        // TODO add apple (or other) leave drops?
        // if (state.getValue(VARIANT) == BlockAMPlanks.EnumType.BEECH &&
        // worldIn.rand.nextInt(chance) == 0) {
        // spawnAsEntity(worldIn, pos, new ItemStack(Items.apple, 1, 0));
        // }
    }

    protected int getSaplingDropChance(IBlockState state) {
        return super.getSaplingDropChance(state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockPlanks.EnumType blockamplanks$enumtype : BlockPlanks.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamplanks$enumtype.getMetadata()));
        }
    }

    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((meta & 3) % 4)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
                .withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata();

        if (!((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
            i |= 4;
        }

        if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue()) {
            i |= 8;
        }

        return i;
    }

    @Override
    public EnumType getWoodType(int meta) {
        return null;
    }

    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT, CHECK_DECAY, DECAYABLE });
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

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
            player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
        } else {
            super.harvestBlock(worldIn, player, pos, state, te);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata())));

    }
}