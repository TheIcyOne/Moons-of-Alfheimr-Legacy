package alfheimrsmoons.common.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import alfheimrsmoons.world.gen.WorldGenAMTrees;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSapling extends BlockBush implements IGrowable {

    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType> create("variant", BlockPlanks.EnumType.class,
            new Predicate<BlockPlanks.EnumType>() {
                public boolean apply(BlockPlanks.EnumType type) {
                    return type.getMetadata() < 4;
                }
            });
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BlockSapling() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.BEECH).withProperty(STAGE, Integer.valueOf(0)));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        String name = "sapling";
        this.setUnlocalizedName(name);
        setStepSound(soundTypeGrass);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockPlanks.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });
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

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta & 7)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((BlockPlanks.EnumType) state.getValue(VARIANT)).getMetadata();
        i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT, STAGE });
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
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (((Integer) state.getValue(STAGE)).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
            return;
        Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (state.getValue(VARIANT)) {
        case BEECH:
            object = new WorldGenAMTrees(ModBlocks.amLog, ModBlocks.amLeaves, BlockPlanks.EnumType.BEECH.getMetadata(), BlockPlanks.EnumType.BEECH.getMetadata(), false, 4, false);
            break;
        case ELM:
            object = new WorldGenAMTrees(ModBlocks.amLog, ModBlocks.amLeaves, BlockPlanks.EnumType.ELM.getMetadata(), BlockPlanks.EnumType.ELM.getMetadata(), false, 5, false);
            break;
        case RED_BUD:
            object = new WorldGenAMTrees(ModBlocks.amLog, ModBlocks.amLeaves, BlockPlanks.EnumType.RED_BUD.getMetadata(), BlockPlanks.EnumType.RED_BUD.getMetadata(), false, 4,
                    false);
            break;
        case LARCH:
            object = new WorldGenAMTrees(ModBlocks.amLog, ModBlocks.amLeaves, BlockPlanks.EnumType.LARCH.getMetadata(), BlockPlanks.EnumType.LARCH.getMetadata(), false, 8, false);
            break;
        default:
            return;
        }

        IBlockState iblockstate1 = Blocks.air.getDefaultState();

        if (flag) {
            worldIn.setBlockState(pos.add(i, 0, j), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate1, 4);
        } else {
            worldIn.setBlockState(pos, iblockstate1, 4);
        }

        if (!((WorldGenerator) object).generate(worldIn, rand, pos.add(i, 0, j))) {
            if (flag) {
                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            } else {
                worldIn.setBlockState(pos, state, 4);
            }
        }
    }

    /**
     * Check whether the given BlockPos has a Sapling of the given type
     */
    public boolean isTypeAt(World worldIn, BlockPos pos, BlockPlanks.EnumType type) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == this && iblockstate.getValue(VARIANT) == type;
    }

}