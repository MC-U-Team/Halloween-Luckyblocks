package info.u_team.halloween_luckyblock.handler;

import static info.u_team.u_team_core.util.registry.CommonRegistry.registerEventHandler;

import info.u_team.halloween_luckyblock.listener.ListenerGhostFlash;

public class HalloweenClientHandler {
	
	public HalloweenClientHandler() {
		event();
	}
	
	private void event() {
		registerEventHandler(new ListenerGhostFlash());
	}
	
}
