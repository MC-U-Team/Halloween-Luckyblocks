package de.luckycrew.halloween.handler;

import static info.u_team.u_team_core.util.registry.CommonRegistry.registerEventHandler;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.core.*;
import de.luckycrew.halloween.event.*;
import de.luckycrew.halloween.listener.*;

public class HalloweenCommonHandler {
	
	public static LuckyHandler handler;
	
	public HalloweenCommonHandler() {
		event();
		luckyhandler();
		luckyevent();
	}
	
	private void event() {
		registerEventHandler(new ListenerKillerKniveAttack());
		registerEventHandler(new ListenerWitchsBroomstickFly());
		registerEventHandler(new ListenerZombieArmor());
	}
	
	private void luckyhandler() {
		handler = new LuckyHandler();
		registerEventHandler(new BreakBlockListener(HalloweenBlocks.luckyblock, handler));
	}
	
	private void luckyevent() {
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
	}
	
}
