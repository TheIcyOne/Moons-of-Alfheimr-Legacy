package alfheimrsmoons.world.structure;

import java.util.HashMap;
import java.util.Map;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class StructureBase {
	
	protected int width, length;
	protected NonNullList<String[]> layers;
	protected Map<String, IBlockState> blockMap = new HashMap<String, IBlockState>();
	
	public StructureBase(int wide, int len){
		width = wide;
		length = len;
		layers = NonNullList.create();
	}
	
	protected StructureBase addLayer(String[] layer){
		layers.add(layer);
		return this;
	}
	
	protected StructureBase addAllLayers(String[]... layerList){
		for (String[] layer : layerList){
			layers.add(layer);
		}
		return this;
	}
	
	protected StructureBase addMapping(String key, IBlockState state){
		blockMap.put(key, state);
		return this;
	}
	
	protected void genInWorld(World w, int xPos, int yPos, int zPos){
		for (int  x= 0; x < length; x++){
			for (int y = 0; y < layers.size(); y++){
				for (int z = 0; z < width; z++){
				place(w, new BlockPos(xPos + x, yPos + y, zPos + z), blockMap.get(layers.get(y)[x].substring(z, z+1)));
				}
			}
		}
	}
	
	protected void place(World w, BlockPos pos, IBlockState state){
		w.setBlockState(pos, state);
	}
	

}
