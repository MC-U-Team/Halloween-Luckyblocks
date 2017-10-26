package de.luckycrew.halloween.block;

import de.luckycrew.core.DefaultLuckyBlock;
import de.luckycrew.halloween.tab.HalloweenTabs;

public class HalloweenBlocks {
	
	public static DefaultLuckyBlock luckyblock;
	
	public HalloweenBlocks() {
		luckyblock();
	}
	
	private void luckyblock() {
		luckyblock = new DefaultLuckyBlock("luckyblock_halloween", HalloweenTabs.tab);
		HalloweenTabs.tab.setIcon(luckyblock);
	}
	
}
