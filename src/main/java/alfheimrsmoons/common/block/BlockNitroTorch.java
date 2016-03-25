package alfheimrsmoons.common.block;

import java.util.Random;

import com.google.common.base.Predicate;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.AMAchievementPage;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNitroTorch extends BlockTorch {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
        public boolean apply(EnumFacing p_apply_1_) {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
    private String name;

    protected BlockNitroTorch() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
        this.setTickRandomly(true);
        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);

        this.name = "nitro_torch";
        setUnlocalizedName(this.name);
        setLightLevel(0.9375f);
        setStepSound(soundTypeWood);

        GameRegistry.registerBlock(this, this.name);
    }

    public String getName() {
        return this.name;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        if (enumfacing.getAxis().isHorizontal() || enumfacing.getAxis().isVertical()) {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.6D,
                    -1.0D, 1.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, -0.7D, 0D, 0.8D, new int[0]);
        } else {
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (placer instanceof EntityPlayer) {
            ((EntityPlayer) placer).triggerAchievement(AMAchievementPage.amTestAchievement);
        }
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (player != null) {
            player.triggerAchievement(AMAchievementPage.amTempAchievement);
        }
    }

}