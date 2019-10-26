package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.network.MessageGhostFlash;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockNetwork {
	
	public static final String PROTOCOL = "1.14.4-1";
	
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(HalloweenLuckyBlockMod.MODID, "network"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);
	
	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		NETWORK.registerMessage(0, MessageGhostFlash.class, MessageGhostFlash::encode, MessageGhostFlash::decode, MessageGhostFlash.Handler::handle);
	}
	
}
