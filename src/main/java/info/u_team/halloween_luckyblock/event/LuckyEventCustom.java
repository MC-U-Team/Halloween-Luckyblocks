package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.core.LuckyEvent;

public abstract class LuckyEventCustom extends LuckyEvent {
	
	protected HalloweenLuckyBlockMod mod = HalloweenLuckyBlockMod.instance;
	
	public LuckyEventCustom(String name, int count) {
		super(name, count);
	}
	
}
