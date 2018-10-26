package info.u_team.halloween_luckyblock.proxy;

import info.u_team.halloween_luckyblock.handler.HalloweenCommonHandler;
import info.u_team.halloween_luckyblock.init.*;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		HalloweenLuckyBlockBlocks.preinit();
		HalloweenLuckyBlockItems.preinit();
		HalloweenLuckyBlockEntities.preinit();
		HalloweenLuckyBlockSounds.preinit();
		HalloweenLuckyBlockNetwork.preinit();
	}
	
	public void init(FMLInitializationEvent event) {
		HalloweenLuckyBlockCreativeTabs.init();
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		new HalloweenCommonHandler();
	}
	
}
