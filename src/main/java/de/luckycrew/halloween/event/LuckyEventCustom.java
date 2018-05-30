package de.luckycrew.halloween.event;

import de.luckycrew.halloween.HalloweenMod;
import de.luckycrew.halloween.core.LuckyEvent;

public abstract class LuckyEventCustom extends LuckyEvent {
	
	protected HalloweenMod mod = HalloweenMod.instance;
	
	public LuckyEventCustom(String name, int count) {
		super(name, count);
	}
	
}
