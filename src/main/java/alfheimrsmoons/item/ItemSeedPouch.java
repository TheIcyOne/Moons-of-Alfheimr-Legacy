package alfheimrsmoons.item;

import alfheimrsmoons.entity.EntitySeedPouch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSeedPouch extends Item
{
    public ItemSeedPouch()
    {
        setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        } else
        {
            if (ItemDye.applyBonemeal(stack, world, pos, player))
            {
                if (!world.isRemote)
                {
                    world.playAuxSFX(2005, pos, 0);
                }

                return EnumActionResult.SUCCESS;
            }

            return EnumActionResult.PASS;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.entity_snowball_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            EntitySeedPouch seedPouchEntity = new EntitySeedPouch(world, player);
            seedPouchEntity.func_184538_a(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntityInWorld(seedPouchEntity);
        }

        player.addStat(StatList.func_188057_b(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
}
