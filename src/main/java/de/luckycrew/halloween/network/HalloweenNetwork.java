package de.luckycrew.halloween.network;

import de.luckycrew.halloween.HalloweenConstants;
import de.luckycrew.halloween.network.message.MessageGhostFlash;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HalloweenNetwork {
	
	public static SimpleNetworkWrapper network;
	
	public HalloweenNetwork() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(HalloweenConstants.MODID);
		message();
	}
	
	private void message() {
		network.registerMessage(MessageGhostFlash.Handler.class, MessageGhostFlash.class, 0, Side.CLIENT);
	}
	
}
