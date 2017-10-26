package de.luckycrew.core;

import java.util.ArrayList;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.*;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyHandler {
	
	private ArrayList<LuckyEvent> events = new ArrayList<LuckyEvent>();
	
	public void add(LuckyEvent event) {
		addList(event);
	}
	
	private void addList(LuckyEvent event) {
		for (int i = 0; i < event.getCount(); i++) {
			events.add(event);
		}
	}
	
	public void post(EntityPlayer player, BlockPos pos) {
		if (!(player instanceof EntityPlayerMP)) {
			return;
		}
		World world = player.getEntityWorld();
		if (world.isRemote) {
			return;
		}
		if (events.size() == 0) {
			return;
		}
		int r = MathUtil.getRandomNumberInRange(0, events.size() - 1);
		
		LuckyEvent event = events.get(r);
		
		event.execute((EntityPlayerMP) player, world, pos);
	}
	
}
