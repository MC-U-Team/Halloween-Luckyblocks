package de.luckycrew.halloween.handler;

import de.luckycrew.core.*;
import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.event.LuckyEventTest;
import de.luckycrew.halloween.listener.*;

import static info.u_team.u_team_core.util.registry.CommonRegistry.registerEventHandler;

public class HalloweenHandler {
	
	public static LuckyHandler handler;
	
	public HalloweenHandler() {
		event();
		luckyhandler();
		luckyevent();
	}
	
	private void event() {
		registerEventHandler(new ListenerKillerKniveAttack());
		registerEventHandler(new ListenerWitchsBroomstickFly());
	}
	
	private void luckyhandler() {
		handler = new LuckyHandler();
		registerEventHandler(new BreakBlockListener(HalloweenBlocks.luckyblock, handler));
	}
	
	private void luckyevent() {
		handler.add(new LuckyEventTest());
	}
	
}
