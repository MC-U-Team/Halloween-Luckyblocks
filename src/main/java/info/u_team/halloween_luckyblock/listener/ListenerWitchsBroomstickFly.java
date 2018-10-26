package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenerWitchsBroomstickFly {
	
	private boolean flying = false;
	
	@SubscribeEvent
	public void on(LivingUpdateEvent event) {
		Entity entity = event.getEntityLiving();
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) entity;
		if (player.capabilities.isCreativeMode) {
			return;
		}
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || stack.getItem() == null || stack.getItem() != HalloweenLuckyBlockItems.witchsbroomstick) {
			if (flying) {
				player.capabilities.isFlying = false;
				flying = false;
			}
			return;
		}
		player.capabilities.isFlying = true;
		player.fallDistance = 0.0F;
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (!server.isFlightAllowed()) {
			server.setAllowFlight(true);
		}
		flying = true;
		if (!player.world.isRemote) {
			stack.damageItem(1, player);
		}
	}
}
