package info.u_team.u_team_core.schematic;

import net.minecraft.block.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.BlockStateFlatteningMap;
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
	
	private final ResourceLocation registryname;
	private final int meta;
	private CompoundNBT nbt;
	
	public USchematicEntry(World world, BlockPos pos) {
		final BlockState state = world.getBlockState(pos);
		final Block block = state.getBlock();
		
		registryname = ForgeRegistries.BLOCKS.getKey(block);
		meta = 0; // placeholder. We have no meta data naymore
		
		final TileEntity tileentity = world.getTileEntity(pos);
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
		Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(BlockStateFlatteningMap.updateName(registryname.toString())));
		
		if (block == null) {
			System.err.println("Block registryname " + registryname + " in schematic was not found in minecraft!? Mods missing?");
			block = Blocks.AIR;
		}
		
		final BlockState state = block.getDefaultState();
		world.setBlockState(pos, state);
		
		final TileEntity tileentity = world.getTileEntity(pos);
		
		if (tileentity != null && nbt != null) {
			nbt.putInt("x", pos.getX());
			nbt.putInt("y", pos.getY());
			nbt.putInt("z", pos.getZ());
			tileentity.read(state, nbt);
		}
	}
	
	public CompoundNBT getTag() {
		final CompoundNBT entry = new CompoundNBT();
		
		entry.putString("name", registryname.toString());
		entry.putInt("meta", meta);
		if (nbt != null) {
			entry.put("nbt", nbt);
		}
		return entry;
	}
	
}
