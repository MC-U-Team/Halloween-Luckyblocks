package info.u_team.halloween_luckyblock.proxy;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.listener.*;
import info.u_team.u_team_core.registry.CommonRegistry;
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
		HalloweenLuckyBlockEvents.init();
		CommonRegistry.registerEventHandler(ListenerKillerKniveAttack.class, ListenerWitchsBroomstickFly.class, ListenerZombieArmor.class);
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		new HalloweenLuckyBlockEvents();
	}
	
}
