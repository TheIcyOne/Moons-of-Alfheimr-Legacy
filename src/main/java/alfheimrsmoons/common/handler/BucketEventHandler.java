package alfheimrsmoons.common.handler;

import alfheimrsmoons.common.block.BlockFluidTest;
import alfheimrsmoons.common.block.ModBlocks;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketEventHandler {

    @SubscribeEvent
    public void onRightClickHoldingBucket(FillBucketEvent event) {
        if (event.current.getItem() != Items.bucket) {
            return;
        }
        if (event.target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        BlockPos blockpos = event.target.getBlockPos();
        if (!event.world.isBlockModifiable(event.entityPlayer, blockpos)) {
            return;
        }
        if (!event.entityPlayer.canPlayerEdit(blockpos.offset(event.target.sideHit), event.target.sideHit, event.current)) {
            return;
        }

        // determine if the block is one of our fluids
        IBlockState iblockstate = event.world.getBlockState(blockpos);
        Item filled_bucket = null;
        if (iblockstate.getBlock() == ModBlocks.fluidTest && ((Integer) iblockstate.getValue(BlockFluidTest.LEVEL)).intValue() == 0) {
            filled_bucket = ModItems.bucketSomething;
        } else {
            return;
        }

        // remove the fluid and return the appropriate filled bucket
        event.setResult(Result.ALLOW);
        event.result = new ItemStack(filled_bucket);
        event.world.setBlockToAir(blockpos);

        // TODO track stats?!
        // event.entityPlayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(event.current.getItem())]);
    }

}