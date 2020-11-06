package info.u_team.u_team_core.schematic;

import net.minecraft.nbt.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Schematic API<br>
 * -> Save Region
 * 
 * @author HyCraftHD
 * @date 21.10.2017
 */
public class USchematicSaveRegion {
	
	private final World world;
	private final BlockPos min, max;
	private final int sizex, sizey, sizez, count;
	
	public USchematicSaveRegion(World world, BlockPos pos1, BlockPos pos2) {
		this.world = world;
		min = new BlockPos(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
		max = new BlockPos(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));
		sizex = max.getX() - min.getX() + 1;
		sizey = max.getY() - min.getY() + 1;
		sizez = max.getZ() - min.getZ() + 1;
		count = sizex * sizey * sizez;
	}
	
	public World getWorld() {
		return world;
	}
	
	public int getCount() {
		return count;
	}
	
	public BlockPos getMax() {
		return max;
	}
	
	public BlockPos getMin() {
		return min;
	}
	
	public int getSizeX() {
		return sizex;
	}
	
	public int getSizeY() {
		return sizey;
	}
	
	public int getSizeZ() {
		return sizez;
	}
	
	public CompoundNBT saveNBT() {
		final CompoundNBT root = new CompoundNBT();
		
		root.putInt("sizex", getSizeX());
		root.putInt("sizey", getSizeY());
		root.putInt("sizez", getSizeZ());
		root.putInt("count", getCount());
		
		root.put("blocks", saveBlocks());
		
		return root;
	}
	
	private ListNBT saveBlocks() {
		final ListNBT list = new ListNBT();
		for (int x = min.getX(); x <= max.getX(); x++) {
			for (int z = min.getZ(); z <= max.getZ(); z++) {
				for (int y = min.getY(); y <= max.getY(); y++) {
					final BlockPos pos = new BlockPos(x, y, z);
					final USchematicEntry entry = new USchematicEntry(world, pos);
					list.add(entry.getTag());
				}
			}
		}
		return list;
	}
	
}
