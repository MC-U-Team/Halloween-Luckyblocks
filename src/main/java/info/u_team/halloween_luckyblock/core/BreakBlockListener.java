package info.u_team.halloween_luckyblock.core;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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
		if (event.getState().getBlock() == block) {
			EntityPlayer player = event.getPlayer();
			if (player.capabilities.isCreativeMode) {
				return;
			}
			handler.post(player, event.getPos());
		}
	}
	
}
