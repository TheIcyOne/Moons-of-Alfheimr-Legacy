package alfheimrsmoons.entity;

import alfheimrsmoons.init.AMItems;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySeedPouch extends EntityThrowable
{
    public EntitySeedPouch(World world)
    {
        super(world);
    }

    public EntitySeedPouch(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntitySeedPouch(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        boolean success = false;

        if (result.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            BlockPos pos = result.getBlockPos();
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() instanceof IGrowable)
            {
                IGrowable growable = (IGrowable) state.getBlock();

                if (growable.canGrow(world, pos, state, world.isRemote))
                {
                    if (!world.isRemote)
                    {
                        if (growable.canUseBonemeal(world, world.rand, pos, state))
                        {
                            growable.grow(world, world.rand, pos, state);
                        }
                    }

                    success = true;
                }
            }
        }

        if (!world.isRemote)
        {
            setDead();

            if (success)
            {
                world.playEvent(2005, result.getBlockPos(), 0);
            }
            else
            {
                world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(AMItems.SEED_POUCH)));
            }
        }
    }
}
