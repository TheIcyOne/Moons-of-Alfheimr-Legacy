package alfheimrsmoons.world.structure;

import java.util.Random;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.init.AMBlocks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.IWorldGenerator;

public class StructureSacredSpring extends StructureBase implements IWorldGenerator	{

	public StructureSacredSpring(){
		super(9, 11);
		
		//Mappings
		addMapping(" ", Blocks.AIR.getDefaultState());
		addMapping("G", Blocks.GRASS.getDefaultState());
		addMapping("C", Blocks.COBBLESTONE.getDefaultState());
		addMapping("M", Blocks.MOSSY_COBBLESTONE.getDefaultState());
		addMapping("F", Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS));
		addMapping("S", AMBlocks.SACRED_WATER.getDefaultState().withProperty(BlockFluidBase.LEVEL, 0));
		addMapping("1", AMBlocks.SACRED_WATER.getDefaultState().withProperty(BlockFluidBase.LEVEL, 1));
		addMapping("2", AMBlocks.SACRED_WATER.getDefaultState().withProperty(BlockFluidBase.LEVEL, 2));
		
		//Layers
		addLayer(new String[]{
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG"});
		
		addLayer(new String[]{
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGMCGGG",
				"GGGGMGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG"});
		
		addLayer(new String[]{
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGGGGGGG",
				"GGGSSGGGG",
				"GGSSSSGGG",
				"GSSSSSSGG",
				"GSSSSSSGG",
				"GGSSSSSGG",
				"GGCSSSGGG",
				"GGGGGGGGG"});
		
		addLayer(new String[]{
				" F  F    ",
				"  GCG  F ",
				"  CGGG FF",
				"  G CG F ",
				" FF     F",
				"      FF ",
				"F        ",
				"        F",
				" F       ",
				"F      F ",
				"   F F   "});
		
		addLayer(new String[]{
				"         ",
				"   CG    ",
				"  CGG    ",
				"  FSC    ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         "});
		
		addLayer(new String[]{
				"         ",
				"   C     ",
				"         ",
				"    C    ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",});
		
		addLayer(new String[]{
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",});
		
		addLayer(new String[]{
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",
				"         ",});
		
}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == DimensionType.OVERWORLD.getId() && !world.isRemote){
			if (random.nextInt(500)!=0) return;
				
			int centX = chunkX*16 + 4 + random.nextInt(6);
			int centZ = chunkZ*16 + 4 + random.nextInt(6);
			int height = world.getHeight(centX, centZ)-3;
			
			if (!(world.getBiome(new BlockPos(centX, height, centZ)).canRain()))
				return;
			
			for (int i = -2; i < 3; i ++){
				for (int j = -2; j < 3; j ++){
					if ((world.getBlockState(new BlockPos(centX+i, height, centZ+j)).getBlock() == Blocks.AIR)){
						return;
					}
				}
			}
				genInWorld(world, centX, height, centZ);
				AlfheimrsMoons.logger.info("Generating Sacred Spring at " + new BlockPos(centX, height, centZ).toString());
		}	
	}
	
	
}
