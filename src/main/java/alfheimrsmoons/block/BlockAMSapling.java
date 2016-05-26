package alfheimrsmoons.block;

import alfheimrsmoons.block.BlockAMPlanks.EnumType;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.world.WorldGenAMBigTree;
import alfheimrsmoons.world.WorldGenAMTrees;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAMSapling extends BlockSapling {
    public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("variant", EnumType.class);

    public BlockAMSapling() {
        blockState = new BlockStateContainer(this, TYPE, STAGE);
        setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumType.RUNE).withProperty(STAGE, 0));
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public String getLocalizedName() {
        return I18n.translateToLocal(getUnlocalizedName() + "." + getDefaultState().getValue(TYPE).getName() + ".name");
    }

    @Override
    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!TerrainGen.saplingGrowTree(world, rand, pos)) {
            return;
        }

        EnumType type = state.getValue(TYPE);
        WorldGenerator worldGen = null;
        int xOffset = 0;
        int yOffset = 0;
        boolean isMegaTree = false;

        switch (type) {
            //TODO: specialized tree generators
            case RUNE:
            case BEECH:
            case ELM:
            case RED_BUD:
            case LARCH:
            default:
        }

        if (worldGen == null) {
            BlockAMLog wood;
            BlockAMLeaves leaves;

            if (type.getMetadata() < 4) {
                wood = AMBlocks.log;
                leaves = AMBlocks.leaves;
            } else {
                wood = AMBlocks.log2;
                leaves = AMBlocks.leaves2;
            }

            IBlockState woodState = wood.getDefaultState().withProperty(wood.variant, type);
            IBlockState leavesState = leaves.getDefaultState().withProperty(leaves.variant, type);
            boolean isBigTree;

            switch (type) {
                case RUNE:
                    isBigTree = rand.nextInt(5) == 0;
                    break;
                default:
                    isBigTree = rand.nextInt(10) == 0;
                    break;
            }

            if (isBigTree) {
                worldGen = new WorldGenAMBigTree(true, woodState, leavesState, this);
            } else {
                worldGen = new WorldGenAMTrees(true, woodState, leavesState);
            }
        }

        IBlockState air = Blocks.air.getDefaultState();

        if (isMegaTree) {
            world.setBlockState(pos.add(xOffset, 0, yOffset), air, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, yOffset), air, 4);
            world.setBlockState(pos.add(xOffset, 0, yOffset + 1), air, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, yOffset + 1), air, 4);
        } else {
            world.setBlockState(pos, air, 4);
        }

        if (!worldGen.generate(world, rand, pos.add(xOffset, 0, yOffset))) {
            if (isMegaTree) {
                world.setBlockState(pos.add(xOffset, 0, yOffset), state, 4);
                world.setBlockState(pos.add(xOffset + 1, 0, yOffset), state, 4);
                world.setBlockState(pos.add(xOffset, 0, yOffset + 1), state, 4);
                world.setBlockState(pos.add(xOffset + 1, 0, yOffset + 1), state, 4);
            } else {
                world.setBlockState(pos, state, 4);
            }
        }
    }

    private boolean shouldGrowMegaTree(World world, BlockPos pos, int xOffset, int zOffset, EnumType type) {
        return isTypeAt(world, pos.add(xOffset, 0, zOffset), type) && isTypeAt(world, pos.add(xOffset + 1, 0, zOffset), type) && isTypeAt(world, pos.add(xOffset, 0, zOffset + 1), type) && isTypeAt(world, pos.add(xOffset + 1, 0, zOffset + 1), type);
    }

    @Override
    public boolean isTypeAt(World world, BlockPos pos, BlockPlanks.EnumType type) {
        return false;
    }

    public boolean isTypeAt(World world, BlockPos pos, EnumType type) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == this && state.getValue(TYPE) == type;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(TYPE).getMetadata();
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
        return getDefaultState().withProperty(TYPE, VariantHelper.getVariantFromMeta(EnumType.values, meta & 7)).withProperty(STAGE, (meta & 8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(TYPE).getMetadata();
        i = i | state.getValue(STAGE) << 3;
        return i;
    }
}
