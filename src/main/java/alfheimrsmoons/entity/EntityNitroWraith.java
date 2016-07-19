package alfheimrsmoons.entity;

import alfheimrsmoons.init.AMEntities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityNitroWraith extends EntityMob implements IRangedAttackMob
{
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(EntityNitroWraith.class, DataSerializers.BYTE);

    public EntityNitroWraith(World world)
    {
        super(world);
        setSize(0.65F, 1.45F);
    }

    @Override
    protected void initEntityAI()
    {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(4, new AINitroWraithAttack(this));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new AINitroWraithTarget<>(this, EntityPlayer.class));
        targetTasks.addTask(3, new AINitroWraithTarget<>(this, EntityIronGolem.class));
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateClimber(this, world);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        dataManager.register(CLIMBING, (byte) 0);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!worldObj.isRemote)
        {
            setBesideClimbableBlock(isCollidedHorizontally);
        }
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return AMEntities.NITRO_WRAITH_LOOT_TABLE;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public boolean isOnLadder()
    {
        return isBesideClimbableBlock();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)
    {
        EntityArrow arrow = new EntityTippedArrow(worldObj, this);
        double d0 = target.posX - posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - arrow.posY;
        double d2 = target.posZ - posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        arrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - worldObj.getDifficulty().getDifficultyId() * 4));
        arrow.setDamage((double) (p_82196_2_ * 2.0F) + rand.nextGaussian() * 0.25D + (double) ((float) worldObj.getDifficulty().getDifficultyId() * 0.11F));
        playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
        worldObj.spawnEntityInWorld(arrow);
    }

    public boolean isBesideClimbableBlock()
    {
        return (dataManager.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing)
    {
        byte value = dataManager.get(CLIMBING);

        if (climbing)
        {
            value = (byte) (value | 1);
        }
        else
        {
            value = (byte) (value & -2);
        }

        dataManager.set(CLIMBING, value);
    }

    public static class AINitroWraithAttack extends EntityAIAttackRanged
    {
        private final EntityNitroWraith nitroWraith;

        public AINitroWraithAttack(EntityNitroWraith nitroWraith)
        {
            super(nitroWraith, 1.0D, 40, 10.0F);
            this.nitroWraith = nitroWraith;
        }

        @Override
        public boolean continueExecuting()
        {
            float brightness = nitroWraith.getBrightness(1.0F);

            if (brightness >= 0.5F && nitroWraith.getRNG().nextInt(100) == 0)
            {
                nitroWraith.setAttackTarget(null);
                return false;
            }
            else
            {
                return super.continueExecuting();
            }
        }
    }

    public static class AINitroWraithTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T>
    {
        public AINitroWraithTarget(EntityNitroWraith nitroWraith, Class<T> classTarget)
        {
            super(nitroWraith, classTarget, true);
        }

        @Override
        public boolean shouldExecute()
        {
            float brightness = taskOwner.getBrightness(1.0F);
            return brightness < 0.5F && super.shouldExecute();
        }
    }
}
