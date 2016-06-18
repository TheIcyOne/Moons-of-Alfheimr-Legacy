package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockYggdrasilLeaves extends BlockEmptyDrops
{
    public BlockYggdrasilLeaves()
    {
        super(Material.leaves);
        setBlockUnbreakable().setResistance(6000000.0F);
        setStepSound(SoundType.PLANT);
        disableStats();
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
    {
        return false;
    }
    
    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
    {
        return false;
    }
}
