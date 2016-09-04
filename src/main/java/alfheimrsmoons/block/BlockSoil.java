package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockSoil extends Block
{
    public BlockSoil()
    {
        super(Material.GROUND);
        setRegistryName("soil");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "soil");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState());
        setHardness(0.5F);
        setSoundType(SoundType.GROUND);
        setHarvestLevel("shovel", 0);
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
                    if (world.getBlockState(pos.offset(horizontal)).getMaterial() == Material.WATER)
                        return true;
        }

        return false;
    }
}
