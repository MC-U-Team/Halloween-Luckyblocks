package de.luckycrew.halloween.handler;

import static info.u_team.u_team_core.util.registry.CommonRegistry.registerEventHandler;

import de.luckycrew.halloween.listener.ListenerGhostFlash;

public class HalloweenClientHandler {
	
	public HalloweenClientHandler() {
		event();
	}
	
	private void event() {
		registerEventHandler(new ListenerGhostFlash());
	}
	
}
