package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.network.MessageGhostFlash;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class HalloweenLuckyBlockNetwork {
	
	public static final String PROTOCOL = "1.16.3-1";
	
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(HalloweenLuckyBlockMod.MODID, "network"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);
	
	private static void setup(FMLCommonSetupEvent event) {
		NETWORK.registerMessage(0, MessageGhostFlash.class, MessageGhostFlash::encode, MessageGhostFlash::decode, MessageGhostFlash.Handler::handle);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(HalloweenLuckyBlockNetwork::setup);
	}
	
}
