package de.luckycrew.halloween.core;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakBlockListener {
	
	private Block block;
	private LuckyHandler handler;
	
	public BreakBlockListener(Block block, LuckyHandler handler) {
		this.block = block;
		this.handler = handler;
	}
	
	@SubscribeEvent
	public void onBlockBreakEvent(BreakEvent event) {
		if (event.state.getBlock() == block) {
			EntityPlayer player = event.getPlayer();
			if (player.capabilities.isCreativeMode) {
				return;
			}
			BlockPos pos = event.pos;
			handler.post(player, pos);
		}
	}
	
}
