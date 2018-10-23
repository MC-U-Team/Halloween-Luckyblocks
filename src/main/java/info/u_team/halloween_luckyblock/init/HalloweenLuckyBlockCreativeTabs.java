package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.u_team_core.creativetab.UCreativeTab;

public class HalloweenLuckyBlockCreativeTabs {
	
	public static final UCreativeTab tab = new UCreativeTab(HalloweenLuckyBlockConstants.MODID, "tab");
	
	public static void init() {
		tab.setIcon(HalloweenLuckyBlockBlocks.luckyblock);
	}
	
}
