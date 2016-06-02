package alfheimrsmoons.block;

import alfheimrsmoons.util.EnumWoodVariant;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.util.IVariantBlock;
import alfheimrsmoons.util.VariantHelper;
import alfheimrsmoons.world.gen.feature.WorldGenAMBigTree;
import alfheimrsmoons.world.gen.feature.WorldGenAMTrees;
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

public class BlockAMSapling extends BlockSapling implements IVariantBlock<EnumWoodVariant>
{
    public static final PropertyEnum<EnumWoodVariant> VARIANT_PROPERTY = PropertyEnum.create("variant", EnumWoodVariant.class);

    public BlockAMSapling()
    {
        blockState = new BlockStateContainer(this, VARIANT_PROPERTY, STAGE);
        setDefaultState(blockState.getBaseState().withProperty(STAGE, 0));
        setHardness(0.0F);
        setStepSound(SoundType.PLANT);
    }

    @Override
    public PropertyEnum<EnumWoodVariant> getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public EnumWoodVariant[] getVariants()
    {
        return EnumWoodVariant.values();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public String getLocalizedName()
    {
        return I18n.translateToLocal(getUnlocalizedName() + "." + getDefaultState().getValue(VARIANT_PROPERTY).getName() + ".name");
    }

    @Override
    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!TerrainGen.saplingGrowTree(world, rand, pos))
        {
            return;
        }

        EnumWoodVariant variant = state.getValue(VARIANT_PROPERTY);
        WorldGenerator worldGen = null;
        int xOffset = 0;
        int yOffset = 0;
        boolean isMegaTree = false;

        switch (variant)
        {
            //TODO: specialized tree generators
            case RUNE:
            case BEECH:
            case ELM:
            case REDBUD:
            case LARCH:
            default:
        }

        if (worldGen == null)
        {
            BlockAMLog wood;
            BlockAMLeaves leaves;

            if (VariantHelper.getMetaFromVariant(this, variant) < 4)
            {
                wood = AMBlocks.log;
                leaves = AMBlocks.leaves;
            }
            else
            {
                wood = AMBlocks.log2;
                leaves = AMBlocks.leaves2;
            }

            IBlockState woodState = VariantHelper.getDefaultStateWithVariant(wood, variant);
            IBlockState leavesState = VariantHelper.getDefaultStateWithVariant(leaves, variant);
            boolean isBigTree;

            switch (variant)
            {
                case RUNE:
                    isBigTree = rand.nextInt(5) == 0;
                    break;
                default:
                    isBigTree = rand.nextInt(10) == 0;
                    break;
            }

            if (isBigTree)
            {
                worldGen = new WorldGenAMBigTree(true, woodState, leavesState, this);
            }
            else
            {
                worldGen = new WorldGenAMTrees(true, woodState, leavesState);
            }
        }

        IBlockState air = Blocks.air.getDefaultState();

        if (isMegaTree)
        {
            world.setBlockState(pos.add(xOffset, 0, yOffset), air, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, yOffset), air, 4);
            world.setBlockState(pos.add(xOffset, 0, yOffset + 1), air, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, yOffset + 1), air, 4);
        }
        else
        {
            world.setBlockState(pos, air, 4);
        }

        if (!worldGen.generate(world, rand, pos.add(xOffset, 0, yOffset)))
        {
            if (isMegaTree)
            {
                world.setBlockState(pos.add(xOffset, 0, yOffset), state, 4);
                world.setBlockState(pos.add(xOffset + 1, 0, yOffset), state, 4);
                world.setBlockState(pos.add(xOffset, 0, yOffset + 1), state, 4);
                world.setBlockState(pos.add(xOffset + 1, 0, yOffset + 1), state, 4);
            }
            else
            {
                world.setBlockState(pos, state, 4);
            }
        }
    }

    private boolean isTwoByTwoOfVariant(World world, BlockPos pos, int xOffset, int zOffset, EnumWoodVariant variant)
    {
        return isVariantAt(world, pos.add(xOffset, 0, zOffset), variant) && isVariantAt(world, pos.add(xOffset + 1, 0, zOffset), variant) && isVariantAt(world, pos.add(xOffset, 0, zOffset + 1), variant) && isVariantAt(world, pos.add(xOffset + 1, 0, zOffset + 1), variant);
    }

    public boolean isVariantAt(World world, BlockPos pos, EnumWoodVariant variant)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == this && state.getValue(VARIANT_PROPERTY) == variant;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return VariantHelper.getMetaFromState(this, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        VariantHelper.addSubItems(this, item, list);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return VariantHelper.getDefaultStateWithMeta(this, meta & 7).withProperty(STAGE, (meta & 8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | VariantHelper.getMetaFromState(this, state);
        i = i | state.getValue(STAGE) << 3;
        return i;
    }
}
