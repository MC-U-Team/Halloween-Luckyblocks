package info.u_team.halloween_luckyblock.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class HalloweenLuckyBlockGlobalEntityTypeAttributes {
	
	private static void setup(FMLCommonSetupEvent event) {
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(HalloweenLuckyBlockGlobalEntityTypeAttributes::setup);
	}
	
}
