package info.u_team.u_team_core.schematic;

import java.util.*;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.nbt.*;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

/**
 * Schematic API<br>
 * -> Load Region
 * 
 * @author HyCraftHD
 * @date 21.10.2017
 */
public class USchematicLoadRegion {
	
	private World world;
	private BlockPos start;
	
	private int sizex, sizey, sizez;
	
	private boolean centerstart = false;
	private USchematicRotation rotation = USchematicRotation.ROTATION_0;
	
	public USchematicLoadRegion(World world, BlockPos start) {
		this.world = world;
		this.start = start;
	}
	
	public USchematicLoadRegion center() {
		centerstart = true;
		return this;
	}
	
	public USchematicLoadRegion rotate(USchematicRotation rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public World getWorld() {
		return world;
	}
	
	public BlockPos getStart() {
		return start;
	}
	
	public void readNBT(CompoundNBT root) {
		sizex = root.getInt("sizex");
		sizey = root.getInt("sizey");
		sizez = root.getInt("sizez");
		
		readBlocks(root.getList("blocks", 10));
	}
	
	private void readBlocks(ListNBT list) {
		
		if (centerstart) {
			centerstart();
		}
		
		final List<Pair<BlockPos, USchematicEntry>> blocks = new ArrayList<>();
		
		int i = 0;
		for (int x = 0; x < sizex; x++) {
			for (int z = 0; z < sizez; z++) {
				for (int y = 0; y < sizey; y++) {
					BlockPos pos = start.add(rotate(new BlockPos(x, y, z)));
					
					USchematicEntry entry = new USchematicEntry(list.getCompound(i++));
					blocks.add(Pair.of(pos, entry));
					// entry.setBlock(world, pos);
				}
			}
		}
		
		final int split = 30;
		final int partition = blocks.size() / split;
		
		for (int c = 0; c < split; c++) {
			int capture = c;
			world.getServer().enqueue(new TickDelayedTask(c, () -> {
				for (int index = 0; index < partition; index++) {
					final Pair<BlockPos, USchematicEntry> pair = blocks.get((partition * capture) + index);
					pair.getRight().setBlock(world, pair.getLeft());
				}
			}));
		}
		
		int splitPortionMult = split * partition;
		int rest = blocks.size() - splitPortionMult;
		
		if (rest > 0) {
			world.getServer().enqueue(new TickDelayedTask(0, () -> {
				for (int c = 0; c < rest; c++) {
					final Pair<BlockPos, USchematicEntry> pair = blocks.get(splitPortionMult + c);
					pair.getRight().setBlock(world, pair.getLeft());
				}
			}));
		}
	}
	
	private void centerstart() {
		switch (rotation) {
		case ROTATION_0:
			start = start.subtract(new Vec3i(sizex / 2, 0, sizez / 2));
			break;
		case ROTATION_90:
			start = start.subtract(new Vec3i(-sizez / 2, 0, sizex / 2));
			break;
		case ROTATION_180:
			start = start.subtract(new Vec3i(-sizex / 2, 0, -sizez / 2));
			break;
		case ROTATION_270:
			start = start.subtract(new Vec3i(sizez / 2, 0, -sizex / 2));
			break;
		}
	}
	
	private BlockPos rotate(BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		switch (rotation) {
		case ROTATION_0:
			return pos;
		case ROTATION_90:
			return new BlockPos(-z, y, x);
		case ROTATION_180:
			return new BlockPos(-x, y, -z);
		case ROTATION_270:
			return new BlockPos(z, y, -x);
		}
		return pos;
	}
	
}
