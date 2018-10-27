package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.core.*;
import info.u_team.halloween_luckyblock.event.*;
import info.u_team.u_team_core.registry.CommonRegistry;

public class HalloweenLuckyBlockEvents {
	
	public static void init() {
		LuckyHandler handler = new LuckyHandler();
		
		handler.add(new LuckyEventBlocks());
		handler.add(new LuckyEventBoom());
		handler.add(new LuckyEventChest());
		handler.add(new LuckyEventDeath());
		handler.add(new LuckyEventDropInventory());
		handler.add(new LuckyEventEnchant());
		handler.add(new LuckyEventGeneration());
		handler.add(new LuckyEventItems());
		handler.add(new LuckyEventMerchant());
		handler.add(new LuckyEventRainingPumkin());
		handler.add(new LuckyEventSound());
		handler.add(new LuckyEventSpawnCreature());
		handler.add(new LuckyEventThunder());
		
		CommonRegistry.registerEventHandler(new BreakBlockListener(HalloweenLuckyBlockBlocks.luckyblock, handler));
	}
	
}
