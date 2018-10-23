package info.u_team.halloween_luckyblock.handler;

import static info.u_team.u_team_core.util.registry.CommonRegistry.registerEventHandler;

import info.u_team.halloween_luckyblock.core.*;
import info.u_team.halloween_luckyblock.event.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.listener.*;

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
		registerEventHandler(new BreakBlockListener(HalloweenLuckyBlockBlocks.luckyblock, handler));
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
