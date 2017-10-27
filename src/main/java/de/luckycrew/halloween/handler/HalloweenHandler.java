package de.luckycrew.halloween.handler;

import de.luckycrew.core.*;
import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.event.LuckyEventTest;
import de.luckycrew.halloween.listener.ListenerKillerKniveAttack;
import info.u_team.u_team_core.util.registry.CommonRegistry;

public class HalloweenHandler {
	
	public static LuckyHandler handler;
	
	public HalloweenHandler() {
		event();
		luckyhandler();
		luckyevent();
	}
	
	private void event() {
		CommonRegistry.registerEventHandler(new ListenerKillerKniveAttack());
	}

	private void luckyhandler() {
		handler = new LuckyHandler();
		CommonRegistry.registerEventHandler(new BreakBlockListener(HalloweenBlocks.luckyblock, handler));
	}
	
	private void luckyevent() {
		handler.add(new LuckyEventTest());
	}
	
}
