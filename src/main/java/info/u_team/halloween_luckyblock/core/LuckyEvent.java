package info.u_team.halloween_luckyblock.core;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class LuckyEvent {
	
	private String name;
	private int count;
	
	public LuckyEvent(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public abstract void execute(ServerPlayerEntity player, World world, BlockPos pos);
	
	public String getName() {
		return name;
	}
	
	public int getCount() {
		return count;
	}
	
}
