package info.u_team.halloween_luckyblock.network;

import java.util.function.Supplier;

import info.u_team.halloween_luckyblock.listener.ListenerGhostFlash;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageGhostFlash {
	
	public static void encode(MessageGhostFlash message, PacketBuffer sendBuffer) {
	}
	
	public static MessageGhostFlash decode(PacketBuffer sendBuffer) {
		return new MessageGhostFlash();
	}
	
	public static class Handler {
		
		public static void handle(MessageGhostFlash message, Supplier<Context> contextSupplier) {
			final Context context = contextSupplier.get();
			ListenerGhostFlash.flash = true;
			context.setPacketHandled(true);
		}
	}
}