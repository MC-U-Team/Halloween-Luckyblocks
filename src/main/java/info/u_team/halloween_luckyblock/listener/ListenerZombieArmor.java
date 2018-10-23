package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.util.ArmorUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenerZombieArmor {
	
	@SubscribeEvent
	public void on(LivingUpdateEvent event) {
		Entity entity = event.entityLiving;
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) entity;
		if (player.capabilities.isCreativeMode) {
			return;
		}
		int count = ArmorUtility.getArmorBaseCount(player, "zombie");
		if (count > 0) {
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 2, 0, false, false));
			if (player.worldObj.rand.nextInt(500) == 0) {
				player.playSound("mob.zombie.say", 0.2F, 1.0F);
			}
		}
	}
}
