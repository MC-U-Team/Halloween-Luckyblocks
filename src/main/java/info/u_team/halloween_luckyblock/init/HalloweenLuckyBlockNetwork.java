package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.network.MessageGhostFlash;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HalloweenLuckyBlockNetwork {
	
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(HalloweenLuckyBlockConstants.MODID);
	
	public static void preinit() {
		network.registerMessage(MessageGhostFlash.Handler.class, MessageGhostFlash.class, 0, Side.CLIENT);
	}
	
}
