package info.u_team.halloween_luckyblock.proxy;

import info.u_team.halloween_luckyblock.handler.HalloweenCommonHandler;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.network.HalloweenNetwork;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		HalloweenLuckyBlockBlocks.preinit();
		new HalloweenLuckyBlockItems();
		new HalloweenLuckyBlockEntities();
		new HalloweenSounds();
		new HalloweenNetwork();
	}
	
	public void init(FMLInitializationEvent event) {
		HalloweenLuckyBlockCreativeTabs.init();
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		new HalloweenCommonHandler();
	}
	
}
