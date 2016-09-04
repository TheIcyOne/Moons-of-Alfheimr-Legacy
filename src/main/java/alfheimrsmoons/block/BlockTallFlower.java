package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import alfheimrsmoons.combo.VariantFlower;
import alfheimrsmoons.combo.EnumHalf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.item.ItemBlockMulti;
import zaggy1024.util.BlockStateToMetadata;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockTallFlower extends BlockBush implements IGrowable
{
    @BlockProperties
    public static final IProperty<?>[] PROPERTIES = {EnumHalf.PROPERTY};

    public final VariantsOfTypesCombo<VariantFlower> owner;
    public final ObjectType<VariantFlower, ? extends BlockTallFlower, ? extends ItemBlockMulti<VariantFlower>> type;

    public final List<VariantFlower> variants;
    public final PropertyIMetadata<VariantFlower> variantProperty;

    public BlockTallFlower(VariantsOfTypesCombo<VariantFlower> owner,
                           ObjectType<VariantFlower, ? extends BlockTallFlower, ? extends ItemBlockMulti<VariantFlower>> type,
                           List<VariantFlower> variants, Class<VariantFlower> variantClass)
    {
        super(Material.VINE);

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProperty = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProperty, EnumHalf.PROPERTY);
        setDefaultState(blockState.getBaseState().withProperty(EnumHalf.PROPERTY, EnumHalf.BOTTOM));

        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof BlockSoil || block instanceof BlockGrassySoil || super.canSustainBush(state);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    protected VariantFlower getVariant(IBlockAccess world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this)
        {
            return state.getActualState(world, pos).getValue(variantProperty);
        }
        else
        {
            return VariantFlower.COLOMBINE;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return super.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() != this;
    }

    @Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!canBlockStay(world, pos, state))
        {
            boolean isTop = state.getValue(EnumHalf.PROPERTY).isTop();
            BlockPos topPos = isTop ? pos : pos.up();
            BlockPos bottomPos = isTop ? pos.down() : pos;
            Block topBlock = isTop ? this : world.getBlockState(topPos).getBlock();
            Block bottomBlock = isTop ? world.getBlockState(bottomPos).getBlock() : this;

            if (!isTop)
            {
                dropBlockAsItem(world, pos, state, 0); //Forge move above the setting to air.
            }

            if (topBlock == this)
            {
                world.setBlockState(topPos, Blocks.AIR.getDefaultState(), 2);
            }

            if (bottomBlock == this)
            {
                world.setBlockState(bottomPos, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() != this)
        {
            return super.canBlockStay(world, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        }

        if (state.getValue(EnumHalf.PROPERTY).isTop())
        {
            return world.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            return world.getBlockState(pos.up()).getBlock() == this && super.canBlockStay(world, pos, state);
        }
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(EnumHalf.PROPERTY).isTop())
        {
            return null;
        }
        else
        {
            return super.getItemDropped(state, rand, fortune);
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return !state.getValue(EnumHalf.PROPERTY).isTop() ? owner.getItemMetadata(type, state.getValue(variantProperty)) : 0;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        world.setBlockState(pos.up(), getDefaultState().withProperty(EnumHalf.PROPERTY, EnumHalf.TOP), 2);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(EnumHalf.PROPERTY).isTop())
        {
            BlockPos bottomPos = pos.down();

            if (world.getBlockState(bottomPos).getBlock() == this)
            {
                if (player.capabilities.isCreativeMode)
                {
                    world.setBlockToAir(bottomPos);
                }
                else
                {
                    world.destroyBlock(bottomPos, true);
                }
            }
        }
        else
        {
            BlockPos topPos = pos.up();

            if (world.getBlockState(topPos).getBlock() == this)
            {
                world.setBlockState(topPos, Blocks.AIR.getDefaultState(), 2);
            }
        }

        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return owner.getStack(type, getVariant(world, pos, state));
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        spawnAsEntity(world, pos, owner.getStack(type, getVariant(world, pos, state)));
    }

    @Override
    public IBlockState getStateFromMeta(int metadata)
    {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), metadata, variantProperty, EnumHalf.PROPERTY);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(EnumHalf.PROPERTY).isTop())
        {
            IBlockState bottomState = world.getBlockState(pos.down());

            if (bottomState.getBlock() == this)
            {
                state = bottomState.withProperty(EnumHalf.PROPERTY, EnumHalf.TOP);
            }
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProperty, EnumHalf.PROPERTY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        //Forge: Break both parts on the client to prevent the top part flickering as default type for a few frames.
        if (state.getBlock() == this && state.getValue(EnumHalf.PROPERTY).isBottom())
        {
            BlockPos topPos = pos.up();

            if (world.getBlockState(topPos).getBlock() == this)
            {
                world.setBlockToAir(topPos);
            }
        }

        return world.setBlockToAir(pos);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 100;
    }
}
