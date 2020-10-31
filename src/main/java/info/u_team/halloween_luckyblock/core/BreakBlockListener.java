package info.u_team.halloween_luckyblock.core;

import java.util.function.*;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BreakBlockListener {
	
	private final Predicate<Block> blockPredicate;
	private final LuckyHandler handler;
	
	public BreakBlockListener(Predicate<Block> blockPredicate, LuckyHandler handler) {
		this.blockPredicate = blockPredicate;
		this.handler = handler;
	}
	
	@SubscribeEvent
	public void onBlockBreakEvent(BreakEvent event) {
		if (blockPredicate.test(event.getState().getBlock())) {
			final PlayerEntity player = event.getPlayer();
			// if (player.abilities.isCreativeMode) {
			// return;
			// }
			handler.post(player, event.getPos());
		}
	}
	
}
