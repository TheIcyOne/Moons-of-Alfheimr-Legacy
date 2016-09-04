package alfheimrsmoons.block;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockYggdrasilLeaves extends BlockEmptyDrops
{
    public BlockYggdrasilLeaves()
    {
        super(Material.LEAVES);
        setRegistryName("yggdrasil_leaves");
        setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + "yggdrasil_leaves");
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        setBlockUnbreakable().setResistance(6000000.0F);
        setSoundType(SoundType.PLANT);
        disableStats();
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
    {
        return !(entity instanceof EntityDragon || entity instanceof EntityWither);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
    {
        return false;
    }
}
