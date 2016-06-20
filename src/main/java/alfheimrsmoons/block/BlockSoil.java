package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.util.DefaultBlockHelper;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockSoil extends BlockDirt
{
    public BlockSoil()
    {
        super();
        blockState = new BlockStateContainer(this);
        setDefaultState(blockState.getBaseState());
        setHardness(0.5F);
        setSoundType(SoundType.GROUND);
        setHarvestLevel("shovel", 0);
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        EntityEnderman.setCarriable(this, true);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Desert:
            case Plains:
                return true;
            case Beach:
                for (EnumFacing horizontal : EnumFacing.HORIZONTALS)
                {
                    if (world.getBlockState(pos.offset(horizontal)).getMaterial() == Material.WATER)
                    {
                        return true;
                    }
                }
        }

        return false;
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.DIRT;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return DefaultBlockHelper.getActualState(this, state, world, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        DefaultBlockHelper.getSubBlocks(this, item, tab, list);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return DefaultBlockHelper.getItem(this, world, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return DefaultBlockHelper.getStateFromMeta(this, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return DefaultBlockHelper.getMetaFromState(this, state);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return DefaultBlockHelper.damageDropped(this, state);
    }
}
