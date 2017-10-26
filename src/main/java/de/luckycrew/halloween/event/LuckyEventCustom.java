package de.luckycrew.halloween.event;

import de.luckycrew.core.LuckyEvent;
import de.luckycrew.halloween.HalloweenMod;

public abstract class LuckyEventCustom extends LuckyEvent {
	
	protected HalloweenMod mod = HalloweenMod.instance;
	
	public LuckyEventCustom(String name, int count) {
		super(name, count);
	}
	
}
