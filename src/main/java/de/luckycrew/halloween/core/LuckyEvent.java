package de.luckycrew.halloween.core;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class LuckyEvent {
	
	private String name;
	private int count;
	
	public LuckyEvent(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public abstract void execute(EntityPlayerMP player, World world, BlockPos pos);
	
	public String getName() {
		return name;
	}
	
	public int getCount() {
		return count;
	}
	
}
