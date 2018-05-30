package de.luckycrew.halloween.block;

import static de.luckycrew.halloween.tab.HalloweenTabs.tab;

import de.luckycrew.halloween.core.DefaultLuckyBlock;

public class HalloweenBlocks {
	
	public static DefaultLuckyBlock luckyblock;
	
	public static BlockPumpkinBomb pumpkinbomb;
	
	public HalloweenBlocks() {
		block();
		pumpkinbomb = new BlockPumpkinBomb("pumpkinbomb", tab);
		
	}
	
	private void block() {
		luckyblock();
	}
	
	private void luckyblock() {
		luckyblock = new DefaultLuckyBlock("luckyblock_halloween", tab);
		tab.setIcon(luckyblock);
	}
	
}
