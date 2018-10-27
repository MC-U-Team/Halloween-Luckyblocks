package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import info.u_team.halloween_luckyblock.util.ArmorUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenerZombieArmor {
	
	@SubscribeEvent
	public static void on(LivingUpdateEvent event) {
		Entity entity = event.getEntityLiving();
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) entity;
		if (player.capabilities.isCreativeMode) {
			return;
		}
		int count = ArmorUtility.getArmorBaseCount(player, "zombie");
		if (count > 0) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 0, false, false));
			if (player.world.rand.nextInt(500) == 0) {
				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_AMBIENT, HalloweenLuckyBlockSounds.category, 0.2F, 1.0F);
			}
		}
	}
}
