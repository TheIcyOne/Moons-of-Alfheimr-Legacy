package alfheimrsmoons.block.fluid;

import java.util.Random;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEitr extends BlockFluidClassic{

	public BlockEitr(Fluid fluid) {
		super(fluid, Material.WATER);
		setRegistryName("eitr");
		setUnlocalizedName(AlfheimrsMoons.UNLOCALIZED_PREFIX + this.getRegistryName());
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, BlockPos p, IBlockState s, Entity e){
		if (e instanceof EntityLivingBase)
			((EntityLivingBase)e).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
	}
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void randomDisplayTick(IBlockState s, World w, BlockPos p, Random r) {
		 super.randomDisplayTick(s, w, p, r);
	        if (r.nextInt(2)==0)          
	        	w.spawnParticle(EnumParticleTypes.SLIME, p.getX() + r.nextFloat(), p.getY() + 1.0F, p.getZ() + r.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
	        }
}
