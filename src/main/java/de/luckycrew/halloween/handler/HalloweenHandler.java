package de.luckycrew.halloween.handler;

import de.luckycrew.core.*;
import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.event.LuckyEventTest;
import info.u_team.u_team_core.util.registry.CommonRegistry;

public class HalloweenHandler {
	
	public static LuckyHandler handler;
	
	public HalloweenHandler() {
		luckyhandler();
		luckyevent();
	}
	
	private void luckyhandler() {
		handler = new LuckyHandler();
		CommonRegistry.registerEventHandler(new BreakBlockListener(HalloweenBlocks.luckyblock, handler));
	}
	
	private void luckyevent() {
		handler.add(new LuckyEventTest());
	}
	
}
