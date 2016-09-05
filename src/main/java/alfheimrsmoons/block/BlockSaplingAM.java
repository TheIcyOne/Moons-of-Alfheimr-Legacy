package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.VariantTree;
import alfheimrsmoons.world.gen.feature.WorldGenBigTreeAM;
import alfheimrsmoons.world.gen.feature.WorldGenTreeAM;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

import java.util.List;
import java.util.Random;

public class BlockSaplingAM extends BlockBush implements IGrowable
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = {BlockSapling.STAGE};

    public static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public final VariantsOfTypesCombo<VariantTree> owner;
    public final ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type;

    public final List<VariantTree> variants;
    public final PropertyIMetadata<VariantTree> variantProperty;

    public BlockSaplingAM(VariantsOfTypesCombo<VariantTree> owner,
                          ObjectType<VariantTree, ? extends BlockFlowerAM, ? extends ItemBlockMulti<VariantTree>> type,
                          List<VariantTree> variants, Class<VariantTree> variantClass)
    {
        super();

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty, BlockSapling.STAGE);
        setDefaultState(blockState.getBaseState().withProperty(BlockSapling.STAGE, 0));

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                grow(world, rand, pos, state);
            }
        }
    }

    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!TerrainGen.saplingGrowTree(world, rand, pos))
        {
            return;
        }

        VariantTree variant = state.getValue(variantProperty);
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
                worldGen = new WorldGenBigTreeAM(true, variant);
            }
            else
            {
                worldGen = new WorldGenTreeAM(true, variant);
            }
        }

        IBlockState air = Blocks.AIR.getDefaultState();

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

    private boolean isTwoByTwoOfVariant(World world, BlockPos pos, int xOffset, int zOffset, VariantTree variant)
    {
        return isVariantAt(world, pos.add(xOffset, 0, zOffset), variant) && isVariantAt(world, pos.add(xOffset + 1, 0, zOffset), variant) && isVariantAt(world, pos.add(xOffset, 0, zOffset + 1), variant) && isVariantAt(world, pos.add(xOffset + 1, 0, zOffset + 1), variant);
    }

    public boolean isVariantAt(World world, BlockPos pos, VariantTree variant)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == this && state.getValue(variantProperty) == variant;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return owner.getItemMetadata(type, state.getValue(variantProperty));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return (double) world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(BlockSapling.STAGE) == 0)
        {
            world.setBlockState(pos, state.cycleProperty(BlockSapling.STAGE), 4);
        }
        else
        {
            generateTree(world, pos, state, rand);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), meta, variantProperty, BlockSapling.STAGE);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty, BlockSapling.STAGE);
    }
}
