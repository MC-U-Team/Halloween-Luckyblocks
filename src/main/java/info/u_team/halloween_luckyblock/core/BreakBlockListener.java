package info.u_team.halloween_luckyblock.core;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BreakBlockListener {
	
	private final Block block;
	private final LuckyHandler handler;
	
	public BreakBlockListener(Block block, LuckyHandler handler) {
		this.block = block;
		this.handler = handler;
	}
	
	@SubscribeEvent
	public void onBlockBreakEvent(BreakEvent event) {
		if (event.getState().getBlock() == block) {
			final PlayerEntity player = event.getPlayer();
//			if (player.abilities.isCreativeMode) {
//				return;
//			}
			handler.post(player, event.getPos());
		}
	}
	
}
