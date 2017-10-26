package de.luckycrew.halloween.proxy;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.handler.HalloweenHandler;
import de.luckycrew.halloween.item.HalloweenItems;
import de.luckycrew.halloween.tab.HalloweenTabs;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		new HalloweenTabs();
		new HalloweenBlocks();
		new HalloweenItems();
	}
	
	public void init(FMLInitializationEvent event) {
		new HalloweenHandler();
	}
	
	public void postinit(FMLPostInitializationEvent event) {
	}
	
}
