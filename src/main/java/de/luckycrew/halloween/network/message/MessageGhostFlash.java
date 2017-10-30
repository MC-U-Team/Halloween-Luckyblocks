package de.luckycrew.halloween.network.message;

import de.luckycrew.halloween.listener.ListenerGhostFlash;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageGhostFlash implements IMessage {
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class Handler implements IMessageHandler<MessageGhostFlash, IMessage> {
		
		@Override
		public IMessage onMessage(MessageGhostFlash message, MessageContext ctx) {
			ListenerGhostFlash.flash = true;
			return null;
		}
	}
}