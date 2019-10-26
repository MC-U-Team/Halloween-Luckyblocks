package info.u_team.u_team_core.schematic;

import net.minecraft.block.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Schematic API<br>
 * -> Entry
 * 
 * @author HyCraftHD
 * @date 21.10.2017
 */
public class USchematicEntry {
	
	private ResourceLocation registryname;
	private int meta;
	private CompoundNBT nbt;
	
	public USchematicEntry(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
		registryname = ForgeRegistries.BLOCKS.getKey(block);
		meta = 0; // placeholder. We have no meta data naymore
		
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity != null) {
			tileentity.write(nbt = new CompoundNBT());
			nbt.remove("x"); // We don't need the old location
			nbt.remove("y");
			nbt.remove("z");
		}
	}
	
	public USchematicEntry(CompoundNBT tag) {
		registryname = new ResourceLocation(tag.getString("name"));
		meta = tag.getInt("meta");
		nbt = tag.getCompound("nbt");
	}
	
	public void setBlock(World world, BlockPos pos) {
		Block block = ForgeRegistries.BLOCKS.getValue(registryname);
		if (block == null) {
			System.err.println("Block registryname " + registryname + " in schematic was not found in minecraft!? Mods missing?");
			block = Blocks.AIR;
		}
		
		BlockState state = block.getDefaultState();
		world.setBlockState(pos, state);
		
		TileEntity tileentity = world.getTileEntity(pos);
		
		if (tileentity != null && nbt != null) {
			nbt.putInt("x", pos.getX());
			nbt.putInt("y", pos.getY());
			nbt.putInt("z", pos.getZ());
			tileentity.read(nbt);
		}
	}
	
	public CompoundNBT getTag() {
		CompoundNBT entry = new CompoundNBT();
		
		entry.putString("name", registryname.toString());
		entry.putInt("meta", meta);
		if (nbt != null) {
			entry.put("nbt", nbt);
		}
		return entry;
	}
	
}
