package alfheimrsmoons.item;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.EntityAMArrow;
import alfheimrsmoons.init.AMItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBranchBow extends ItemBow
{
    public ItemBranchBow()
    {
        super();
        setCreativeTab(AlfheimrsMoons.CREATIVE_TAB);
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            @Override
            public float apply(ItemStack stack, World world, EntityLivingBase entity)
            {
                if (entity == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack activeStack = entity.getActiveItemStack();
                    return activeStack != null && activeStack.getItem() instanceof ItemBranchBow ? (float) (stack.getMaxItemUseDuration() - entity.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            boolean infinite = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack arrowStack = findAmmo(player);

            int i = getMaxItemUseDuration(arrowStack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(arrowStack, world, (EntityPlayer) entityLiving, i, arrowStack != null || infinite);
            if (i < 0) return;

            if (arrowStack != null || infinite)
            {
                if (arrowStack == null)
                {
                    arrowStack = new ItemStack(AMItems.ROCK_ARROW);
                }

                float f = getArrowVelocity(i);

                if ((double) f >= 0.1D)
                {
                    boolean infiniteArrows = infinite && arrowStack.getItem() instanceof ItemAMArrow;

                    if (!world.isRemote)
                    {
                        ItemAMArrow arrowItem = (ItemAMArrow) (arrowStack.getItem() instanceof ItemAMArrow ? arrowStack.getItem() : AMItems.ROCK_ARROW);
                        EntityAMArrow arrowEntity = arrowItem.createArrow(world, arrowStack, player);
                        arrowEntity.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                            arrowEntity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, arrowStack);

                        if (j > 0)
                        {
                            arrowEntity.setDamage(arrowEntity.getDamage() + (double) j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, arrowStack);

                        if (k > 0)
                        {
                            arrowEntity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, arrowStack) > 0)
                        {
                            arrowEntity.setFire(100);
                        }

                        arrowStack.damageItem(1, player);

                        if (infiniteArrows)
                        {
                            arrowEntity.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        world.spawnEntityInWorld(arrowEntity);
                    }

                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!infiniteArrows)
                    {
                        --arrowStack.stackSize;

                        if (arrowStack.stackSize == 0)
                        {
                            player.inventory.deleteStack(arrowStack);
                        }
                    }

                    player.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }

    private ItemStack findAmmo(EntityPlayer player)
    {
        if (isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int slot = 0; slot < player.inventory.getSizeInventory(); ++slot)
            {
                ItemStack stack = player.inventory.getStackInSlot(slot);

                if (isArrow(stack))
                {
                    return stack;
                }
            }

            return null;
        }
    }
}
