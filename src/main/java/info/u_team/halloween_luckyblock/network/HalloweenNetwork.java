package info.u_team.halloween_luckyblock.network;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.network.message.MessageGhostFlash;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HalloweenNetwork {
	
	public static SimpleNetworkWrapper network;
	
	public HalloweenNetwork() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(HalloweenLuckyBlockConstants.MODID);
		message();
	}
	
	private void message() {
		network.registerMessage(MessageGhostFlash.Handler.class, MessageGhostFlash.class, 0, Side.CLIENT);
	}
	
}
